package leetcode;

/*
LeetCode 98. Validate Binary Search Tree

Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.


Example 1:
    2
   / \
  1   3

Input: [2,1,3]
Output: true



Example 2:
    5
   / \
  1   4
     / \
    3   6

Input: [5,1,4,null,null,3,6]
Output: false
Explanation: The root node's value is 5 but its right child's value is 4.

Takeaways:
- Inorder traversal is left, root, right. (Inorder = IN between left and right)

Good notes from issac3 on LeetCode:
I will show you all how to tackle various tree questions using iterative inorder traversal.
First one is the standard iterative inorder traversal using stack. Hope everyone agrees with this solution.

Question : Binary Tree Inorder Traversal

public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> list = new ArrayList<>();
    if(root == null) return list;
    Stack<TreeNode> stack = new Stack<>();
    while(root != null || !stack.empty()){
        while(root != null){
            stack.push(root);
            root = root.left;
        }
        root = stack.pop();
        list.add(root.val);
        root = root.right;

    }
    return list;
}
Now, we can use this structure to find the Kth smallest element in BST.

Question : Kth Smallest Element in a BST

 public int kthSmallest(TreeNode root, int k) {
     Stack<TreeNode> stack = new Stack<>();
     while(root != null || !stack.isEmpty()) {
         while(root != null) {
             stack.push(root);
             root = root.left;
         }
         root = stack.pop();
         if(--k == 0) break;
         root = root.right;
     }
     return root.val;
 }
We can also use this structure to solve BST validation question.

Question : Validate Binary Search Tree

public boolean isValidBST(TreeNode root) {
   if (root == null) return true;
   Stack<TreeNode> stack = new Stack<>();
   TreeNode pre = null;
   while (root != null || !stack.isEmpty()) {
      while (root != null) {
         stack.push(root);
         root = root.left;
      }
      root = stack.pop();
      if(pre != null && root.val <= pre.val) return false;
      pre = root;
      root = root.right;
   }
   return true;
}
 */

import java.util.*;

public class validateBinarySearchTree {

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


     /*
     I think the solution to this one is to perform an inorder traversal of the tree and make sure the elements
     we are getting are in order (ascending).

     Inorder traversal is left, root, right. So we need to look at the left most nodes first, then the root, then the
     right nodes.

     We can utilize a stack to keep track of the nodes we still have to check.

     Time: O(n) where n = number of TreeNodes
     Space: O(n) where n = number of TreeNodes b/c of our stack.
      */
     public boolean isValidBST(TreeNode root) {
         Stack<TreeNode> stack = new Stack<TreeNode>();
         double prevVal = - Double.MAX_VALUE;

         while( !stack.empty() || root != null){
             // Go to the left most node:
             while(root!=null){
                 stack.push(root);
                 root = root.left;
             }

             // Get the last pushed item onto the stack, since root will be null at the end of the above loop.
             root = stack.pop();

             // If we find that the inorder traversal finds descending values, it is not a valid BST.
             if(root.val <= prevVal)
                 return false;

             prevVal = (double)root.val;
             root = root.right;
         }

         return true;
    }
}
