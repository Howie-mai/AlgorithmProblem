package com.mh.leetcode;

import com.mh.leetcode.bean.TreeNode;
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
//        System.out.println(backtrack.subsets(new int[]{1, 1, 2, 3}));
//        System.out.println(backtrack.canFinish(3,new int[][]{ {1,0},{2,0} }));
//        System.out.println(backtrack.permute(new int[]{9, 8, 7, 6, 5, 4}));
        System.out.println(Arrays.toString(backtrack.sumOfDistancesInTree(6, new int[][]{{0, 1}, {0, 2}, {2, 3}, {2, 4}, {2, 5}})));
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
        backTrackBy37(board, 0, 0);
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
        if (board[row][col] != '.') {
            return backTrackBy37(board, row, col + 1);
        }

        // 循环1-9
        for (char ch = '1'; ch <= '9'; ch++) {
            if (!isVaild(board, row, col, ch)) {
                continue;
            }
            // 做选择
            board[row][col] = ch;
            if (backTrackBy37(board, row, col + 1)) {
                return true;
            }
            board[row][col] = '.';
        }
        return false;
    }

    public boolean isVaild(char[][] board, int row, int col, char ch) {
        for (int i = 0; i < 9; i++) {
            // 判断行有没有重复字符
            if (board[row][i] == ch) {
                return false;
            }
            // 判断列有没有重复字符
            if (board[i][col] == ch) {
                return false;
            }

            // 判断九宫格有没有重复字符
            if (board[(row / 3) * 3 + i / 3][(col / 3) * 3 + i % 3] == ch) {
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
        if (index == nums.length && list.get(list.size() - 1) % 2 != 0) {
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

    public void dfsBy47(int[] nums, int index, List<Integer> list, boolean[] used) {
        if (index == nums.length) {
            ans47List.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i] || (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])) {
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
        if (rooms.isEmpty()) {
            return true;
        }
        visit = new boolean[rooms.size()];
        dfs(rooms, 0);
        return roomVisitNum == rooms.size();
    }

    public void dfs(List<List<Integer>> rooms, int roomNum) {
        visit[roomNum] = true;
        roomVisitNum++;
        for (Integer room : rooms.get(roomNum)) {
            if (!visit[room]) {
                dfs(rooms, room);
            }
        }
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
        while (!queue.isEmpty()) {
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                String cur = queue.poll();

                if (cur.equals(target)) {
                    return step;
                }

                for (int j = 0; j < 4; j++) {
                    String up = plusOne(cur, j);
                    if (!visited.contains(up)) {
                        visited.add(up);
                        queue.add(up);
                    }
                    String down = minusOne(cur, j);
                    if (!visited.contains(down)) {
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

    /**
     * 78. 子集
     * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
     */
    public List<List<Integer>> subsets(int[] nums) {
        Deque<Integer> deque = new ArrayDeque<>();
//        boolean[] used = new boolean[nums.length];
//        Arrays.sort(nums);
//        ansBy78.add(new ArrayList<>());
        dfsBy78(nums, deque, used, 0);
        return ansBy78;
    }

    List<List<Integer>> ansBy78 = new ArrayList<>();

    public void dfsBy78(int[] nums, Deque<Integer> deque, boolean[] used, int index) {
        if (index == nums.length) {
            ansBy78.add(new ArrayList<>(deque));
            return;
        }

        deque.addLast(nums[index]);
        dfsBy78(nums, deque, used, index + 1);
        deque.removeLast();
        dfsBy78(nums, deque, used, index + 1);
    }

    /**
     * 207. 课程表
     * 你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1 。
     * <p>
     * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1]
     */
    public boolean backTrackBy207(List<List<Integer>> adjacency, int[] learn, int i) {
        /**
         * 借助一个标志列表 flags，用于判断每个节点 i （课程）的状态：
         * 未被 DFS 访问：i == 0；
         * 已被其他节点启动的 DFS 访问：i == -1；
         * 已被当前节点启动的 DFS 访问：i == 1。
         */

        // 当 flag[i] == -1，说明当前访问节点已被其他节点启动的 DFS 访问，无需再重复搜索，直接返回 true。
        if (learn[i] == -1) {
            return true;
        }

        // 当 flag[i] == 1，说明在本轮 DFS 搜索中节点 i 被第 22 次访问，即 课程安排图有环 ，直接返回 False。
        if (learn[i] == 1) {
            return false;
        }

        learn[i] = 1;
        for (Integer index : adjacency.get(i)) {
            if (!backTrackBy207(adjacency, learn, index)) {
                return false;
            }
        }
        learn[i] = -1;
        return true;
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {

        List<List<Integer>> adjacency = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjacency.add(new ArrayList<>());
        }

        int[] flags = new int[numCourses];
        for (int[] cp : prerequisites) {
            adjacency.get(cp[1]).add(cp[0]);
        }
        for (int i = 0; i < numCourses; i++) {
            if (!backTrackBy207(adjacency, flags, i)) {
                return false;
            }
        }
        return true;
//        /**
//         * 使用广度优先解决
//         */
//        if(numCourses <= 0){
//            return false;
//        }
//
//        int len = prerequisites.length;
//
//        // 记录入度
//        int[] inDegree = new int[numCourses];
//        // 记录 prerequisite[0] -> prerequisite[1]
//        List<Set<Integer>> list = new ArrayList<>();
//        for (int i = 0; i < numCourses; i++) {
//            list.add(new HashSet<>());
//        }
//
//        for (int[] prerequisite : prerequisites) {
//            inDegree[prerequisite[0]]++;
//            list.get(prerequisite[1]).add(prerequisite[0]);
//        }
//
//        Deque<Integer> deque = new LinkedList<>();
//        for (int i = 0; i < inDegree.length; i++) {
//            if(inDegree[i] == 0){
//                deque.add(i);
//            }
//        }
//
//        int cnt = 0;
//        while (!deque.isEmpty()){
//            Integer poll = deque.poll();
//            cnt++;
//            for (Integer course : list.get(poll)) {
//                inDegree[course]--;
//                if(inDegree[course] == 0){
//                    deque.add(course);
//                }
//            }
//        }
//
//        return cnt == numCourses;
    }

    /**
     * 834. 树中距离之和
     * 给定一个无向、连通的树。树中有 N 个标记为 0...N-1 的节点以及 N-1 条边 。
     * <p>
     * 第 i 条边连接节点 edges[i][0] 和 edges[i][1] 。
     * <p>
     * 返回一个表示节点 i 与其他所有节点距离之和的列表 ans。
     */
    public int[] sumOfDistancesInTree(int N, int[][] edges) {

        for(int i = 0; i < N; i++) {
            graph.add(new ArrayList<Integer>());
        }
        for (int[] edge : edges) {
            int src = edge[0];
            int dst = edge[1];
            graph.get(src).add(dst);
            graph.get(dst).add(src);
        }
        distSum = new int[N];
        nodeNum = new int[N];
        Arrays.fill(nodeNum, 1);
        postOrder(0, -1);
        preOrder(0, -1);
        return distSum;
    }
    /**
     * 邻接表
     */
    private List<List<Integer>> graph = new ArrayList<>();
    /**
     * 距离和
     */
    int[] distSum;
    /**
     * 子树节点个数（包括自己）
     */
    int[] nodeNum;

    //求root到子树所有节点的距离和
    private void postOrder(int root, int parent) {
        List<Integer> neighbors = graph.get(root);
        for(Integer neighbor : neighbors) {
            if(neighbor == parent){
                //如果邻接点是父节点，则跳过
                continue;
            }
            postOrder(neighbor, root);
            nodeNum[root] += nodeNum[neighbor];
            distSum[root] += distSum[neighbor] + nodeNum[neighbor];
        }
    }
    //根据root计算其邻居到所在子树之外的节点的距离和（包括root节点）
    private void preOrder(int root, int parent) {
        List<Integer> neighbors = graph.get(root);
        for(Integer neighbor : neighbors) {
            if(neighbor == parent){
                continue;
            }
            distSum[neighbor] = distSum[root] - nodeNum[neighbor] + (graph.size() - nodeNum[neighbor]);
            preOrder(neighbor, root);
        }
    }

    /**
     * 337. 打家劫舍 III
     * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。
     * 这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。
     * 一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
     * 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
     * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
     */
    public int rob(TreeNode root) {
//        int[] ans = dp(root);
//        return Math.max(ans[0],ans[1]);

        // 备忘录解法
        if(root == null){
            return 0;
        }
        if(map.containsKey(root)){
            return map.get(root);
        }

        // 抢这家,去下下家
        int yesRob = root.val
                + ( root.left == null ? 0 : rob(root.left.left) + rob(root.left.right))
                + ( root.right == null ? 0 : rob(root.right.left) + rob(root.right.right));

        // 不抢这家，去左右两边下家
        int noRob = rob(root.right) + rob(root.left);

        int res = Math.max(yesRob,noRob);
        map.put(root,res);
        return res;

    }
    Map<TreeNode,Integer> map = new HashMap<>();

    public int[] dp(TreeNode node){
        // int[0] 表示这个节点不被抢的收益 int[1] 被抢的收益
        if(node == null){
            return new int[]{0,0};
        }

        // 左节点
        int[] left = dp(node.left);
        int[] right = dp(node.right);

        // 抢这个节点
        int yesRob = node.val + left[0] + right[0];

        // 不抢这个 判断左边 和 右边的情况哪个大
        int noRob = Math.max(left[0],left[1]) + Math.max(right[0],right[1]);

        return new int[]{noRob,yesRob};
    }

    /**
     * 547. 省份数量
     * 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，
     * 且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
     * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
     * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，
     * 而 isConnected[i][j] = 0 表示二者不直接相连。
     * 返回矩阵中 省份 的数量
     */
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        int ans = 0;
        int[] visited = new int[n];
        for (int i = 0; i < isConnected.length; i++) {
            if(visited[i] == 0){
                backTrackBy547(isConnected,visited,i);
                ans++;
            }
        }
        return ans;
    }

    public void backTrackBy547(int[][] isConnected,int[] visted,int index){
        for (int i = 0; i < isConnected[index].length; i++) {
            if(isConnected[index][i] == 1 && visted[i] == 0){
                visted[i] = 1;
                backTrackBy547(isConnected,visted,i);
            }
        }
    }

}
