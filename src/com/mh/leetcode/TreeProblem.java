package com.mh.leetcode;

import com.mh.leetcode.bean.ListNode;
import com.mh.leetcode.bean.Node;
import com.mh.leetcode.bean.TreeNode;

import java.util.*;

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
        TreeNode treeNode = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        left.left = new TreeNode(3);
        left.right = new TreeNode(4);
        treeNode.left = left;

        TreeNode right = new TreeNode(2);
        right.left = new TreeNode(4);
        right.right = new TreeNode(3);
        treeNode.right = right;

//        treeProblem.flatten(node);
//        System.out.println(treeProblem.postOrderTraversal(node));

//        System.out.println(treeProblem.sortedArrayToBST(new int[]{-10,-3,0,5,9}));
//        System.out.println(treeProblem.rangeSumBST(node,3,6));
        Node listNode = new Node();
        List<Node> child1 = new ArrayList<>();

        Node node3 = new Node(3);
        List<Node> child = new ArrayList<>();
        child.add(new Node(5));
        child.add(new Node(6));
        node3.children = child;

        Node node2 = new Node(2);
        Node node4 = new Node(4);
        child1.add(node3);
        child1.add(node2);
        child1.add(node4);
        listNode.children = child1;
//        System.out.println(treeProblem.maxDepth(listNode));
        System.out.println(treeProblem.isSymmetric(treeNode));
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
     * 144. 二叉树的前序遍历
     * 二叉树前序遍历（迭代）
     */
    public List<Integer> preOrderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> output = new ArrayList<>();
        if (root == null) {
            return output;
        }

        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            output.add(node.val);
            if (node.right != null) {
                stack.add(node.right);
            }
            if (node.left != null) {
                stack.add(node.left);
            }
        }
        return output;
    }

    /**
     * 99
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
     * 145. 二叉树的后序遍历
     * 给定一个二叉树，返回它的 后序 遍历
     * 迭代方法
     */
    public List<Integer> postOrderTraversal(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        LinkedList<Integer> output = new LinkedList<>();
        if (root == null) {
            return output;
        }

        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pollLast();
            output.addFirst(node.val);
            if (node.left != null) {
                stack.add(node.left);
            }
            if (node.right != null) {
                stack.add(node.right);
            }
        }
        return output;
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
     * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
     *
     * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return get(nums,0,nums.length-1);
    }

    public TreeNode get(int[] nums,int start ,int end){
        if(start > end){
            return null;
        }

        int mid = (start + end) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = get(nums,start,mid - 1);
        root.right = get(nums,mid + 1,end);

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

    /**
     * 中序遍历二叉树，把节点的值保存到list里面
     */
    public void inorderTraverseByList(TreeNode node,List<Integer> list){
        if(node == null){
            return;
        }
        inorderTraverseByList(node.left,list);
        list.add(node.val);
        inorderTraverseByList(node.right,list);
    }

    /**
     * 124. 二叉树中的最大路径和
     * 给定一个非空二叉树，返回其最大路径和。
     *
     * 本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
     */
    int totalPath = Integer.MIN_VALUE;
    @SuppressWarnings("unused")
    public int maxPathSum(TreeNode root) {
        getNodeGain(root);
        return totalPath;
    }

    public int getNodeGain(TreeNode node){
        if(node == null){
            return 0;
        }

        int rootVal = node.val;

        // 获取左节点的值，如果小于0则不用计算。肯定不会是最大的。
        int leftVal = Math.max(getNodeGain(node.left),0);
        int rightVal = Math.max(getNodeGain(node.right),0);

        // 计算该节点与它的子节点的路径和
        int totalVal = leftVal + rightVal + rootVal;

        // 比较最大的路径，并保存
        totalPath = Math.max(totalVal,totalPath);

        // 返回该节点 + 最大的子节点的路径，即为该节点能提供的最大路径
        return Math.max(leftVal,rightVal) + rootVal;
    }

    /**
     * 700. 二叉搜索树中的搜索
     * 给定二叉搜索树（BST）的根节点和一个值。 你需要在BST中找到节点值等于给定值的节点。 返回以该节点为根的子树。 如果节点不存在，则返回 NULL。
     */
    TreeNode treeNode = null;
    @SuppressWarnings("unused")
    public TreeNode searchBST(TreeNode root, int val) {
        if(root == null){
            return null;
        }
        if(root.val > val){
            return searchBST(root.left,val);
        }else if(root.val < val){
            return searchBST(root.right,val);
        }else {
            return root;
        }

//        inorderTraversalV700(root,val);
//        return treeNode;
    }

    @SuppressWarnings("unused")
    public void inorderTraversalV700(TreeNode node,int val){
        if(node == null){
            return;
        }
        inorderTraversalV700(node.left,val);
        if(node.val == val){
            treeNode = node;
        }
        if(node.val > val){
            return;
        }
        inorderTraversalV700(node.right,val);
    }

    /**
     * 面试题 04.08. 首个共同祖先
     * 设计并实现一个算法，找出二叉树中某两个节点的第一个共同祖先。不得将其他的节点存储在另外的数据结构中。注意：这不一定是二叉搜索树。
     */
    @SuppressWarnings("unused")
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q){
            return root;
        }
        TreeNode leftNode = lowestCommonAncestor(root.left, p, q);
        TreeNode rightNode = lowestCommonAncestor(root.right, p, q);

        if(leftNode != null && rightNode != null){
            return root;
        }
        return leftNode == null ? rightNode : leftNode;
    }

    /**
     * 590. N叉树的后序遍历
     * 给定一个 N 叉树，返回其节点值的后序遍历。
     */
    public List<Integer> postorder(Node root) {
        List<Integer> list = new ArrayList<>();
        postOrderByDiGui(root,list);
        return list;
    }

    private void postOrderByDiGui(Node root,List<Integer> list) {
        if(root == null){
            return;
        }
        List<Node> children = root.children;
        for (Node child : children) {
            preOrderByDiGui(child,list);
        }
        list.add(root.val);
    }

    /**
     * 589. N叉树的前序遍历
     * 给定一个 N 叉树，返回其节点值的前序遍历。
     */
    @SuppressWarnings("unused")
    public List<Integer> preorder(Node root) {
        List<Integer> list = new ArrayList<>();
        preOrderByDiGui(root,list);
        return list;
    }

    private void preOrderByDiGui(Node root,List<Integer> list) {
        if(root == null){
            return;
        }
        list.add(root.val);
        List<Node> children = root.children;
        for (Node child : children) {
            preOrderByDiGui(child,list);
        }
    }

    /**
     * 437. 路径总和 III
     * 给定一个二叉树，它的每个结点都存放着一个整数值。
     * 找出路径和等于给定数值的路径总数。
     * 路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
     */
//    int totalPath = 0;
    @SuppressWarnings("unused")
    public int pathSum(TreeNode root, int sum) {
        if(root == null){
            return 0;
        }
        sum(root,sum);
        pathSum(root.left,sum);
        pathSum(root.right,sum);

        return totalPath;
    }

    public void sum(TreeNode root, int sum){
        if(root == null){
            return;
        }
        sum -= root.val;
        if(sum == 0){
            totalPath++;
        }
        sum(root.left,sum);
        sum(root.right,sum);
    }

    /**
     * 104. 二叉树的最大深度
     * 给定一个二叉树，找出其最大深度。
     *
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     */
    public int maxDepth(TreeNode root) {
        return getMaxHeight(root);
    }

    public int getMaxHeight(TreeNode node){
        if(node == null){
            return 0;
        }

        int leftHeight = getMaxHeight(node.left);
        int rightHeight = getMaxHeight(node.right);

        return Math.max(leftHeight,rightHeight) + 1;
    }

    /**
     * 938. 二叉搜索树的范围和
     * 给定二叉搜索树的根结点 root，返回 L 和 R（含）之间的所有结点的值的和。
     */
    int sum = 0;
    public int rangeSumBST(TreeNode root, int L, int R) {
        sumBstByInOrder(root,L,R);
        return sum;
    }

    public void sumBstByInOrder(TreeNode root,int L,int R){
        if(root == null){
            return;
        }

        sumBstByInOrder(root.left,L,R);
        int rootVal = root.val;
        if(rootVal >= L && rootVal <= R){
            sum += rootVal;
        }else if (rootVal > R){
            return;
        }
        sumBstByInOrder(root.right,L,R);
    }

    /**
     * 559. N叉树的最大深度
     * 给定一个 N 叉树，找到其最大深度。
     *
     * 最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。
     */
    public int maxDepth(Node root) {
        if(root == null){
            return 0;
        }

        if(root.children.isEmpty()){
            return 1;
        }

        List<Node> children = root.children;
        List<Integer> list = new ArrayList<>();
        for (Node child : children) {
            list.add(maxDepth(child));
        }

        return Collections.max(list) + 1;
    }

    /**
     * 101. 对称二叉树
     * 给定一个二叉树，检查它是否是镜像对称的
     */
    public boolean isSymmetric(TreeNode root) {
        return isSymmetric(root,root);
    }

    public boolean isSymmetric(TreeNode leftNode,TreeNode rightNode) {
        if(leftNode == null && rightNode == null){
            return true;
        }
        if(leftNode == null || rightNode == null){
            return false;
        }

        return leftNode.val == rightNode.val &&
                isSymmetric(leftNode.left , rightNode.right) &&
                isSymmetric(leftNode.right,rightNode.left);
    }

    /**
     * 98. 验证二叉搜索树
     * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
     *
     * 假设一个二叉搜索树具有如下特征：
     *
     * 节点的左子树只包含小于当前节点的数。
     * 节点的右子树只包含大于当前节点的数。
     * 所有左子树和右子树自身必须也是二叉搜索树。
     */
    long pre = Long.MIN_VALUE;
    boolean ans = true;
    public boolean isValidBST(TreeNode root) {
//        List<Integer> list = new ArrayList<>();
//        inorderTraverseByList(root,list);
//
//        if(list.isEmpty() || list.size() == 1){
//            return true;
//        }
//
//        int max = list.get(0);
//        for (int i = 1; i < list.size(); i++) {
//            if(list.get(i) <= max){
//                return false;
//            }
//            max = list.get(i);
//        }
//        return true;

        if(root == null){
            return true;
        }

        if(!isValidBST(root.left)){
            return false;
        }
        if(root.val <= pre){
            return false;
        }
        pre = root.val;
        return isValidBST(root.right);
    }

//    public void inorderTraverseByListV2(TreeNode node,List<Integer> list){
//        if(node == null){
//            return;
//        }
//        inorderTraverseByListV2(node.left,list);
//        if(!list.isEmpty() && list.get(list.size() - 1) > node.val){
//            ans = false;
//            return;
//        }
//        inorderTraverseByListV2(node.right,list);
//    }
}
