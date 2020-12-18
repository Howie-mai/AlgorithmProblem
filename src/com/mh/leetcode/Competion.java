package com.mh.leetcode;

import java.io.IOException;
import java.util.*;

/**
 * ClassName：
 * Time：2020/9/12 16:03
 * Description：
 * Author： mh
 */
public class Competion {
    public static void main(String[] args) throws IOException {
        Competion c = new Competion();
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入关机时间：");
        String s = scanner.nextLine();
        try {
            String exec = "shutdown -s -t " + s;
            Runtime.getRuntime().exec(exec);
            int second = Integer.parseInt(s);
            for (int i = second; i >= 0; i--) {
                System.out.println(i + "秒后将关机");
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("关机啦~");
    }

    public static int calculate(String s) {
        int x = 1, y = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 'A') {
                x = 2 * x + y;
            } else if (c == 'B') {
                y = 2 * y + x;
            }
        }
        return x + y;
    }

    public int breakfastNumber(int[] staple, int[] drinks, int x) {
        long ans = 0;
        int r = drinks.length - 1;
        Arrays.sort(staple);
        Arrays.sort(drinks);
        for (int i : staple) {
            int remain = x - i;
            if (remain < 0) {
                break;
            }
            while (r >= 0 && remain > drinks[r]) {
                r++;
            }
            ans += r + 1;
        }
        return (int) ans % 10000007;
    }

    public int minOperations(String[] logs) {
        int ans = 0;
        for (String log : logs) {
            if ("./".equals(log)) {
            } else if ("../".equals(log)) {
                if (ans == 0) {
                    continue;
                }
                ans--;
            } else {
                ans++;
            }
        }
        return ans;
    }

    public int minOperationsMaxProfit(int[] customers, int boardingCost, int runningCost) {
        int total = Integer.MIN_VALUE;
        int ans = 0;
        int customer = 0, pay = 0;
        for (int i = 0; i < customers.length || customer > 0; i++) {
            if (i < customers.length) {
                customer += customers[i];
            }
            int x;
            if (customer > 4) {
                pay += 4;
                x = pay * boardingCost;
                customer -= 4;
            } else {
                pay += customer;
                x = pay * boardingCost;
                customer -= customer;
            }

            int y = (i + 1) * runningCost;
            if (x - y > total) {
                ans = i + 1;
                total = x - y;
            }
        }

        return total < 0 ? -1 : ans;
    }

}
