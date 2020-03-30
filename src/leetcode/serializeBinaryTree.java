package leetcode;

/*
LeetCode 297. Serialize and Deserialize a Binary Tree

Serialization is the process of converting a data structure or object into a sequence of bits so that it can be
stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in
the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your
serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be
serialized to a string and this string can be deserialized to the original tree structure.

Example:
You may serialize the following tree:

    1
   / \
  2   3
     / \
    4   5

as "[1,2,3,null,null,4,5]"
Clarification: The above format is the same as how LeetCode serializes a binary tree. You do not necessarily need
to follow this format, so please be creative and come up with different approaches yourself.

Note: Do not use class member/global/static variables to store states. Your serialize and deserialize
algorithms should be stateless.

Takeaways:
- Remember all types of traversals for binary trees.
- Need to think of how you will serialize and rebuild your tree. Using preorder makes most sense because you make
  the root first and add on to it.
- Syntax for adding and splitting a string into a queue: queue.addAll(Arrays.asList(data.split(splitter)));
 */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class serializeBinaryTree {

    /**
     * Definition for a binary tree node.
    */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode (int x) {val = x;}
    }

    /*
    I believe the best approach to this problem is to traverse the given array in preorder. The reason for doing it
    in preorder is to have the root be first first element in the serialized string.

    Preorder traversal: root, left, right
    So in the above example, it will be serialized to: "1,2,X,X,3,4,X,X,5,X,X" where X represents null.

    To rebuild the tree from this serialized we will use a Queue that contains all the characters in
    the serialized string. We will recursively build the tree back through preorder traversal.

    Time: serialize & deserialize are O(n) where n = number of nodes.
    Space: O(n) where n = number of nodes because we are storing a string and queue relative to the length of the
           given tree.
     */
    private static final String splitter = ",";
    private static final String nullValue = "X";


    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        // utilize a StringBuilder to build the serialized binary tree.
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }

    // Recursively build the serialized tree in preorder traversal.
    private void buildString(TreeNode root, StringBuilder sb){
        if(root == null){
            sb.append(nullValue).append(splitter);
            return;
        } else{
            // Recursively building the serialized string:
            sb.append(root.val).append(splitter);
            buildString(root.left, sb);
            buildString(root.right, sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Queue<String> queue = new LinkedList<String>();
        queue.addAll(Arrays.asList(data.split(splitter)));
        return decode(queue);
    }

    // Recursively deserializes the string in preorder traversal.
    private TreeNode decode(Queue<String> queue){
        String val = queue.remove();
        if(val.equals(nullValue))
            return null;
        else{
            TreeNode root = new TreeNode(Integer.valueOf(val));
            root.left = decode(queue);
            root.right = decode(queue);

            return root;
        }
    }
}