package cn.mars.leetcode;

/**
 * Description：
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。

 示例 1:

 输入: num1 = "2", num2 = "3"
 输出: "6"
 示例 2:

 输入: num1 = "123", num2 = "456"
 输出: "56088"
 说明：

 num1 和 num2 的长度小于110。
 num1 和 num2 只包含数字 0-9。
 num1 和 num2 均不以零开头，除非是数字 0 本身。
 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/multiply-strings
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by Mars on 2020/3/7.
 */
public class 字符串相乘 {

    public String multiply(String num1, String num2) {
        if(num1.length() == 0 || num2.length() == 0){
            return "";
        }
        int nl1 = num1.length();
        int nl2 = num2.length();

        return "";
    }
}
