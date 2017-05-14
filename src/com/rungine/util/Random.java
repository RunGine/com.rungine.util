package com.rungine.util;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>Description:</b><br>
 * @author <a href="http://www.neilren.com/" target="_blank">NeilRen</a>
 * @version 1.0
 * @Note
 * <b>ProjectName:</b> com.rungine.util
 * <br><b>PackageName:</b> com.rungine.util
 * <br><b>ClassName:</b> Random
 * <br><b>Date:</b> 2017-05-14 15:48:08
 */
public class Random {
    private static java.util.Random rand = new java.util.Random();
    private static final char[] ARR_NUMBER = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
    private static final char[] ARR_LETTER_LOW = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
            'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private static final char[] ARR_LETTER_UPPER = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
            'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private static final char[] ARR_SPECIAL = new char[]{'`', '~', '!', '@', '#', '$', '%', '^', '&', '*'
            , '(', ')', '-', '_', '+', '=', '[', ']', '{', '}', ';', ':', '\'', '"', '<', '>', ',', '.', '/', '?', '|', '\\'};
    private static final char[] ARR_CONFUSION = new char[]{'1', 'l', '0', 'o', 'O', 'I', '|'};

    public enum CHAR_TYPE {
        DIGIT,
        LETTER_LOW,
        LETTER_UPPER,
        SPECIAL
    }

    /**
     * 生成随机字符串
     *
     * @param length    长度
     * @param digitCase 是否使用数字
     * @param upperCase 使用大写字母
     * @param lowerCase 使用小写字母
     * @param special   使用特殊字符
     * @return 使用易混淆字符随机字符串
     */
    public static String generateRandomString(int length, boolean digitCase,
                                              boolean upperCase, boolean lowerCase, boolean special) {
        return generateRandomString(length, digitCase, upperCase, lowerCase, special, false);
    }

    /**
     * 生成随机字符串
     *
     * @param length         长度
     * @param digitCase      是否使用数字
     * @param upperCase      使用大写字母
     * @param lowerCase      使用小写字母
     * @param special        使用特殊字符
     * @param nonuseConfused 不使用易混淆字符
     * @return 随机字符串
     */
    public static String generateRandomString(int length, boolean digitCase, boolean upperCase,
                                              boolean lowerCase, boolean special, boolean nonuseConfused) {
        if (length <= 0)
            return "";
        List<CHAR_TYPE> listCHAR_TYPE = new ArrayList<>();
        if (digitCase)
            listCHAR_TYPE.add(CHAR_TYPE.DIGIT);
        if (upperCase)
            listCHAR_TYPE.add(CHAR_TYPE.LETTER_UPPER);
        if (lowerCase)
            listCHAR_TYPE.add(CHAR_TYPE.LETTER_LOW);
        if (special)
            listCHAR_TYPE.add(CHAR_TYPE.SPECIAL);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(
                    generateRandom(
                            listCHAR_TYPE.get(
                                    rand.nextInt(listCHAR_TYPE.size())
                            ), nonuseConfused
                    )
            );
        }
        return stringBuilder.toString();
    }

    /**
     * 生成随机字符
     *
     * @return 使用易混淆字符的随机字符
     */
    public static char generateRandom(CHAR_TYPE charType) {
        return generateRandom(charType, false);
    }

    /**
     * 生成随机字符
     *
     * @param nonuseConfused 是否不是使用易混淆字符
     * @return 不使用易混淆字符的随机字符
     */
    public static char generateRandom(CHAR_TYPE charType, boolean nonuseConfused) {

        char character = '0';
        do {
            switch (charType) {
                case DIGIT:
                    character = ARR_NUMBER[rand.nextInt(ARR_NUMBER.length)];
                    break;
                case LETTER_LOW:
                    character = ARR_LETTER_LOW[rand.nextInt(ARR_LETTER_LOW.length)];
                    break;
                case LETTER_UPPER:
                    character = ARR_LETTER_UPPER[rand.nextInt(ARR_LETTER_UPPER.length)];
                    break;
                case SPECIAL:
                    character = ARR_SPECIAL[rand.nextInt(ARR_SPECIAL.length)];
                    break;
            }
        } while (nonuseConfused && judgeOfConfusionChar(character));
        return character;
    }

    /**
     * 判断是否是易混淆字符
     *
     * @param character 待检查字符
     * @return 是否是易混淆字符
     */
    public static boolean judgeOfConfusionChar(char character) {
        for (char tempChar : ARR_CONFUSION) {
            if (tempChar == character)
                return true;
        }
        return false;
    }
}
