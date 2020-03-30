package leetcode;

/*
LeetCode 133. Clone Graph

Given a reference of a node in a connected undirected graph.

Return a deep copy (clone) of the graph.

Each node in the graph contains a val (int) and a list (List[Node]) of its neighbors.

class Node {
    public int val;
    public List<Node> neighbors;
}


Test case format:

For simplicity sake, each node's value is the same as the node's index (1-indexed). For example, the first node with val = 1, the second node with val = 2, and so on. The graph is represented in the test case using an adjacency list.

Adjacency list is a collection of unordered lists used to represent a finite graph. Each list describes the set of neighbors of a node in the graph.

The given node will always be the first node with val = 1. You must return the copy of the given node as a reference to the cloned graph.



Example 1:


Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
Output: [[2,4],[1,3],[2,4],[1,3]]
Explanation: There are 4 nodes in the graph.
1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
Example 2:


Input: adjList = [[]]
Output: [[]]
Explanation: Note that the input contains one empty list. The graph consists of only one node with val = 1 and it does not have any neighbors.
Example 3:

Input: adjList = []
Output: []
Explanation: This an empty graph, it does not have any nodes.
Example 4:


Input: adjList = [[2],[1]]
Output: [[2],[1]]


Constraints:

1 <= Node.val <= 100
Node.val is unique for each node.
Number of Nodes will not exceed 100.
There is no repeated edges and no self-loops in the graph.
The Graph is connected and all nodes can be visited starting from the given node.
 */
import java.util.*;

public class cloneGraph {

    /*
    Solution involves using a HashMap to keep track of Nodes that were already created and clone the graph using DFS.

    Time: O(n) where n = number of nodes in graph.
    Space: O(n) for the HashMap keeping track of Nodes that we have already cloned.
     */
    public Node cloneGraph(Node node) {
        // To deep clone a graph, we will have to do a DFS and make new Nodes.

        // Using a map to keep track of nodes we already created.
        HashMap<Integer, Node> nodes = new HashMap<>();

        return clone(node, nodes);
    }

    // Recursive method to clone the graph DFS style.
    private Node clone(Node node, HashMap<Integer, Node> map){
        if(node == null)
            return null;

        // If we already created a new node for the current node's value, use that node.
        if(map.containsKey(node.val))
            return map.get(node.val);

        // Else we will have to create a new node for the current node's value and add it to our HashMap.
        Node newNode = new Node(node.val);
        map.put(node.val, newNode);

        List<Node> neighborsList = node.neighbors;

        // Iterate the cloning process through all the neighbors.
        for(int i = 0; i < neighborsList.size(); i++){
            newNode.neighbors.add(clone(neighborsList.get(i), map));
        }

        return newNode;
    }

    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}
