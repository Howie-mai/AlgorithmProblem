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
//        System.out.println(commonProblem.judgePoint24(new int[]{1, 8, 8, 10}));
//        System.out.println(commonProblem.rangeBitwiseAnd(1,7));
//        System.out.println(commonProblem.repeatedSubstringPattern("abaababaababaab"));
//        System.out.println(commonProblem.reverseWords("Let's take LeetCode contest"));
//        System.out.println(commonProblem.daysBetweenDates("2020-01-15", "2019-12-31"));
//        System.out.println(commonProblem.getPermutation(4,9));
//        System.out.println(commonProblem.permute(new int[]{1}));
//        System.out.println(commonProblem.combine(4,2));
//        System.out.println(commonProblem.findLengthOfShortestSubarray(new int[]{61,19,38,47,38,30,1,16,40,56,25,59,52,1,56,47,36,12,17,56,3,30,39,28,42,41,16,57,33,15,15}));
//        System.out.println(commonProblem.findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}));
//        System.out.println(commonProblem.convert("LEETCODEISHIRING" , 3));
//        System.out.println(commonProblem.myAtoi("+1"));
//        System.out.println(commonProblem.permuteUnique(new int[]{1,1,2}));
//        System.out.println(commonProblem.romanToInt("IX"));
//        System.out.println(commonProblem.intToRoman(1234343));
//        System.out.println(commonProblem.mySqrt(2147395599));
//        System.out.println(commonProblem.countAndSay(6));
//        System.out.println(commonProblem.backspaceCompare("y#fo##f","y#f#o##f"));
//        System.out.println(commonProblem.isLongPressedName("pyplrz", "ppyypllr"));
//        System.out.println(commonProblem.kClosest(new int[][]{{3,3},{5,-1},{-2,4}},2));
//        System.out.println(commonProblem.reconstructQueue(new int[][]{{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}}));
//        System.out.println(commonProblem.allCellsDistOrder(6,5,3,4));
//        System.out.println(commonProblem.canCompleteCircuit(new int[]{1,2,3,4,5},new int[]{3,4,5,1,2}));
//        System.out.println(commonProblem.countPrimes(499979));
//        System.out.println(commonProblem.prefixesDivBy5(new int[]{1,1,0,0,0,1,0,0,1}));
        System.out.println(commonProblem.addToArrayForm(new int[]{9,9,9,9,9,9,9,9,9},1));
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
        while (true){
            Scanner in = new Scanner(System.in);
            String[] a = in.nextLine().split(" ");
            if(a.length != 4){
                break;
            }
            //转成double数组计算
    //        double[] doubles = Arrays.stream(nums).asDoubleStream().toArray();
            double[] doubles = Arrays.stream(new int[]{Integer.parseInt(a[0]),Integer.parseInt(a[1]),
                                                    Integer.parseInt(a[2]),Integer.parseInt(a[3])})
                                    .asDoubleStream().toArray();
//            return solve(doubles);
            System.out.println(solve(doubles));
            System.out.println("");
        }
        return false;
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
//                if (x == 0 && y == 2) {
//                    System.out.println("123");
//                }
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
                    System.out.println(Arrays.toString(temp));
                    return true;
                }

                //减法
                temp[x] = Math.abs(nums[x] - nums[y]);
                //合法就返回
                if (solve(temp)) {
                    System.out.println(Arrays.toString(temp));
                    return true;
                }

                //乘法
                temp[x] = nums[x] * nums[y];
                //合法就返回
                if (solve(temp)) {
                    System.out.println(Arrays.toString(temp));
                    return true;
                }

                //除法，除与被除
                if (nums[y] != 0) {
                    temp[x] = nums[x] / nums[y];
                    //合法就返回
                    if (solve(temp)) {
                        System.out.println(Arrays.toString(temp));
                        return true;
                    }
                }
                if (nums[x] != 0) {
                    temp[x] = nums[y] / nums[x];
                    //合法就返回
                    if (solve(temp)) {
                        System.out.println(Arrays.toString(temp));
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
     * 557. 反转字符串中的单词 III
     * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
     */
    public String reverseWords(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] s1 = s.split(" ");
        for (int i = 0; i < s1.length; i++) {
            StringBuilder builder = new StringBuilder(s1[i]);
            stringBuilder.append(builder.reverse());
        }
        return stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length()).toString();
    }

    /*
     * 486. 预测赢家
     * 给定一个表示分数的非负整数数组。 玩家 1 从数组任意一端拿取一个分数，随后玩家 2 继续从剩余数组任意一端拿取分数，然后玩家 1 拿，…… 。
     * 每次一个玩家只能拿取一个分数，分数被拿取之后不再可取。直到没有剩余分数可取时游戏结束。最终获得分数总和最多的玩家获胜。
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
        if (numRows == 1) {
            return s;
        }
        List<StringBuilder> list = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            list.add(new StringBuilder());
        }

        char[] chars = s.toCharArray();
        int curIndex = 0;
        for (char ch : chars) {
            list.get(curIndex).append(ch);
            if (curIndex == 0) {
                curIndex++;
            } else if (curIndex == numRows - 1) {
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

    /**
     * 8. 字符串转换整数 (atoi)
     * 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
     * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。接下来的转化规则如下：
     * 如果第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字字符组合起来，形成一个有符号整数。
     * 假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成一个整数。
     * 该字符串在有效的整数部分之后也可能会存在多余的字符，那么这些字符可以被忽略，它们对函数不应该造成影响。
     * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换，即无法进行有效转换。
     * 在任何情况下，若函数不能进行有效的转换时，请返回 0 。
     */
    public int myAtoi(String str) {
        char[] chars = str.toCharArray();
        int ans;
        int start = -1;
        boolean flag = true, first = true;

        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if (ch == ' ') {
                continue;
            }

            if (ch != '+' && ch != '-' && !(ch >= '0' && ch <= '9')) {
                break;
            }

            if ((ch == '-' || ch == '+') && (i == chars.length - 1)) {
                break;
            }

            if (ch == '-') {
                start = i;
                flag = false;
                first = false;
                break;
            }

            if (ch == '+') {
                start = i;
                first = false;
                break;
            }

            start = i;
            break;
        }

        if (start == -1 || (start == chars.length - 1 && !first)) {
            return 0;
        }

        if (start == chars.length - 1) {
            return Integer.parseInt(str.substring(start));
        }

        int end = start + 1;

        if (!first) {
            if (!(chars[end] >= '0' && chars[end] <= '9')) {
                return 0;
            }
        }

        while (end < chars.length) {
            char ch = chars[end];
            if (!(ch >= '0' && ch <= '9')) {
                break;
            }
            end++;
        }

        String substring = str.substring(start, end);
        try {
            ans = Integer.parseInt(substring);
        } catch (Exception e) {
            ans = flag ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }

        return ans;
    }

    /**
     * 13. 罗马数字转整数
     * 如果前面的数字比后面的小，则为IV、IX这种4，9的数字。
     */
    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int ans = 0;
        char[] charArray = s.toCharArray();
        // 第二种解法
        // I -> V,X | X -> L,C | C ->D,M
        int pre = map.get(charArray[0]);
        for (int i = 1; i < charArray.length; i++) {
            int cur = map.get(charArray[i]);
            if (pre < cur) {
                ans -= pre;
            } else {
                ans += pre;
            }
            pre = cur;
        }
        ans += pre;
        return ans;
    }

    /**
     * 12. 整数转罗马数字
     * 贪心算法
     */
    public String intToRoman(int num) {
        int[] romanValue = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romanStr = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < romanValue.length && num >= 0; i++) {
            while (romanValue[i] <= num) {
                num -= romanValue[i];
                sb.append(romanStr[i]);
            }
        }

        return sb.toString();
    }

    /**
     * 11. 盛最多水的容器
     * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，
     * 垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     * <p>
     * 说明：你不能倾斜容器，且 n 的值至少为 2。
     * <p>
     * 解法：双指针
     */
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int ans = Integer.MIN_VALUE;
        while (left < right) {
            int count = Math.min(height[left], height[right]) * (right - left);
            ans = Math.max(count, ans);

            if (height[left] > height[right]) {
                right--;
            } else {
                left++;
            }
        }
        return ans;
    }

    /**
     * 14. 最长公共前缀
     * 编写一个函数来查找字符串数组中的最长公共前缀。
     * <p>
     * 如果不存在公共前缀，返回空字符串 ""。
     * <p>
     * 示例 1:
     * <p>
     * 输入: ["flower","flow","flight"]
     * 输出: "fl"
     */
    public String longestCommonPrefix(String[] strs) {
        String commonPrefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            int index = 0;
            int minLen = Math.min(commonPrefix.length(), strs[i].length());
            while (index < minLen && commonPrefix.charAt(index) == strs[i].charAt(index)) {
                index++;
            }
            commonPrefix = commonPrefix.substring(0, index);
            if (commonPrefix.length() == 0) {
                break;
            }
        }
        return commonPrefix;
    }

    /**
     * 69. x 的平方根
     * 实现 int sqrt(int x) 函数。
     * <p>
     * 计算并返回 x 的平方根，其中 x 是非负整数。
     * <p>
     * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
     */
    public int mySqrt(int x) {
        int left = 0, right = x, ans = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if ((long) mid * mid > x) {
                right = mid - 1;
            } else {
                ans = mid;
                left = mid + 1;
            }
        }
        return ans;
    }

    /**
     * 38. 外观数列
     * 给定一个正整数 n（1 ≤ n ≤ 30），输出外观数列的第 n 项。
     * 注意：整数序列中的每一项将表示为一个字符串。
     * 「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。前五项如下：
     * 1.     1
     * 2.     11
     * 3.     21
     * 4.     1211
     * 5.     111221
     * 第一项是数字 1
     * 描述前一项，这个数是 1 即 “一个 1 ”，记作 11
     * 描述前一项，这个数是 11 即 “两个 1 ” ，记作 21
     * 描述前一项，这个数是 21 即 “一个 2 一个 1 ” ，记作 1211
     * 描述前一项，这个数是 1211 即 “一个 1 一个 2 两个 1 ” ，记作 111221
     */
    public String countAndSay(int n) {
        String str = "1";
        StringBuilder sb;
        for (int i = 1; i < n; i++) {
            char[] chars = str.toCharArray();
            sb = new StringBuilder();
            for (int j = 0; j < chars.length; ) {
                if ((j < chars.length - 1 && chars[j] != chars[j + 1]) || j == chars.length - 1) {
                    sb.append("1").append(chars[j++]);
                    continue;
                }
                int count = 1;
                char ch = chars[j++];
                while (j < chars.length && chars[j] == ch) {
                    count++;
                    j++;
                }

                sb.append(count).append(ch);
            }
            str = sb.toString();
        }
        return str;
    }

    /**
     *
     */
    public boolean backspaceCompare(String S, String T) {
        return getString(S).equals(getString(T));

    }

    public String getString(String str) {
        StringBuilder sb = new StringBuilder();
        for (char ch : str.toCharArray()) {
            if (ch == '#' && sb.length() != 0) {
                sb.deleteCharAt(sb.length() - 1);
            } else if (ch != '#') {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    /**
     * 925. 长按键入
     * 你的朋友正在使用键盘输入他的名字 name。偶尔，在键入字符 c 时，按键可能会被长按，而字符可能被输入 1 次或多次。
     * <p>
     * 你将会检查键盘输入的字符 typed。如果它对应的可能是你的朋友的名字（其中一些字符可能被长按），那么就返回 True。
     */
    public boolean isLongPressedName(String name, String typed) {
        int i = 0, j = 0;
        char[] nameArray = name.toCharArray();
        char[] typeArray = typed.toCharArray();
        int n = nameArray.length;
        int n1 = typeArray.length;
        while (j < n1) {
            if (i < n && nameArray[i] == typeArray[j]) {
                i++;
                j++;
            } else if (j > 0 && typeArray[j] == typeArray[j - 1]) {
                j++;
            } else {
                return false;
            }
        }
        return i == n;
    }

    /**
     * 1365. 有多少小于当前数字的数字
     * 给你一个数组 nums，对于其中每个元素 nums[i]，请你统计数组中比它小的所有数字的数目。
     * 换而言之，对于每个 nums[i] 你必须计算出有效的 j 的数量，其中 j 满足 j != i 且 nums[j] < nums[i] 。
     * 以数组形式返回答案。
     */
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] temp = new int[nums.length];
        System.arraycopy(nums,0,temp,0,nums.length);
        Arrays.sort(temp);
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0;i < temp.length;i++){
            if(!map.containsKey(nums[i])){
                map.put(nums[i],i);
            }
        }

        for(int i = 0;i < nums.length;i++){
            temp[i] = map.get(nums[i]);
        }
        return temp;
    }

    /**
     * 845. 数组中的最长山脉
     * 我们把数组 A 中符合下列属性的任意连续子数组 B 称为 “山脉”：
     * B.length >= 3
     * 存在 0 < i < B.length - 1 使得 B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
     * （注意：B 可以是 A 的任意子数组，包括整个数组 A。）
     * 给出一个整数数组 A，返回最长 “山脉” 的长度。
     * 如果不含有 “山脉” 则返回 0。
     */
    public int longestMountain(int[] A) {
        int result = 0,i = 1,len = A.length,start = 0,end = 0;
        while(i < len){
            start = 0;
            end = 0;
            while(i < len && A[i - 1] < A[i]){
                i++;
                start++;
            }

            while(i < len && A[i - 1] > A[i]){
                i++;
                end++;
            }

            if(start > 0 && end > 0){
                result = Math.max(result,start + end + 1);
            }

            while(i < len && A[i - 1] == A[i]){
                i++;
            }
        }
        return result;
    }

    /**
     * 973. 最接近原点的 K 个点
     * 我们有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。
     */
    public int[][] kClosest(int[][] points, int K) {
        Map<Integer,Double> map = new HashMap<>();
        for (int i = 0; i < points.length; i++) {
            int x = points[i][0],y = points[i][1];
            map.put(i,Math.sqrt(x * x + y * y));
        }

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((o1, o2) -> map.get(o2).compareTo(map.get(o1)));
        for (Integer key : map.keySet()) {
            if(priorityQueue.size() < K){
                priorityQueue.add(key);
            }else if(map.get(key) < map.get(priorityQueue.peek())){
                priorityQueue.remove();
                priorityQueue.add(key);
            }
        }

        int[][] ans = new int[K][2];
        int n  = priorityQueue.size();
        for (int i = 0; i < n; i++) {
            ans[i] = points[priorityQueue.remove()];
        }
        return ans;
    }

    /**
     * 406. 根据身高重建队列
     * 假设有打乱顺序的一群人站成一个队列。
     * 每个人由一个整数对(h, k)表示，其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。
     * 编写一个算法来重建这个队列。
     */
    public int[][] reconstructQueue(int[][] people) {
        // 先按身高降序排序，相同身高按k升序排序
        // 排序的每个元素是一个一维数组，也就是原二维数组的一行数据
        Arrays.sort(people, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                return o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0];
            }
        });

        // 遍历数组，把每行元素添加到一个集合的p[1]位置
        List<int[]> list = new LinkedList<int[]>();
        // 把二维数组一行元素作为一个对象
        for(int[] p : people){
            list.add(p[1], p);
        }
        int n = list.size();
        // 将集合转换成二维数组
        return list.toArray(new int[n][2]);
    }

    /**
     * 1030. 距离顺序排列矩阵单元格
     * 给出 R 行 C 列的矩阵，其中的单元格的整数坐标为 (r, c)，满足 0 <= r < R 且 0 <= c < C。
     * 另外，我们在该矩阵中给出了一个坐标为 (r0, c0) 的单元格。
     * 返回矩阵中的所有单元格的坐标，并按到 (r0, c0) 的距离从最小到最大的顺序排，
     * 其中，两单元格(r1, c1) 和 (r2, c2) 之间的距离是曼哈顿距离，|r1 - r2| + |c1 - c2|。
     */
    public int[][] allCellsDistOrder(int r, int c, int r0, int c0) {
        int[][] ans = new int[r * c][2];
        int index = 0;
        ans[index++] = new int[]{r0,c0};
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                ans[index++] = new int[]{i,j};
            }
        }

        Arrays.sort(ans, Comparator.comparingInt(o -> (Math.abs(o[0] - r0) + Math.abs(o[1] - c0))));
        return ans;
    }

    /**
     * 134. 加油站
     * 在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
     *
     * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。
     * 你从其中的一个加油站出发，开始时油箱为空。
     *
     * 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int len = gas.length;
        int spare = 0;
        int minSpare = Integer.MAX_VALUE;
        int minIndex = 0;

        for (int i = 0; i < len; i++) {
            spare += gas[i] - cost[i];
            if (spare < minSpare) {
                minSpare = spare;
                minIndex = i;
            }
        }

        return spare < 0 ? -1 : (minIndex + 1) % len;
    }

    /**
     * 1370. 上升下降字符串
     * 给你一个字符串 s ，请你根据下面的算法重新构造字符串：
     *
     * 从 s 中选出 最小 的字符，将它 接在 结果字符串的后面。
     * 从 s 剩余字符中选出 最小 的字符，且该字符比上一个添加的字符大，将它 接在 结果字符串后面。
     * 重复步骤 2 ，直到你没法从 s 中选择字符。
     * 从 s 中选出 最大 的字符，将它 接在 结果字符串的后面。
     * 从 s 剩余字符中选出 最大 的字符，且该字符比上一个添加的字符小，将它 接在 结果字符串后面。
     * 重复步骤 5 ，直到你没法从 s 中选择字符。
     * 重复步骤 1 到 6 ，直到 s 中所有字符都已经被选过。
     * 在任何一步中，如果最小或者最大字符不止一个 ，你可以选择其中任意一个，并将其添加到结果字符串。
     *
     * 请你返回将 s 中字符重新排序后的 结果字符串 。
     */
    public String sortString(String s) {
        StringBuilder sb = new StringBuilder();
        int[] nums = new int[26];
        char[] chars = s.toCharArray();
        for (char c : chars) {
            nums[c - 'a']++;
        }

        int index = 0;
        while (index < chars.length){
            for (int i = 0; i < 26; i++) {
                if(nums[i] > 0){
                    sb.append((char) i + 'a');
                    nums[i]--;
                }
            }

            for (int i = 25; i >= 0; i--) {
                if(nums[i] < 0){
                    sb.append((char) i + 'a');
                    nums[i]--;
                }
            }

            index++;
        }
        return sb.toString();
    }

    /**
     * 164. 最大间距
     * 给定一个无序的数组，找出数组在排序之后，相邻元素之间最大的差值。
     *
     * 如果数组元素个数小于 2，则返回 0。
     */
    public int maximumGap(int[] nums) {
        if(nums.length < 2){
            return 0;
        }
        int ans = Integer.MIN_VALUE;
        Arrays.sort(nums);
        int index = 1;
        for (int i = 0; i < nums.length - 1; i++) {
            ans = Math.max(ans,nums[index++] - nums[i]);
        }
        return ans;
    }

    /**
     * 204. 计数质数
     * 统计所有小于非负整数 n 的质数的数量。
     */
    public int countPrimes(int n) {
        int result = 0;
        // 初始化默认值都为 false，为质数标记
        boolean[] b = new boolean[n];
        // 如果大于 2 则一定拥有 2 这个质数
        if (2 < n) {
            result++;
        }

        // 从 3 开始遍历，且只遍历奇数
        for (int i = 3; i < n; i += 2) {
            // 是质数
            if (!b[i]) {
                for (int j = 3; i * j < n; j += 2) {
                    // 将当前质数的奇数倍都设置成非质数标记 true
                    b[i * j] = true;
                }
                result++;
            }
        }
        return result;
    }

    /**
     * 49. 字母异位词分组
     * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> map = new HashMap<>();
        for (String str : strs) {
            int[] count = new int[26];
            char[] chars = str.toCharArray();
            for (char ch : chars) {
                count[(ch - 'a')]++;
            }

            StringBuilder sb = new StringBuilder();
            for (int i : count) {
                if(i != 0){
                    sb.append((char)(i + 'a'));
                    sb.append(i);
                }
            }

            String key = sb.toString();
            if(!map.containsKey(key)){
                map.put(key,new ArrayList<>());
            }
            map.get(key).add(str);
        }

        return new ArrayList<>(map.values());
    }

    /**
     * 389. 找不同
     * 给定两个字符串 s 和 t，它们只包含小写字母。
     * 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
     * 请找出在 t 中被添加的字母
     */
    public char findTheDifference(String s, String t) {
        int pre = 0,after = 0;
        for (int i = 0; i < s.length(); i++) {
            pre += s.charAt(i);
        }

        for (int i = 0; i < t.length(); i++) {
            after += t.charAt(i);
        }

        return (char) (after - pre);
    }

    /**
     * 830. 较大分组的位置
     * 在一个由小写字母构成的字符串 s 中，包含由一些连续的相同字符所构成的分组。
     * 例如，在字符串 s = "abbxxxxzyy" 中，就含有 "a", "bb", "xxxx", "z" 和 "yy" 这样的一些分组。
     * 分组可以用区间 [start, end] 表示，其中 start 和 end 分别表示该分组的起始和终止位置的下标。
     * 上例中的 "xxxx" 分组用区间表示为 [3,6] 。
     * 我们称所有包含大于或等于三个连续字符的分组为 较大分组 。
     * 找到每一个 较大分组 的区间，按起始位置下标递增顺序排序后，返回结果。
     */
    public List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> ret = new ArrayList<>();
        int n = s.length();
        int num = 1;
        for (int i = 0; i < n; i++) {
            if (i == n - 1 || s.charAt(i) != s.charAt(i + 1)) {
                if (num >= 3) {
                    ret.add(Arrays.asList(i - num + 1, i));
                }
                num = 1;
            } else {
                num++;
            }
        }
        return ret;
    }

    /**
     * 1018. 可被 5 整除的二进制前缀
     * 给定由若干 0 和 1 组成的数组 A。我们定义 N_i：从 A[0] 到 A[i] 的第 i 个子数组被解释为一个二进制数（从最高有效位到最低有效位）。
     *
     * 返回布尔值列表 answer，只有当 N_i 可以被 5 整除时，答案 answer[i] 为 true，否则为 false。
     */
    public List<Boolean> prefixesDivBy5(int[] A) {
        List<Boolean> ans = new ArrayList<>();
        int num = 0;
        for (int value : A) {
            num += value;
            num %= 10;
            ans.add(num % 5 == 0);
            num *= 2;
        }
        return ans;
    }

    public List<Integer> addToArrayForm(int[] A, int K) {
        int n = A.length;
        int x = 0;
        List<Integer> ans = new LinkedList<>();

        for(int i = n - 1;i >= 0;i--){
            int temp = A[i] + K % 10 + x;

            if(temp >= 10){
                ans.add(0,temp % 10);
            }else {
                ans.add(0,temp);
            }

            K /= 10;
            x = temp / 10;
        }

        if(x > 0){
            ans.add(0,x);
        }

        return ans;
    }

    public int[] plusOne(int[] digits) {
        int n = digits.length;
        int x = 0;
        int K = 1;
        List<Integer> ans = new LinkedList<>();

        for(int i = n - 1;i >= 0;i--){
            int temp = digits[i] + K % 10 + x;
            if(temp >= 10){
                ans.add(0,temp % 10);
            }else {
                ans.add(0,temp);
            }
            K = 0;
            x = temp / 10;
        }

        if(x > 0){
            ans.add(0,x);
        }

        int[] res = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            res[i] = ans.get(i);
        }

        return res;
    }
}
