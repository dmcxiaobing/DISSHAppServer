package com.david.disshappserver.utils;

/**
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
import org.apache.log4j.Logger;

/**
 * log4j的日志封装
 */
public class LogUtils {
    //创建日志对象
    private static Logger log = Logger.getLogger(LogUtils.class);



    public static void I(String messge){
        log.info(messge);
    }

    public static void i(String messge){
        log.info(messge);
    }

    public static void E(String messge){
        log.error(messge);
    }

    public static void e(String messge){
        log.error(messge);
    }
}
