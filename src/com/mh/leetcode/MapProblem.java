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
        char[][] board = {{'E','E','E','E','E'},{'E','E','M','E','E'},{'E','E','E','E','E'},{'E','E','E','E','E'}};
//        char[][] chars = mapProblem.updateBoard(board, new int[]{3, 0});
//        System.out.println(chars);
        List<List<String>> tickets = new ArrayList<>();
        tickets.add(Arrays.asList("JFK","SFO"));
        tickets.add(Arrays.asList("JFK","ATL"));
        tickets.add(Arrays.asList("SFO","ATL"));
        tickets.add(Arrays.asList("ATL","JFK"));
        tickets.add(Arrays.asList("ATL","SFO"));
        mapProblem.findItinerary(tickets);
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
    int[] numsX = new int[]{1,-1,0,0,-1,1,-1,1},numsY = new int[]{0,0,-1,1,-1,-1,1,1};
    public char[][] updateBoard(char[][] board, int[] click) {
        int x = click[0];
        int y = click[1];
        if(board[x][y] == 'M'){
            board[x][y] = 'X';
        }else {
            dfs(board,x,y);
        }
        return board;
    }

    /**
     * DFS (Deep First Search) 深度优先遍历
     * 顾名思义，这种遍历方法是以深度为优先进行对图的搜索或者遍历，至于什么是以深度为优先条件，先看下面DFS的基本步骤：
     * 从当前节点开始，先标记当前节点，再寻找与当前节点相邻，且未标记过的节点：
     *（1）: 当前节点不存在下一个节点，则返回前一个节点进行DFS
     *（2）: 当前节点存在下一个节点，则从下一个节点进行DFS
     */
    private void dfs(char[][] board, int x, int y) {
        int boomCount = 0;
        for(int i = 0;i < numsX.length;i++){
            // 循环搜索点击处旁边的方格
            int tempX = x + numsX[i];
            int tempY = y + numsY[i];
            if(tempX < 0 || tempX >= board.length || tempY < 0 || tempY >= board[0].length){
                continue;
            }
            // 查找点击处周围八个方格的地雷数
            if(board[tempX][tempY] == 'M'){
                boomCount++;
            }
        }

        if(boomCount > 0){
            // 有地雷，走规则3
            board[x][y] = (char) (boomCount + '0');
        }else {
            // 没有地雷将点击处变为已点击，利用dfs处理附近的方格
            board[x][y] = 'B';
            for(int i = 0;i < numsX.length;i++){
                // 循环搜索点击处旁边的方格
                int tempX = x + numsX[i];
                int tempY = y + numsY[i];
                if(tempX < 0 || tempX >= board.length || tempY < 0 || tempY >= board[0].length || board[tempX][tempY] != 'E'){
                    continue;
                }
                dfs(board,tempX,tempY);
            }
        }
    }

    /**
     * 332. 重新安排行程
     * 给定一个机票的字符串二维数组 [from, to]，子数组中的两个成员分别表示飞机出发和降落的机场地点，对该行程进行重新规划排序。所有这些机票都属于一个从 JFK（肯尼迪国际机场）出发的先生，所以该行程必须从 JFK 开始。
     *
     * 说明:
     *
     * 如果存在多种有效的行程，你可以按字符自然排序返回最小的行程组合。例如，行程 ["JFK", "LGA"] 与 ["JFK", "LGB"] 相比就更小，排序更靠前
     * 所有的机场都用三个大写字母表示（机场代码）。
     * 假定所有机票至少存在一种合理的行程。
     * 示例 1:
     *
     * 输入: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
     * 输出: ["JFK", "MUC", "LHR", "SFO", "SJC"]
     */
    Map<String, PriorityQueue<String>> paramMap = new HashMap<>();
    LinkedList<String> result = new LinkedList<>();
    public List<String> findItinerary(List<List<String>> tickets) {
        for (List<String> ticket : tickets) {
            String from = ticket.get(0),to = ticket.get(1);
            if(!paramMap.containsKey(from)){
                paramMap.put(from,new PriorityQueue<>());
            }
            paramMap.get(from).offer(to);
        }
        dfs("JFK");
        return result;
    }

    private void dfs(String curr) {
        while(paramMap.containsKey(curr) && paramMap.get(curr).size() > 0){
            String temp = paramMap.get(curr).poll();
            dfs(temp);
        }
        result.addFirst(curr);
    }
}
