package com.mh.leetcode;

import java.util.Stack;

/**
 * ClassName：
 * Time：20/8/14 上午10:04
 * Description：栈的用法
 *
 * @author mh
 */
public class StackProblem {

    public static void main(String[] args) {
        StackProblem main7 = new StackProblem();

        String s = "";
        boolean valid = isValid(s);
        System.out.println(valid);

        int[] T = new int[]{73, 74, 75, 71, 69, 72, 76, 73};
        int[] ints = main7.dailyTemperatures(T);
        for (int anInt : ints) {
            System.out.print(anInt + " , ");
        }

    }

    /**
     * 有效括号
     * @param s
     * @return boolean
     * @author mh
     * @date 20/8/14 下午1:59
     */
    public  static  boolean isValid(String s) {
        if(s.length() % 2 != 0){
            return false;
        }

        Stack<Character> stack = new Stack<>();
        for (int i = 0;i < s.length();i++){
            Character character = s.charAt(i);
            if(character == '{'){
                stack.push('}');
            }else if(character == '[') {
                stack.push(']');
            }else if(character == '('){
                stack.push(')');
            }else {
                if(stack.isEmpty()){
                    return false;
                }
                Character pop = stack.pop();
                if(!pop.equals(character)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    /**
     * 739 每日温度
     * 请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。
     * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
     *
     * @param T
     * @return int[]
     * @author mh
     * @date 20/8/14 下午1:58
     */
    public int[] dailyTemperatures(int[] T) {
//        Deque<Integer> stack = new LinkedList<>();
        int[] result = new int[T.length];

//        for (int i = 0;i < T.length;i++) {
//
//            while (!stack.isEmpty() && T[i] > T[stack.peek()]) {
//                Integer pop = stack.pop();
//                result[pop] = i - pop;
//            }
//
//            stack.push(i);
//        }
        for(int i = T.length - 2;i >= 0;i--){

            int j = i + 1;
            while (true){
                if(T[i] < T[j]){
                    result[i] = j - i;
                    break;
                }else if(result[j] == 0){
                    result[i] = 0;
                    break;
                }
                j += result[j];
            }
        }

        return result;
    }
}
