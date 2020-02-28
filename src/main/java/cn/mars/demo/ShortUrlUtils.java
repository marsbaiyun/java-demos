package cn.mars.demo;

import java.util.HashMap;
import java.util.Random;

/**
 * Description：
 * Created by Mars on 2020/2/20.
 */
public class ShortUrlUtils {

    private static final String BASE = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static HashMap<String, String> urlMap = new HashMap<>();

    public static void main(String[] args) {
//        String longUrl = "https://apppukyptrl1086.pc.xiaoe-tech.com/detail/v_5e3a005e42d7d_sDIQg9Ka/3?from=p_5dd3ccd673073_9LnpmMju&type=6";
//        long id = Math.abs(new Random().nextLong());
//        System.out.println(id);
//        String shortUrlKey = toBase62(id);
//        System.out.println(shortUrlKey);
//        urlMap.put(shortUrlKey, longUrl);
//        System.out.println(urlMap.get(shortUrlKey));
        for (long i = 0;i < 1000;i ++)
            System.out.printf("%s %s \n",i, toBase62(i));
    }

    public static String toBase62(long num) {
        StringBuilder sb = new StringBuilder();
        int targetBase = BASE.length();
        do {
            int i = (int) (num % targetBase);
            sb.append(BASE.charAt(i));
            num /= targetBase;
        } while (num > 0);

        return sb.reverse().toString();
    }
}
