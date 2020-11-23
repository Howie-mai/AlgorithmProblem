package com.mh.leetcode;

import java.util.*;

/**
 * ClassName：
 * Time：2020/9/12 16:03
 * Description：
 * Author： mh
 */
public class Competion {
    public static void main(String[] args) {
        Competion c = new Competion();
//        System.out.println(c.breakfastNumber(new int[]{10,20,11,5},new int[]{5,5,2},15));
//        System.out.println(c.numSpecial(new int[][]{{0,0,0,0,0},{1,0,0,0,0},{0,1,0,0,0},{0,0,1,0,0},{0,0,0,1,1}}));
//        System.out.println(c.unhappyFriends(4, new int[][]{{1, 3, 2}, {2, 3, 0}, {1, 3, 0}, {0, 2, 1}}, new int[][]{{1, 3}, {0, 2}}));
//        System.out.println(c.minimumOperations("rryryrrryyyrr"));
//        System.out.println(calculate("AB"));
//        int n =2;
//        int count = 0,totalSum = 0;
//        for(;n <= 20;n +=2){
//            count += n;
//            totalSum +=count;
//        }
//        System.out.println(totalSum);

//        int ans =  0;
//        for (int i = 20;i <= 25;i++){
//            int count = 1;
//            for (int j = 0;j <= i;j++){
//                count *= i;
//            }
//            ans += count;
//        }
//        System.out.println(ans);
        Scanner scanner = new Scanner(System.in);
        while (true){
            String s = scanner.nextLine();
            System.out.println("输出" + s);
            if("1".equals(s)){
                break;
            }
        }
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
            while (r >= 0 && remain > drinks[r]){
                r++;
            }
            ans += r+ 1;
        }
       return (int)ans % 10000007;
    }

    public int minOperations(String[] logs) {
        int ans = 0;
        for (String log : logs) {
            if("./".equals(log)){
            }else if("../".equals(log)){
                if(ans == 0){
                    continue;
                }
                ans--;
            }else {
                ans++;
            }
        }
        return ans;
    }

    public int minOperationsMaxProfit(int[] customers, int boardingCost, int runningCost) {
        int total = Integer.MIN_VALUE;
        int ans = 0;
        int customer = 0,pay = 0;
        for (int i = 0; i < customers.length || customer > 0; i++) {
            if(i < customers.length){
                customer += customers[i];
            }
            int x;
            if(customer > 4){
                pay += 4;
                x = pay * boardingCost;
                customer -= 4;
            }else {
                pay += customer;
                x = pay * boardingCost;
                customer -= customer;
            }

            int y = (i + 1) * runningCost;
            if(x - y > total){
                ans = i + 1;
                total = x - y;
            }
        }

        return total < 0 ? -1 : ans;
    }

}
