package com.you.util;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/7/25 14:39
 * 睡眠工具类
 */
public class SleepUtils {
    public static void sleep(int second){
        try {
            Thread.sleep(10000*second);
        } catch (InterruptedException e) {
           Thread.currentThread().interrupt();
        }
    }
}
