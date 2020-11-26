package com.hegp.module;

import cn.hutool.core.convert.Convert;

import java.util.Base64;

public class Test {
    public static void main(String[] args) {
        System.out.println(Long.MAX_VALUE);
        System.out.println(Base64.getEncoder().encodeToString((Long.MAX_VALUE+"").getBytes()));
        System.out.println(Base64.getEncoder().encodeToString(Convert.longToBytes(Long.MAX_VALUE)));
        System.out.println(new String(Base64.getEncoder().encode(Convert.longToBytes(89L))));
        System.out.println(new String(Base64.getEncoder().encode(Convert.intToBytes(89))));
    }
}
