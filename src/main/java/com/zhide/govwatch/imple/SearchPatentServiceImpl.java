package com.zhide.govwatch.imple;

import com.alibaba.fastjson.JSON;
import com.zhide.govwatch.common.*;
import com.zhide.govwatch.config.queryExpress;
import com.zhide.govwatch.define.ISearchPatentService;
import com.zhide.govwatch.model.*;
import com.zhide.govwatch.repository.PatentRepository;
import com.zhide.govwatch.repository.patentInfoPermissionRepository;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedLongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.AggregationsContainer;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName: SearchPatentServiceImpl
 * @Author: 肖新民
 * @*TODO:
 * @CreateTime: 2021年12月22日 9:44
 **/
@Service
public class SearchPatentServiceImpl implements ISearchPatentService {
    addressParsor addParsor=new addressParsor();
    @Autowired
    patentQueryBuilder queryBuilder;
    @Autowired
    ElasticsearchRestTemplate elClient;
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    patentInfoPermissionRepository patentInfoPermissionRepository;
    @Autowired
    PatentRepository patentRepository;
    Logger logger= LoggerFactory.getLogger(SearchPatentServiceImpl.class);

    //简单查询。只搜索这几个字段。
    List<String> simpleFields = Arrays.asList("memo", "famingmc","address","shenqingh","shenqinglx","famingrxm",
            "shenqingrxm");

    @Override
    public pageObject simpleSearch(String Fields, String Code, Pageable pageable) throws Exception {
        pageObject object=new pageObject();
        Long begin=System.currentTimeMillis();
        if (StringUtils.isEmpty(Fields) == false) simpleFields = ListUtils.parse(Fields, String.class);
        List<String> Codes = preSimpleCodes(Code);
        sqlParameter parameter = packageParameters(Codes);
        QueryBuilder query = queryBuilder.create(parameter);
        HighlightBuilder highlight=createHighlight(simpleFields);
        NativeSearchQueryBuilder queryBuilder=new NativeSearchQueryBuilder();
        queryBuilder.withQuery(query);
        queryBuilder.withTrackTotalHits(true);
        queryBuilder.withPageable(pageable);
        SearchHits<patentElInfo> records=elClient.search(queryBuilder.build(),patentElInfo.class);
        logger.info("query express:{}",query.toString());
        long  Num=records.getTotalHits();
        object.setTotal(Num);
        logger.info("search {} rows",Num);
        object.setSuccess(true);

        NativeSearchQueryBuilder queryBuilder1=new NativeSearchQueryBuilder();
        queryBuilder1.withSearchType(SearchType.DEFAULT);
        queryBuilder1.withQuery(query);
        queryBuilder1.withAggregations(AggregationBuilders.terms("shenqingrCount").field("shenqingr").size(Integer.parseInt(Long.toString(Num))));

        SimpleDateFormat simple=new SimpleDateFormat("yyyy-MM-dd");
        SearchHits<patentElInfo> result= elClient.search(queryBuilder1.build(),patentElInfo.class);
        AggregationsContainer<?> vs= result.getAggregations();
        Aggregations ass= (Aggregations) vs.aggregations();
        Map<String,Aggregation> maps= ass.getAsMap();
        ParsedLongTerms add=(ParsedLongTerms) maps.get("shenqingrCount");
        List<? extends Terms.Bucket> bs= add.getBuckets();
        Map<Integer,Long> Years=new HashMap<>();
        int d=0;
        for(int i=0;i<bs.size();i++){
            Terms.Bucket b=bs.get(i);
            long num=b.getDocCount();
            Date newDate=simple.parse(b.getKeyAsString());
            Integer Year= newDate.getYear()+1900;
            if(Years.containsKey(Year)){
                Long nn=Years.get(Year);
                Years.put(Year,nn+num);
            } else Years.put(Year,num);
            d+=num;
        }

        logger.info("total:{},json:{}",d,JSON.toJSONString(Years));
        List<patentInfoPermission> listPat = patentInfoPermissionRepository.findAll();
        List<patentElInfo> Datas=records.get().map(f->{
            patentElInfo Info=f.getContent();
            try {
                applyHighlight(simpleFields,Codes,Info);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return Info;
        }).collect(Collectors.toList());
        Map<String, Object> data = new HashMap<>();
        data.put("datas", Datas);
        String X = freeMarkerUtils.compile("searchSection", data);
        object.setData(X);
        Long end=System.currentTimeMillis();
        addToHistory(Code);
        object.setTime(end-begin);
        return object;
    }

    @Override
    public pageObject complexSearch(String words, Pageable pageable) throws Exception {
        pageObject object=new pageObject();
        Long begin=System.currentTimeMillis();
        queryExpress express=new queryExpress();
        sqlParameter parameter = express.parse(words);
        QueryBuilder query = queryBuilder.create(parameter);
        NativeSearchQueryBuilder queryBuilder=new NativeSearchQueryBuilder();
        queryBuilder.withQuery(query);
        queryBuilder.withPageable(pageable);
        queryBuilder.withTrackTotalHits(true);
        SearchHits<patentElInfo> records=elClient.search(queryBuilder.build(),patentElInfo.class);
        logger.info("query express:{}",query.toString());
        long  Num=records.getTotalHits();
        object.setTotal(Num);
        logger.info("search {} rows",Num);
        object.setSuccess(true);
        List<sqlParameter> children=sqlParameterUtils.expandAll(parameter);
        List<String> fields=children.stream().map(f->f.getField()).distinct().collect(Collectors.toList());
        List<String> values=children.stream().map(f->f.getValue()).distinct().collect(Collectors.toList());
        List<patentInfoPermission> listPat = patentInfoPermissionRepository.findAll();
        List<patentElInfo> Datas=records.get().map(f->{
            patentElInfo Info=f.getContent();
            try {
                applyHighlight(fields,values,Info);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return Info;
        }).collect(Collectors.toList());
        Map<String, Object> data = new HashMap<>();
        data.put("datas", Datas);
        String X = freeMarkerUtils.compile("searchSection", data);
        object.setData(X);
        Long end=System.currentTimeMillis();
        addToHistory(words);
        object.setTime(end-begin);
        return object;
    }

    @Override
    public pageObject simpleAnnualSearch(String Fields, String Code, Pageable pageable) throws  Exception {
        pageObject object = new pageObject();
        try {
            if (StringUtils.isEmpty(Fields) == false) simpleFields = ListUtils.parse(Fields, String.class);
            List<String> Codes = preSimpleCodes(Code);
            queryExpress express = new queryExpress();
//            sqlParameter parameter = express.parse("shenqingh=2020104602693 or famingmc=一种具有散热效果的轻量化引擎盖 And shenqingh=2018110865326 or famingmc=一种具有可折叠挡风装置的赛车");
            sqlParameter parameter = packageParameters(Codes);
            QueryBuilder query = queryBuilder.create(parameter);
            HighlightBuilder highlight = createHighlight(simpleFields);

            NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
            queryBuilder.withQuery(query);
            queryBuilder.withHighlightBuilder(highlight);
            queryBuilder.withPageable(pageable);
            //queryBuilder.withSorts(pageable.)
            queryBuilder.withMaxResults(1000000);
            SearchHits<patentElInfo> records = elClient.search(queryBuilder.build(), patentElInfo.class);
            logger.info("query express:{}", query.toString());
            long Num = records.getTotalHits();
            object.setTotal(Num);
            logger.info("search {} rows", Num);
            object.setSuccess(true);

            List<patentElInfo> Datas = records.get().map(f -> {
                Map<String, List<String>> HighlightTexts = f.getHighlightFields();
                patentElInfo Info = f.getContent();
                if (HighlightTexts.size() > 0) {
                    for (String field : HighlightTexts.keySet()) {
                        try {
                            Field x = Info.getClass().getDeclaredField(field);
                            if (x != null) {
                                x.setAccessible(true);
                                x.set(Info, org.apache.tomcat.util.buf.StringUtils.join(HighlightTexts.get(field), ' ').trim());
                            }
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (NoSuchFieldException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return Info;
            }).collect(Collectors.toList());
            Long X2 = System.currentTimeMillis();
            List<patentInfoPermission> listPat = patentInfoPermissionRepository.findAll();
            Datas.stream().forEach(f -> {
                Optional<patentInfoPermission> findOne = listPat.stream().filter(x -> x.getShenqingh().equals(f.getShenqingh())).findFirst();
                if (findOne.isPresent()) {
                    f.setJkstatus("已监控");
                } else f.setJkstatus("未监控");
            });
            object.setData(Datas);
        }catch (Exception ax){
            ax.printStackTrace();
        }
        return object;
    }

    private List<String> preSimpleCodes(String Code) {
        String[] tCodes = Code.split(" ");
        List<String> Codes = new ArrayList<>();
        for (String code : tCodes) {
            code = code.trim();
            if (StringUtils.isEmpty(code)) continue;
            Codes.add(code);
        }
        return Codes;
    }
    private sqlParameter packageParameters(List<String> Codes) {
        List<sqlParameter> res = new ArrayList<>();
        Codes.forEach(code->{
            List<sqlParameter> singleFieldParameters = new ArrayList<>();
            simpleFields.forEach(field -> {
                sqlParameter single = new sqlParameter();
                single.setField(field);
                single.setValue(code);
                single.setOper("=");
                singleFieldParameters.add(single);
            });
            sqlParameter singleField=null;
            if(singleFieldParameters.size()>1) {
                singleField = sqlParameterUtils.addOr(singleFieldParameters);
            }
            else singleField=singleFieldParameters.get(0);
            res.add(singleField);
        });
        return sqlParameterUtils.addAnd(res);
    }
    private HighlightBuilder createHighlight(List<String> fields) {
        HighlightBuilder builder = new HighlightBuilder();
        for (String field : fields) {
            builder.field(field)
            .preTags("<span style='color:blue;text-decoration:underline '>")
            .postTags("</span>");
        }
        return builder;
    }
    String pre="<span style='color:blue;text-decoration:underline'>";
    String end="</span>";
    private void applyHighlight(List<String> simpleFields,List<String> Codes,patentElInfo Info) throws Exception{
        if(Info==null) return;
        for(int i=0;i< simpleFields.size();i++){
            String field= simpleFields.get(i);
            Field field1=Info.getClass().getDeclaredField(field);
            if(field1!=null){
                field1.setAccessible(true);
                Object fieldValue= field1.get(Info);
                if(StringUtils.isEmpty(fieldValue)==false){
                    String fieldText=fieldValue.toString();
                    for(int n=0;n<Codes.size();n++){
                        String code=Codes.get(n);
                        if(fieldText.indexOf(code)>-1){
                            String newText=StringUtils.replace(fieldText,code,pre+code+end);
                            field1.set(Info,newText);
                        }
                    }
                }
            }
        }
    }

    private Boolean addToHistory(String word){
      LoginUserInfo Info=CompanyContext.get();
      String code=Info.getAccount();
      String Key="search::"+code+"::history";
      List<String> Alls=redisTemplate.opsForList().range(Key,0,50);
      if(Alls.contains(word)==false)redisTemplate.opsForList().leftPush(Key,word);
      redisTemplate.opsForList().trim(Key,0,20);
      return true;
    }


    @Override
    public List<String> ParameterSearchCount(String Field, String words, String SearchPage) throws Exception {
        List<String> list = new ArrayList<>();
        sqlParameter parameter;
        Pageable pageable=Pageable.ofSize(1).withPage(1);
        if (SearchPage.equals("TableSearch")) {
            //表格查询和语法检索查询取值方式
            queryExpress express = new queryExpress();
            parameter = express.parse(words);
        }else {
            //简单查询取值方式
            List<String> Codes = preSimpleCodes(words);
            parameter = packageParameters(Codes);
        }
        QueryBuilder query = queryBuilder.create(parameter);
        NativeSearchQueryBuilder queryBuilder=new NativeSearchQueryBuilder();
        queryBuilder.withQuery(query);
        queryBuilder.withPageable(pageable);
        queryBuilder.withTrackTotalHits(true);
        SearchHits<patentElInfo> records=elClient.search(queryBuilder.build(),patentElInfo.class);
        logger.info("query express:{}",query.toString());
        long Num=records.getTotalHits();

        if (Num > 0) {
            NativeSearchQueryBuilder queryBuilder1 = new NativeSearchQueryBuilder();
            queryBuilder1.withSearchType(SearchType.DEFAULT);
            queryBuilder1.withQuery(query);
            queryBuilder1.withPageable(pageable);
            queryBuilder1.withTrackTotalHits(true);
            queryBuilder1.withAggregations(AggregationBuilders.terms(Field + "Count").field(Field).size(Integer.parseInt(Long.toString(Num))));

            SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
            SearchHits<patentElInfo> result = elClient.search(queryBuilder1.build(), patentElInfo.class);
            AggregationsContainer<?> vs = result.getAggregations();
            Aggregations ass = (Aggregations) vs.aggregations();
            Map<String, Aggregation> maps = ass.getAsMap();
            ParsedLongTerms addLong;
            ParsedStringTerms addString;
            List<? extends Terms.Bucket> bs;
            if (Field.equals("shenqingr")) {
                addLong = (ParsedLongTerms) maps.get(Field + "Count");
                bs = addLong.getBuckets();
            } else {
                addString = (ParsedStringTerms) maps.get(Field + "Count");
                bs = addString.getBuckets();
            }
            Map<Integer, Long> FieldIntContent = new HashMap<>();
            Map<String, Long> FieldStrContent = new HashMap<>();
            int d = 0;
            for (int i = 0; i < bs.size(); i++) {
                Terms.Bucket b = bs.get(i);
                long num = b.getDocCount();
                if (Field.equals("shenqingr")) {
                    Date newDate = simple.parse(b.getKeyAsString());
                    Integer Year = newDate.getYear() + 1900;
                    if (FieldIntContent.containsKey(Year)) {
                        Long nn = FieldIntContent.get(Year);
                        FieldIntContent.put(Year, nn + num);
                    } else FieldIntContent.put(Year, num);
                }
                else
                {
                    String Content = b.getKeyAsString();
                    if (FieldStrContent.containsKey(Content)){
                        Long nn = FieldStrContent.get(Content);
                        FieldStrContent.put(Content,nn + num);
                    } else FieldStrContent.put(Content, num);
                }
                d += num;
            }

            if (Field.equals("shenqingr")){
                FieldIntContent = FieldIntContent.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByKey())).collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,(e1,e2) -> e1,LinkedHashMap::new));
                list.add(JSON.toJSONString(FieldIntContent));
            }else{
                List<Map.Entry<String, Long>> info = new ArrayList<Map.Entry<String, Long>>(FieldStrContent.entrySet());
                Collections.sort(info, new Comparator<Map.Entry<String, Long>>() {
                    public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
                        return (Integer.parseInt(o2.getValue().toString()) - Integer.parseInt(o1.getValue().toString()));
                    }
                });
                list.add(JSON.toJSONString(info));
            }
        }
        return list;
    }

    @Override
    public List<ComboboxItem> GetDistinctLAWSTATUS() {
        List<ComboboxItem> results = new ArrayList<>();
        List<String> items = patentRepository.getDistinctByLawstatus();
        items.stream().forEach(f -> {
            ComboboxItem item = new ComboboxItem();
            item.setId(f);
            item.setText(f);
            results.add(item);
        });
        return results;
    }

    @Override
    public List<ComboboxItem> GetDistinctSECLAWSTATUS() {
        List<ComboboxItem> results = new ArrayList<>();
        List<String> items = patentRepository.getDistinctBySeclawstatus();
        items.stream().forEach(f -> {
            ComboboxItem item = new ComboboxItem();
            item.setId(f);
            item.setText(f);
            results.add(item);
        });
        return results;
    }
}
