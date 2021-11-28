package com.mrlep.meon.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class StringUtils {

    public static boolean isNullOrEmpty(String value){
        return value == null || value.trim().isEmpty();
    }

    public static String formatNumber(String pattern, Number value, Boolean dotToComma) {
        if (pattern != null && value != null) {
            try {
                DecimalFormatSymbols dfs = new DecimalFormatSymbols();
                dfs.setGroupingSeparator(dotToComma ? '.' : ',');
                dfs.setDecimalSeparator(dotToComma ? ',' : '.');
                DecimalFormat df = new DecimalFormat(pattern, dfs);
                df.applyPattern(pattern);
                return df.format(value);
            } catch (NumberFormatException e) {
            }
        }
        return "0";
    }

}
