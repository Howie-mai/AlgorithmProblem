package com.mh.leetcode;

import com.mh.leetcode.bean.ListNode;
import com.mh.leetcode.bean.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * ClassName：
 * Time：20/8/17 上午10:14
 * Description：树的用法
 *
 * @author mh
 */
public class TreeProblem {
    public static void main(String[] args) {
        TreeProblem treeProblem = new TreeProblem();
        ListNode head = new ListNode(1);
        ListNode p = head;
        for (int i = 2; i < 7; i++) {
            p.next = new ListNode(i);
            p = p.next;
        }
        treeProblem.sortedListToBST(head);
    }

    /**
     * 110 给定一个二叉树，判断它是否是高度平衡的二叉树。
     * 本题中，一棵高度平衡二叉树定义为：
     * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
     *
     * @date 20/8/17 上午10:16
     */
    @SuppressWarnings("unused")
    public boolean isBalanced(TreeNode root) {
        return height(root) != -1;
    }

    /**
     * 递归获取树的高度
     */
    public int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = height(root.left);
        if (leftHeight == -1) {
            return leftHeight;
        }
        int rightHeight = height(root.right);
        if (rightHeight == -1) {
            return rightHeight;
        }
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }

    List<Integer> traversalResult = new ArrayList<>();
    /**
     * 230 给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
     */
    int result;
    @SuppressWarnings("unused")
    public int kthSmallest(TreeNode root, int k) {
        inorderTraversal(root, k);
        return result;
    }

    /**
     * 中序遍历(递归方法)。
     */
    public void inorderTraversal(TreeNode node, int k) {
        if (node == null) {
            return;
        }
        kthSmallest(node.left, k);
        /**
         * 230题目代码 因为中序遍历二叉搜索树就是按从小到大顺序遍历树，所以当遍历到k的时候，返回数值
         */
//         if(--k == 0){
//            result = node.val;
//            return;
//         }
        traversalResult.add(node.val);
        kthSmallest(node.right, k);
    }

    /**
     * 中序遍历(迭代)，因为中序遍历二叉搜索树就是按从小到大顺序遍历树，所以当遍历了k次的时候，返回数值。
     */
    @SuppressWarnings("unused")
    public List<Integer> inorderTraversalV2(TreeNode node, int k) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = node;
        while (curr != null || !stack.isEmpty()) {
            // 节点不为空一直压栈，寻找最左的树
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            TreeNode pop = stack.pop();
            traversalResult.add(pop.val);
            /**
             * 230题目代码 因为中序遍历二叉搜索树就是按从小到大顺序遍历树，所以当遍历了k次的时候，返回数值。
             */
//            if(--k == 0){
//                return pop.val;
//            }
            curr = pop.right;
        }
        return traversalResult;
    }

    /**
     * 109题
     * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
     * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
     */
    List<ListNode> listNodeList = new ArrayList<>();
    ListNode globalNode;
    @SuppressWarnings("unused")
    public TreeNode sortedListToBST(ListNode head) {
        int len = 0;
        globalNode = head;
        while (head != null) {
            len++;
//            listNodeList.add(head);
            head = head.next;
        }
//        return buildTree(0, listNodeList.size() - 1);
        return buildTree(0, len - 1);
    }

    /**
     * 根据中序 分治建立BST树
     */
    public TreeNode buildTree(int start, int end) {
        if (start > end) {
            return null;
        }
        /**
         * 把链表存入list找中间值
         */
//        int mid = (start + end) / 2;
//        TreeNode root = new TreeNode(listNodeList.get(mid).val);
//        root.left = buildTree(start, mid - 1);
//        root.right = buildTree(mid + 1, end);
//        return root;

        /**
         * 通过指针找中间值
         */
        int mid = (start + end) / 2;
        TreeNode left = buildTree(start, mid - 1);
        TreeNode root = new TreeNode(globalNode.val);

        globalNode = globalNode.next;
        root.left = left;

        root.right = buildTree(mid + 1, end);
        return root;
    }



}
