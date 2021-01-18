package com.mh.leetcode;

import java.util.*;

/**
 * ClassName：
 * Time：20/8/20 上午9:57
 * Description：图相关问题
 *
 * @author mh
 */
public class MapProblem {

    public static void main(String[] args) {
        MapProblem mapProblem = new MapProblem();
//        char[][] board = {{'E','E','E','E','E'},{'E','E','M','E','E'},{'E','E','E','E','E'},{'E','E','E','E','E'}};
////        char[][] chars = mapProblem.updateBoard(board, new int[]{3, 0});
////        System.out.println(chars);
//        List<List<String>> tickets = new ArrayList<>();
//        tickets.add(Arrays.asList("JFK","SFO"));
//        tickets.add(Arrays.asList("JFK","ATL"));
//        tickets.add(Arrays.asList("SFO","ATL"));
//        tickets.add(Arrays.asList("ATL","JFK"));
//        tickets.add(Arrays.asList("ATL","SFO"));
//        mapProblem.findItinerary(tickets);

//        System.out.println(Arrays.toString(mapProblem.findRedundantConnection(new int[][]{{1,2},{1,3},{2,3}})));
//        System.out.println(Arrays.toString(mapProblem.findRedundantDirectedConnection(new int[][]{{1, 2}, {1, 3}, {2, 3}})));
        List<List<String>> e = new ArrayList<>();
        e.add(Arrays.asList("a","b"));
        e.add(Arrays.asList("b","c"));

        double[] value = new double[e.size()];
        value[0] = 2.0;
        value[1] = 3.0;

        List<List<String>> q = new ArrayList<>();
        q.add(Arrays.asList("a","c"));
        q.add(Arrays.asList("b","a"));
        q.add(Arrays.asList("a","e"));
        q.add(Arrays.asList("a","a"));
        q.add(Arrays.asList("x","x"));
        System.out.println(Arrays.toString(mapProblem.calcEquation(e, value, q)));
    }

    /**
     * 529 扫雷游戏
     * 给定一个代表游戏板的二维字符矩阵。
     *  'M' 代表一个未挖出的地雷，
     * 'E' 代表一个未挖出的空方块，
     * 'B' 代表没有相邻（上，下，左，右，和所有4个对角线）地雷的已挖出的空白方块，
     * 数字（'1' 到 '8'）表示有多少地雷与这块已挖出的方块相邻，
     * 'X' 则表示一个已挖出的地雷。
     *
     * 现在给出在所有未挖出的方块中（'M'或者'E'）的下一个点击位置（行和列索引），根据以下规则，返回相应位置被点击后对应的面板：
     *
     * 1.如果一个地雷（'M'）被挖出，游戏就结束了- 把它改为 'X'。
     * 2.如果一个没有相邻地雷的空方块（'E'）被挖出，修改它为（'B'），并且所有和其相邻的未挖出方块都应该被递归地揭露。
     * 3.如果一个至少与一个地雷相邻的空方块（'E'）被挖出，修改它为数字（'1'到'8'），表示相邻地雷的数量。
     * 4.如果在此次点击中，若无更多方块可被揭露，则返回面板。
     *
     */
    /**
     * 为了搜索点击处旁边的8个方向方格
     * 分别为上，下，左，右，左上，左下，右上，右下
     */
    int[] numsX = new int[]{1, -1, 0, 0, -1, 1, -1, 1}, numsY = new int[]{0, 0, -1, 1, -1, -1, 1, 1};

    public char[][] updateBoard(char[][] board, int[] click) {
        int x = click[0];
        int y = click[1];
        if (board[x][y] == 'M') {
            board[x][y] = 'X';
        } else {
            dfs(board, x, y);
        }
        return board;
    }

    /**
     * DFS (Deep First Search) 深度优先遍历
     * 顾名思义，这种遍历方法是以深度为优先进行对图的搜索或者遍历，至于什么是以深度为优先条件，先看下面DFS的基本步骤：
     * 从当前节点开始，先标记当前节点，再寻找与当前节点相邻，且未标记过的节点：
     * （1）: 当前节点不存在下一个节点，则返回前一个节点进行DFS
     * （2）: 当前节点存在下一个节点，则从下一个节点进行DFS
     */
    private void dfs(char[][] board, int x, int y) {
        int boomCount = 0;
        for (int i = 0; i < numsX.length; i++) {
            // 循环搜索点击处旁边的方格
            int tempX = x + numsX[i];
            int tempY = y + numsY[i];
            if (tempX < 0 || tempX >= board.length || tempY < 0 || tempY >= board[0].length) {
                continue;
            }
            // 查找点击处周围八个方格的地雷数
            if (board[tempX][tempY] == 'M') {
                boomCount++;
            }
        }

        if (boomCount > 0) {
            // 有地雷，走规则3
            board[x][y] = (char) (boomCount + '0');
        } else {
            // 没有地雷将点击处变为已点击，利用dfs处理附近的方格
            board[x][y] = 'B';
            for (int i = 0; i < numsX.length; i++) {
                // 循环搜索点击处旁边的方格
                int tempX = x + numsX[i];
                int tempY = y + numsY[i];
                if (tempX < 0 || tempX >= board.length || tempY < 0 || tempY >= board[0].length || board[tempX][tempY] != 'E') {
                    continue;
                }
                dfs(board, tempX, tempY);
            }
        }
    }

    /**
     * 332. 重新安排行程
     * 给定一个机票的字符串二维数组 [from, to]，子数组中的两个成员分别表示飞机出发和降落的机场地点，对该行程进行重新规划排序。所有这些机票都属于一个从 JFK（肯尼迪国际机场）出发的先生，所以该行程必须从 JFK 开始。
     * <p>
     * 说明:
     * <p>
     * 如果存在多种有效的行程，你可以按字符自然排序返回最小的行程组合。例如，行程 ["JFK", "LGA"] 与 ["JFK", "LGB"] 相比就更小，排序更靠前
     * 所有的机场都用三个大写字母表示（机场代码）。
     * 假定所有机票至少存在一种合理的行程。
     * 示例 1:
     * <p>
     * 输入: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
     * 输出: ["JFK", "MUC", "LHR", "SFO", "SJC"]
     */
    Map<String, PriorityQueue<String>> paramMap = new HashMap<>();
    LinkedList<String> result = new LinkedList<>();

    public List<String> findItinerary(List<List<String>> tickets) {
        for (List<String> ticket : tickets) {
            String from = ticket.get(0), to = ticket.get(1);
            if (!paramMap.containsKey(from)) {
                paramMap.put(from, new PriorityQueue<>());
            }
            paramMap.get(from).offer(to);
        }
        dfs("JFK");
        return result;
    }

    private void dfs(String curr) {
        while (paramMap.containsKey(curr) && paramMap.get(curr).size() > 0) {
            String temp = paramMap.get(curr).poll();
            dfs(temp);
        }
        result.addFirst(curr);
    }

    /**
     * 684. 冗余连接
     * 在本问题中, 树指的是一个连通且无环的无向图。
     * 输入一个图，该图由一个有着N个节点 (节点值不重复1, 2, ..., N) 的树及一条附加的边构成。
     * 附加的边的两个顶点包含在1到N中间，这条附加的边不属于树中已存在的边。
     * 结果图是一个以边组成的二维数组。每一个边的元素是一对[u, v] ，满足 u < v，表示连接顶点u 和v的无向图的边。
     * 返回一条可以删去的边，使得结果图是一个有着N个节点的树。如果有多个答案，则返回二维数组中最后出现的边。
     * 答案边 [u, v] 应满足相同的格式 u < v。
     * <p>
     * 输入: [[1,2], [2,3], [3,4], [1,4], [1,5]]
     * 输出: [1,4]
     * 解释: 给定的无向图为:
     * 5 - 1 - 2
     * |   |
     * 4 - 3
     * <p>
     * 使用并查集算法，找各个节点的共同节点。如果[u,v]中两个节点是同一个节点，便形成了一个环，所以[u,v]为答案
     */
    public int[] findRedundantConnection(int[][] edges) {
        int[] index = new int[edges.length + 1];
        int[] ans = new int[2];
        // 初始化各个节点，索引等于自身
        for (int i = 0; i < index.length; i++) {
            index[i] = i;
        }

        for (int[] edge : edges) {
            int xFather = find(index, edge[0]);
            int yFather = find(index, edge[1]);
            if (xFather != yFather) {
                index[xFather] = yFather;
            } else {
                ans = edge;
                break;
            }

        }
        return ans;
    }

    public int find(int[] index, int x) {
        while (index[x] != x) {
            x = index[x];
        }
        return x;
    }

    /**
     * 685. 冗余连接 II
     * 与684差不多，不过该题为有向图。
     * 树中的每个节点都有一个父节点，除了根节点没有父节点。在多了一条附加的边之后，可能有以下两种情况：
     * <p>
     * 附加的边指向根节点，则包括根节点在内的每个节点都有一个父节点，此时图中一定有环路；
     * <p>
     * 附加的边指向非根节点，则恰好有一个节点（即被附加的边指向的节点）有两个父节点，此时图中可能有环路也可能没有环路。
     */
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int nodes = edges.length;
        // 记录每个节点的父节点,看该节点是否有两个父节点，如果是则为冲突边
        int[] parent = new int[nodes + 1];

        // 记录并集，查看哪条边导致回环
        int[] ancestor = new int[nodes + 1];

        for (int i = 1; i <= nodes; i++) {
            parent[i] = i;
            ancestor[i] = i;
        }

        int conflict = -1;
        int cycle = -1;
        for (int i = 0; i < edges.length; i++) {
            int node1 = edges[i][0], node2 = edges[i][1];
            // 判断子节点是否已经有父节点，说明node2有两个父节点，该边为冲突边
            if (parent[node2] != node2) {
                conflict = i;
            } else {
                // 更改node2的父节点为node1
                parent[node2] = node1;
                // 判断是否有回环路，没有则合并集合
                int x = find(ancestor, node1);
                int y = find(ancestor, node2);
                if (x == y) {
                    cycle = i;
                } else {
                    ancestor[x] = y;
                }
            }

        }

        //同一条边不可能同时被记为导致冲突的边和导致环路出现的边。如果访问到的边确实同时导致冲突和环路出现，则这条边被记为导致冲突的边。
        if (conflict < 0) {
            // 没有冲突的边，则附加的边一定为回环的边
            return edges[cycle];
        } else {
            // 如果有冲突的边，需要通过判断是否有导致环路的边决定哪条边是附加的边。
            if (cycle >= 0) {
                // 如果有回环的边，则返回[parent[v],v]
                return new int[]{parent[edges[conflict][1]], edges[conflict][1]};
            } else {
                // 如果有冲突的边，无回环的边，则附加的边是后被访问到的指向 v 的边
                return edges[conflict];
            }
        }
    }

    /**
     * 399. 除法求值
     * 给你一个变量对数组 equations 和一个实数值数组 values 作为已知条件，
     * 其中 equations[i] = [Ai, Bi] 和 values[i] 共同表示等式 Ai / Bi = values[i] 。每个 Ai 或 Bi 是一个表示单个变量的字符串。
     * 另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，请你根据已知条件找出 Cj / Dj = ? 的结果作为答案。
     * 返回 所有问题的答案 。如果存在某个无法确定的答案，则用 -1.0 替代这个答案。
     */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        double[] ans = new double[queries.size()];
        int index = 0;
        Map<String, Integer> map = new HashMap<>();
        for (List<String> equation : equations) {
            String s = equation.get(0);
            String s1 = equation.get(1);
            if (!map.containsKey(s)) {
                map.put(s, index++);
            }
            if (!map.containsKey(s1)) {
                map.put(s1, index++);
            }
        }
        int[] parent = new int[index];
        // 权重 表示 weight[a] 表示 a / parent[a] 的值
        double[] weight = new double[index];
        Arrays.fill(weight, 1.0);
        for (int i = 0; i < index; i++) {
            parent[i] = i;
        }
        // 2. 更新并查集权重
        int n = equations.size();
        for (int i = 0; i < n; i++) {
            int a = map.get(equations.get(i).get(0));
            int b = map.get(equations.get(i).get(1));
            int aParent = find(parent,weight,a);

            // 建立 root_a -> b 的连接 并更新权重
            parent[aParent] = b;
            // values[i]: a->b权重（题目给的）
            // weights[a]: a -> aParent 权重(已知)
            // weights[aParent]: aParent->b 权重（待求)
            weight[aParent] = values[i] / weight[a];
        }
        // 3. 计算答案
        int qLen = queries.size();
        for (int i = 0; i < qLen; i++) {
            String s = queries.get(i).get(0);
            String s1 = queries.get(i).get(1);
            if(!map.containsKey(s) || !map.containsKey(s1)){
                ans[i] = -1.0;
            }else if(find(parent,weight,map.get(s)) != find(parent,weight,map.get(s1))){
                ans[i] = -1.0;
            }else {
                // 以 a->c->d  b->d  为例 a-b的距离就是  a-d / b-d
                // weights[a]:a-d    weights[b]: b-d
                ans[i] = weight[map.get(s)] / weight[map.get(s1)];
            }
        }
        return ans;
    }

    public int find(int[] parents,double[] weights, int count) {
        if (parents[count] != count) {
            // 顺序不能错  不能写成
            // weights[a] = weights[a] * weights[parents[a]];
            // parents[a] = find(parents[a]); 会少一次b权重更新的过程 结果会出错
            // 因为 a的权重依赖于b的权重
            int temp = find(parents,weights,parents[count]);
            // a->c 的权重 =  a->b 的权重 *  b->c 的权重
            weights[count] = weights[count] * weights[parents[count]];
            // a->c 连接
            parents[count] = temp;
        }
        // 返回a的母结点
        return parents[count];
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

        int[] parents = new int[n];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if(isConnected[i][j] == 1){
                    merge(parents,i,j);
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < parents.length; i++) {
            if(parents[i] == i){
                ans++;
            }
        }
        return ans;
    }

    public void merge(int[] parents,int i,int j){
        parents[find(parents,i)] = find(parents,j);
    }

}
