package com.zhide.govwatch.common;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @ClassName: Random
 * @Author: 肖新民
 * @*TODO:
 * @CreateTime: 2021年12月30日 22:50
 **/
public class RandomUtils {
    public static  String nextId(){
        long a= ThreadLocalRandom.current().nextLong();
        return Long.toString(Math.abs(a));
    }
}
