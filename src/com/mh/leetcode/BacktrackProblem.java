package com.mh.leetcode;

import com.sun.org.apache.regexp.internal.RE;

import java.util.*;

/**
 * ClassName：回溯算法问题
 * Time：20/9/8 上午10:33
 * Description：
 *
 * @author mh
 */
public class BacktrackProblem {

    public static void main(String[] args) {
        BacktrackProblem backtrack = new BacktrackProblem();
        Long start = System.currentTimeMillis();

//        System.out.println(backtrack.combinationSum2(new int[]{10,1,2,7,6,1,5},8));
//        System.out.println(backtrack.combinationSum3(3,15));
        System.out.println(backtrack.combinationSum4(new int[]{1, 2, 5}, 5));
        Long end = System.currentTimeMillis();
        System.out.println("执行时间：" + (end - start) + "ms");
    }

    /**
     * 79. 单词搜索
     * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
     * <p>
     * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
     */
    public boolean exist(char[][] board, String word) {

        if (word.length() == 0) {
            return false;
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (backTrack(board, word, i, j, 0)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 上下左右数组
     */
    int[][] direct = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    public boolean backTrack(char[][] board, String word, int i, int j, int index) {

        if (index == word.length()) {
            return true;
        }

        // 标记为已访问
        char tmp = board[i][j];
        if (board[i][j] != word.charAt(index)) {
            return false;
        }

        if (index + 1 == word.length()) {
            return true;
        }
        board[i][j] = '/';
        for (int x = 0; x < 4; x++) {
            int newI = i + direct[x][0];
            int newJ = j + direct[x][1];
            if (inArea(newI, newJ, board)) {
                if (backTrack(board, word, newI, newJ, index + 1)) {
                    return true;
                }
            }
        }
        board[i][j] = tmp;

        return false;
    }

    public boolean inArea(int i, int j, char[][] board) {
        return i >= 0 && i < board.length && j >= 0 && j < board[0].length && board[i][j] != '/';
    }

    /**
     * 39. 组合总和
     * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     * <p>
     * candidates 中的数字可以无限制重复被选取
     */
    List<List<Integer>> ans39List = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Deque<Integer> list = new ArrayDeque<>();
        if (candidates.length == 0) {
            return ans39List;
        }

        backTrackBy39(candidates, list, target, 0);
        return ans39List;

    }

    public void backTrackBy39(int[] candidates, Deque<Integer> list, int target, int index) {
        if (target == 0) {
            ans39List.add(new ArrayList<>(list));
            return;
        }

        if (target < 0) {
            return;
        }

        if (index == candidates.length) {
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            list.addLast(candidates[i]);
            backTrackBy39(candidates, list, target - candidates[i], i);
            list.removeLast();
        }
    }

    /**
     * 40. 组合总和 II
     * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     * <p>
     * candidates 中的每个数字在每个组合中只能使用一次。
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Deque<Integer> list = new ArrayDeque<>();

        Arrays.sort(candidates);
        backTrackBy40(candidates, list, ans, target, 0);
        return ans;
    }

    public void backTrackBy40(int[] candidates, Deque<Integer> list, List<List<Integer>> ans, int target, int index) {
        if (target == 0) {
            ans.add(new ArrayList<>(list));
            return;
        }

        if (index == candidates.length) {
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            // 减枝避免重复集合
            if (target - candidates[i] < 0) {
                break;
            }

            if (i > index && candidates[i] == candidates[i - 1]) {
                continue;
            }

            list.addLast(candidates[i]);
            backTrackBy40(candidates, list, ans, target - candidates[i], i + 1);
            list.removeLast();
        }
    }

    /**
     * 216. 组合总和 III
     * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ans = new ArrayList<>();
        Deque<Integer> list = new ArrayDeque<>();

        backTrackBy216(list, ans, n, k, 1);
        return ans;
    }

    public void backTrackBy216(Deque<Integer> list, List<List<Integer>> ans, int n, int k, int index) {
//        if(list.size() > k){
//            return;
//        }

        if (0 == k && n == 0) {
            ans.add(new ArrayList<>(list));
            return;
        }

        for (int i = index; i <= 9; i++) {
            if (n - i < 0 || k < 0) {
                break;
            }

            list.addLast(i);
            backTrackBy216(list, ans, n - i, k - 1, i + 1);
            list.removeLast();
        }
    }

    /**
     * 377. 组合总和 Ⅳ
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
     * 752. 打开转盘锁
     * 你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。
     * 每个拨轮可以自由旋转：例如把 '9' 变为  '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。
     * 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
     * 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
     * 字符串 target 代表可以解锁的数字，你需要给出最小的旋转次数，如果无论如何不能解锁，返回 -1。
     */
    public int openLock(String[] deadEnds, String target) {
        Queue<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>(Arrays.asList(deadEnds));
        queue.offer("0000");
        visited.add("0000");
        int step = 0;
        while (!queue.isEmpty()){
            int len = queue.size();
            for (int i = 0;i < len;i++){
                String cur = queue.poll();

                if(cur.equals(target)){
                    return step;
                }

                for (int j = 0;j < 4;j++){
                    String up = plusOne(cur, j);
                    if(!visited.contains(up)){
                        visited.add(up);
                        queue.add(up);
                    }
                    String down = minusOne(cur, j);
                    if(!visited.contains(down)){
                        visited.add(down);
                        queue.add(down);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    public String plusOne(String s, int j) {
        char[] chars = s.toCharArray();
        if (chars[j] == '9') {
            chars[j] = '0';
        } else {
            chars[j] += 1;
        }
        return Arrays.toString(chars);
    }

    public String minusOne(String s, int j) {
        char[] chars = s.toCharArray();
        if (chars[j] == '0') {
            chars[j] = '9';
        } else {
            chars[j] -= 1;
        }
        return Arrays.toString(chars);
    }
}
