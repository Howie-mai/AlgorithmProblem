package com.mh.leetcode.bean;

/**
 * ClassName：
 * Time：20/8/13 下午5:52
 * Description：
 *
 * @author mh
 */
public class ListNode {
    public int val;
    public ListNode next;
    public ListNode(int x) { val = x; }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public int getVal() {
    return val;
    }

    public void setVal(int val) {
    this.val = val;
    }

    public ListNode getNext() {
    return next;
    }

    public void setNext(ListNode next) {
    this.next = next;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}
