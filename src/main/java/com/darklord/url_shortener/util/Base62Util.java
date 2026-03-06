package com.darklord.url_shortener.util;

public class Base62Util {
    private static final String ALLOWED_CHARACTERS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int BASE = ALLOWED_CHARACTERS.length();

    public static String encode(long input) {
        StringBuilder encodedString = new StringBuilder();

        if(input == 0) {
            return String.valueOf(ALLOWED_CHARACTERS.charAt(0));
        }

        while(input > 0) {
            encodedString.append(ALLOWED_CHARACTERS.charAt((int)(input%BASE)));
            input = input/BASE;
        }
        return encodedString.reverse().toString();
    }
}
