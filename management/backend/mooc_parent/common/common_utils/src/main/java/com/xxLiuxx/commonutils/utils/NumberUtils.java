package com.xxLiuxx.commonutils.utils;

import java.util.Random;

/**
 * @author Yuchen Liu
 */
public class NumberUtils {

    /**
     * generate code
     * @param len
     * @return
     */
    public static String generateCode(int len){
        len = Math.min(len, 8);
        int min = Double.valueOf(Math.pow(10, len - 1)).intValue();
        int num = new Random().nextInt(Double.valueOf(Math.pow(10, len + 1)).intValue() - 1) + min;
        return String.valueOf(num).substring(0,len);
    }
}
