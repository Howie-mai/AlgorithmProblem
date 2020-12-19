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
//        System.out.println(dp.isMatch("aab","c*a*b"));
        System.out.println(dp.robWithCircle(new int[]{2,3,2}));
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

    /**
     * 1277. 统计全为 1 的正方形子矩阵
     * 给你一个 m * n 的矩阵，矩阵中的元素不是 0 就是 1，请你统计并返回其中完全由 1 组成的 正方形 子矩阵的个数。
     */
    public int countSquares(int[][] matrix) {
        int sum = 0;
        if(matrix.length == 0){
            return sum;
        }

        int[][] dp = new int[matrix.length][matrix[0].length];

        for (int i = 0;i < matrix.length;i++){
            for (int j = 0;j < matrix[i].length;j++){
                if(matrix[i][j] == 0){
                    dp[i][j] = 0;
                }else if(i == 0 || j == 0){
                    dp[i][j] = matrix[i][j];
                }else {
                    int a =  Math.min(dp[i][j - 1],dp[i - 1][j]);
                    int b =  Math.min(a,dp[i - 1][j - 1]) + 1;
                    dp[i][j] = b;
                }
                sum += dp[i][j];
            }
        }

        return sum;
    }

    /**
     * 10. 正则表达式匹配
     * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
     * '.' 匹配任意单个字符
     * '*' 匹配零个或多个前面的那一个元素
     * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
     */
    public boolean isMatch(String s, String p) {

        return true;
    }

    /**
     * 416. 分割等和子集
     * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
     */
    public boolean canPartition(int[] nums) {
        if(nums.length < 2){
            return false;
        }

        int sum = 0,maxNum = -1;
        for (int num : nums) {
            sum += num;
            maxNum = Math.max(maxNum,num);
        }

        // 如果和为奇数 直接返回
        if(sum % 2 != 0){
            return false;
        }

        int target = sum / 2;
        if(maxNum > target){
            return false;
        }

        // 动态规划，dp[i][j]表示 从nums数组中的[0...i]区间中是否有等于j的方案。数字可选可不选
        // dp[0][nums[0]]持续为true
        boolean[][] dp = new boolean[nums.length][target + 1];

        for (int i = 0; i < nums.length; i++) {
            if(i == 0){
                dp[0][nums[0]] = true;
                continue;
            }
            for (int j = 0; j <= target; j++) {
                // 当j  < nums[i] ，说明 nums[i] 肯定选择不被选择，所以直接复制上个状态
                if(j < nums[i]){
                    dp[i][j] = dp[i - 1][j];
                }else {
                    // 当 j >= nums[i] ，可不选择 或 选择 nums[i]
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
                }
            }
        }
        return dp[nums.length - 1][target];
    }

    /**
     * 62. 不同路径
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
     *
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
     *
     * 问总共有多少条不同的路径？
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }

        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
            }
        }

        return dp[m - 1][n - 1];
    }

    /**
     * 198. 打家劫舍
     * 你是一个专业的小偷，计划偷窃沿街的房屋。
     * 每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
     */
    public int rob(int[] nums) {
//        memo = new int[nums.length];
//        Arrays.fill(memo,-1);
//        return dp(nums,0);

        // 递归备忘录模式相当于自底向上的动态规划
        int n = nums.length;
        int[] dp = new int[n + 2];
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = Math.max(dp[i + 1],dp[i + 2] + nums[i]);
        }
        return dp[0];
    }

    int[] memo;
    public int dp(int[] nums,int index){
        if(index >= nums.length){
            return 0;
        }

        if(memo[index] != -1){
            return memo[index];
        }

        // 不抢直接去下一家
        int noRob = dp(nums,index + 1);
        // 抢 去下下家
        int yesRob = nums[index] + dp(nums,index + 2);
        int res = Math.max(noRob,yesRob);
        memo[index] = res;
        return res;
    }

    /**
     * 213. 打家劫舍 II
     * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。
     * 这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。
     * 同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，能够偷窃到的最高金额。
     */
    public int robWithCircle(int[] nums) {
        int n  = nums.length;
        // 成环就需要再第一题的基础上再考虑两种情况
        //  1. 偷第一间，不偷最后一间，所以区间为[0,n-2]
        //  2. 不偷第一间，偷最后一间，所以区间为[1,n-1]
        //  在两种情况下取最大值即为正确答案
        return Math.max(
                rob(nums,0,n - 2),
                rob(nums,1,n - 1)
        );
    }

    public int rob(int[] nums,int start,int end){
        // 为了不溢出
        int[] dp = new int[end - start + 4];
        for (int i = end; i >= start; i--) {
            dp[i] = Math.max(dp[i + 1],dp[i + 2] + nums[i]);
        }
        return dp[start];
    }

}
