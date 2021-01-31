package com.mh;

import com.mh.niuke.Edge;

import java.util.*;

/**
 * ClassName：
 * Time：20/9/4 下午10:20
 * Description：随机数生成算法
 *
 * @author mh
 */
public class Main8 {
    public static void main(String[] args) {
        Main8 main8 = new Main8();
        main8.minimumEffortPath(new int[][]{{1,2,2},{3,8,2},{5,3,5}});
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
        List<Edge> list = new LinkedList<>();
        int rows = heights.length;
        int colums = heights[0].length;
        int[] parent = new int[colums * rows];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < colums; j++) {
                if(j + 1 < colums){
                    list.add(new Edge(i * colums + j,i * colums + j + 1,Math.abs(heights[i][j] - heights[i][j + 1])));
                }
                if(i + 1 < rows){
                    list.add(new Edge(i * colums + j,(i + 1) * colums + j,Math.abs(heights[i][j] - heights[i + 1][j])));
                }
            }
        }

        list.sort(Comparator.comparingInt(edge -> edge.len));

        int ans = -1;
        for (Edge edge : list) {
            int xParent = find(parent,edge.i);
            int yParent = find(parent,edge.j);
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
    int n;
    int[] p = new int[2509];

    public int swimInWater(int[][] grid) {
        n = grid.length;
        List<int[]> edges =  new ArrayList<>();
        for (int i = 0; i < n ;i++) {
            for (int j = 0; j < n; j++) {
                int idx = getIndex(i, j);
                p[idx] = idx;
                if (i + 1 < n) edges.add(new int[]{idx, getIndex(i + 1, j), Math.max(grid[i][j], grid[i + 1][j])});
                if (j + 1 < n) edges.add(new int[]{idx, getIndex(i, j + 1), Math.max(grid[i][j], grid[i][j + 1])});
            }
        }
        edges.sort(Comparator.comparingInt(a -> a[2]));
        int start = getIndex(0, 0), end = getIndex(n - 1, n - 1);
        for (int[] edge : edges) {
            int a = edge[0], b = edge[1], w = edge[2];
            int aParent = find(p,a);
            int bParent = find(p,b);
            if(aParent != bParent){
                p[aParent] = p[bParent];
            }
            if (isSame(p,start, end)) return w;
        }
        return -1;
    }
    int getIndex(int i, int j) {
        return i * n + j;
    }

    public int find(int[] parent,int i){
        while (parent[i] != i){
            i = parent[i];
        }
        return i;
    }

    public boolean isSame(int[] parent, int i, int j){
        return find(parent,i) == find(parent,j);
    }
}
