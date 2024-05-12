package com.itheima;

public class test01 {
    public static void main(String[] args) {
        String s = new String("012345.3.4.5.1");
        System.out.println(s.substring(s.lastIndexOf("."))); // 345
    }
}
