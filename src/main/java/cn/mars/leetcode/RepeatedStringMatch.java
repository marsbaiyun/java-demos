package cn.mars.leetcode;

/**
 * Q:
 * Given two strings A and B, find the minimum number of times A has to be repeated such that B is a substring of it. If no such solution, return -1.
 * For example, with A = "abcd" and B = "cdabcdab".Return 3, because by repeating A three times (“abcdabcdabcd”), B is a substring of it; and B is not a substring of A repeated two times ("abcdabcd").
 * Note:The length of A and B will be between 1 and 10000.
 *
 * Description：
 * Created by Mars on 2018/4/25.
 */
public class RepeatedStringMatch {

    public int repeatedStringMatch(String A, String B) {
        char[] charsA = A.toCharArray();
        char[] charsB = B.toCharArray();
        int m = charsA.length, n = charsB.length;
        for (int i = 0; i < m; ++i) {
            int j = 0;
            while (j < n && charsA[(i + j) % m] == charsB[j]) ++j;
            if (j == n) return (i + j - 1) / m + 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        String A = "abababaaba";
        String B = "aabaaba";
        System.out.println(new RepeatedStringMatch().repeatedStringMatch(A, B));
    }

}
