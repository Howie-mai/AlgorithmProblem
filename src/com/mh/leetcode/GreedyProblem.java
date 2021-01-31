package com.mh.leetcode;

import java.util.*;

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
        int[] nums1 = new int[]{2, 5, 6, 4, 4, 0};
        int[] nums2 = new int[]{7, 3, 8, 0, 6, 5, 7, 6, 2};
//        greedyProblem.reorganizeString("aab");
//        System.out.println(Arrays.toString(greedyProblem.getMaxSubsequence(new int[]{9, 1, 2, 5, 8, 3}, 1)));
//        System.out.println(Arrays.toString(greedyProblem.merge(nums1, nums2, 15)));
//        System.out.println(greedyProblem.removeKdigits("99899",1));
//        System.out.println(greedyProblem.monotoneIncreasingDigits(332));
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
     * <p>
     * 若可行，输出任意可行的结果。若不可行，返回空字符串。
     */
    public String reorganizeString(String s) {
        int[] count = new int[26];
        char[] chars = s.toCharArray();
        for (char ch : chars) {
            count[ch - 'a']++;
        }

        PriorityQueue<Character> priorityQueue = new PriorityQueue<>((o1, o2) -> Integer.compare(count[o2 - 'a'], count[o1 - 'a']));
        for (int i = 0; i < count.length; i++) {
            if (count[i] > 0) {
                priorityQueue.offer((char) (i + 'a'));
            }
        }

        if (count[priorityQueue.peek() - 'a'] > (s.length() + 1) / 2) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        while (priorityQueue.size() > 1) {
            Character first = priorityQueue.poll();
            Character second = priorityQueue.poll();
            sb.append(first).append(second);
            count[first - 'a']--;
            count[second - 'a']--;
            if (count[first - 'a'] > 0) {
                priorityQueue.offer(first);
            }
            if (count[second - 'a'] > 0) {
                priorityQueue.offer(second);
            }
        }
        if (priorityQueue.size() > 0) {
            sb.append(priorityQueue.poll());
        }
        return sb.toString();
    }

    /**
     * 321. 拼接最大数
     * 给定长度分别为 m 和 n 的两个数组，其元素由 0-9 构成，表示两个自然数各位上的数字。
     * 现在从这两个数组中选出 k (k <= m + n) 个数字拼接成一个新的数，要求从同一个数组中取出的数字保持其在原数组中的相对顺序。
     * <p>
     * 求满足该条件的最大数。结果返回一个表示该最大数的长度为 k 的数组。
     */
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int n = nums1.length, m = nums2.length;
        int[] maxSub = new int[k];
        // 从nums1中至少要拿start个，
        //      如果k-m小于0，说明nums2可以自己拿出k个，所以nums1最少拿出0个
        //      如果k-m大于0，说明nums2不可以自己拿出k个，至少要nums1拿出k-m个
        // 从nums1中最多只能拿end个
        //      如果nums1的长度比k大，可以从nums1拿k个，否则只能拿n个
        int start = Math.max(0, k - m), end = Math.min(k, n);
        for (int i = start; i <= end; i++) {
            // 从nums1拿i个，组成最大子序列
            int[] sub1 = getMaxSubsequence(nums1, i);
            // 从nums2拿k - i个，组成最大子序列
            int[] sub2 = getMaxSubsequence(nums2, k - i);
            // 合并sub1和sub2组成最大子序列
            int[] curSub = merge(sub1, sub2, k);
            // 比较当前的子序列跟目前最大的子序列
            if (compareSub(curSub,0 ,maxSub,0) > 0) {
                System.arraycopy(curSub, 0, maxSub, 0, k);
            }
        }
        return maxSub;
    }

    /**
     * 从数组里面拿k个数字，组成最大子序列
     */
    public int[] getMaxSubsequence(int[] nums, int k) {
        int length = nums.length;
        // 用数组假装栈
        int[] stack = new int[k];
        int remain = length - k;
        int top = -1;

        for (int num : nums) {
            while (top >= 0 && stack[top] < num && remain > 0) {
                top--;
                remain--;
            }
            if (top < k - 1) {
                stack[++top] = num;
            } else {
                remain--;
            }
        }
        return stack;
    }

    /**
     * 合并两个子序列
     */
    public int[] merge(int[] nums1, int[] nums2, int k) {
        int x = nums1.length, y = nums2.length;
        if (x == 0) {
            return nums2;
        }
        if (y == 0) {
            return nums1;
        }
        int mergeLength = x + y;
        int[] merged = new int[mergeLength];
        int index1 = 0, index2 = 0;
        for (int i = 0; i < mergeLength; i++) {
            if (compareSub(nums1, index1, nums2, index2) > 0) {
                merged[i] = nums1[index1++];
            } else {
                merged[i] = nums2[index2++];
            }
        }
        return merged;
    }

    /**
     * 比较从index1起的curr大还是从index2起的max大
     */
    public int compareSub(int[] curr, int index1, int[] max, int index2) {
        int n = curr.length,m = max.length;
        while (index1 < n && index2 < m) {
           int diff = curr[index1++] - max[index2++];
           if(diff != 0){
               return diff;
           }
        }
        return (n - index1) - (m - index2);
    }

    /**
     * 402. 移掉K位数字
     * 给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。
     *
     * 注意:
     *
     * num 的长度小于 10002 且 ≥ k。
     * num 不会包含任何前导零。
     */
    public String removeKdigits(String s, int k) {
        char[] chars = s.toCharArray();
        if(chars.length == k){
            return "0" ;
        }
        Deque<Character> deque = new LinkedList<>();

        for (char ch : chars) {
            while (!deque.isEmpty() && k > 0 && deque.peekLast() > ch) {
                deque.pollLast();
                k--;
            }
            deque.offerLast(ch);
        }

        for (;k > 0;k--){
            deque.pollLast();
        }

        StringBuilder sb = new StringBuilder();
        for (Character character : deque) {
            if(sb.length() == 0 && character == '0'){
                continue;
            }
            sb.append(character);
        }
        return sb.toString().length() == 0 ? "0" : sb.toString();
    }

    /**
     * 659. 分割数组为连续子序列
     * 给你一个按升序排序的整数数组 num（可能包含重复数字），请你将它们分割成一个或多个子序列，
     * 其中每个子序列都由连续整数组成且长度至少为 3 。
     *
     * 如果可以完成上述分割，则返回 true ；否则，返回 false 。
     */
    public boolean isPossible(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<Integer, Integer>();
        Map<Integer, Integer> endMap = new HashMap<Integer, Integer>();
        for (int x : nums) {
            int count = countMap.getOrDefault(x, 0) + 1;
            countMap.put(x, count);
        }
        for (int x : nums) {
            int count = countMap.getOrDefault(x, 0);
            if (count > 0) {
                int prevEndCount = endMap.getOrDefault(x - 1, 0);
                if (prevEndCount > 0) {
                    countMap.put(x, count - 1);
                    endMap.put(x - 1, prevEndCount - 1);
                    endMap.put(x, endMap.getOrDefault(x, 0) + 1);
                } else {
                    int count1 = countMap.getOrDefault(x + 1, 0);
                    int count2 = countMap.getOrDefault(x + 2, 0);
                    if (count1 > 0 && count2 > 0) {
                        countMap.put(x, count - 1);
                        countMap.put(x + 1, count1 - 1);
                        countMap.put(x + 2, count2 - 1);
                        endMap.put(x + 2, endMap.getOrDefault(x + 2, 0) + 1);
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 649. Dota2 参议院
     * Dota2 的世界里有两个阵营：Radiant(天辉)和 Dire(夜魇)
     * Dota2 参议院由来自两派的参议员组成。现在参议院希望对一个 Dota2 游戏里的改变作出决定。他们以一个基于轮为过程的投票进行。
     * 在每一轮中，每一位参议员都可以行使两项权利中的一项：
     * 禁止一名参议员的权利：
     * 参议员可以让另一位参议员在这一轮和随后的几轮中丧失所有的权利。
     * 宣布胜利：
     * 1 2 3 4 4 5 6
     * 如果参议员发现有权利投票的参议员都是同一个阵营的，他可以宣布胜利并决定在游戏中的有关变化。
     */
    public String predictPartyVictory(String senate) {
        int length = senate.length();
        char[] chars = senate.toCharArray();
        Deque<Integer> radiant = new ArrayDeque<>();
        Deque<Integer> dire = new ArrayDeque<>();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 'R'){
                radiant.offer(i);
            }else {
                dire.offer(i);
            }
        }

        while (!radiant.isEmpty() && !dire.isEmpty()){
            Integer radiantIndex = radiant.poll();
            Integer direIndex = dire.poll();
            if(radiantIndex < direIndex){
                radiant.offer(radiantIndex + length);
            }else {
                dire.offer(direIndex + length);
            }
        }
        return radiant.isEmpty() ? "Dire" : "Radiant";
    }

    /**
     * 738. 单调递增的数字
     * 给定一个非负整数 N，找出小于或等于 N 的最大的整数，同时这个整数需要满足其各个位数上的数字是单调递增。
     *
     * （当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。）
     */
    public int monotoneIncreasingDigits(int N) {
        char[] strN = Integer.toString(N).toCharArray();
        int i = 1;
        while (i < strN.length && strN[i - 1] <= strN[i]) {
            i += 1;
        }
        if (i < strN.length) {
            while (i > 0 && strN[i - 1] > strN[i]) {
                strN[i - 1]--;
                i--;
            }
            for (i += 1; i < strN.length; ++i) {
                strN[i] = '9';
            }
        }
        return Integer.parseInt(new String(strN));
    }

    /**
     * 135. 分发糖果
     * 老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。
     * 你需要按照以下要求，帮助老师给这些孩子分发糖果：
     * 每个孩子至少分配到 1 个糖果。
     * 相邻的孩子中，评分高的孩子必须获得更多的糖果。
     * 那么这样下来，老师至少需要准备多少颗糖果呢？
     */
    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] left = new int[n];
        for (int i = 0; i < n; i++) {
            if(i > 0 && ratings[i] > ratings[i - 1]){
                left[i] = left[i - 1] + 1;
            }else {
                left[i] = 1;
            }
        }

        int right = 0,ans = 0;
        for (int i = n - 1; i >=0 ; i--) {
            if(i < n - 1 && ratings[i] > ratings[i + 1]){
                right++;
            }else {
                right = 1;
            }

            ans += Math.max(left[i],right);
        }
        return ans;
    }

    /**
     * 455. 分发饼干
     * 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
     * 对每个孩子 i，都有一个胃口值 g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j，都有一个尺寸 s[j] 。
     * 如果 s[j] >= g[i]，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。
     * 你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
     */
    public int findContentChildren(int[] g, int[] s) {
        int ans = 0;
        Arrays.sort(g);
        Arrays.sort(s);
        int a = 0, b = 0;
        while (a < g.length && b < s.length){
            if(s[b] >= g[a]){
                a++;
                b++;
                ans++;
            }else if(g[a] > s[b]){
                b++;
            }
        }
        return ans;
    }
}
