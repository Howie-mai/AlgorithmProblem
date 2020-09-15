package com.mh.leetcode;

import java.util.*;

/**
 * ClassName：
 * Time：20/9/10 上午9:59
 * Description：动态规划问题
 *
 * @author mh
 */
public class DpProblem {

    public static void main(String[] args) {
        DpProblem dp = new DpProblem();
//        System.out.println(dp.findMaxForm(new String[]{"10", "0001", "111001", "1", "0"},5,3));
        String s = "aaa";
//        System.out.println(dp.countPalindromicSubsequences(s));
    }

    /**
     * 474. 一和零
     * 在计算机界中，我们总是追求用有限的资源获取最大的收益。
     *
     * 现在，假设你分别支配着 m 个 0 和 n 个 1。另外，还有一个仅包含 0 和 1 字符串的数组。
     *
     * 你的任务是使用给定的 m 个 0 和 n 个 1 ，找到能拼出存在于数组中的字符串的最大数量。每个 0 和 1 至多被使用一次。
     */
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (String s: strs) {
            int[] count = countzeroesones(s);
            for (int zeroes = m; zeroes >= count[0]; zeroes--){
                for (int ones = n; ones >= count[1]; ones--){
                    dp[zeroes][ones] = Math.max(1 + dp[zeroes - count[0]][ones - count[1]], dp[zeroes][ones]);
                }
            }
        }
        return dp[m][n];
    }
    public int[] countzeroesones(String s) {
        int[] c = new int[2];
        for (int i = 0; i < s.length(); i++) {
            c[s.charAt(i)-'0']++;
        }
        return c;
    }

    /**
     * 第647题
     * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
     * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
     * 示例 ：
     * 输入："aaa"
     * 输出：6
     * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
     */
    @SuppressWarnings("unused")
    public int countSubstrings(String s) {
        int n = s.length(), ans = 0;
        // 0 : 不是 1 : 是
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if(s.charAt(i) == s.charAt(j) && (i - j <= 1 || result[j + 1] == 1)){
                    result[j] = 1;
                    ans++;
                }else {
                    result[j] = 0;
                }
            }
        }
        return ans;
    }

    /**
     * 5. 最长回文子串
     * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
     */
    public String longestPalindrome(String s) {
        if (s.length() == 0){
            return "";
        }
        int n = s.length();
        int max = 0;
        int[] index = new int[2];
        // result[i][j] 表示字符串的下标为j到下标i的字符串是否为回文串
        // 0 : 不是 1 : 是
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if(s.charAt(i) == s.charAt(j) && (i - j <= 1 || result[j + 1] == 1)){
                    result[j] = 1;
                    if(i - j + 1 > max){
                        max = i - j + 1;
                        index[0] = j;
                        index[1] = i;
                    }
                }else {
                    result[j] = 0;
                }
            }
        }
        return s.substring(index[0],index[1] + 1);
    }

    /**
     * 516. 最长回文子序列
     * 给定一个字符串 s ，找到其中最长的回文子序列，并返回该序列的长度。可以假设 s 的最大长度为 1000 。
     *
     * 输入: "bbbab"
     * 输出: 4  最长子序列为："bbbb"
     *
     */
    public int longestPalindromeSubSeq(String s) {
        int len = s.length();
        if(len < 2){
            return s.length();
        }
        // dp[i][j]表示从 i 到 j的最长子序列长度
        int[][] dp = new int[len][len];
        // 自底向上计算 ，先计算[i:j]里面的最长子序列
        for (int i = len - 1;i >= 0;i--){
            // 自身回文长度为1
            dp[i][i] = 1;
            for (int j = i + 1;j < len;j++){
                if(s.charAt(i) == s.charAt(j)){
                    // 如果两段字符相同，把两段都加入[i+1，j-1]中变成最长的子序列
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                }else {
                    // 不相同，则把两端分别加入到 [i+1,j-1]中看哪边的回文子序列更长
                    dp[i][j] = Math.max(dp[i][j-1],dp[i+1][j]);
                }
            }
        }
        // 最后返回右上角即为最大值
        return dp[0][len-1];
    }

    /**
     * 377. 组合总和 Ⅳ（使用动态规划），回溯算法会超时
     * 给定一个由正整数组成且不存在重复数字的数组，找出和为给定目标正整数的组合的个数。
     */
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        // 这个值被其它状态参考，设置为 1 是合理的
        dp[0] = 1;
        /**
         * dp[4] = dp[3] + dp[2] + dp[1]
         * 即：4 的组合数可以由三部分组成，1 和 dp[3]，2 和 dp[2], 3 和dp[1];
         */
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (num <= i) {
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }
}
