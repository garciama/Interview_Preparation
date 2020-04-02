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

Takeaways:
- You can get elements by index in ArrayList.
- ArrayList maintains insert order.
 */
import java.util.*;

public class binaryTreeLevelOrderTraversal {

    /*
    For this problem, I think the solution involves recursively calling a function that keeps track of its
    level and inserts it's value at the corresponding list.

    So for our given list, we will call the method with root and int level = 0
    If root == null return;
    Else add root.val to correct list and recursively call on root.left and root.right, with level + 1

    Time: O(n) where n = number of nodes in binary tree.
    Space: O(n) stored in the result list.
    */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        levelHelper(res, root, 0);
        return res;
    }

    public void levelHelper(List<List<Integer>> res, TreeNode root, int height) {
        if (root == null) return;
        if (height >= res.size()) {
            res.add(new LinkedList<Integer>());
        }
        res.get(height).add(root.val);
        levelHelper(res, root.left, height+1);
        levelHelper(res, root.right, height+1);
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
