package Zaliczeniowe2.Utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringUtils {
    public static boolean isEmpty(String s){
        if(s == null || s.isEmpty())
            return true;
        else
            return false;
    }

    public static boolean isAnyEmpty(String... args){
        for (String s : args) {
            if(s == null || s.isEmpty())
                return true;
        }
        return false;
    }

    public static String makeSHA256(String s) {
        MessageDigest md = null;
        byte[] digest = new byte[0];
        try {
            md = MessageDigest.getInstance("SHA-256");
            md.update(s.getBytes("UTF-8"));
            digest = md.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return String.format("%064x", new java.math.BigInteger(1, digest));
    }
}
