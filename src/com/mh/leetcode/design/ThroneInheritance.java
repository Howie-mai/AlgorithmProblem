package com.mh.leetcode.design;

import com.mh.leetcode.bean.ListNode;
import com.mh.leetcode.bean.Node;

import java.util.*;

/**
 * ClassName：
 * Time：20/9/27 上午11:01
 * Description：
 *
 * @author mh
 */
public class ThroneInheritance {

    public static void main(String[] args) {
        ThroneInheritance t = new ThroneInheritance("king");
//        t.birth("king", "clyde"); // 继承顺序：king > andy
//        t.getInheritanceOrder("king", "bob"); // 继承顺序：king > andy > bob
//        t.birth("king", "catherine"); // 继承顺序：king > andy > bob > catherine
//        t.birth("andy", "matthew"); // 继承顺序：king > andy > matthew > bob > catherine
//        t.birth("bob", "alex"); // 继承顺序：king > andy > matthew > bob > alex > catherine
//        t.birth("bob", "asha"); // 继承顺序：king > andy > matthew > bob > alex > asha > catherine
//        t.getInheritanceOrder(); // 返回 ["king", "andy", "matthew", "bob", "alex", "asha", "catherine"]
//        t.death("bob"); // 继承顺序：king > andy > matthew > bob（已经去世）> alex > asha > catherine
//        t.getInheritanceOrder(); // 返回 ["king", "andy", "matthew", "alex", "asha", "catherine"]

//          t.birth("king", "clyde"); // 继承顺序：king > andy
//          t.death("king");
//        t.birth("clyde", "shonon"); // 继承顺序：king > andy
//        t.birth("shonon", "scott"); // 继承顺序：king > andy
//        t.death("clyde");

//        t.birth("king", "clyde"); // 继承顺序：king > andy
//        t.birth("clyde", "shannon"); // 继承顺序：king > andy
//        t.birth("shannon", "scott"); // 继承顺序：king > andy
//        t.birth("king", "keith"); // 继承顺序：king > andy
//        t.getInheritanceOrder();
//        t.birth("clyde", "joseph"); // 继承顺序：king > andy
//        t.getInheritanceOrder();

        t.birth("king", "logan"); // 继承顺序：king > andy
        t.birth("logan", "hosea"); // 继承顺序：king > andy
        t.birth("king", "leonard"); // 继承顺序：king > andy
        t.death("king");
        t.birth("logan", "carl"); // 继承顺序：king > andy
        t.death("hosea");
        t.birth("leonard","ronda");
        t.birth("logan","betty");
        t.getInheritanceOrder();
        t.birth("betty", "helen"); // 继承顺序：king > andy
        t.birth("betty", "alfred"); // 继承顺序：king > andy
        t.birth("logan", "luella"); // 继承顺序：king > andy
        t.death("leonard");
        t.getInheritanceOrder();
    }

//    static class Node{
//        String name;
//        Boolean alive = true;
//        List<Node> list = new ArrayList<>();
//
//        public Node(String name) {
//            this.name = name;
//        }
//
//        public static Node search(Node node,String target){
//            for (Node root : node.list) {
//                if(root.name.equals(target)){
//                    return root;
//                }
//                Node result = search(root, target);
//                if (result != null){
//                    return result;
//                }
//            }
//            return null;
//        }
//    }

    Map<String,List<String>> map = new HashMap<>();
    Set<String> dead = new HashSet<>();
    String kingName;
    List<String> ans;

    public ThroneInheritance(String kingName) {
        this.kingName = kingName;
        map.put(kingName,new ArrayList<>());
    }

    public void birth(String parentName, String childName) {
        map.get(parentName).add(childName);
        map.put(childName,new ArrayList<>());
    }

    public void death(String name) {
        dead.add(name);
    }

    public List<String> getInheritanceOrder() {
        ans = new ArrayList<>();
        dfs(this.kingName);
        System.out.println(ans);
        return ans;
    }

    public void dfs(String name){
        if(!dead.contains(name)){
            ans.add(name);
        }
        List<String> child = map.get(name);
        for (String s : child) {
            dfs(s);
        }
    }
}
