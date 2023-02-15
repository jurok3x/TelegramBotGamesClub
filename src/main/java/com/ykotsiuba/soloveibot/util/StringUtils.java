package com.ykotsiuba.soloveibot.util;

public class StringUtils {
    
    public static String trim(String s) {
        if (isBlank(s)) {
          return "";
        }
        return s.trim();
      }

      public static boolean isBlank(String s) {
        if (s == null) {
          return true;
        }
        return s.trim().length() == 0;
      }

}
