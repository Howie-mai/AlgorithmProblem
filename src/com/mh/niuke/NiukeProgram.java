package com.mh.niuke;

import com.mh.niuke.bean.ListNode;
import com.mh.niuke.bean.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @className:NiukeProgram
 * @description TDOO
 * @author: mh
 * @create: 2019-08-27 12:22
 */
public class NiukeProgram {
    public static void main(String[] args) {
        NiukeProgram niukeProgram = new NiukeProgram();
        niukeProgram.reConstructBinaryTree(new int[]{3, 9, 20, 5, 7}, new int[]{9, 3, 15, 20, 7});
    }

    /**
     * 1、
     * 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
     * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     *
     * @return boolean
     * @author mh
     * @date 2019/8/27
     */
    @SuppressWarnings("unused")
    public boolean find(int target, int[][] array) {
        if (array.length == 0) {
            return false;
        }
        int rowCount = array[0].length - 1;
        int i = rowCount - 1;
        int j = 0;
        while (i >= 0 && j < array.length) {
            int temp = array[i][j];
            if (target > temp) {
                j++;
            }
            if (target < temp) {
                i--;
            }
            if (temp == target) {
                return true;
            }
        }
        return false;
    }

    /**
     * 2、
     * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。
     * 例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
     *
     * @return java.lang.String
     * @author mh
     * @date 2019/8/27
     */
    @SuppressWarnings("unused")
    public String replaceSpace(StringBuffer str) {
        String newStr1 = str.toString();
        return newStr1.replace(" ", "%20");
    }

    /**
     * 3、
     * 输入一个链表，按链表从尾到头的顺序返回一个ArrayList
     * 用栈解决
     *
     * @return java.util.ArrayList<java.lang.Integer>
     * @author mh
     * @date 2019/8/27
     */
    @SuppressWarnings("unused")
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> list = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        while (listNode != null) {
            stack.push(listNode.val);
            listNode = listNode.next;
        }
        while (!stack.empty()) {
            list.add(stack.pop());
        }
        return list;
    }

    /**
     * 3、
     * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
     * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
     * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
     *
     * @return TreeNode
     * @author mh
     * @date 2019/8/27
     */
    @SuppressWarnings("unused")
    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        Map<Integer, Integer> map = new HashMap<>();
        int inLen = 0;
        for (int i = 0; i < in.length; i++) {
            map.put(in[i], i);
            inLen++;
        }
        return reConstructBinaryTree(pre, 0, pre.length - 1, in, 0, inLen - 1, map);
    }

    /**
     * 根据根节点分为左子树和有子树遍历
     *
     * @author mh
     * @date 2019/8/27
     */
    @SuppressWarnings("unused")
    private TreeNode reConstructBinaryTree(int[] pre, int startPre, int endPre, int[] in, int startIn, int endIn,
                                           Map<Integer, Integer> indexMap) {
        if (startPre > endPre || startIn > endIn) {
            return null;
        }

        int rootVal = pre[startPre];
        TreeNode root = new TreeNode(rootVal);
        if (startPre == endPre) {
            return root;
        }
        int i = indexMap.get(rootVal);
        // 左右子树的数量
        int leftNodes = i - startIn, rightNodes = endIn - startIn;
        root.left = reConstructBinaryTree(pre, startPre + 1, startPre + leftNodes,
                in, startIn, i - 1, indexMap);
        root.right = reConstructBinaryTree(pre, endPre - rightNodes + 1, endPre,
                in, i + 1, endIn, indexMap);
        return root;
    }

    /**
     * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
     *
     * @author mh
     * @date 2019/8/27
     */
    @SuppressWarnings("unused")
    public static class Program5 {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();

        public void push(int node) {
            stack1.push(node);
        }

        public int pop() {
            if (stack1.empty() && stack2.empty()) {
                throw new RuntimeException("为空");
            }
            if (stack2.empty()) {
                while (!stack1.empty()) {
                    stack2.push(stack1.pop());
                }
            }
            return stack2.pop();
        }
    }

}
