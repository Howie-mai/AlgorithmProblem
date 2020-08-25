package com.mh.leetcode.bean;

import java.util.List;

/**
 * ClassName：
 * Time：20/8/25 下午2:47
 * Description：
 *
 * @author mh
 */
public class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int val) {
        this.val = val;
    }

    public Node(int val, List<Node> children) {
        this.val = val;
        this.children = children;
    }
}
