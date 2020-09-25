package com.mh.leetcode.design;

import java.util.Arrays;

/**
 * ClassName：
 * Time：20/9/23 上午11:07
 * Description：用数组实现三个栈
 *
 * @author mh
 */
public class TripleInOne {
    int[] stack;
    int stackSize;
    int stack1Head;
    int stack2Head;
    int stack3Head;

    public TripleInOne(int stackSize) {
        stack = new int[stackSize * 3];
        this.stackSize = stackSize;
        Arrays.fill(stack,-1);
        stack1Head = -1;
        stack2Head = stack1Head + stackSize;
        stack3Head = stack2Head + stackSize;
    }

    public void push(int stackNum, int value) {
        switch (stackNum){
            case 0:
                if(stack1Head != stackSize - 1){
                    stack[++stack1Head] = value;
                }
                break;
            case 1:
                if(stack2Head != stackSize * 2 - 1){
                    stack[++stack2Head] = value;
                }
                break;
            case 2:
                if(stack3Head != stackSize * 3 - 1){
                    stack[++stack3Head] = value;
                }
                break;
            default:
                break;
        }
    }

    public int pop(int stackNum) {
        switch (stackNum){
            case 0:
                return stack1Head != -1 ? stack[stack1Head--] : -1;
            case 1:
                return stack2Head != stackSize - 1 ? stack[stack2Head--] : -1;
            case 2:
                return stack3Head != stackSize * 2 -1 ? stack[stack3Head--] : -1;
            default:
                break;
        }
        return -1;
    }

    public int peek(int stackNum) {
        switch (stackNum){
            case 0:
                return stack1Head != -1 ? stack[stack1Head] : -1;
            case 1:
                return stack2Head != stackSize - 1 ? stack[stack2Head] : -1;
            case 2:
                return stack3Head != stackSize * 2 -1 ? stack[stack3Head] : -1;
            default:
                break;
        }
        return -1;
    }

    public boolean isEmpty(int stackNum) {
        switch (stackNum){
            case 0:
                return stack1Head == -1;
            case 1:
                return stack2Head == stackSize - 1;
            case 2:
                return stack3Head == stackSize * 2 - 1;
            default:
                break;
        }
        return false;
    }
}
