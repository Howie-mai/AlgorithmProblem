package com.mh.niuke.bean;

/**
 * @ClassName:ListNode
 * @description 链表节点类
 * @author: mh
 * @create: 2019-08-27 13:02
 */
public class ListNode {
    public int val;
    public ListNode next = null;
    public ListNode(int val) {
        this.val = val;
    }

    public static void printListNode(ListNode head) {
        while(head!=null) {
            System.out.print(head.val+" ");
            head = head.next;
        }
    }
}
