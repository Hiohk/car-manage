package com.hkgroup.sys.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class RandomUtils {
    private static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMM-dd");
    private static SimpleDateFormat sdf2 = new
            SimpleDateFormat("yyyyMMddHHmmssSSS");
    private static SimpleDateFormat sdf3 = new
            SimpleDateFormat("yyyyMMdd_HHmmss_SSS");
    private static Random random = new Random();

    /**
     * 得到当前⽇期
     */
    public static String getCurrentDateForString() {
        return sdf1.format(new Date());
    }

    /**
     * ⽣成⽂件名使⽤时间+4位随机数
     *
     * @param fileName ⽂件名称
     */
    public static String createFileNameUseTime(String fileName) {
        String
                fileSuffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
        String time = sdf2.format(new Date());
        Integer num = random.nextInt(9000) + 1000;
        return time + num + fileSuffix;
    }

    /**
     * ⽣成⽂件名使⽤时间+4位随机数
     *
     * @param fileName ⽂件名称
     * @param suffix   临时⽂件的后缀
     */
    public static String createFileNameUseTime(String fileName, String suffix) {
        String fileSuffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
        String time = sdf2.format(new Date());
        Integer num = random.nextInt(9000) + 1000;
        return time + num + fileSuffix + suffix;
    }

    /**
     * ⽣成⽂件名使⽤UUID
     *
     * @param fileName ⽂件名称
     */
    public static String createFileNameUseUUID(String fileName) {
        String fileSuffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
        return UUID.randomUUID().toString().replace("-", "").toUpperCase() + fileSuffix;
    }

    /**
     * 根据时间+五位随机数⽣成字符串
     *
     * @param preffx
     * @return
     */
    public static String createRandomStringUseTime(String preffx) {
        return preffx + "_" + sdf3.format(new Date()) + "_" +
                (random.nextInt(90000) + 10000);
    }
}
