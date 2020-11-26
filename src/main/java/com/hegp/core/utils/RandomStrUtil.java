package com.hegp.core.utils;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;

/**
 * 参考博客的例子生成完全乱序的编码, https://blog.csdn.net/sunboy_2050/article/details/81436493 , 该方法生成的短码是不可逆的
 */
public class RandomStrUtil {
    // 要生成短码的字符串的字符
    private static String[] chars = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
            "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A",
            "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"};

    private static Snowflake snowflake = IdUtil.getSnowflake(1,1);

    /**
     * 产生随机的6位字符串
     */
    public static String randomStr() {
        long id = snowflake.nextId();
        String hex = SecureUtil.md5(id+"");

        // 把加密字符按照8位一组16进制与0x3FFFFFFF进行位与运算
        String tempSubString = hex.substring(8, 8 + 8);

        // 这里需要使用 long 型来转换，因为 Inteter.parseInt() 只能处理 31 位 , 首位为符号位 , 如果不用 long ，则会越界
        long lHexLong = 0x3FFFFFFF & Long.parseLong(tempSubString, 16);
        String outChars = "";
        for (int j = 0; j < 6; j++) {
            long index = 0x0000003D & lHexLong;     // 把得到的值与 0x0000003D 进行位与运算，取得字符数组 chars 索引
            outChars += chars[(int) index];         // 把取得的字符相加
            lHexLong = lHexLong >> 5;               // 每次循环按位右移 5 位
        }
        return outChars;                            // 把字符串存入对应索引的输出数组
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            System.out.println(randomStr());
        }
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }
}