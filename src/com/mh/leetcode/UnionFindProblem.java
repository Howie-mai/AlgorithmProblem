package com.mh.leetcode;

import java.util.*;

/**
 * ClassName：
 * Time：2021/1/18 9:48 上午
 * Description：
 *
 * @author mh
 */
public class UnionFindProblem {

    public static void main(String[] args) {
        UnionFindProblem problem = new UnionFindProblem();
        problem.minCostConnectPoints(new int[][]{{0,0},{2,2},{3,10},{5,2},{7,0}});
    }

    /**
     * 721. 账户合并
     * 给定一个列表 accounts，每个元素 accounts[i] 是一个字符串列表，
     * 其中第一个元素 accounts[i][0] 是 名称 (name)，其余元素是 emails 表示该账户的邮箱地址。
     * 现在，我们想合并这些账户。如果两个账户都有一些共同的邮箱地址，则两个账户必定属于同一个人。
     * 请注意，即使两个账户具有相同的名称，它们也可能属于不同的人，因为人们可能具有相同的名称。
     * 一个人最初可以拥有任意数量的账户，但其所有账户都具有相同的名称。
     * 合并账户后，按以下格式返回账户：每个账户的第一个元素是名称，其余元素是按顺序排列的邮箱地址。账户本身可以以任意顺序返回。
     */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        // 利用一个字符串的映射存储并查集
        Map<String, String> map = new HashMap<>();
        // 存储对应名字
        Map<String, String> names = new HashMap<>();
        for (List<String> account : accounts) {
            for (int i = 1; i < account.size(); i++) {
                if (!map.containsKey(account.get(i))) {
                    // 建立关系
                    map.put(account.get(i), account.get(i));
                    // 建立名字关系
                    names.put(account.get(i), account.get(0));
                }
                // 如果该用户有多个邮箱，建立并查集关系
                if (i > 1) {
                    map.put(findStrByMap(map, account.get(i)), findStrByMap(map, account.get(i - 1)));
                }
            }
        }

        Map<String, List<String>> temp = new HashMap<>();
        // 把相同用户整合起来
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String parent = findStrByMap(map, key);
            if (!temp.containsKey(parent)) {
                temp.put(parent, new ArrayList<>());
            }
            temp.get(parent).add(key);
        }

        List<List<String>> ans = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : temp.entrySet()) {
            String key = entry.getKey();
            List<String> list = temp.get(key);
            ans.add(list);
            Collections.sort(list);
            list.add(0, names.get(key));
        }
        return ans;
    }

    private String findStrByMap(Map<String, String> map, String s) {
        while (!map.get(s).equals(s)) {
            s = map.get(s);
        }
        return s;
    }

    private int findByInt(int[] parent, int s) {
        while (parent[s] != s) {
            s = parent[s];
        }
        return s;
    }

    /**
     * 面试题 17.07. 婴儿名字
     * 每年，政府都会公布一万个最常见的婴儿名字和它们出现的频率，也就是同名婴儿的数量。
     * 有些名字有多种拼法，例如，John 和 Jon 本质上是相同的名字，但被当成了两个名字公布出来。
     * 给定两个列表，一个是名字及对应的频率，另一个是本质相同的名字对。设计一个算法打印出每个真实名字的实际频率。
     * 注意，如果 John 和 Jon 是相同的，并且 Jon 和 Johnny 相同，则 John 与 Johnny 也相同，即它们有传递和对称性。
     * 在结果列表中，选择 字典序最小 的名字作为真实名字。
     */
    public String[] trulyMostPopular(String[] names, String[] synonyms) {
        Map<String, Integer> count = new HashMap<>();
        // 利用一个字符串的映射存储并查集
        Map<String, String> map = new HashMap<>();
        for (String name : names) {
            int beginIndex = name.indexOf("(");
            String key = name.substring(0, beginIndex);
            int endIndex = name.lastIndexOf(")");
            int num = Integer.parseInt(name.substring(beginIndex + 1, endIndex));
            map.put(key, key);
            count.put(key, num);
        }

        for (String synonym : synonyms) {
            String[] split = synonym.substring(1, synonym.length() - 1).split(",");
            if (!map.containsKey(split[0])) {
                map.put(split[0], split[0]);
            }
            if (!map.containsKey(split[1])) {
                map.put(split[1], split[1]);
            }
            map.put(findStrByMap(map, split[1]), findStrByMap(map, split[0]));
        }

        Map<String, List<String>> relation = new HashMap<>();
        // 把相同用户整合起来
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String parent = findStrByMap(map, key);
            if (!relation.containsKey(parent)) {
                relation.put(parent, new ArrayList<>());
            }
            relation.get(parent).add(key);
        }

        String[] ans = new String[relation.size()];
        int index = 0;
        for (Map.Entry<String, List<String>> entry : relation.entrySet()) {
            List<String> value = entry.getValue();
            Collections.sort(value);
            StringBuilder sb = new StringBuilder();
            sb.append(value.get(0)).append("(");
            int sum = 0;
            for (String s : value) {
                sum += count.getOrDefault(s, 0);
            }
            sb.append(sum).append(")");
            ans[index++] = sb.toString();
        }
        return ans;
    }

    /**
     * 1584. 连接所有点的最小费用
     * 给你一个points 数组，表示 2D 平面上的一些点，其中 points[i] = [xi, yi] 。
     * 连接点 [xi, yi] 和点 [xj, yj] 的费用为它们之间的 曼哈顿距离 ：|xi - xj| + |yi - yj| ，其中 |val| 表示 val 的绝对值。
     * 请你返回将所有点连接的最小总费用。只有任意两点之间 有且仅有 一条简单路径时，才认为所有点都已连接。
     */
    public int minCostConnectPoints(int[][] points) {
        int length = points.length;
        List<Edge> list = new LinkedList<>();
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                list.add(new Edge(i,j,getManhattanDistance(points[i][0],points[i][1],points[j][0],points[j][1])));
            }
        }

        int[] parent = new int[length];
        for (int i = 0; i < length; i++) {
            parent[i] = i;
        }
        list.sort(Comparator.comparingInt(o -> o.val));

        int ans = 0;
        while (length > 1){
            Edge edge = list.get(0);
            int xParent = findByInt(parent,edge.a);
            int yParent = findByInt(parent,edge.b);
            if(xParent != yParent){
                parent[xParent] = yParent;
                ans += edge.val;
                length--;
            }
            list.remove(0);
        }
        return ans;
    }

    public int getManhattanDistance(int x,int y,int x1,int y1){
        return Math.abs(y1 - y) + Math.abs(x1 - x);
    }

    /**
     * 1631. 最小体力消耗路径
     * 你准备参加一场远足活动。给你一个二维 rows x columns 的地图 heights ，
     * 其中 heights[row][col] 表示格子 (row, col) 的高度。一开始你在最左上角的格子 (0, 0) ，
     * 且你希望去最右下角的格子 (rows-1, columns-1) （注意下标从 0 开始编号）。
     * 你每次可以往 上，下，左，右 四个方向之一移动，你想要找到耗费 体力 最小的一条路径。
     * 一条路径耗费的 体力值 是路径上相邻格子之间 高度差绝对值 的 最大值 决定的。
     * 请你返回从左上角走到右下角的最小 体力消耗值 。
     */
    public int minimumEffortPath(int[][] heights) {
        List<com.mh.niuke.Edge> list = new LinkedList<>();
        int rows = heights.length;
        int colums = heights[0].length;
        int[] parent = new int[colums * rows];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < colums; j++) {
                if(j + 1 < colums){
                    list.add(new com.mh.niuke.Edge(i * colums + j,i * colums + j + 1,Math.abs(heights[i][j] - heights[i][j + 1])));
                }
                if(i + 1 < rows){
                    list.add(new com.mh.niuke.Edge(i * colums + j,(i + 1) * colums + j,Math.abs(heights[i][j] - heights[i + 1][j])));
                }
            }
        }

        list.sort(Comparator.comparingInt(edge -> edge.len));

        int ans = -1;
        for (com.mh.niuke.Edge edge : list) {
            int xParent = findByInt(parent,edge.i);
            int yParent = findByInt(parent,edge.j);
            if(xParent != yParent){
                parent[xParent] = yParent;
            }
            if(isSame(parent,0,rows * colums - 1)){
                ans = edge.len;
                break;
            }
        }
        return ans;
    }

    /**
     * 778. 水位上升的泳池中游泳
     * 在一个 N x N 的坐标方格 grid 中，每一个方格的值 grid[i][j] 表示在位置 (i,j) 的平台高度。
     * 现在开始下雨了。当时间为 t 时，此时雨水导致水池中任意位置的水位为 t 。
     * 你可以从一个平台游向四周相邻的任意一个平台，但是前提是此时水位必须同时淹没这两个平台。
     * 假定你可以瞬间移动无限距离，也就是默认在方格内部游动是不耗时的。当然，在你游泳的时候你必须待在坐标方格里面。
     * 你从坐标方格的左上平台 (0，0) 出发。最少耗时多久你才能到达坐标方格的右下平台 (N-1, N-1)？
     */
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        int[] p = new int[n * n];
        List<int[]> edges =  new ArrayList<>();
        for (int i = 0; i < n ;i++) {
            for (int j = 0; j < n; j++) {
                int idx = getIndex(n,i, j);
                p[idx] = idx;
                if (i + 1 < n){
                    edges.add(new int[]{idx, getIndex(n,i + 1, j), Math.max(grid[i][j], grid[i + 1][j])});
                }
                if (j + 1 < n){
                    edges.add(new int[]{idx, getIndex(n,i, j + 1), Math.max(grid[i][j], grid[i][j + 1])});
                }
            }
        }
        edges.sort(Comparator.comparingInt(a -> a[2]));
        int start = getIndex(n,0, 0), end = getIndex(n,n - 1, n - 1);
        for (int[] edge : edges) {
            int a = edge[0], b = edge[1], w = edge[2];
            int aParent = findByInt(p,a);
            int bParent = findByInt(p,b);
            if(aParent != bParent){
                p[aParent] = p[bParent];
            }
            if (isSame(p,start, end)){
                return w;
            }
        }
        return -1;
    }
    int getIndex(int n,int i, int j) {
        return i * n + j;
    }

    public boolean isSame(int[] parent, int i, int j){
        return findByInt(parent,i) == findByInt(parent,j);
    }
}

class Edge{
    int a;
    int b;
    int val;

    public Edge(int a, int b, int val) {
        this.a = a;
        this.b = b;
        this.val = val;
    }
}
