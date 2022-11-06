package com.dss.login.util;

import java.util.regex.Pattern;

public class StringValidator {

    public static boolean isAlphabetic(String characters){
        if(characters != null) {
            return Pattern.matches(".*[a-zA-Z].*", characters);
        }
        return false;
    }

    public static boolean isNumeric(String characters) {
        if (characters != null) {
            return Pattern.matches(".*[0-9].*", characters);
        }
        return false;
    }

    public static String isValidPassword(String characters) {
        boolean isValid = true;
        StringBuilder sb = new StringBuilder("Password must have at least ");
        if(characters != null) {
            if(!Pattern.matches("(.*[A-Z].*)", characters)){
                sb.append("an uppercase character, ");
                isValid = false;
            }
            if(!Pattern.matches("(.*[a-z].*)", characters)){
                sb.append("a lowercase character, ");
                isValid = false;
            }
            if(!Pattern.matches("(.*[0-9].*)", characters)){
                sb.append("a digit, ");
                isValid = false;
            }
            if(!Pattern.matches("(.*[!@#$%^&*].*)", characters)){
                sb.append("a special character among !@#$%^&*");
                isValid = false;
            }
        }
        if(!isValid){
            return sb.toString();
        }
        return null;
    }
}
