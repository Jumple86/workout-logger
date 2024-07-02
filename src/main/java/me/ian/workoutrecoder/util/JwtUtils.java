package me.ian.workoutrecoder.util;

public class JwtUtils {
    private static String SECRET_KEY;

   public static void setSecretKey(String secretKey) {
       SECRET_KEY = secretKey;
   }


}
