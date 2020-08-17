package com.mh.leetcode.bean;

/**
 * ClassName：
 * Time：20/8/17 上午10:11
 * Description：
 *
 * @author mh
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    TreeNode() {}
    TreeNode(int val) {
        this.val = val;
    }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
