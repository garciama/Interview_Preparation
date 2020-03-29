package leetcode;

/*
LeetCode 226. Invert Binary Tree

Invert a binary tree.

Example:

Input:

     4
   /   \
  2     7
 / \   / \
1   3 6   9
Output:

     4
   /   \
  7     2
 / \   / \
9   6 3   1
Trivia:
This problem was inspired by this original tweet by Max Howell:

Google: 90% of our engineers use the software you wrote (Homebrew), but you can’t invert a binary tree on a whiteboard so f*** off.

Takeaways:
- DFS involves going to the leftmost node first, then the right nodes.
- Keep in mind recursive strategies for problems involving trees.
 */


public class invertBinaryTree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /*
    Main idea with this one is to use DFS recursive technique to swap all values.
    Time: O(n) where n=number of nodes.
    Space: O(n) worst case when only left subtrees (all nodes will be in recursive memory)
     */
    public TreeNode invertTree(TreeNode root) {
        // Perform DFS recursive search:

        // Base case:
        if(root == null)
            return null;

        // For DFS we will go as far down the left subtree first, then right subtrees
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);

        root.left = right;
        root.right = left;

        return root;
    }
}
