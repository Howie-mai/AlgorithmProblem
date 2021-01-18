package com.mh.leetcode;

import java.util.*;

/**
 * ClassName：
 * Time：2021/1/18 9:48 上午
 * Description：
 *
 * @author mh
 */
public class UnionFindProblem {

    public static void main(String[] args) {
        UnionFindProblem problem = new UnionFindProblem();
        problem.trulyMostPopular(new String[]{"John(15)","Jon(12)","Chris(13)","Kris(4)","Christopher(19)"},new String[]{"(Jon,John)","(John,Johnny)","(Chris,Kris)","(Chris,Christopher)"});
    }

    /**
     * 721. 账户合并
     * 给定一个列表 accounts，每个元素 accounts[i] 是一个字符串列表，
     * 其中第一个元素 accounts[i][0] 是 名称 (name)，其余元素是 emails 表示该账户的邮箱地址。
     * 现在，我们想合并这些账户。如果两个账户都有一些共同的邮箱地址，则两个账户必定属于同一个人。
     * 请注意，即使两个账户具有相同的名称，它们也可能属于不同的人，因为人们可能具有相同的名称。
     * 一个人最初可以拥有任意数量的账户，但其所有账户都具有相同的名称。
     * 合并账户后，按以下格式返回账户：每个账户的第一个元素是名称，其余元素是按顺序排列的邮箱地址。账户本身可以以任意顺序返回。
     */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        // 利用一个字符串的映射存储并查集
        Map<String, String> map = new HashMap<>();
        // 存储对应名字
        Map<String, String> names = new HashMap<>();
        for (List<String> account : accounts) {
            for (int i = 1; i < account.size(); i++) {
                if(!map.containsKey(account.get(i))){
                    // 建立关系
                    map.put(account.get(i),account.get(i));
                    // 建立名字关系
                    names.put(account.get(i),account.get(0));
                }
                // 如果该用户有多个邮箱，建立并查集关系
                if(i > 1){
                    map.put(findStrByMap(map,account.get(i)),findStrByMap(map,account.get(i - 1)));
                }
            }
        }

        Map<String,List<String>> temp = new HashMap<>();
        // 把相同用户整合起来
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String parent = findStrByMap(map, key);
            if(!temp.containsKey(parent)){
                temp.put(parent,new ArrayList<>());
            }
            temp.get(parent).add(key);
        }

        List<List<String>> ans = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : temp.entrySet()) {
            String key = entry.getKey();
            List<String> list = temp.get(key);
            ans.add(list);
            Collections.sort(list);
            list.add(0,names.get(key));
        }
        return ans;
    }

    private String findStrByMap(Map<String, String> map, String s) {
        while (!map.get(s).equals(s)){
            s = map.get(s);
        }
        return s;
    }

    /**
     * 面试题 17.07. 婴儿名字
     * 每年，政府都会公布一万个最常见的婴儿名字和它们出现的频率，也就是同名婴儿的数量。
     * 有些名字有多种拼法，例如，John 和 Jon 本质上是相同的名字，但被当成了两个名字公布出来。
     * 给定两个列表，一个是名字及对应的频率，另一个是本质相同的名字对。设计一个算法打印出每个真实名字的实际频率。
     * 注意，如果 John 和 Jon 是相同的，并且 Jon 和 Johnny 相同，则 John 与 Johnny 也相同，即它们有传递和对称性。
     * 在结果列表中，选择 字典序最小 的名字作为真实名字。
     */
    public String[] trulyMostPopular(String[] names, String[] synonyms) {
        Map<String, Integer> count = new HashMap<>();
        // 利用一个字符串的映射存储并查集
        Map<String, String> map = new HashMap<>();
        for (String name : names) {
            int beginIndex = name.indexOf("(");
            String key = name.substring(0,beginIndex);
            int endIndex = name.lastIndexOf(")");
            int num = Integer.parseInt(name.substring(beginIndex + 1, endIndex));
            map.put(key,key);
            count.put(key,num);
        }

        for (String synonym : synonyms) {
            String[] split = synonym.substring(1, synonym.length() - 1).split(",");
            if(!map.containsKey(split[0])){
                map.put(split[0],split[0]);
            }
            if(!map.containsKey(split[1])){
                map.put(split[1],split[1]);
            }
            map.put(findStrByMap(map,split[1]),findStrByMap(map,split[0]));
        }

        Map<String,List<String>> relation = new HashMap<>();
        // 把相同用户整合起来
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String parent = findStrByMap(map, key);
            if(!relation.containsKey(parent)){
                relation.put(parent,new ArrayList<>());
            }
            relation.get(parent).add(key);
        }

        String[] ans = new String[relation.size()];
        int index = 0;
        for (Map.Entry<String, List<String>> entry : relation.entrySet()) {
            List<String> value = entry.getValue();
            Collections.sort(value);
            StringBuilder sb = new StringBuilder();
            sb.append(value.get(0)).append("(");
            int sum = 0;
            for (String s : value) {
                sum += count.getOrDefault(s,0);
            }
            sb.append(sum).append(")");
            ans[index++] = sb.toString();
        }
        return ans;
    }
}
