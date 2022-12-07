package com.zhide.govwatch.config;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: HashMapUtils
 * @Author: 肖新民
 * @*TODO:
 * @CreateTime: 2021年12月15日 10:08
 **/
public class HashMapUtils {
    public static <K,V> Map<K, V> of(Object...args) throws Exception{
        if(args.length%2==1) throw new Exception("参数列表集合长度必须为偶数!");
        Map<K,V>  res=new HashMap<>();
        K key=null;
        V val=null;
        for(int i=0;i<args.length;i++){
            if(i%2==0) {
                key = (K) args[i];
            }
            if(i%2==1){
                val=(V)args[i];
            }
            if(key!=null && val!=null){
                res.put(key,val);
            }
        }
        return (Map<K,V>)res;
    }
}
