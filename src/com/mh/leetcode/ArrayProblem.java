package com.mh.leetcode;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * ClassName：
 * Time：20/8/3 上午10:52
 * Description：数组的用法
 * Author： mh
 */
public class ArrayProblem {

    public static void main(String[] args) {
       String s = "anagram";
       String t = "nagaram";
        System.out.println(isAnagramV2(s,t));
    }

    /**
     * 242
     * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
     * 示例 1:
     * 输入: s = "anagram", t = "nagaram"
     * 输出: true
     */
    public static boolean isAnagram(String s, String t) {
        if(s.length() == t.length()){

            char[] sCh = s.toCharArray();
            char[] tCh = t.toCharArray();

            Arrays.sort(sCh);
            Arrays.sort(tCh);

            String finalS = String.valueOf(sCh);
            String finalT = String.valueOf(tCh);
            if(finalS.equals(finalT)){
                return true;
            }else {
                return false;
            }
        }

        return false;
    }

    public static boolean isAnagramV2(String s, String t) {
        if(s.length() == t.length()){
            int[] table = new int[26];
            for (int i = 0;i < s.length();i++){
                table[s.charAt(i) - 'a'] ++;
                table[t.charAt(i) - 'a']--;
            }

            for (int i : table) {
                if(i < 0){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 1
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
     */
    public int[] twoSum(int[] nums, int target) {
        Double mapInit = (nums.length + 1) / 0.75;
        Map<Integer,Integer> map = new HashMap<>(mapInit.intValue());
        for (int i = 0; i < nums.length; i++) {
            int remain = target - nums[i];
            if(remain < 0){
                continue;
            }

            if(map.containsKey(remain)){
                return new int[]{map.get(remain),i};
            }

            map.put(nums[i],i);
        }
        return null;
    }
}
