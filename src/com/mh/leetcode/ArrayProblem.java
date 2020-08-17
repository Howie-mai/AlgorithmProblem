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
}
