package leetcode;

/*
Leetcode 105. Construct Binary Tree from Preorder and Inorder Traversal.

Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

For example, given

preorder = [3,9,20,15,7]
inorder = [9,3,15,20,7]
Return the following binary tree:

    3
   / \
  9  20
    /  \
   15   7

Takeaways:
-
*/

import java.util.*;

public class constructBinaryTreeFromPreAndInorder {

    /*
    We know that preorder is: root, left, right and inorder is: left, root, right.
    So we need to figure out how to use both of these arrays to construct the binary tree.

    We should use the preorder array to get the first root. Once we get the first root, we can look at where
    that root is located in the inorder array and find out what it's left and right subtrees are. We will use a DFS
    approach and get the left most elements first, then right elements.

    So for our given example:
    preorder = [3,9,20,15,7]
    inorder = [9,3,15,20,7]

    The first root is 3, and if we look at where three is in the inorder array, we can find what the left node is.
         9<-3

    Time: O(n) where n = number of nodes (including null nodes)
    Space: O(n) where n = number of nodes ( the HashMap containing index locations of all values.
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // Utilizing a HashMap to keep track of where each item is in the inOrder array.
        HashMap<Integer, Integer> inorderMap = new HashMap<Integer, Integer>();

        for(int i = 0; i < inorder.length; i++){
            inorderMap.put(inorder[i], i);
        }

        return buildRecursively(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inorderMap);
    }

    private TreeNode buildRecursively(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd, HashMap<Integer, Integer> inorderMap)    {
        if( preStart > preEnd || inStart > inEnd)
            return null;

        // Get current root, get the index of the current root in the inorder array, and calculate how many elements will be
        // in the left subtree of the current root.
        TreeNode root = new TreeNode(preorder[preStart]);
        int inorderRootLocation = inorderMap.get(root.val);
        int numsInLeftSubtree = inorderRootLocation - inStart;

        // To understand this better, just remember how the preorder and inorder array stores its values.
        //
        // To get the left subtree, in the preorder array you have to move the preStart one ahead and the preEnd to preStart plus
        // numsInLeftSubtree. In the inorder array you will have to keep the inStart, and the inEnd will be 1 before the
        // current location of the root.
        //
        // To get the right subtree, in the preorder array you have to move preStart to preStart + numsInLeftSubtree, and preEnd stays the same.
        // For the inorder array you'll have to move the inStart to the inOrderRootLocation + 1 and the inEnd stays the same.
        root.left = buildRecursively(preorder, preStart + 1, preStart + numsInLeftSubtree, inorder, inStart, inorderRootLocation - 1, inorderMap);
        root.right = buildRecursively(preorder, preStart + numsInLeftSubtree + 1, preEnd, inorder, inorderRootLocation + 1, inEnd, inorderMap);

        return root;
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
