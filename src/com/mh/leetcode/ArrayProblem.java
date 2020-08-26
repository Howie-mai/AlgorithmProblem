package com.mh.leetcode;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * ClassName：
 * Time：20/8/3 上午10:52
 * Description：数组的用法
 *
 * @author： mh
 */
public class ArrayProblem {

    public static void main(String[] args) {
//       String s = "anagram";
//       String t = "nagaram";
//        System.out.println(isAnagramV2(s,t));
        ArrayProblem problem = new ArrayProblem();
//        System.out.println(problem.findSubsequences(new int[]{4,6,7,7}));
//        System.out.println(problem.letterCombinations(""));
        System.out.println(problem.minPathSum(new int[][]{{1,3,1},{1,5,1},{4,2,1}}));
    }

    /**
     * 242
     * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
     * 示例 1:
     * 输入: s = "anagram", t = "nagaram"
     * 输出: true
     */
    @SuppressWarnings("unused")
    public static boolean isAnagram(String s, String t) {
        if (s.length() == t.length()) {

            char[] sCh = s.toCharArray();
            char[] tCh = t.toCharArray();

            Arrays.sort(sCh);
            Arrays.sort(tCh);

            String finalS = String.valueOf(sCh);
            String finalT = String.valueOf(tCh);
            return finalS.equals(finalT);
        }
        return false;
    }

    public static boolean isAnagramV2(String s, String t) {
        if (s.length() == t.length()) {
            int[] table = new int[26];
            for (int i = 0; i < s.length(); i++) {
                table[s.charAt(i) - 'a']++;
                table[t.charAt(i) - 'a']--;
            }

            for (int i : table) {
                if (i < 0) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 1
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
     */
    @SuppressWarnings("unused")
    public int[] twoSum(int[] nums, int target) {
        Double mapInit = (nums.length + 1) / 0.75;
        Map<Integer, Integer> map = new HashMap<>(mapInit.intValue());
        for (int i = 0; i < nums.length; i++) {
            int remain = target - nums[i];
            if (remain < 0) {
                continue;
            }

            if (map.containsKey(remain)) {
                return new int[]{map.get(remain), i};
            }

            map.put(nums[i], i);
        }
        return null;
    }

    /**
     * 491. 递增子序列
     * 给定一个整型数组, 你的任务是找到所有该数组的递增子序列，递增子序列的长度至少是2。
     */
    @SuppressWarnings("unused")
    List<Integer> temp = new ArrayList<>();
    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> findSubsequences(int[] nums) {
        dfs(0, Integer.MIN_VALUE, nums);
        return ans;
    }


    public void dfs(int cur, int last, int[] nums) {
        if (cur == nums.length) {
            if (temp.size() >= 2) {
                ans.add(new ArrayList<>(temp));
            }
            return;
        }
        if (nums[cur] >= last) {
            temp.add(nums[cur]);
            dfs(cur + 1, nums[cur], nums);
            temp.remove(temp.size() - 1);
        }
        if (nums[cur] != last) {
            dfs(cur + 1, last, nums);
        }
    }

    /**
     * 17. 电话号码的字母组合
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
     * <p>
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     * 使用回溯算法
     */
    StringBuilder buffer = new StringBuilder();
    List<String> result = new ArrayList<String>();

    @SuppressWarnings("unused")
    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) {
            result.add("\"\"");
            return result;
        }
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("2", "abc");
        paramMap.put("3", "def");
        paramMap.put("4", "ghi");
        paramMap.put("5", "jkl");
        paramMap.put("6", "mno");
        paramMap.put("7", "pqrs");
        paramMap.put("8", "tuv");
        paramMap.put("9", "wxyz");

        combination(digits, paramMap, 0);
        return result;
    }

    public void combination(String digits, Map<String, String> paramMap, int index) {
        // 跳出递归，并把结果加入
        if (index == digits.length()) {
            result.add(buffer.toString());
            return;
        }
        char combination = digits.charAt(index);
        String s = paramMap.get(Character.toString(combination));
        for (int i = 0; i < s.length(); i++) {
            buffer.append(s.charAt(i));
            combination(digits, paramMap, index + 1);
            buffer.deleteCharAt(buffer.length() - 1);
        }
    }

    /**
     * 64. 最小路径和
     * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
     * 动态规划，先列出方程式
     * 当 m = 0 , n > 0的时候，路径和数组[0][n] = 路径和数组[0][n-1] + 原数组[0][n]
     * 当 m > 0 , n = 0的时候，路径和数组[m][0] = 路径和数组[m-1][0] + 原数组[m][0]
     * 当 m = 0 , n = 0的时候，路径和数组[m][n] = 原数组的[m][n] + 路径和数组的左或者上的最小值
     */
    @SuppressWarnings("unused")
    public int minPathSum(int[][] grid) {
        int x = grid.length, y = grid[0].length;
        int[][] dp = new int[x][y];
        dp[0][0] = grid[0][0];
        // 设置第一列的值
        for (int i = 1; i < x; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        // 设置第一行的值
        for (int i = 1; i < y; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }
        // 设置数组其他位置数据
        for (int i = 1; i < x; i++) {
            for (int j = 1; j < y; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }

        return dp[x - 1][y - 1];
    }
}
