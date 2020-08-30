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
        System.out.println(commonProblem.reverseWords("Let's take LeetCode contest"));
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
     * 第647题
     * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
     * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
     * 示例 ：
     * 输入："aaa"
     * 输出：6
     * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
     */
    @SuppressWarnings("unused")
    public int countSubstrings(String s) {
        int n = s.length(), ans = 0;
        // result[i][j] 表示字符串的下标为i到下标j的字符串是否为回文串
        // 0 : 不是 1 : 是
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (i == j) {
                    result[i][j] = 1;
                    ans++;
                } else {
                    boolean isSame = s.charAt(i) == s.charAt(j);
                    if (i - j == 1 && isSame) {
                        // 相邻字符相同也为回文串
                        result[i][j] = 1;
                        ans++;
                    } else if (i - j > 1 && isSame && result[i - 1][j + 1] == 1) {
                        result[i][j] = 1;
                        ans++;
                    }
                }
            }
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
        if(nums.length == 0){
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
                if(x == 0 && y == 2){
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
        while (m != n){
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
        for(int i = 1;i <= s.length() / 2 && !flag;i++){
            if(s.length() % i != 0){
                continue;
            }
            String example = s.substring(0, i);
            int j = i * 2;
            if(j > s.length()){
                break;
            }
            String str = s.substring(i,j);
            if(example.equals(str)){
                if(j == s.length()){
                    return true;
                }
                for (int k = j;k < s.length();k += example.length()){
                    int offset = k + example.length();

                    String substring = s.substring(k, offset);
                    if(!substring.equals(example)) {
                        flag = false;
                        break;
                    }else {
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
