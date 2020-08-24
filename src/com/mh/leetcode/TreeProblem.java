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
//        ListNode head = new ListNode(1);
//        ListNode p = head;
//        for (int i = 2; i < 7; i++) {
//            p.next = new ListNode(i);
//            p = p.next;
//        }
//        treeProblem.sortedListToBST(head);
        TreeNode node = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        left.left = new TreeNode(3);
        left.right = new TreeNode(4);
        node.left = left;

        TreeNode right = new TreeNode(5);
//        right.left = new TreeNode(6);
        right.right = new TreeNode(6);
        node.right = right;

        treeProblem.flatten(node);
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

    /**
     * 111. 二叉树的最小深度
     * 给定一个二叉树，找出其最小深度。
     *
     * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
     */
    public int minDepth(TreeNode root) {
        if(root == null){
            return 0;
        }

        if(root.left == null && root.right == null){
            return 1;
        }

        int height = Integer.MAX_VALUE;
        if(root.left != null){
            height = Math.min(minDepth(root.left),height);
        }

        if(root.right != null){
            height = Math.min(minDepth(root.right),height);
        }

        return height + 1;
    }

    /**
     * 114. 二叉树展开为链表
     * 给定一个二叉树，原地将它展开为一个单链表。
     */
    public void flatten(TreeNode root) {
//        if(root == null){
//            return;
//        }
//
//        TreeNode oldRight = root.right;
//
//        root.right = root.left;
//
//        root.left = null;
//
//        TreeNode rightRoot = root;
//        while (rightRoot.right != null){
//            rightRoot = rightRoot.right;
//        }
//
//        rightRoot.right = oldRight;
//
//        flatten(root.right);
        List<TreeNode> list = new ArrayList<>();
        preOrderTraverse(root,list);
        for (int i = 1; i < list.size(); i++) {
            TreeNode pre = list.get(i - 1),curr = list.get(i);
            pre.left = null;
            pre.right = curr;
        }
        System.out.println(root);

    }

    public void preOrderTraverse(TreeNode root,List<TreeNode> list){
        if(root == null){
            return;
        }
        list.add(root);
        preOrderTraverse(root.left,list);
        preOrderTraverse(root.right,list);
    }

    /**
     * 100. 相同的树
     * 给定两个二叉树，编写一个函数来检验它们是否相同。
     *
     * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
     */
    @SuppressWarnings("unused")
    public boolean isSameTree(TreeNode p, TreeNode q) {
//        if(p == null && q == null){
//            return true;
//        } else if(p == null || q == null){
//            return false;
//        } else if(p.val != q.val){
//            return false;
//        }
//        return isSameTree(p.left,q.left) && isSameTree(p.right,q.right);

        List<Integer> tree1 = new ArrayList<>();
        List<Integer> tree2 = new ArrayList<>();
        preOrderTraverseByIntList(p,tree1);
        preOrderTraverseByIntList(q,tree2);
        return tree1.equals(tree2);
    }

    public void preOrderTraverseByIntList(TreeNode root,List<Integer> list){
        if(root == null){
            list.add(null);
            return;
        }
        list.add(root.val);
        preOrderTraverseByIntList(root.left,list);
        preOrderTraverseByIntList(root.right,list);
    }

    /**
     * 99. 恢复二叉搜索树
     * 二叉搜索树中的两个节点被错误地交换。
     *
     * 请在不改变其结构的情况下，恢复这棵树。
     */
    @SuppressWarnings("unused")
    public void recoverTree(TreeNode root) {
        List<TreeNode> nodeList = new ArrayList<>();
        inorderTraverseByTreeList(root,nodeList);
        /**
         * 查找哪两个节点需要调换
         */
        TreeNode x = null;
        TreeNode y = null;
        for (int i = 0;i < nodeList.size() - 1;i++){
            if(nodeList.get(i).val > nodeList.get(i + 1).val){
                if(x == null){
                    x = nodeList.get(i);
                }
                y = nodeList.get(i + 1);
            }
        }
        /**
         * 更改两个节点的值
         */
        if(x != null && y != null){
            int tem = x.val;
            x.val = y.val;
            y.val = tem;
        }
    }

    /**
     * 中序遍历二叉树，把结果保存到保存节点的list里面
     */
    public void inorderTraverseByTreeList(TreeNode node,List<TreeNode> list){
        if(node == null){
            return;
        }
        inorderTraverseByTreeList(node.left,list);
        list.add(node);
        inorderTraverseByTreeList(node.right,list);
    }
}
