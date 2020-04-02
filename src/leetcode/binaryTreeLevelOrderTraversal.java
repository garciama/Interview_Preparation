package leetcode;

/*
LeetCode 102. Binary Tree Level Order Traversal

Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
]
 */
import java.util.*;

public class binaryTreeLevelOrderTraversal {

    /*
    For this problem, I think the solution involves recursively calling a function that keeps track of its
    level and inserts it's value at the corresponding list.

    So for our given list, we will call the method with root and int level = 0
    If root == null return;
    Else recursive call on root.left and root.right, with level + 1
    */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        HashMap<Integer, List<Integer>> nodesPerLevel = new HashMap<Integer, List<Integer>>();
        if(root == null)
            return results;

        dfs(root, 0, nodesPerLevel);

        results.addAll(nodesPerLevel.values());
        return results;
    }

    private void dfs(TreeNode root, int level, HashMap<Integer, List<Integer>> nodesPerLevel){
        if(root == null)
            return;
        if(nodesPerLevel.containsKey(level)){
            List<Integer> updatedList = nodesPerLevel.get(level);
            updatedList.add(root.val);
            nodesPerLevel.put(level, updatedList);
        } else{
            List<Integer> newList = new ArrayList<>();
            newList.add(root.val);
            nodesPerLevel.put(level, newList);
        }

        dfs(root.left, level + 1, nodesPerLevel);
        dfs(root.right, level + 1, nodesPerLevel);
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
