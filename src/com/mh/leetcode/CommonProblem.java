package com.mh.leetcode;

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
        commonProblem.reverse(123456);
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
}
