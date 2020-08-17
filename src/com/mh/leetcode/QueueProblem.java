package com.mh.leetcode;

import java.util.ArrayDeque;

/**
 * ClassName：
 * Time：20/8/17 上午11:21
 * Description：
 *
 * @author mh
 */
public class QueueProblem {

    public static void main(String[] args) {
        QueueProblem queue = new QueueProblem();
        int[] nums = new int[]{1,3,-1,-3,0,3,6,7};
        int[] x = queue.maxSlidingWindow(nums, 3);
        for (int i : x) {
            System.out.print(i + ",");
        }
    }
    /**
     * 239题
     * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
     * 返回滑动窗口中的最大值。
     * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
     * 输出: [3,3,5,5,6,7]
     */
    ArrayDeque<Integer> deq = new ArrayDeque<>();
    int [] nums;
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (n * k == 0){
            return new int[0];
        }
        if (k == 1){
            return nums;
        }
        this.nums = nums;
        int [] output = new int[n - k + 1];
        for (int i  = 0; i < n; i++) {
            cleanDeque(i, k);
            deq.addLast(i);
            // 前2个不用对output赋值
            if(i - k + 1 >= 0){
                output[i - k + 1] = nums[deq.getFirst()];
            }
        }
        return output;
    }

    /**
     * 清理双向队列
     * 只保留当前滑动窗口中有的元素的索引。
     * 移除比当前元素小的所有元素，它们不可能是最大的。
     */
    public void cleanDeque(int i, int k) {
        // 清除不在滑动窗口内，但是在队列里面的下标
        if (!deq.isEmpty() && deq.getFirst() == i - k){
            deq.removeFirst();
        }
        // 清除队列中比当前值小的值的下标
        if (!deq.isEmpty() && nums[i] > nums[deq.getFirst()]){
            deq.addFirst(i);
        }
    }
}
