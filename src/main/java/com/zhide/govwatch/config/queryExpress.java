package com.zhide.govwatch.config;

import com.zhide.govwatch.common.QueryTextUtils;
import com.zhide.govwatch.common.RandomUtils;
import com.zhide.govwatch.model.sqlParameter;
import org.apache.logging.log4j.util.Strings;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName: ExpressParsor
 * @Author: 肖新民
 * @*TODO:
 * @CreateTime: 2021年12月30日 13:41
 **/
public class queryExpress {
    List<String> opers = Arrays.asList("=");
    List<String> logic = Arrays.asList("AND", "OR", "NOT");
    //左括号位置记录。
    List<Integer> lPos = new ArrayList<>();
    List<Integer> rPos = new ArrayList<>();

    public sqlParameter parse(String queryText) throws Exception {
        sqlParameter res = new sqlParameter();
        countText(queryText);
        if (lPos.size() != rPos.size()) {
            throw new Exception("括号不配对，无法进行解析。");
        }
        //根据有没有括号拆分为简单表达式和复杂表达式。
        if (lPos.size() > 0) {
            List<Integer> leftPos= lPos.stream().sorted((a,b)-> -Integer.compare(a,b)).collect(Collectors.toList());
            //从小到大。
            List<Integer> rightPos= rPos.stream().sorted().collect(Collectors.toList());
            Map<String,sqlParameter> resultHash=new HashMap<>();
            complexExpress complex=new complexExpress(resultHash);
            while (leftPos.size()>0){
                int left= leftPos.get(0);
                int right= rightPos.stream().filter(f->f>left).min((a,b)->Integer.compare(a,b)).get();
                String exText=queryText.substring(left,right+1);
                String key=complex.parse(exText);
                queryText=queryText.replace(exText,key);
                countText(queryText);
                rightPos= rPos.stream().sorted().collect(Collectors.toList());
                leftPos= lPos.stream().sorted((a,b)-> -Integer.compare(a,b)).collect(Collectors.toList());
            }
            String kk= complex.parse(queryText);
            res=resultHash.get(kk);
        } else {
            simpleExpress simple = new simpleExpress();
            res = simple.parse(queryText);
        }
        return res;
    }

    /**
     * create by: mmzs
     * description: TODO
     * create time:
     * <p>
     * 统计一下有没有括号。
     *
     * @return
     */
    private void countText(String queryText) {
        lPos.clear();
        rPos.clear();
        for (int i = 0; i < queryText.length(); i++) {
            String T = queryText.substring(i, i + 1);
            if (T.equals("(")) lPos.add(i);
            if (T.equals(")")) rPos.add(i);
        }
    }
    //单行，没有括号的表达式。
    class simpleExpress {
        singleExpress single = new singleExpress();
        public sqlParameter parse(String queryText) throws Exception {
            sqlParameter res = new sqlParameter();
            List<sqlParameter> parentHash = new ArrayList<>();
            queryText = QueryTextUtils.fixedKeyText(queryText);
            String[] words = queryText.split(" ");
            List<String> items = new ArrayList<>();//纯表达式
            List<String> logs = new ArrayList<>();//纯逻辑操作符。
            List<Object> allItems = new ArrayList<>();//混合项目集合。
            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                if (Strings.isEmpty(word)) continue;
                if (isSingle(word)) items.add(word);
                else {
                    String dd = word.toUpperCase(Locale.ROOT).trim();
                    if (logic.contains(dd) == false) {
                        throw new Exception(word + "不是一个可以解析的表达式。");
                    }
                    logs.add(word);
                }
                allItems.add(word);
            }
            if(allItems.size()>1) {
                if (logs.size() + 1 != items.size()) throw new Exception(queryText + "逻辑不正确。");
                while (true) {
                    List<Object> part = allItems.stream().limit(3).collect(Collectors.toList());
                    reBuild(part, allItems);
                    sqlParameter one = createOne(part);
                    parentHash.add(one);
                    for (int i = 2; i >= 0; i--) {
                        allItems.remove(i);
                    }
                    Optional<Object> findOne = allItems.stream()
                            .filter(f -> f.getClass() == sqlParameter.class)
                            .filter(f -> ((sqlParameter) f).getId().equals(one.getId())).findFirst();
                    if (allItems.size() == 0) {
                        if (findOne.isPresent() == false) allItems.add(one);
                        break;
                    } else {
                        if (findOne.isPresent() == false) allItems.add(0, one);
                    }
                }
                if (allItems.size() > 1) {
                    res.setRelation(((sqlParameter) allItems.get(0)).getRelation());
                    for (int i = 0; i < allItems.size(); i++) {
                        res.getChildren().add((sqlParameter) allItems.get(i));
                    }
                } else res = ((sqlParameter) allItems.get(0));
            } else {
                res=single.create(allItems.get(0).toString());
            }
            return res;
        }
        //一次处理三个。两个sqlParamter加上关系运算符。
        public  sqlParameter createOne(List<Object> items) throws Exception {
            String OO = items.get(1).toString();
            if (logic.contains(OO) == false) throw new Exception("表达式排列不正确，应为[表达式 AND/OR/NOT 表达式]");

            sqlParameter one = null;
            sqlParameter two = null;
            Object s1 = items.get(0);
            boolean create = true;
            String s1Text=s1.toString();
            //有可能存在一个sqlParamter和String一起构建条件的关系。
            if (s1Text.startsWith("com.")==false) one = single.create(s1Text);
            else {
                one = (sqlParameter) s1;
                create = false;
            }
            Object s2 = items.get(2);
            String s2Text=s2.toString();
            if (s2Text.startsWith("com.")==false) two = single.create(s2Text);
            else {
                two = (sqlParameter) s2;
                create = false;
            }
            if (create == true) {
                sqlParameter parent = new sqlParameter();
                parent.setId(RandomUtils.nextId());
                parent.setRelation(OO);
                parent.getChildren().add(one);
                parent.getChildren().add(two);
                return parent;
            } else {
                String Rela=one.getRelation();
                if(StringUtils.isEmpty(Rela)==false){
                    if (one.getRelation().equals(OO)) {
                        one.getChildren().add(two);
                        return one;
                    } else {
                        sqlParameter parent = new sqlParameter();
                        parent.setId(RandomUtils.nextId());
                        parent.setRelation(OO);
                        parent.getChildren().add(one);
                        parent.getChildren().add(two);
                        return parent;
                    }
                } else {
                    sqlParameter parent = new sqlParameter();
                    parent.setId(RandomUtils.nextId());
                    parent.setRelation(OO);
                    parent.getChildren().add(one);
                    parent.getChildren().add(two);
                    return parent;
                }
            }
        }

        private boolean isSingle(String word) {
            for (int i = 0; i < opers.size(); i++) {
                String f = opers.get(i);
                if (word.indexOf(f) > -1) return true;
            }
            return false;
        }
        private void reBuild(List<Object> part,List<Object> items){
            Object one=part.get(0);
            if(one.getClass()==sqlParameter.class){
                 String relation=((sqlParameter) one).getRelation();
                 String nowRelation=part.get(1).toString();
                 if(relation.equals(nowRelation)) return;
                 else {
                     List<Object> Others=items.stream().skip(3).collect(Collectors.toList());
                     for(int i=0;i<Others.size();i++){
                         String  item=Others.get(i).toString();
                         if(item.equals(relation)){
                             Object replaceOne=items.get(i+4);
                             Object nowOne=items.get(2);
                             items.set(i+4,nowOne);
                             items.set(i+3,nowRelation);
                             items.set(2,replaceOne);
                             items.set(1,item);

                             part.set(1,item);
                             part.set(2,replaceOne);
                             break;
                         }
                     }
                 }
            }
        }
    }
    class complexExpress {
        Map<String,sqlParameter> resultHash;
        Map<String,String>VV=new HashMap<>();
        public complexExpress(Map<String,sqlParameter> p){
            resultHash=p;
            if(resultHash==null) resultHash=new HashMap<>();
        }
        public String  parse(String exText) throws Exception{
            sqlParameter s=null;
            String pureText=exText.replace("(","").replace(")","").trim();
            pureText= QueryTextUtils.fixedKeyText(pureText);
            simpleExpress simple = new simpleExpress();
            if(pureText.indexOf("$st")==-1) {
                s = simple.parse(pureText);
            } else {
                String[] ws=pureText.split(" ");
                List<Object> args=new ArrayList<>();
                for(int i=0;i<ws.length;i++){
                    String w=ws[i];
                    if(StringUtils.isEmpty(w)) continue;
                    if(w.indexOf("$st")>-1){
                        sqlParameter p=resultHash.get(w);
                        args.add(p);
                    } else args.add(w);
                }
                if(args.size()==1){
                    s=(sqlParameter) args.get(0);
                }
                else if (args.size()==3) {
                    s = simple.createOne(args);
                } else {
                    while (true){
                        List<Object> bbs=args.stream().limit(3).collect(Collectors.toList());
                        s= simple.createOne(bbs);
                        for(int i=bbs.size()-1;i>=0;i--)args.remove(i);
                        if(args.size()==0) break;
                        String key=maxKey(resultHash);
                        resultHash.put(key,s);
                        args.add(0,s);
                    }
                }
            }
            String key=maxKey(resultHash);
            resultHash.put(key,s);
            return key;
        }
    }
    private String maxKey(Map<String,sqlParameter> hash){
        int num=hash.size();
        return "$st"+ num;
    }

    /**
     * create by: mmzs
     * description: TODO
     * create time:
     * <p>
     * 单个参数处理类。类似于:A=B,A=C,D
     *
     * @return
     */
    class singleExpress {
        public sqlParameter create(String text) throws Exception {
            sqlParameter param = new sqlParameter();
            String[] words = text.split("=");
            for (int i = 0; i < words.length; i++) {
                String word = words[i].trim();
                if (StringUtils.isEmpty(word)) throw new Exception("表达式:" + text + "不正确。");
            }
            if (words.length != 2) throw new Exception("表达式:" + text + "不正确。");
            param.setField(words[0]);
            param.setValue(words[1]);
            param.setOper("=");
            param.setId(RandomUtils.nextId());
            return param;
        }
    }
}
