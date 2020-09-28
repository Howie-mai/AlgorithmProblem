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

        int n =2;
        int count = 0,totalSum = 0;
        for(;n <= 20;n +=2){
            count += n;
            totalSum +=count;
        }
        System.out.println(totalSum);
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
            while (r >= 0 && drinks[r] > remain) {
                r--;
            }
            ans += r + 1;
        }
        return (int) (ans % 1000000007);
    }

    public int numSpecial(int[][] mat) {
        Map<Integer, Integer> row = new HashMap<>();
        Map<Integer, Integer> col = new HashMap<>();
        List<Integer> temp = new ArrayList<>();
        for (int i = 0; i < mat.length; i++) {
            row.put(i, 0);
            temp.add(-1);
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 0) {
                    continue;
                }
                row.put(i, row.get(i) + 1);
                temp.set(i, j);
                if (col.containsKey(j)) {
                    col.put(j, col.get(j) + 1);
                } else {
                    col.put(j, 1);
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < mat.length; i++) {
            if (row.get(i) != 1) {
                continue;
            }
            if (col.get(temp.get(i)) == 1) {
                ans++;
            }
        }
        return ans;
    }

    public int unhappyFriends(int n, int[][] preferences, int[][] pairs) {
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for (int[] pair : pairs) {
            map.put(pair[0], pair[1]);
        }

        for (int i = 0; i < n; i++) {
            int friend = getFriend(i,map);

            if(friend == preferences[i][0]){
                System.out.println(i + "是快乐的");
                continue;
            }
            int x = -1;
            for (int j = 1; j < preferences[i].length; j++) {
                if(preferences[i][j] == friend){
                    x = j;
                    break;
                }
            }

            for(int z = 0;z < x;z++){
                int friend1 = preferences[i][z];
                int friend2 = getFriend(friend1, map);

                int a = -1,b = -1;
                for (int i1 = 0; i1 < preferences[friend1].length; i1++) {
                    if(a != -1 && b != -1){
                        break;
                    }
                    if(preferences[friend1][i1] == i){
                        a = i1;
                    }
                    if(preferences[friend1][i1] == friend2){
                        b = i1;
                    }
                }

                if(a < b){
                    System.out.println(i + "是不开心的");
                    ans++;
                    break;
                }
            }
        }
        return ans;
    }

    public int getFriend(int i, Map<Integer, Integer> map) {
        int friend = -1;
        if (map.containsKey(i)) {
            friend = map.get(i);
        }else {
            for (Integer key : map.keySet()) {
                if (map.get(key) == i) {
                    friend = key;
                }
            }
        }
       return friend;
    }

}
