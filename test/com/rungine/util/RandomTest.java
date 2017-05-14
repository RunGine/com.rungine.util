package com.rungine.util;

/**
 * Created by neil on 14/05/2017.
 */
public class RandomTest {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(Random.generateRandomString(64,true,true,true,true));
        }
    }
}
