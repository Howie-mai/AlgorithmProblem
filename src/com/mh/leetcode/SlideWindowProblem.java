package com.mh.leetcode;

import java.util.*;

/**
 * ClassName：
 * Time：20/9/14 下午3:47
 * Description：滑动窗口
 *
 * @author mh
 */
public class SlideWindowProblem {

    public static void main(String[] args) {
        SlideWindowProblem problem = new SlideWindowProblem();

        Long start = System.currentTimeMillis();

        System.out.println(problem.lengthOfLongestSubstring("pwwkew"));
//        System.out.println(problem.minWindow("ab","a"));
//        System.out.println(problem.checkInclusion("abc","ccccbbbbaaaa"));

        Long end = System.currentTimeMillis();
        System.out.println(end - start + "ms");
    }

    /**
     * 3. 无重复字符的最长子串
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     * 示例 1:
     * 输入: "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     */
    public int lengthOfLongestSubstring(String s) {
//        int ans = 0;
//        int j = -1;
//        Set<Character> set = new HashSet<>();
//        for (int i = 0;i < s.length();i++){
//            if(i != 0){
//                set.remove(s.charAt(i - 1));
//            }
//            while (j + 1 < s.length() && !set.contains(s.charAt(j + 1))){
//                set.add(s.charAt(j + 1));
//                j++;
//            }
//            ans = Math.max(ans,j - i + 1);
//        }
//        return ans;
        Map<Character,Integer> window = new HashMap<>();
        int ans = 0;
        int left = 0,right = 0;
        char[] chars = s.toCharArray();

        while (right < chars.length){
            char ch = chars[right];
            right++;

            int count = window.getOrDefault(ch,0) + 1;
            window.put(ch,count);

            while (window.get(ch) > 1){
                char leftCh = chars[left];
                left++;
                count = window.getOrDefault(leftCh,0) - 1;
                window.put(leftCh,count);
            }

            ans = Math.max(ans,right - left);
        }
        return ans;
    }

    /**
     * 76. 最小覆盖子串
     * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
     *
     * 注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。
     */
    public String minWindow(String s, String t) {
        Map<Character,Integer> map = new HashMap<>();
        Map<Character,Integer> window = new HashMap<>();
        char[] tchars = t.toCharArray();
        for (char ch : tchars) {
            int count = map.getOrDefault(ch,0) + 1;
            map.put(ch,count);
        }
        // 判断窗口是否都有集齐t的字符
        int valid = 0;
        char[] schars = s.toCharArray();
        int left = 0,right = 0,start = 0,len = Integer.MAX_VALUE;
        while (right < schars.length) {
            char sch = schars[right];
            right++;

            int count;
            if(map.containsKey(sch)){
                count = window.getOrDefault(sch,0) + 1;
                window.put(sch,count);
                if(window.get(sch).equals(map.get(sch))){
                    valid++;
                }
            }

            while (valid == map.size()){
                if(right - left < len){
                    start = left;
                    len = right - left;
                }

                sch = schars[left];
                left++;

                if(map.containsKey(sch)){
                    if(window.get(sch).equals(map.get(sch))){
                        valid--;
                    }
                    count = window.getOrDefault(sch,0) - 1;
                    window.put(sch,count);
                }
            }
        }

        return len == Integer.MAX_VALUE ? "" : s.substring(start,start + len);
    }

    /**
     * 567. 字符串的排列
     * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
     *
     * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
     */
    public boolean checkInclusion(String s1, String s2) {
        Map<Character,Integer> need = new HashMap<>();
        Map<Character,Integer> window = new HashMap<>();
        char[] tchars = s1.toCharArray();
        for (char ch : tchars) {
            int count = need.getOrDefault(ch,0) + 1;
            need.put(ch,count);
        }

        int valid = 0;
        char[] schars = s2.toCharArray();
        int left = 0,right = 0;
        while (right < schars.length) {
            char sch = schars[right];
            right++;

            int count;
            if(need.containsKey(sch)){
                count = window.getOrDefault(sch,0) + 1;
                window.put(sch,count);
                if(window.get(sch).equals(need.get(sch))){
                    valid++;
                }
            }

            while (right - left >= tchars.length){
                if(valid == need.size()){
                   return true;
                }

                sch = schars[left];
                left++;

                if(need.containsKey(sch)){
                    if(window.get(sch).equals(need.get(sch))){
                        valid--;
                    }
                    count = window.getOrDefault(sch,0) - 1;
                    window.put(sch,count);
                }
            }
        }

        return false;
    }

    /**
     * 438. 找到字符串中所有字母异位词
     * 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
     *
     * 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
     */
    public List<Integer> findAnagrams(String s, String p) {
        Map<Character,Integer> need = new HashMap<>();
        Map<Character,Integer> window = new HashMap<>();
        char[] tchars = p.toCharArray();
        for (char ch : tchars) {
            int count = need.getOrDefault(ch,0) + 1;
            need.put(ch,count);
        }

        List<Integer> ans = new ArrayList<>();
        int valid = 0;
        char[] schars = s.toCharArray();
        int left = 0,right = 0;
        while (right < schars.length) {
            char sch = schars[right];
            right++;

            int count;
            if(need.containsKey(sch)){
                count = window.getOrDefault(sch,0) + 1;
                window.put(sch,count);
                if(window.get(sch).equals(need.get(sch))){
                    valid++;
                }
            }

            while (right - left >= tchars.length){
                if(valid == need.size()){
                    ans.add(left);
                }

                sch = schars[left];
                left++;

                if(need.containsKey(sch)){
                    if(window.get(sch).equals(need.get(sch))){
                        valid--;
                    }
                    count = window.getOrDefault(sch,0) - 1;
                    window.put(sch,count);
                }
            }
        }
        return ans;
    }
}
