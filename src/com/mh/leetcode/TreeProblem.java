package com.mh.leetcode;

import com.mh.leetcode.bean.TreeNode;

/**
 * ClassName：
 * Time：20/8/17 上午10:14
 * Description：树的用法
 *
 * @author mh
 */
public class TreeProblem {
    public static void main(String[] args) {

    }

    /**
     * 给定一个二叉树，判断它是否是高度平衡的二叉树。
     * 本题中，一棵高度平衡二叉树定义为：
     * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
     * @date 20/8/17 上午10:16
     */
    public boolean isBalanced(TreeNode root) {
        return height(root) != -1;
    }

    public int height(TreeNode root){
        if(root == null){
            return 0;
        }
        int leftHeight = height(root.left);
        if(leftHeight == -1){
            return leftHeight;
        }
        int rightHeight = height(root.right);
        if(rightHeight == -1){
            return rightHeight;
        }
        if(Math.abs(leftHeight - rightHeight) > 1){
            return -1;
        }
        return Math.max(leftHeight,rightHeight) + 1;
    }
}
