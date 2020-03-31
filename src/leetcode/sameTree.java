package leetcode;
/*
LeetCode 100. Same Tree

Given two binary trees, write a function to check if they are the same or not.

Two binary trees are considered the same if they are structurally identical and the nodes have the same value.

Example 1:

Input:     1         1
          / \       / \
         2   3     2   3

        [1,2,3],   [1,2,3]

Output: true
Example 2:

Input:     1         1
          /           \
         2             2

        [1,2],     [1,null,2]

Output: false
Example 3:

Input:     1         1
          / \       / \
         2   1     1   2

        [1,2,1],   [1,1,2]

Output: false
 */

public class sameTree {

    /*
    Time: O(n) where n = lowest number of nodes in q or p.
    Space: O(1) because we are not storing any extra variables.
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(q == null && p == null)
            return true;

        if(p == null || q == null || p.val != q.val)
            return false;

        return(isSameTree(p.left, q.left) && isSameTree(p.right, q.right));
    }

    /**
     * Definition for a binary tree node.
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }
}
