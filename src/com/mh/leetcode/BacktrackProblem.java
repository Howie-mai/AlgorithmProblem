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
     * 77. 组合
     * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
     */
    List<List<Integer>> ans77List = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        if (n == 0) {
            return ans77List;
        }

        Deque<Integer> list = new ArrayDeque<>();
        dfs(1, n, k, list);
        return ans77List;
    }

    public void dfs(int index, int n, int k, Deque<Integer> list) {
        // 剪枝：temp 长度加上区间 [index, n] 的长度小于 k，不可能构造出长度为 k 的 temp
        if (list.size() + (n - index + 1) < k) {
            return;
        }

        if (list.size() == k) {
            ans77List.add(new ArrayList<>(list));
            return;
        }

        list.addLast(index);
        dfs(index + 1, n, k, list);
        list.removeLast();

        dfs(index + 1, n, k, list);
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

    /**
     * 51. N 皇后
     * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
     */
    public List<List<String>> solveQueens(int n) {
        String[][] board = new String[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], ".");
        }

        backtrack(board, 0);
        return ansList;
    }

    List<List<String>> ansList = new ArrayList<>();
    private void backtrack(String[][] board, int row) {
        // 结束条件并添加进结果集
        if (row == board.length) {
            ansList.add(changeList(board));
            return;
        }

        int n = board[row].length;
        // 回溯算法
        for (int col = 0; col < n; col++) {
            if (!isValid(board, row, col)) {
                continue;
            }
            // 做选择
            board[row][col] = "Q";
            // 进入下一决策
            backtrack(board, row + 1);
            // 撤销选择
            board[row][col] = ".";
        }
    }

    /**
     * 是否可以在board[row][col]放置皇后
     */
    private boolean isValid(String[][] board, int row, int col) {
        int n = board.length;
        for (String[] strings : board) {
            // 检查列是否有皇后冲突
            if ("Q".equals(strings[col])) {
                return false;
            }
        }
        // 检查右上方是否有皇后相互冲突
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if ("Q".equals(board[i][j])) {
                return false;
            }
        }
        // 检查左上方是否有皇后相互冲突
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if ("Q".equals(board[i][j])) {
                return false;
            }
        }
        return true;
    }

    private List<String> changeList(String[][] board) {
        List<String> temp = new ArrayList<>();
        for (String[] strings : board) {
            StringBuilder stringBuilder = new StringBuilder();
            for (String string : strings) {
                stringBuilder.append(string);
            }
            temp.add(stringBuilder.toString());
        }
        return temp;
    }

    /**
     * 46. 全排列
     * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
     */
    List<List<Integer>> ans46List = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        if (nums.length == 0) {
            return ans46List;
        }

        List<Integer> list = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        dfs(nums, 0, list, used);
        return ans46List;
    }

    public void dfs(int[] nums, int index, List<Integer> list, boolean[] used) {
        if (index == nums.length) {
            ans46List.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }

            list.add(nums[i]);
            used[i] = true;
            dfs(nums, index + 1, list, used);
            list.remove(list.size() - 1);
            used[i] = false;
        }
    }

    /**
     * 47. 全排列 II
     * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
     */
    List<List<Integer>> ans47List = new ArrayList<>();
    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums.length == 0) {
            return ans47List;
        }

        Arrays.sort(nums);
        List<Integer> list = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        dfsBy47(nums, 0, list, used);
        return ans47List;
    }

    public void dfsBy47(int[] nums, int index, List<Integer> list,boolean[] used) {
        if (index == nums.length) {
            ans47List.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i] || ( i > 0 && nums[i] == nums[i - 1] && !used[i - 1])) {
                continue;
            }

            list.add(nums[i]);
            used[i] = true;
            dfsBy47(nums, index + 1, list, used);
            list.remove(list.size() - 1);
            used[i] = false;
        }
    }

    /**
     * 60. 第k个排列
     * 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。
     * 给定 n 和 k，返回第 k 个排列。
     */

    int[] fc;
    boolean[] used;
    int k;
    int n;

    public String getPermutation(int n, int k) {
        this.n = n;
        this.k = k;
        used = new boolean[n + 1];
        fc = new int[n + 1];
        fc[0] = 1;
        for (int i = 1; i <= n; i++) {
            fc[i] = fc[i - 1] * i;
        }

        StringBuilder sb = new StringBuilder();
        dfs(0, sb);
        return sb.toString();
    }

    /**
     * 在这一步之前已经选择了几个数字，其值恰好等于这一步需要确定的下标位置
     */
    public void dfs(int index, StringBuilder path) {
        if (index == n) {
            return;
        }

        // // 计算还未确定的数字的全排列的个数
        int cnt = fc[n - 1 - index];
        for (int i = 1; i <= n; i++) {
            if (used[i]) {
                continue;
            }

            if (cnt < k) {
                k -= cnt;
                continue;
            }

            path.append(i);
            used[i] = true;
            dfs(index + 1, path);
            // 注意 1：没有回溯（状态重置）的必要

            // 注意 2：这里要加 return，后面的数没有必要遍历去尝试了
            return;
        }
    }

    /**
     * 841. 钥匙和房间
     * 有 N 个房间，开始时你位于 0 号房间。每个房间有不同的号码：0，1，2，...，N-1，并且房间里可能有一些钥匙能使你进入下一个房间。
     * 在形式上，对于每个房间 i 都有一个钥匙列表 rooms[i]，每个钥匙 rooms[i][j] 由 [0,1，...，N-1] 中的一个整数表示，
     * 其中 N = rooms.length。 钥匙 rooms[i][j] = v 可以打开编号为 v 的房间。
     * 最初，除 0 号房间外的其余所有房间都被锁住。
     * 你可以自由地在房间之间来回走动。
     * 如果能进入每个房间返回 true，否则返回 false。
     */
    boolean[] visit;
    int roomVisitNum = 0;
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        if(rooms.isEmpty()){
            return true;
        }
        visit = new boolean[rooms.size()];
        dfs(rooms,0);
        return roomVisitNum == rooms.size();
    }

    public void dfs(List<List<Integer>> rooms,int roomNum){
        visit[roomNum] = true;
        roomVisitNum++;
        for (Integer room : rooms.get(roomNum)) {
            if(!visit[room]){
                dfs(rooms,room);
            }
        }
    }
}
