package cn.mars.demo;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Description：
 * Created by Mars on 2019/12/24.
 */
public class PrintABC {

    public static void main(String[] args) {
        String s = "abc";
        char[] chs = s.toCharArray();
        handlde(chs);

        List<String> result = Lists.newArrayList();
        getAllLists(result, s, "");
        System.out.println(JSON.toJSONString(result));
    }

    public static void handlde(char[] chs) {
        int len = chs.length;
        int nbits = 1 << len;
        for (int i = 0; i < nbits; ++i) {
            int t;
            for (int j = 0; j < len; j++) {
                t = 1 << j;
                if ((t & i) != 0) {
                    System.out.print(chs[j]);
                }
            }
            System.out.println();
        }
    }

    /**   
     * @param base 以该字符串作为基础字符串，进行选择性组合。  
     * @param buff 所求字符串的临时结果  
     * @param result 存放所求结果  
     */
    public static void getAllLists(List<String> result, String base, String buff){
        if(base.length() <= 0){
            result.add(buff.toString());
        }
        for(int i = 0; i < base.length(); i++){
            getAllLists(result, new StringBuilder(base).deleteCharAt(i).toString(),
                    buff + base.charAt(i));
        }
    }
}
