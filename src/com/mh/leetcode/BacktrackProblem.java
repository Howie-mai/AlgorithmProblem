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
//        System.out.println(backtrack.combinationSum4(new int[]{1, 2, 5}, 5));
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
     * 37. 解数独
     * 编写一个程序，通过已填充的空格来解决数独问题。
     * 一个数独的解法需遵循如下规则：
     * 数字 1-9 在每一行只能出现一次。
     * 数字 1-9 在每一列只能出现一次。
     * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
     * 空白格用 '.' 表示。
     * 所有的数组规格都是9X9
     */
    public void solveSudoku(char[][] board) {
        backTrackBy37(board,0,0);
    }

    public boolean backTrackBy37(char[][] board, int row, int col) {
        // 结束一行换另一行
        if (col == board[0].length) {
            return backTrackBy37(board, row + 1, 0);
        }

        // 所有行被遍历完，base case
        if (row == board.length) {
            return true;
        }

        // 判断是否有数字
        if(board[row][col] != '.'){
            return backTrackBy37(board,row,col + 1);
        }

        // 循环1-9
        for (char ch = '1'; ch <= '9'; ch++) {
            if(!isVaild(board,row,col,ch)){
                continue;
            }
            // 做选择
            board[row][col] = ch;
            if(backTrackBy37(board, row, col + 1)){
                return true;
            }
            board[row][col] = '.';
        }
        return false;
    }

    public boolean isVaild(char[][] board,int row ,int col,char ch){
        for (int i = 0;i < 9;i++){
            // 判断行有没有重复字符
            if(board[row][i] == ch){
                return false;
            }
            // 判断列有没有重复字符
            if(board[i][col] == ch){
                return false;
            }

            // 判断九宫格有没有重复字符
            if(board[(row/3)*3 + i/3][(col/3)*3 + i%3] == ch){
                return false;
            }
        }
        return true;
    }
}
