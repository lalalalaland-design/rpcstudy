package com.luban.framework;


import java.util.List;
import java.util.Random;

public class LoadBalance {
    public static URL random(List<URL> list) {
        Random random = new Random();
        int i = random.nextInt(list.size());
//        该方法的作用是生成一个随机的int值，该值介于[0,n)的区间，也就是0到n之间的随机int值，包含0而不包含n。
        return list.get(i);
    }
}
