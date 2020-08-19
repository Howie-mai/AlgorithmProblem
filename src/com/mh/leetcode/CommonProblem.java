package com.mh.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName：
 * Time：20/8/18 下午4:58
 * Description：
 *
 * @author mh
 */
public class CommonProblem {
    public static void main(String[] args) {
        CommonProblem commonProblem = new CommonProblem();
        Long start = System.currentTimeMillis();
//        commonProblem.reverse(123456);
//        System.out.println(commonProblem.fib(45));
        System.out.println(commonProblem.countSubstrings("aaaaa"));
        Long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
    /**
     * 7
     * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
     * 示例 1:
     * 输入: 123
     * 输出: 321
     */
    @SuppressWarnings("unused")
    public int reverse(int x) {
        int ans = 0;
        while (x != 0) {
            int pop = x % 10;
            if (ans > Integer.MAX_VALUE / 10 || (ans == Integer.MAX_VALUE / 10 && pop > 7)){
                return 0;
            }
            if (ans < Integer.MIN_VALUE / 10 || (ans == Integer.MIN_VALUE / 10 && pop < -8)){
                return 0;
            }
            ans = ans * 10 + pop;
            x /= 10;
        }
        return ans;
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
        // result[i][j] 表示字符串的下标为i到下标j的字符串是否为回文串
        // 0 : 不是 1 : 是
        int[][] result = new int[n][n];
        for (int i = 0;i < n;i++){
            for (int j = 0;j <= i;j++){
                if(i == j){
                    result[i][j] = 1;
                    ans++;
                }else {
                    boolean isSame = s.charAt(i) == s.charAt(j);
                    if(i - j == 1 && isSame){
                        // 相邻字符相同也为回文串
                        result[i][j] = 1;
                        ans++;
                    }else if(i - j > 1 && isSame && result[i-1][j+1] == 1){
                        result[i][j] = 1;
                        ans++;
                    }
                }
            }
        }
        return ans;
    }

    /**
     * 剑指 Offer 10- I 、 509题
     * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项。斐波那契数列的定义如下：
     * F(0) = 0,   F(1) = 1
     * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
     */
    @SuppressWarnings("unused")
//    Map<Integer,Integer> map = new HashMap<>();
    public int fib(int n) {
//        if(n < 2) {
//            return n;
//        }
//        if(!map.containsKey(n)){
//            map.put(n,(fib(n - 1) + fib(n - 2)) % 1000000007);
//        }
//        return map.get(n);

        int[] nums = new int[n + 1];
        nums[0] = 0;
        nums[1] = 1;
        for(int i = 2;i <= n;i++){
            nums[i] = (nums[i - 1] + nums [i - 2]) % 1000000007;
        }
        return nums[n];
    }
}
