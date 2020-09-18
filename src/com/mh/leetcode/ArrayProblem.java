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
//        System.out.println(problem.diagonalSum(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));
//        System.out.println(problem.findLengthOfShortestSubarray(new int[]{1,2,3}));
//        System.out.println(problem.solveNQueens(3));
//        int[] x = problem.missingTwo(new int[]{1,2,5});
//        for (int i : x) {
//            System.out.println(i);
//        }
//        System.out.println(problem.maximalSquare(new char[][]{{'1','0','1','0'},{'1','0','1','1'},{'1','0','1','1'},{'1','1','1','1'}}));
        System.out.println(problem.maximalSquare(new char[][]{{'0','1'}}));
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

    /**
     * 174. 地下城游戏
     * 一些恶魔抓住了公主（P）并将她关在了地下城的右下角。地下城是由 M x N 个房间组成的二维网格。我们英勇的骑士（K）最初被安置在左上角的房间里，他必须穿过地下城并通过对抗恶魔来拯救公主。
     * 骑士的初始健康点数为一个正整数。如果他的健康点数在某一时刻降至 0 或以下，他会立即死亡。
     * 有些房间由恶魔守卫，因此骑士在进入这些房间时会失去健康点数（若房间里的值为负整数，则表示骑士将损失健康点数）；其他房间要么是空的（房间里的值为 0），要么包含增加骑士健康点数的魔法球（若房间里的值为正整数，则表示骑士将增加健康点数）。
     * 为了尽快到达公主，骑士决定每次只向右或向下移动一步。
     * 编写一个函数来计算确保骑士能够拯救到公主所需的最低初始健康点数。
     */
    public int calculateMinimumHP(int[][] dungeon) {
        int x = dungeon.length, y = dungeon[0].length;
        return 0;
    }

    /**
     * 面试题 17.19. 消失的两个数字
     * 给定一个数组，包含从 1 到 N 所有的整数，但其中缺了两个数字。你能在 O(N) 时间内只用 O(1) 的空间找到它们吗？
     * <p>
     * 以任意顺序返回这两个数字均可。
     */
    public int[] missingTwo(int[] nums) {
        int n = nums.length + 2;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        int sumTwo = n * (n + 1) / 2 - sum, limit = sumTwo / 2;
        sum = 0;
        for (int x : nums) {
            // 两个数不相同那么一个大于，一个小于
            if (x <= limit) {
                sum += x;
            }
        }
        int one = limit * (limit + 1) / 2 - sum;
        return new int[]{one, sumTwo - one};
    }

    /**
     * LCP
     * 二维数组的两条对角线和 ，交点只算一次
     */
    public int diagonalSum(int[][] mat) {
        if (mat.length == 1 && mat[0].length == 1) {
            return mat[0][0];
        }

        // 0,0 ,1,1
        int i = 0, j = 0;
        int x = 0, y = mat.length - 1;
        int sum = 0;
        for (; i < mat.length && j < mat.length; i++, j++) {
            sum += mat[i][j];
        }

        // 0,4
        for (; x < mat.length && y >= 0; x++, y--) {
            if (x == y) {
                continue;
            }
            sum += mat[x][y];
        }

        return sum;
    }

    /**
     * 221. 最大正方形
     * 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
     */
    public int maximalSquare(char[][] matrix) {
        if(matrix.length == 0){
            return 0;
        }

        int[][] dp = new int[matrix.length][matrix[0].length];
        int sum = 0;
        // 设置第一行 第一列
//        for (int i = 0; i < matrix.length; i++) {
//            dp[0][i] = matrix[0][i] - '0';
//        }
//
//        for (int i = 0; i < matrix.length; i++) {
//            dp[i][0] = matrix[i][0] - '0';
//        }

        for (int i = 0;i < matrix.length;i++){
            for (int j = 0;j < matrix[i].length;j++){
                if(matrix[i][j] == '0'){
                    continue;
                }

                if(i == 0 || j == 0){
                    dp[i][j] = matrix[i][j] - '0';
                    sum = Math.max(sum,dp[i][j]);
                    continue;
                }

                int a =  Math.min(dp[i][j - 1],dp[i - 1][j]);
                int b =  Math.min(a,dp[i - 1][j - 1]) + 1;
                dp[i][j] = b;
                sum = Math.max(b * b , sum);
            }
        }
        return sum;
    }

    /**
     * 31. 下一个排列
     * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
     * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
     * 必须原地修改，只允许使用额外常数空间。
     * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
     * 1,2,3 → 1,3,2
     * 3,2,1 → 1,2,3
     * 1,1,5 → 1,5,1
     */
    public void nextPermutation(int[] nums) {
        int start = -1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if(nums[i] <= nums[i + 1]){
                start = i;
                break;
            }
        }

        if(start == -1){
            Arrays.sort(nums);
            return;
        }

        for (int i = nums.length - 1; i >= 0; i--) {
            if(nums[i] > nums[start]){
                // 交换
                swapNum(nums,i,start);
                break;
            }
        }
        // 把[end,nums.length-1]区间的数变为升序
        reverse(nums,start + 1);
    }

    private void swapNum(int[] nums, int i, int start) {
        int temp = nums[i];
        nums[i] = nums[start];
        nums[start] = temp;
    }


    private void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            swapNum(nums, i, j);
            i++;
            j--;
        }
    }

}
