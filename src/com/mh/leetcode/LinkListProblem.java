package com.mh.leetcode;


import com.mh.leetcode.bean.ListNode;

import java.util.*;

/**
 * ClassName：
 * Time：20/8/13 下午4:04
 * Description：链表的用法
 *
 * @author mh
 */
public class LinkListProblem {

    public static void main(String[] args) {
//        LinkedList<int> list = new LinkedList<>();
        LinkListProblem linkListProblem = new LinkListProblem();
        ListNode listNode1 = new ListNode(-129);
        ListNode listNode2 = new ListNode(-129);
//        ListNode listNode3 = new ListNode(3);
//        ListNode listNode4 = new ListNode(4);
//        ListNode listNode5 = new ListNode(5);
//        ListNode listNode6 = new ListNode(4);
//        ListNode listNode7 = new ListNode(5);

//        listNode6.next = listNode7;
//        listNode5.next = listNode6;
//        listNode4.next = listNode5;
//        listNode3.next = listNode4;
//        listNode2.next = listNode3;
        listNode1.next = listNode2;

//        ListNode listNode2 = new ListNode(1);
//        ListNode node1 = listNode1;
//        ListNode node2 = listNode2;
//        for (int i = 4;i < 6;i++){
//            node1.next = new ListNode(i);
//            node1 = node1.next;
//        }
        for (int i = 1; i <= 9; i++) {
            int val = 9;
            if (i == 6) {
                val = 8;
            }
//            node2.next = new ListNode(val);
//            node2 = node2.next;
        }
        // 541 + 762 = 613
//        linkListProblem.addTwoNumbers(listNode1,listNode2);
//        linkListProblem.deleteDuplicatesKeep(listNode1);
//        linkListProblem.removeNthFromEnd(listNode1, 2);
//        linkListProblem.reorderList(listNode1);
        linkListProblem.isPalindrome(listNode1);
        System.out.println(listNode1);
    }

    /**
     * 25题
     * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
     * k 是一个正整数，它的值小于或等于链表的长度。
     * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
     */
    @SuppressWarnings("unused")
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        /**
         * pre节点的作用是记录已翻转的链表段的最后节点 ，方便与翻转完成后的连标段连接
         */
        ListNode pre = dummy;
        ListNode end = dummy;

        while (end.next != null) {
            /**
             * 找出需要翻转的链表段的最后一个节点
             */
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            if (end == null) {
                break;
            }
            /**
             * 找出需要翻转的链表段的第一个节点
             */
            ListNode start = pre.next;
            /**
             * 记录未翻转链表段的第一个
             */
            ListNode endNext = end.next;
            /**
             * 断开待翻转与未翻转的联系，让待翻转成为一段独立的链表进行翻转
             */
            end.next = null;

            pre.next = reverse(start);
            /**
             * 翻转完成的链表段与未翻转的链表段连接
             */
            start.next = endNext;
            /**
             * 两个节点相当于刚开始的dummy节点,这个时候start已经是相当于是最后一个节点了
             */
            pre = start;
            end = pre;
        }
        return dummy.next;
    }

    /**
     * 剑指offer 24
     * 两个指针解决链表反转
     *
     * @date 20/8/14 下午2:01
     */
    private ListNode reverse(ListNode start) {
        ListNode pre = null;
        ListNode curr = start;
        while (curr != null) {
            ListNode next = curr.next;
            /**
             * 让后一个节点指向前一个节点
             */
            curr.next = pre;
            /**
             * 再把两个节点一起往后移
             */
            pre = curr;
            curr = next;
        }

        return pre;
    }

    /**
     * 24题
     * 链表两个为一组反转
     *
     * @author mh
     * @date 20/8/14 下午2:01
     */
    @SuppressWarnings("unused")
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        while (head.next != null) {

            ListNode curr = head;
            ListNode next = curr.next;

            pre.next = next;
            curr.next = next.next;
            next.next = curr;

            pre = curr;
            head = curr.next;
        }

        return dummy.next;
    }

    /**
     * 获取链表长度
     */
    @SuppressWarnings("unused")
    public static int getListNodeLength(ListNode listNode) {
        int len = 0;
        while (listNode != null) {
            len++;
            listNode = listNode.next;
        }
        return len;
    }

    /**
     * 2
     * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     * 示例：
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     */
    @SuppressWarnings("unused")
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int j = 0;
//        List<Integer> resultList = new ArrayList<>();
        ListNode node = new ListNode(0), result = node;

        for (; l1 != null || l2 != null; ) {
            int s1 = 0, s2 = 0;

            if (l1 != null) {
                s1 = l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                s2 = l2.val;
                l2 = l2.next;
            }

            int sum = s1 + s2 + j;
            int i;
            if (sum >= 10) {
                j = sum / 10;
                i = sum - j * 10;
                if (l2 == null && l1 == null) {
                    node.next = new ListNode(i);
                    node = node.next;
                    node.next = new ListNode(j);
                    node = node.next;
//                    resultList.add(j);
                    continue;
                }
            } else {
                i = sum;
                j = 0;
            }
            node.next = new ListNode(i);
            node = node.next;
//            resultList.add(i);
        }
        return result.next;
    }

    /**
     * 根据列表建立链表
     */
    @SuppressWarnings("unused")
    public ListNode buildListNodeByList(List<Integer> list) {
        if (list.size() == 0) {
            return null;
        } else if (list.size() == 1) {
            return new ListNode(list.get(0));
        } else {
            ListNode result = new ListNode(list.get(0));
            ListNode node = result;
            for (int i = 1; i < list.size(); i++) {
                node.next = new ListNode(list.get(i));
                node = node.next;
            }
            return result;
        }
    }

    /**
     * 83. 删除排序链表中的重复元素
     * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
     */
    public ListNode deleteDuplicatesKeep(ListNode head) {
//        ListNode root = new ListNode(0);
//        root.next = head;
//        ListNode pre = root;
//        ListNode curr;
//        while (pre.next != null){
//            curr = pre.next;
//            if(pre.val != curr.val){
//                pre = pre.next;
//                continue;
//            }
//
//            while (curr != null && curr.val == pre.val){
//                curr = curr.next;
//            }
//
//            pre.next = curr;
//        }
//        return root.next;
        ListNode curr = head;
        while (curr != null && curr.next != null) {
            if (curr.val == curr.next.val) {
                curr.next = curr.next.next;
            } else {
                curr = curr.next;
            }
        }
        return head;
    }

    /**
     * 82. 删除排序链表中的重复元素 II
     * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode root = new ListNode(0);
        root.next = head;
        ListNode pre = root;
        ListNode l, r;
        while (pre.next != null && pre.next.next != null) {
            l = pre.next;
            r = pre.next;
            if (l.val != l.next.val) {
                pre = pre.next;
                continue;
            }

            while (r != null && l.val == r.val) {
                r = r.next;
            }

            pre.next = r;
        }
        return root.next;
    }

    /**
     * 19. 删除链表的倒数第N个节点
     * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode p = dummy, q = dummy;
        for (int i = 1; i <= n; i++) {
            p = p.next;
        }

        while (p.next != null) {
            q = q.next;
            p = p.next;
        }
        q.next = q.next.next;
        return dummy.next;
    }

    /**
     * 21. 合并两个有序链表
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);

        ListNode head = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                head.next = l1;
                l1 = l1.next;
            } else {
                head.next = l2;
                l2 = l2.next;
            }
            head = head.next;
        }

        head.next = l1 == null ? l2 : l1;
        return dummy.next;
//        if (l1 == null) {
//            return l2;
//        } else if (l2 == null) {
//            return l1;
//        } else if (l1.val < l2.val) {
//            l1.next = mergeTwoLists(l1.next,l2);
//            return l1;
//        } else {
//            l2.next = mergeTwoLists(l1,l2.next);
//            return l2;
//        }
    }

    /**
     * 23. 合并K个升序链表
     * 给你一个链表数组，每个链表都已经按升序排列。
     * <p>
     * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
     */
    public ListNode mergeKLists(ListNode[] lists) {
//        ListNode ans = null
//        for (int i = 0; i < lists.length; i++) {
//            ans = mergeTwoLists(ans, lists[i]);
//        }
//        return ans;
        return merge(lists,0,lists.length - 1);
    }

    public ListNode merge(ListNode[] lists, int left, int right) {
        if(left == right){
            return lists[left];
        }

        if(left > right){
            return null;
        }

        int mid = left + (right - left) / 2;
        return mergeTwoLists(merge(lists,left,mid - 1),merge(lists,mid + 1,right));
    }

    /**
     * 141. 环形链表
     * 给定一个链表，判断链表中是否有环。
     *
     * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
     * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
     * 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
     *
     * 如果链表中存在环，则返回 true 。 否则，返回 false 。
     *
     * 龟兔赛跑算法 快慢指针
     */
    public boolean hasCycle(ListNode head) {
        if(head == null){
            return false;
        }
        ListNode slow = head,fast = head.next;
        while(fast != slow){
            if(fast == null || fast.next == null){
                return false;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        return true;
    }

    /**
     * 142. 环形链表 II
     * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
     *
     * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
     * 如果 pos 是 -1，则在该链表中没有环。
     *
     * 说明：不允许修改给定的链表。
     */
    public ListNode detectCycle(ListNode head) {
        /**
         * 哈希表重复判断，也可用于141题，返回的结果不同
         */
//        Set<ListNode> set = new HashSet<>();
//        while (head != null){
//            if(!set.add(head)){
//                return head;
//            }
//            head = head.next;
//        }
//        return null;

        /**
         * 龟兔赛跑算法 快慢指针
         */
        if(head == null){
            return head;
        }
        ListNode slow = head,fast = head;

        do {
            if (fast == null || fast.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        } while (fast != slow);

        ListNode ans = head;
        while (ans != slow) {
            ans = ans.next;
            slow = slow.next;
        }
        return ans;
    }

    /**
     * 143. 重排链表
     * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
     * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
     *
     * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     */
    public void reorderList(ListNode head) {
        if(head == null){
            return;
        }
        ListNode dummy = head.next;
        Deque<ListNode> list = new ArrayDeque<>();
        while (dummy != null){
            list.add(dummy);
            dummy = dummy.next;
        }

        ListNode p1 = head.next,pre = head;
        while (p1 != null && p1.next != null){
            ListNode node = list.removeLast();
            pre.next = node;
            node.next = p1;
            list.getLast().next = null;

            pre = p1;
            p1 = p1.next;
        }
    }

    /**
     * 234. 回文链表
     * 请判断一个链表是否为回文链表。
     */
    public boolean isPalindrome(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while(head != null){
            list.add(head.val);
            head = head.next;
        }

        int x = 0,y = list.size() - 1;
        while(x < y){
            Integer integer = list.get(x++);

            Integer integer1 = list.get(y--);
            if(!integer . equals(integer1)){
                return false;
            }
        }

        return true;
    }
}
