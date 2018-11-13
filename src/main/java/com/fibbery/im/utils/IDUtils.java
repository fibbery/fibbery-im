package com.fibbery.im.utils;

/**
 * @author fibbery
 * @date 2018/11/13
 */
public class IDUtils {

    public static long generateID() {
        return System.currentTimeMillis() / 1000L;
    }
}
