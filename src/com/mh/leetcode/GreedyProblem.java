package com.mh.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * ClassName：
 * Time：2020/11/23 1:57 下午
 * Description：
 *
 * @author mh
 */
public class GreedyProblem {

    public static void main(String[] args) {
        GreedyProblem greedyProblem = new GreedyProblem();
        greedyProblem.reorganizeString("aab");
    }
    /**
     * 452. 用最少数量的箭引爆气球
     * 在二维空间中有许多球形的气球。对于每个气球，提供的输入是水平方向上，气球直径的开始和结束坐标。
     * 由于它是水平的，所以纵坐标并不重要，因此只要知道开始和结束的横坐标就足够了。开始坐标总是小于结束坐标。
     * <p>
     * 一支弓箭可以沿着 x 轴从不同点完全垂直地射出。
     * 在坐标 x 处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend， 且满足  xstart ≤ x ≤ xend，则该气球会被引爆。
     * 可以射出的弓箭的数量没有限制。 弓箭一旦被射出之后，可以无限地前进。我们想找到使得所有气球全部被引爆，所需的弓箭的最小数量。
     * <p>
     * 给你一个数组 points ，其中 points [i] = [xstart,xend] ，返回引爆所有气球所必须射出的最小弓箭数。
     */
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) {
            return 0;
        }
        Arrays.sort(points, Comparator.comparingInt(point -> point[1]));
        int flag = points[0][1];
        int ans = 1;
        for (int[] balloon : points) {
            if (balloon[0] > flag) {
                flag = balloon[1];
                ++ans;
            }
        }
        return ans;
    }

    /**
     * 767. 重构字符串
     * 给定一个字符串S，检查是否能重新排布其中的字母，使得两相邻的字符不同。
     *
     * 若可行，输出任意可行的结果。若不可行，返回空字符串。
     */
    public String reorganizeString(String s) {
        int[] count = new int[26];
        char[] chars = s.toCharArray();
        for (char ch : chars) {
            count[ch - 'a']++;
        }

        PriorityQueue<Character> priorityQueue = new PriorityQueue<>((o1, o2) -> Integer.compare(count[o2 - 'a'], count[o1 - 'a']));
        for (int i = 0;i < count.length;i++){
            if(count[i] > 0){
                priorityQueue.offer((char)(i + 'a'));
            }
        }

        if(count[priorityQueue.peek() - 'a'] > (s.length() + 1) / 2){
            return "";
        }

        StringBuilder sb = new StringBuilder();
        while (priorityQueue.size() > 1){
            Character first = priorityQueue.poll();
            Character second = priorityQueue.poll();
            sb.append(first).append(second);
            count[first - 'a']--;
            count[second - 'a']--;
            if(count[first - 'a'] > 0){
                priorityQueue.offer(first);
            }
            if(count[second - 'a'] > 0){
                priorityQueue.offer(second);
            }
        }
        if(priorityQueue.size() > 0){
            sb.append(priorityQueue.poll());
        }
        return sb.toString();
    }
}
