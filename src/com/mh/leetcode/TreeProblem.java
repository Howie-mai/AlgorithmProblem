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

        TreeNode treeNode1 = new TreeNode(-2);
//        TreeNode left1 = new TreeNode(3);
//        left1.left = new TreeNode(5);
//        left.right = new TreeNode(3);
//        treeNode1.left = left1;

        TreeNode right1 = new TreeNode(-3);
//        right.left = new TreeNode(15);
//        right.right = new TreeNode(6);
        treeNode1.right = right1;

        TreeNode treeNode2 = new TreeNode(2);
        TreeNode left2 = new TreeNode(1);
//        left.left = new TreeNode(5);
        left2.right = new TreeNode(4);
        treeNode2.left = left2;

        TreeNode right2 = new TreeNode(3);
//        right.left = new TreeNode(15);
        right2.right = new TreeNode(7);
        treeNode2.right = right2;
//        System.out.println(treeProblem.mergeTrees(treeNode1,treeNode2));
//        System.out.println(treeProblem.convertBST(treeNode));
//        System.out.println(treeProblem.preOrderTraversal(treeNode));
//        treeProblem.flatten(treeNode);
//        System.out.println(treeProblem.postOrderTraversal(node));

//        System.out.println(treeProblem.sortedArrayToBST(new int[]{-10,-3,0,5,9}));
//        System.out.println(treeProblem.rangeSumBST(node,3,6));
        System.out.println(treeProblem.pathSumII(treeNode1,-5));
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
//        System.out.println(treeProblem.isSymmetric(treeNode));
//        System.out.println(treeProblem.binaryTreePaths(treeNode));
//        System.out.println(treeProblem.levelOrderBottom(treeNode));
//        System.out.println(treeProblem.averageOfLevels(treeNode));

        System.out.println(treeProblem.buildTree(new int[]{9,3,15,20,7},new int[]{9,15,7,20,3}));

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
     * <p>
     * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return get(nums, 0, nums.length - 1);
    }

    public TreeNode get(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = (start + end) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = get(nums, start, mid - 1);
        root.right = get(nums, mid + 1, end);

        return root;
    }

    /**
     * 111. 二叉树的最小深度
     * 给定一个二叉树，找出其最小深度。
     * <p>
     * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            return 1;
        }

        int height = Integer.MAX_VALUE;
        if (root.left != null) {
            height = Math.min(minDepth(root.left), height);
        }

        if (root.right != null) {
            height = Math.min(minDepth(root.right), height);
        }

        return height + 1;
    }

    /**
     * 111 题 使用广度优先搜索（BFS）
     */
    public int minDepthByBFS(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        int depth = 1;
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (poll.left == null && poll.right == null) {
                    return depth;
                }

                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
            }
            depth++;
        }
        return depth;
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
        preOrderTraverse(root, list);
        for (int i = 1; i < list.size(); i++) {
            TreeNode pre = list.get(i - 1), curr = list.get(i);
            pre.left = null;
            pre.right = curr;
        }
        System.out.println(root);

    }

    public void preOrderTraverse(TreeNode root, List<TreeNode> list) {
        if (root == null) {
            return;
        }
        preOrderTraverse(root.left, list);
        list.add(root);
        preOrderTraverse(root.right, list);
    }

    /**
     * 面试题 17.12. BiNode
     * 二叉树数据结构TreeNode可用来表示单向链表（其中left置空，right为下一个链表节点）。
     * 实现一个方法，把二叉搜索树转换为单向链表，要求依然符合二叉搜索树的性质，转换操作应是原址的，也就是在原始的二叉搜索树上直接修改。
     * <p>
     * 返回转换后的单向链表的头节点。
     */
    TreeNode head = new TreeNode(-1);
    TreeNode perv = null;

    public TreeNode convertBiNode(TreeNode root) {
        preOrderTraverse(root);
        return head.right;
    }

    public void preOrderTraverse(TreeNode root) {
        if (root == null) {
            return;
        }
        preOrderTraverse(root.left);
        // 第一个节点
        if (perv == null) {
            // 记录第一个节点
            perv = root;
            // 记录它，它将作为单链表的表头
            head.right = root;
        } else {
            // 第一个节点之后的节点
            // 前一个节点的右指针指向当前节点
            perv.right = root;
            // 更新perv指向
            perv = root;
        }
        root.left = null;
        preOrderTraverse(root.right);
    }

    /**
     * 100. 相同的树
     * 给定两个二叉树，编写一个函数来检验它们是否相同。
     * <p>
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
        preOrderTraverseByIntList(p, tree1);
        preOrderTraverseByIntList(q, tree2);
        return tree1.equals(tree2);
    }

    public void preOrderTraverseByIntList(TreeNode root, List<Integer> list) {
        if (root == null) {
            list.add(null);
            return;
        }
        list.add(root.val);
        preOrderTraverseByIntList(root.left, list);
        preOrderTraverseByIntList(root.right, list);
    }

    /**
     * 99. 恢复二叉搜索树
     * 二叉搜索树中的两个节点被错误地交换。
     * <p>
     * 请在不改变其结构的情况下，恢复这棵树。
     */
    @SuppressWarnings("unused")
    public void recoverTree(TreeNode root) {
        List<TreeNode> nodeList = new ArrayList<>();
        inorderTraverseByTreeList(root, nodeList);
        /**
         * 查找哪两个节点需要调换
         */
        TreeNode x = null;
        TreeNode y = null;
        for (int i = 0; i < nodeList.size() - 1; i++) {
            if (nodeList.get(i).val > nodeList.get(i + 1).val) {
                if (x == null) {
                    x = nodeList.get(i);
                }
                y = nodeList.get(i + 1);
            }
        }
        /**
         * 更改两个节点的值
         */
        if (x != null && y != null) {
            int tem = x.val;
            x.val = y.val;
            y.val = tem;
        }
    }

    /**
     * 中序遍历二叉树，把结果保存到保存节点的list里面
     */
    public void inorderTraverseByTreeList(TreeNode node, List<TreeNode> list) {
        if (node == null) {
            return;
        }
        inorderTraverseByTreeList(node.left, list);
        list.add(node);
        inorderTraverseByTreeList(node.right, list);
    }

    /**
     * 中序遍历二叉树，把节点的值保存到list里面
     */
    public void inorderTraverseByList(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        inorderTraverseByList(node.left, list);
        list.add(node.val);
        inorderTraverseByList(node.right, list);
    }

    /**
     * 124. 二叉树中的最大路径和
     * 给定一个非空二叉树，返回其最大路径和。
     * <p>
     * 本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
     */
    int totalPath = Integer.MIN_VALUE;

    @SuppressWarnings("unused")
    public int maxPathSum(TreeNode root) {
        getNodeGain(root);
        return totalPath;
    }

    public int getNodeGain(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int rootVal = node.val;

        // 获取左节点的值，如果小于0则不用计算。肯定不会是最大的。
        int leftVal = Math.max(getNodeGain(node.left), 0);
        int rightVal = Math.max(getNodeGain(node.right), 0);

        // 计算该节点与它的子节点的路径和
        int totalVal = leftVal + rightVal + rootVal;

        // 比较最大的路径，并保存
        totalPath = Math.max(totalVal, totalPath);

        // 返回该节点 + 最大的子节点的路径，即为该节点能提供的最大路径
        return Math.max(leftVal, rightVal) + rootVal;
    }

    /**
     * 700. 二叉搜索树中的搜索
     * 给定二叉搜索树（BST）的根节点和一个值。 你需要在BST中找到节点值等于给定值的节点。 返回以该节点为根的子树。 如果节点不存在，则返回 NULL。
     */
    TreeNode treeNode = null;

    @SuppressWarnings("unused")
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (root.val > val) {
            return searchBST(root.left, val);
        } else if (root.val < val) {
            return searchBST(root.right, val);
        } else {
            return root;
        }

//        inorderTraversalV700(root,val);
//        return treeNode;
    }

    @SuppressWarnings("unused")
    public void inorderTraversalV700(TreeNode node, int val) {
        if (node == null) {
            return;
        }
        inorderTraversalV700(node.left, val);
        if (node.val == val) {
            treeNode = node;
        }
        if (node.val > val) {
            return;
        }
        inorderTraversalV700(node.right, val);
    }

    /**
     * 面试题 04.08. 首个共同祖先
     * 设计并实现一个算法，找出二叉树中某两个节点的第一个共同祖先。不得将其他的节点存储在另外的数据结构中。注意：这不一定是二叉搜索树。
     */
    @SuppressWarnings("unused")
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode leftNode = lowestCommonAncestor(root.left, p, q);
        TreeNode rightNode = lowestCommonAncestor(root.right, p, q);

        if (leftNode != null && rightNode != null) {
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
        postOrderByDiGui(root, list);
        return list;
    }

    private void postOrderByDiGui(Node root, List<Integer> list) {
        if (root == null) {
            return;
        }
        List<Node> children = root.children;
        for (Node child : children) {
            preOrderByDiGui(child, list);
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
        preOrderByDiGui(root, list);
        return list;
    }

    private void preOrderByDiGui(Node root, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        List<Node> children = root.children;
        for (Node child : children) {
            preOrderByDiGui(child, list);
        }
    }

    /**
     * 113. 路径总和 II
     * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
     */
    public List<List<Integer>> pathSumII(TreeNode root, int sum) {
        backTrackBy113(root,sum);
        return ansBy113;
    }

    List<List<Integer>> ansBy113 = new ArrayList<>();
    Deque<Integer> tempListBy113 = new ArrayDeque<>();
    public void backTrackBy113(TreeNode root, int sum){

        sum -= root.val;
        tempListBy113.add(root.val);
        if(sum == 0 && root.left == null && root.right == null){
            ansBy113.add(new ArrayList<>(tempListBy113));
            tempListBy113.removeLast();
            return;
        }

        if(root.left != null){
            backTrackBy113(root.left,sum);
        }

        if(root.right != null){
            backTrackBy113(root.right,sum);
        }
        tempListBy113.removeLast();
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
        if (root == null) {
            return 0;
        }
        sum(root, sum);
        pathSum(root.left, sum);
        pathSum(root.right, sum);

        return totalPath;
    }

    public void sum(TreeNode root, int sum) {
        if (root == null) {
            return;
        }
        sum -= root.val;
        if (sum == 0) {
            totalPath++;
        }
        sum(root.left, sum);
        sum(root.right, sum);
    }

    /**
     * 104. 二叉树的最大深度
     * 给定一个二叉树，找出其最大深度。
     * <p>
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     */
    public int maxDepth(TreeNode root) {
        return getMaxHeight(root);
    }

    public int getMaxHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = getMaxHeight(node.left);
        int rightHeight = getMaxHeight(node.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * 938. 二叉搜索树的范围和
     * 给定二叉搜索树的根结点 root，返回 L 和 R（含）之间的所有结点的值的和。
     */
    int sum = 0;

    public int rangeSumBST(TreeNode root, int L, int R) {
        sumBstByInOrder(root, L, R);
        return sum;
    }

    public void sumBstByInOrder(TreeNode root, int L, int R) {
        if (root == null) {
            return;
        }

        sumBstByInOrder(root.left, L, R);
        int rootVal = root.val;
        if (rootVal >= L && rootVal <= R) {
            sum += rootVal;
        } else if (rootVal > R) {
            return;
        }
        sumBstByInOrder(root.right, L, R);
    }

    /**
     * 559. N叉树的最大深度
     * 给定一个 N 叉树，找到其最大深度。
     * <p>
     * 最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。
     */
    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }

        if (root.children.isEmpty()) {
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
        return isSymmetric(root, root);
    }

    public boolean isSymmetric(TreeNode leftNode, TreeNode rightNode) {
        if (leftNode == null && rightNode == null) {
            return true;
        }
        if (leftNode == null || rightNode == null) {
            return false;
        }

        return leftNode.val == rightNode.val &&
                isSymmetric(leftNode.left, rightNode.right) &&
                isSymmetric(leftNode.right, rightNode.left);
    }

    /**
     * 98. 验证二叉搜索树
     * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
     * <p>
     * 假设一个二叉搜索树具有如下特征：
     * <p>
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

        if (root == null) {
            return true;
        }

        if (!isValidBST(root.left)) {
            return false;
        }
        if (root.val <= pre) {
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

    /**
     * 剑指 Offer 26. 树的子结构
     * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
     * <p>
     * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        return (A != null && B != null) && (recur(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B));
    }

    public boolean recur(TreeNode A, TreeNode B) {
        if (B == null) {
            return true;
        }
        if (A == null || A.val != B.val) {
            return false;
        }
        return recur(A.left, B.left) && recur(A.right, B.right);
    }

    /**
     * 257. 二叉树的所有路径
     * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
     * <p>
     * 说明: 叶子节点是指没有子节点的节点。
     * <p>
     * 输出: ["1->2->5", "1->3"]
     */
    List<String> resultList = new ArrayList<>();
    List<Integer> list = new ArrayList<>();

    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) {
            return resultList;
        }
        dfs(root);
        return resultList;
    }

    public void dfs(TreeNode root) {
        list.add(root.val);

        if (root.left == null && root.right == null) {
            StringBuilder sb = new StringBuilder();
            for (Integer integer : list) {
                sb.append(integer).append("->");
            }
            resultList.add(sb.delete(sb.length() - 2, sb.length()).toString());
            return;
        }

        if (root.left != null) {
            dfs(root.left);
            list.remove(list.size() - 1);
        }

        if (root.right != null) {
            dfs(root.right);
            list.remove(list.size() - 1);
        }
    }

    /**
     * 103. 二叉树的锯齿形层次遍历
     * 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<List<Integer>>();
        }
        List<List<Integer>> results = new ArrayList<>();
        DFS(root, 0, results);
        return results;
    }

    protected void DFS(TreeNode node, int level, List<List<Integer>> results) {
        if (level >= results.size()) {
            LinkedList<Integer> newLevel = new LinkedList<>();
            newLevel.add(node.val);
            results.add(newLevel);
        } else {
            if (level % 2 == 0) {
                results.get(level).add(node.val);
            } else {
                results.get(level).add(0, node.val);
            }
        }

        if (node.left != null) {
            DFS(node.left, level + 1, results);
        }
        if (node.right != null) {
            DFS(node.right, level + 1, results);
        }
    }


    /**
     * 107. 二叉树的层次遍历 II
     * 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        levelHelper(res, root, 0);
        return res;
    }

    public void levelHelper(List<List<Integer>> list, TreeNode root, int level) {
        //边界条件判断
        if (root == null) {
            return;
        }
        if (level >= list.size()) {
            list.add(0, new ArrayList<>());
        }
        //这里就相当于从后往前打印了
        list.get(list.size() - level - 1).add(root.val);
        //当前节点访问完之后，再使用递归的方式分别访问当前节点的左右子节点
        levelHelper(list, root.left, level + 1);
        levelHelper(list, root.right, level + 1);
    }

    /**
     * 637. 二叉树的层平均值
     * 给定一个非空二叉树, 返回一个由每层节点平均值组成的数组。
     */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Integer> levels = new ArrayList<>();
        List<Double> res = new ArrayList<>();
        dfs(res, levels, root, 0);
        return res;
    }

    public void dfs(List<Double> list, List<Integer> levels, TreeNode root, int level) {
        //边界条件判断
        if (root == null) {
            return;
        }
        if (level >= list.size()) {
            list.add(0.0);
            levels.add(0);
        }

        int count = levels.get(level);
        double levelTotal = list.get(level) * count;
        levelTotal += root.val;
        list.set(level, levelTotal / (count + 1));
        levels.set(level, count + 1);

        //当前节点访问完之后，再使用递归的方式分别访问当前节点的左右子节点
        dfs(list, levels, root.left, level + 1);
        dfs(list, levels, root.right, level + 1);
    }

    /**
     * 993. 二叉树的堂兄弟节点
     * 在二叉树中，根节点位于深度 0 处，每个深度为 k 的节点的子节点位于深度 k+1 处。
     * 如果二叉树的两个节点深度相同，但父节点不同，则它们是一对堂兄弟节点。
     * 我们给出了具有唯一值的二叉树的根节点 root，以及树中两个不同节点的值 x 和 y。
     * 只有与值 x 和 y 对应的节点是堂兄弟节点时，才返回 true。否则，返回 false。
     */
    public boolean isCousins(TreeNode root, int x, int y) {
        dfs(root, null, x, y, 1);
        return parent2 != parent1 && len1 == len2;
    }

    TreeNode parent1, parent2;
    int len1, len2;

    public void dfs(TreeNode node, TreeNode parent, int x, int y, int depth) {
        if (parent1 != null && parent2 != null) {
            return;
        }
        if (node.val == x) {
            parent1 = parent;
            len1 = depth;
        } else if (node.val == y) {
            parent2 = parent;
            len2 = depth;
        } else {
            if (node.left != null) {
                dfs(node.left, node, x, y, depth + 1);
            }
            if (node.right != null) {
                dfs(node.right, node, x, y, depth + 1);
            }
        }
    }

    /**
     * 226. 翻转二叉树
     * 翻转一棵二叉树。
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode left = invertTree(root.left);
        root.left = invertTree(root.right);
        root.right = left;

        return root;
    }

    /**
     * 538. 把二叉搜索树转换为累加树
     * 给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，
     * 使得每个节点的值是原来的节点值加上所有大于它的节点值之和。
     */
    int sumBy538 = 0;

    public TreeNode convertBST(TreeNode root) {
        if (root != null) {
            convertBST(root.right);
            sumBy538 += root.val;
            root.val = sumBy538;
            convertBST(root.left);
        }
        return root;
    }

    /**
     * 968. 监控二叉树
     * 给定一个二叉树，我们在树的节点上安装摄像头。
     * 节点上的每个摄影头都可以监视其父对象、自身及其直接子对象。
     * 计算监控树的所有节点所需的最小摄像头数量。
     */
    public int minCameraCover(TreeNode root) {
        /**
         * 后序遍历遍历 ，设置三种状态
         * 0：待被监控
         * 1：已被监控
         * 2：需要安装监控
         */

        // 如果返回的root状态为0 root需要装监控
        if (installCamera(root) == 0) {
            ansBy968++;
        }
        return ansBy968;
    }

    int ansBy968 = 0;

    public int installCamera(TreeNode node) {

        // 为空返回0
        if (node == null) {
            return 1;
        }

        //左右子树状态
        int right = installCamera(node.right);
        int left = installCamera(node.left);

        // 有6中情况：00，01，02，11，12，22

        // 当左右子树状态其中一个为0的时候，表示该父母节点需要装监控
        if (left == 0 || right == 0) {
            ansBy968++;
            return 2;
        }

        // 当左右子树都为1的时候，则该父母节点需要被监视
        if (left == 1 && right == 1) {
            return 0;
        }

        // 当其中一个子节点装了监控，该父母节点为已监控状态
        if (left == 2 || right == 2) {
            return 1;
        }

        // 随便返回一个数 ，程序走不到这里
        return 0;
    }

    /**
     * 617. 合并二叉树
     * 给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
     * 你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，
     * 否则不为 NULL 的节点将直接作为新二叉树的节点。
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {

        if(t1 == null){
            return t2;
        }

        if(t2 == null){
            return t1;
        }

        TreeNode root = new TreeNode(t1.val + t2.val);
        root.left = mergeTrees(t1.left,t2.left);
        root.right = mergeTrees(t1.right,t2.right);
        return root;
    }

    /**
     * 501. 二叉搜索树中的众数
     * 给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。
     * 不使用额外的空间吗(假设由递归产生的隐式调用栈的开销不被计算在内）
     */
    public int[] findMode(TreeNode root) {
        traversalBy501(root);
        int[] ans = new int[ansListBy501.size()];
        for (int i = 0; i < ansListBy501.size(); i++) {
            ans[i] = ansListBy501.get(i);
        }
        return ans;
    }

    int currValBy501 = 0;
    int currCountBy501 = 0;
    int maxCountBy501 = 0;
    List<Integer> ansListBy501 = new ArrayList<>();
    public void traversalBy501(TreeNode node){
        if(node == null){
            return;
        }

        traversalBy501(node.left);

        int nodeVal = node.val;
        if(nodeVal == currValBy501){
            // 当前值与前一个值相同，count++;
            currCountBy501++;
        }else {
            currValBy501 = nodeVal;
            currCountBy501 = 1;
        }

        if(currCountBy501 > maxCountBy501){
            // 目前的众数比之前最大的还大，则清空列表，并加上
            list.clear();
            list.add(nodeVal);
            maxCountBy501 = currCountBy501;
        }else if(currCountBy501 == maxCountBy501){
            list.add(nodeVal);
        }

        traversalBy501(node.right);
    }

    /**
     * 106. 从中序与后序遍历序列构造二叉树
     * 根据一棵树的中序遍历与后序遍历构造二叉树。
     * 注意:
     * 你可以假设树中没有重复的元素。
     * 中序遍历 inorder = [9,3,15,20,7]
     * 后序遍历 postorder = [9,15,7,20,3]
     */
    public TreeNode buildTree(int[] inOrder, int[] postOrder) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < inOrder.length; i++) {
            map.put(inOrder[i],i);
        }
        return buildTree(map,inOrder,0,inOrder.length - 1,postOrder,0,postOrder.length - 1);
    }

    public TreeNode buildTree(HashMap<Integer,Integer> map,
                              int[] inOrder,int inStart,int inEnd,int[] postOrder,int postStart,int postEnd){
        if(inStart > inEnd || postStart > postEnd){
            return null;
        }

        // 后序遍历数组中最后一个为根节点
        int rootVal = postOrder[postEnd];
        TreeNode root = new TreeNode(rootVal);
        if(postStart == postEnd || inStart == inEnd){
            return root;
        }

        int index = map.get(rootVal);
        int leftNodes = index - inStart,rightNodes = inEnd - index;
        root.left = buildTree(map,inOrder,inStart,index - 1,
                postOrder,postStart,postStart + leftNodes - 1);

        root.right = buildTree(map,inOrder,index + 1,index + rightNodes,
                postOrder,postEnd - rightNodes,postEnd - 1);

        return root;
    }

    /**
     * 117. 填充每个节点的下一个右侧节点指针 II
     * 给定一个二叉树
     *
     * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
     *
     * 初始状态下，所有 next 指针都被设置为 NULL。
     */
    public Node connect(Node root) {
        levelTraverse(root,0);
        return root;
    }

    List<Deque<Node>> ansBy117 = new ArrayList<>();
    public void levelTraverse(Node root,int level){
        if(root == null){
            return;
        }

        if(level >= ansBy117.size()){
            ansBy117.add(new ArrayDeque<>());
        }

        levelTraverse(root.right,level + 1);

        Deque<Node> nodes = ansBy117.get(level);
        if(!nodes.isEmpty()){
            root.next = nodes.getLast();
        }
        nodes.add(root);

        levelTraverse(root.left,level + 1);
    }

}
