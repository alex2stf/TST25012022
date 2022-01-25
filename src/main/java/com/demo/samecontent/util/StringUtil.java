package com.demo.samecontent.util;

public class StringUtil {

    public static boolean hasText(String in){
        return null != in && !("".equals(in.trim()));
    }
}
