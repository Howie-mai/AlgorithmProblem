package com.mh.leetcode;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
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
//        Competion c = new Competion();
//        try {
//            URL url = new URL("https://itdage.com/kkb/kkbsms?key=xzk&number=15812350780&code=fscgll");
//            URLConnection content = url.openConnection();
//            InputStream inputStream = content.getInputStream();
//            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
//            String s = br.readLine();
//            System.out.println(s);
//            br.close();
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("请输入关机时间：");
//        String s = scanner.nextLine();
//        try {
//            String exec = "shutdown -s -t " + s;
//            Runtime.getRuntime().exec(exec);
//            int second = Integer.parseInt(s);
//            for (int i = second; i >= 0; i--) {
//                System.out.println(i + "秒后将关机");
//                Thread.sleep(1000);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println("关机啦~");
        Competion competion = new Competion();
//        competion.countBalls(5,15);
        competion.canEat(new int[]{16,38,8,41,30,31,14,45,3,2,24,23,38,30,31,17,35,4,9,42,28,18,37,18,14,46,11,13,19,3,5,39,24,48,20,29,4,19,36,11,28,49,38,16,23,24,4,22,29,35,45,38,37,40,2,37,8,41,33,8,40,27,13,4,33,5,8,14,19,35,31,8,8},new int[][]{{43,1054,49}});
//[16,38,8,41,30,31,14,45,3,2,24,23,38,30,31,17,35,4,9,42,28,18,37,18,14,46,11,13,19,3,5,39,24,48,20,29,4,19,36,11,28,49,38,16,23,24,4,22,29,35,45,38,37,40,2,37,8,41,33,8,40,27,13,4,33,5,8,14,19,35,31,8,8]
//[[35,669,5],[72,822,74],[47,933,94],[62,942,85],[42,596,11],[56,1066,18],[54,571,45],[39,890,100],[3,175,26],[48,1489,37],[40,447,52],[30,584,7],[26,1486,38],[21,1142,21],[9,494,96],[56,759,81],[13,319,16],[20,1406,57],[11,1092,19],[24,670,67],[38,1702,33],[5,676,32],[50,1386,77],[36,1551,87],[29,1445,13],[58,977,13],[7,887,64],[37,1396,23],[0,765,69],[40,1083,86],[43,1054,49],[48,690,92],[28,1201,56],[47,948,43],[57,233,25],[32,1293,65],[0,1646,34],[43,1467,39],[39,484,23],[21,1576,69],[12,1222,68],[9,457,83],[32,65,9],[10,1424,42],[35,534,3],[23,83,22],[33,501,33],[25,679,51],[2,321,42],[1,240,68],[7,1297,42],[45,480,72],[26,1472,9],[6,649,90],[26,361,57],[49,1592,7],[11,158,95],[35,448,24],[41,1654,10],[61,510,43],[31,1230,95],[11,1471,12],[37,43,84],[56,1147,48],[69,1368,65],[22,170,24],[56,192,80],[34,1207,69],[1,1226,22],[37,1633,50],[11,98,58],[17,125,13],[0,1490,5],[37,1732,43],[45,793,14],[16,578,72],[50,241,78]]
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

    public int countBalls(int lowLimit, int highLimit) {
        Map<Integer,Integer> map = new HashMap<>();
        int max = 0;
        int ans = -1;
        for (int i = lowLimit; i <= highLimit; i++) {
            int num = i;
            int temp = 0;
            while (num != 0){
                temp += (num % 10);
                num /= 10;
            }
            int count = map.getOrDefault(temp, 0) + 1;
            map.put(temp,count);
            max = Math.max(count,max);
        }
        return max;
    }

    public boolean[] canEat(int[] candiesCount, int[][] queries) {
        boolean[] ans = new boolean[queries.length];
        int candyTotal = 0;
        for (int i : candiesCount) {
            candyTotal += i;
        }
        for (int i = 0; i < queries.length; i++) {
            int favoriteType = queries[i][0];
            int favoriteDay = queries[i][1];
            int dailyCap = queries[i][2];

            int candySum = 0;
            for (int j = 0; j < favoriteType; j++) {
                candySum += candiesCount[j];
            }

            if(candyTotal < favoriteDay || candySum + candiesCount[favoriteType] < favoriteDay){
                ans[i] = false;
                continue;
            }

            for (int j = 0; j < favoriteDay; j++) {
                candySum -= dailyCap;
                if(candySum <= 0){
                    break;
                }
            }
            ans[i] = candySum <= 0;
        }

        return ans;
    }
}
