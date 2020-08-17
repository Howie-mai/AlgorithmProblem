package com.mh.leetcode;


import com.mh.leetcode.bean.ListNode;

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
    }

    /**
     * 25题
     * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
     * k 是一个正整数，它的值小于或等于链表的长度。
     * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
     * @param head
     * @param k
     * @date 20/8/14 下午2:00
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        /**
         * pre节点的作用是记录已翻转的链表段的最后节点 ，方便与翻转完成后的连标段连接
         */
        ListNode pre = dummy;
        ListNode end = dummy;

        while (end.next != null){
            /**
             * 找出需要翻转的链表段的最后一个节点
             */
            for (int i =0;i < k && end != null;i++){
                end = end.next;
            }
            if(end == null){
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
     * @param start
     * @date 20/8/14 下午2:01
     */
    private ListNode reverse(ListNode start) {
        ListNode pre = null;
        ListNode curr = start;
        while (curr != null){
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
     * @param head
     * @return com.mh.leetcode.bean.ListNode
     * @author mh
     * @date 20/8/14 下午2:01
     */
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        while (head.next != null){

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
}
