package com.viettel.etc.utils;

public class StringUtils {

    public static boolean isNullOrEmpty(String value){
        return value == null || value.trim().isEmpty();
    }
}
