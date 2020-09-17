package com.mh.leetcode;

import java.util.*;

/**
 * ClassName：
 * Time：20/8/18 下午4:58
 * Description：
 *
 * @author mh
 */
public class CommonProblem {
    public static void main(String[] args) {
        CommonProblem commonProblem = new CommonProblem();
        Long start = System.currentTimeMillis();
//        commonProblem.reverse(123456);
//        System.out.println(commonProblem.fib(45));
//        System.out.println(commonProblem.countSubstrings("aaaaa"));
//        System.out.println(commonProblem.judgePoint24(new int[]{4, 7, 1, 8}));
//        System.out.println(commonProblem.rangeBitwiseAnd(1,7));
//        System.out.println(commonProblem.repeatedSubstringPattern("abaababaababaab"));
//        System.out.println(commonProblem.daysBetweenDates("2020-01-15", "2019-12-31"));
//        System.out.println(commonProblem.getPermutation(4,9));
//        System.out.println(commonProblem.permute(new int[]{1}));
//        System.out.println(commonProblem.combine(4,2));
//        System.out.println(commonProblem.findLengthOfShortestSubarray(new int[]{61,19,38,47,38,30,1,16,40,56,25,59,52,1,56,47,36,12,17,56,3,30,39,28,42,41,16,57,33,15,15}));
//        System.out.println(commonProblem.findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}));
//        System.out.println(commonProblem.convert("LEETCODEISHIRING" , 3));
        System.out.println(commonProblem.myAtoi("+1"));
        Long end = System.currentTimeMillis();
        System.out.println("执行时间：" + (end - start));
    }

    /**
     * 7
     * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
     * 示例 1:
     * 输入: 123
     * 输出: 321
     */
    @SuppressWarnings("unused")
    public int reverse(int x) {
        int ans = 0;
        while (x != 0) {
            int pop = x % 10;
            if (ans > Integer.MAX_VALUE / 10 || (ans == Integer.MAX_VALUE / 10 && pop > 7)) {
                return 0;
            }
            if (ans < Integer.MIN_VALUE / 10 || (ans == Integer.MIN_VALUE / 10 && pop < -8)) {
                return 0;
            }
            ans = ans * 10 + pop;
            x /= 10;
        }
        return ans;
    }

    /**
     * 剑指 Offer 10- I 、 509题
     * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项。斐波那契数列的定义如下：
     * F(0) = 0,   F(1) = 1
     * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
     */
    @SuppressWarnings("unused")
//    Map<Integer,Integer> map = new HashMap<>();
    public int fib(int n) {
//        if(n < 2) {
//            return n;
//        }
//        if(!map.containsKey(n)){
//            map.put(n,(fib(n - 1) + fib(n - 2)) % 1000000007);
//        }
//        return map.get(n);

        int[] nums = new int[n + 1];
        nums[0] = 0;
        nums[1] = 1;
        for (int i = 2; i <= n; i++) {
            nums[i] = (nums[i - 1] + nums[i - 2]) % 1000000007;
        }
        return nums[n];
    }

    /**
     * 679. 24 点游戏
     * 你有 4 张写有 1 到 9 数字的牌。你需要判断是否能通过 *，/，+，-，(，) 的运算得到 24。
     */
    public boolean judgePoint24(int[] nums) {
        //转成double数组计算
        double[] doubles = Arrays.stream(nums).asDoubleStream().toArray();
        return solve(doubles);
    }

    public static boolean solve(double[] nums) {
        if (nums.length == 0) {
            return false;
        }

        //递归出口，剩余一个数，判断是不是24点
        if (nums.length == 1) {
            //处理计算精度问题
            return nums[0] > 23.999 && nums[0] < 24.001;
        }

        //任意拿两个不相同的数，通过运算符组成第三个数，进行后续的24点计算
        //其中，括号不用考虑，因为是任意两个数的所有允许符都参与，所以有括号，没括号的情况，都包含在内了
        for (int x = 0; x < nums.length - 1; x++) {
            for (int y = x + 1; y < nums.length; y++) {
                if (x == 0 && y == 2) {
                    System.out.println("123");
                }
                //是否合法
                boolean isValid;

                //每次两数操作 出来的数组是原来数据的length-1
                //删除后面的元素（这样不会影响前面的元素），前面的元素用来放置新值
                double[] temp = new double[nums.length - 1];
                // y为要和x进行加减乘除的数
                //copy待删除元素y的前部
                System.arraycopy(nums, 0, temp, 0, y);
                //copy待删除元素y的后部
                System.arraycopy(nums, y + 1, temp, y, temp.length - y);

                //加法
                temp[x] = nums[x] + nums[y];
                //合法就返回
                if (solve(temp)) {
                    return true;
                }

                //减法
                temp[x] = Math.abs(nums[x] - nums[y]);
                //合法就返回
                if (solve(temp)) {
                    return true;
                }

                //乘法
                temp[x] = nums[x] * nums[y];
                //合法就返回
                if (solve(temp)) {
                    return true;
                }

                //除法，除与被除
                if (nums[y] != 0) {
                    temp[x] = nums[x] / nums[y];
                    //合法就返回
                    if (solve(temp)) {
                        return true;
                    }
                }
                if (nums[x] != 0) {
                    temp[x] = nums[y] / nums[x];
                    //合法就返回
                    if (solve(temp)) {
                        return true;
                    }
                }
            }
        }
        //不合法
        return false;
    }

    /**
     * 201. 数字范围按位与
     * 给定范围 [m, n]，其中 0 <= m <= n <= 2147483647，返回此范围内所有数字的按位与（包含 m, n 两端点）。
     */
    public int rangeBitwiseAnd(int m, int n) {
        int sum = 0;
        // 比如从5(0101)到7(0111) 因为与是有0就为0，所以只要找相同的前缀，在范围数里面肯定有为0的，所以后面必定计算出为0
        while (m != n) {
            // 通过右移动并赋值找出一样的前缀
            m >>= 1;
            n >>= 1;
            sum++;
        }
        // 找到相同前缀后，再向左补位(即0)
        return m << sum;
    }

    /**
     * 459. 重复的子字符串
     * 给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，并且长度不超过10000。
     */
    public boolean repeatedSubstringPattern(String s) {
        boolean flag = false;
        for (int i = 1; i <= s.length() / 2 && !flag; i++) {
            if (s.length() % i != 0) {
                continue;
            }
            String example = s.substring(0, i);
            int j = i * 2;
            if (j > s.length()) {
                break;
            }
            String str = s.substring(i, j);
            if (example.equals(str)) {
                if (j == s.length()) {
                    return true;
                }
                for (int k = j; k < s.length(); k += example.length()) {
                    int offset = k + example.length();

                    String substring = s.substring(k, offset);
                    if (!substring.equals(example)) {
                        flag = false;
                        break;
                    } else {
                        flag = true;
                    }
                }
            }
        }
        return flag;
    }

    /**
     * 486. 预测赢家
     * 给定一个表示分数的非负整数数组。 玩家 1 从数组任意一端拿取一个分数，随后玩家 2 继续从剩余数组任意一端拿取分数，然后玩家 1 拿，…… 。每次一个玩家只能拿取一个分数，分数被拿取之后不再可取。直到没有剩余分数可取时游戏结束。最终获得分数总和最多的玩家获胜。
     * <p>
     * 给定一个表示分数的数组，预测玩家1是否会成为赢家。你可以假设每个玩家的玩法都会使他的分数最大化。
     */
    public boolean PredictTheWinner(int[] nums) {
        int length = nums.length;
        int[] dp = new int[length];
        for (int i = 0; i < length; i++) {
            dp[i] = nums[i];
        }
        for (int i = length - 2; i >= 0; i--) {
            for (int j = i + 1; j < length; j++) {
                dp[j] = Math.max(nums[i] - dp[j], nums[j] - dp[j - 1]);
            }
        }
        return dp[length - 1] >= 0;
    }

    /**
     * 1360. 日期之间隔几天
     * 请你编写一个程序来计算两个日期之间隔了多少天。
     * <p>
     * 日期以字符串形式给出，格式为 YYYY-MM-DD，如示例所示。
     */
    public int daysBetweenDates(String date1, String date2) {
        return Math.abs(daysBetweenDates(date1) - daysBetweenDates(date2));
    }

    public int daysBetweenDates(String date) {
        int day = 0;
        String[] split1 = date.split("-");
        int[] monthDay = new int[]{-1, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        // 处理年份
        int year = Integer.parseInt(split1[0]);
        int preYear = 1971;
        while (preYear == year) {

            day += 365;
            if (isleap(preYear)) {
                day += 1;
            }

            preYear++;
        }

        // 处理月份
        int month = Integer.parseInt(split1[1]);
        int preMonth = 1;
        while (preMonth == month) {
            if (preMonth == 2) {
                day += monthDay[preMonth];
                if (isleap(year)) {
                    day += 1;
                }
            } else {
                day += monthDay[preMonth];
            }
            preMonth++;
        }

        return day;
    }

    boolean isleap(int y) {
        return y % 4 == 0 && y % 100 != 0 || y % 400 == 0;
    }

    /**
     * 剑指 Offer 20. 表示数值的字符串
     * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
     * 例如，字符串"+100"、"5e2"、"-123"、"3.1416"、"-1E-16"、"0123"都表示数值
     * 但"12e"、"1a3.14"、"1.2.3"、"+-5"及"12e+5.4"都不是。
     */
    public boolean isNumber(String s) {
        if (s.endsWith("f") || s.endsWith("d") || s.endsWith("D")) {
            return false;
        }
        try {
            Double.parseDouble(s);
            return true;
        } catch (Exception e) {
            return false;
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
     * 1574. 删除最短的子数组使剩余数组有序
     * 给你一个整数数组 arr ，请你删除一个子数组（可以为空），使得 arr 中剩下的元素是 非递减 的。
     * 一个子数组指的是原数组中连续的一个子序列。
     * 请你返回满足题目要求的最短子数组的长度。
     */
    public int findLengthOfShortestSubarray(int[] arr) {
        int len = arr.length;
        if (len == 1 || len == 0) {
            return 0;
        }

        int left = -1;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i + 1] < arr[i]) {
                left = i;
                break;
            }
        }

        // 已经是正确的顺序
        if (left == -1) {
            return 0;
        }

        int right = len - 1;
        while (right - 1 > 0 && arr[right] >= arr[right - 1]) {
            right--;
        }

        // 删除[left + 1....len] 或者 [0....right-1]区间的数
        int result = Math.min(len - left - 1, right);

        int i = 0, j = right;
        for (; i <= left && j <= len - 1; ) {
            if (arr[i] <= arr[j]) {
                // 从左边找到 小于等于 右边的数，删除[i+1....j-1]区间的数据
                result = Math.min(result, j - i - 1);
                i++;
            } else {
                j++;
            }
        }

        return result;
    }

    /**
     * 4. 寻找两个正序数组的中位数
     * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
     * <p>
     * 请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
     * <p>
     * 你可以假设 nums1 和 nums2 不会同时为空。
     * <p>
     * 第四题的二分法解决方法
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length1 = nums1.length, length2 = nums2.length;
        int totalLength = length1 + length2;
        if (totalLength % 2 == 1) {
            int midIndex = totalLength / 2;
            return getKthElement(nums1, nums2, midIndex + 1);
        } else {
            int midIndex1 = totalLength / 2 - 1, midIndex2 = totalLength / 2;
            return (getKthElement(nums1, nums2, midIndex1 + 1) + getKthElement(nums1, nums2, midIndex2 + 1)) / 2.0;
        }
    }
//    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
//        int n = nums1.length;
//        int m = nums2.length;
//        int mid = (n + m) / 2;
//        int i = 0, j = 0;
//
//        int left = 0, right = 0;
//        for (int z = 0;z <= mid;z++) {
//            left = right;
//            if (i < nums1.length && j == nums2.length) {
//                right = nums1[i++];
//            } else if (j < nums2.length && i == nums1.length) {
//                right = nums2[j++];
//            } else if (j < nums2.length && i < nums1.length) {
//                if (nums1[i] < nums2[j]) {
//                    right = nums1[i++];
//                } else {
//                    right = nums2[j++];
//                }
//            }
//        }
//
//        if((n + m) % 2 != 0){
//            return right;
//        }else {
//            return (left + right) / 2.0;
//        }
//    }

    public int getKthElement(int[] nums1, int[] nums2, int k) {
        /* 主要思路：要找到第 k (k>1) 小的元素，那么就取 pivot1 = nums1[k/2-1] 和 pivot2 = nums2[k/2-1] 进行比较
         * 这里的 "/" 表示整除
         * nums1 中小于等于 pivot1 的元素有 nums1[0 .. k/2-2] 共计 k/2-1 个
         * nums2 中小于等于 pivot2 的元素有 nums2[0 .. k/2-2] 共计 k/2-1 个
         * 取 pivot = min(pivot1, pivot2)，两个数组中小于等于 pivot 的元素共计不会超过 (k/2-1) + (k/2-1) <= k-2 个
         * 这样 pivot 本身最大也只能是第 k-1 小的元素
         * 如果 pivot = pivot1，那么 nums1[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums1 数组
         * 如果 pivot = pivot2，那么 nums2[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums2 数组
         * 由于我们 "删除" 了一些元素（这些元素都比第 k 小的元素要小），因此需要修改 k 的值，减去删除的数的个数
         */
        int length1 = nums1.length, length2 = nums2.length;
        int index1 = 0, index2 = 0;
        int kthElement = 0;

        while (true) {
            // 边界情况
            if (index1 == length1) {
                return nums2[index2 + k - 1];
            }
            if (index2 == length2) {
                return nums1[index1 + k - 1];
            }
            if (k == 1) {
                return Math.min(nums1[index1], nums2[index2]);
            }

            // 正常情况
            int half = k / 2;
            int newIndex1 = Math.min(index1 + half, length1) - 1;
            int newIndex2 = Math.min(index2 + half, length2) - 1;
            int pivot1 = nums1[newIndex1], pivot2 = nums2[newIndex2];
            if (pivot1 <= pivot2) {
                k -= (newIndex1 - index1 + 1);
                index1 = newIndex1 + 1;
            } else {
                k -= (newIndex2 - index2 + 1);
                index2 = newIndex2 + 1;
            }
        }
    }

    /**
     * 6. Z 字形变换
     * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
     * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
     * L   C   I   R
     * E T O E S I I G
     * E   D   H   N
     * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
     */
    public String convert(String s, int numRows) {
        if(numRows == 1){
            return s;
        }
        List<StringBuilder> list = new ArrayList<>();
        for (int i = 0;i < numRows;i++){
            list.add(new StringBuilder());
        }

        char[] chars = s.toCharArray();
        int curIndex = 0;
        for (char ch : chars) {
           list.get(curIndex).append(ch);
           if(curIndex == 0){
               curIndex++;
           }else if(curIndex == numRows - 1){
               curIndex--;
           }
        }
        StringBuilder ans = new StringBuilder();
        for (StringBuilder stringBuilder : list) {
            System.out.println(stringBuilder.toString());
            ans.append(stringBuilder);
        }
        return ans.toString();
    }

    public int myAtoi(String str) {
        char[] chars = str.toCharArray();
        int ans;
        int start = -1;
        boolean flag = true,first = true;

        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if(ch == ' '){
                continue;
            }

            if(ch!= '+' && ch != '-' && !(ch >= '0' && ch <= '9')){
                break;
            }

            if( (ch == '-' || ch == '+') && (i == chars.length - 1) ){
                break;
            }

            if(ch == '-'){
                start = i;
                flag = false;
                first = false;
                break;
            }

            if(ch == '+'){
                start = i;
                first = false;
                break;
            }

            start = i;
            break;
        }

        if(start == -1 || (start == chars.length - 1 && !first)){
            return 0;
        }

        if(start == chars.length - 1){
            return Integer.parseInt(str.substring(start));
        }

        int end = start + 1;

        if(!first){
            if(!(chars[end] >= '0' && chars[end] <= '9')){
                return 0;
            }
        }

        while (end < chars.length) {
            char ch = chars[end];
            if(!(ch >= '0' && ch <= '9')){
                break;
            }
            end++;
        }

        String substring = str.substring(start, end);
        try {
          ans = Integer.parseInt(substring);
        }catch (Exception e){
            ans = flag ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }

        return ans;
    }
}
