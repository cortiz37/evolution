package com.util;

import java.util.Base64;

public class EncUtil {

    public static void main(String[] args) {
        System.out.println(encode("Batista85**"));
    }

    public static String getKey() {
        String key = System.getenv("ENC_KEY");
        if (key == null) {
            key = "lkjAGSDJhagsdk8jasd9a87SD098as7d";
        }
        return key;
    }

    public static String encode(String value) {
        try {
            int total = 3;
            while (total > 0) {
                value = getKey() + value + getKey();
                value = Base64.getEncoder().encodeToString(value.getBytes());
                total--;
            }
            return value;
        } catch (Exception e) {
            return "enc_value";
        }
    }

    public static String decode(String value) {
        try {
            int total = 3;
            while (total > 0) {
                value = new String(Base64.getDecoder().decode(value));
                value = value.replaceAll(getKey(), "");
                total--;
            }
            return value;
        } catch (Exception e) {
            return "dec_value";
        }
    }
}
