package com.mh.leetcode;

import java.util.Arrays;

/**
 * ClassName：
 * Time：2020/12/17 10:40 上午
 * Description：
 *
 * @author mh
 */
public class StockMarketProblem {

    public static void main(String[] args) {
        StockMarketProblem stockMarketProblem = new StockMarketProblem();
        System.out.println(stockMarketProblem.maxProfitVersion3OrVersion4(new int[]{2,3,2,6,5,0,3},2));
    }

    /**
     * 121. 买卖股票的最佳时机
     * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
     * 注意：你不能在买入股票前卖出股票。
     */
    public int maxProfit(int[] prices) {
        int ans = 0;
        int min = prices.length == 0 ? -1 : prices[0];
        for(int i = 1;i < prices.length;i++){
            min = Math.min(min,prices[i]);
            ans = Math.max(ans,prices[i] - min);
        }
        return ans;
    }

    /**
     * 122. 买卖股票的最佳时机 II
     * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     *
     * 贪心算法：能赚一点是一点
     */
    public int maxProfitVersion2(int[] prices) {
        int res = 0;
        for(int i = 1;i < prices.length;i++){
            if(prices[i - 1] < prices[i]){
                res += prices[i] - prices[i - 1];
            }
        }
        return res;
    }

    /**
     * 123. 买卖股票的最佳时机 III
     * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
     * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
     * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     *
     * 188. 买卖股票的最佳时机 IV
     * 给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。
     * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     */
    public int maxProfitVersion3OrVersion4(int[] prices,int k) {
        int length = prices.length;
        if(length < 2){
            return 0;
        }
        // 第i天 完成第几次交易 是否持有股票
        int[][][] dp = new int[length][k + 1][2];
        for (int count = k;count >= 1;count--){
            dp[0][count][1] = -prices[0];
        }
        for (int i = 1; i < prices.length; i++) {
            for (int count = k;count >= 1;count--){
                dp[i][count][0] = Math.max(dp[i - 1][k][0],dp[i - 1][count][1] + prices[i]);
                dp[i][count][1] = Math.max(dp[i - 1][k][1],dp[i - 1][count - 1][0] - prices[i]);
            }
        }
        return dp[length - 1][k][0];
    }

    /**
     * 714. 买卖股票的最佳时机含手续费
     * 给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。
     *
     * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
     *
     * 返回获得利润的最大值。
     *
     * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
     */
    public int maxProfitWithFee(int[] prices, int fee) {
        int n = prices.length;
        // dp[i][0]:第i天不持有股票的利润
        // dp[i][1]:第i天持有股票的利润
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; ++i) {
            // 穷举
            // 今天不持有股票的情况，从下面两种情况选利润最大的情况
            //   如果前一天没有持有股票，利润则为dp[i - 1][0]
            //   如果前一天持有股票，利润则为 前一天持有股票的利润dp[i - 1][1] + 卖出今天的股票价格 - 手续费
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
            // 今天持有股票的情况，从下面两种情况选利润最大的情况
            //   如果前一天持有股票，利润则为dp[i - 1][1] 跟前一天一样
            //   如果前一天没有持有股票，利润则为前一天不持有股票的利润 dp[i - 1][0] - 买入今天的股票价格
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[n - 1][0];
    }

    /**
     * 309. 最佳买卖股票时机含冷冻期
     * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
     *
     * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
     *
     * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
     */
    public int maxProfitWithCold(int[] prices) {
        int n = prices.length;
        if(n < 2){
            return 0;
        }
        // dp[i][0]:第i天不持有股票的利润
        // dp[i][1]:第i天持有股票的利润
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        dp[1][0] = Math.max(0,dp[0][1] + prices[1]);
        dp[1][1] = Math.max(dp[0][1],-prices[1]);
        // 代表dp[i - 2][0];
        for (int i = 2; i < n; ++i) {
            int temp = dp[i][0];
            // 穷举
            // 今天不持有股票的情况，从下面两种情况选利润最大的情况
            //   如果前一天没有持有股票，利润则为dp[i - 1][0]
            //   如果前一天持有股票，利润则为 前一天持有股票的利润dp[i - 1][1] + 卖出今天的股票价格
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            // 今天持有股票的情况，从下面两种情况选利润最大的情况
            //   如果前一天持有股票，利润则为dp[i - 1][1] 跟前一天一样
            //   如果前一天没有持有股票，因为有冷冻期，利润则为前两天不持有股票的利润 dp[i - 1][0] - 买入今天的股票价格
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][0] - prices[i]);
        }
        return dp[n - 1][0];
    }


}
