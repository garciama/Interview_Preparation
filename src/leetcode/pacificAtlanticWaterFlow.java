package leetcode;

/*
LeetCode 417. Pacific Atlantic Water Flow

Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.

Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.

Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.

Note:

The order of returned grid coordinates does not matter.
Both m and n are less than 150.


Example:

Given the following 5x5 matrix:

  Pacific ~   ~   ~   ~   ~
       ~  1   2   2   3  (5) *
       ~  3   2   3  (4) (4) *
       ~  2   4  (5)  3   1  *
       ~ (6) (7)  1   4   5  *
       ~ (5)  1   1   2   4  *
          *   *   *   *   * Atlantic

Return:

[[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).
 */

import java.util.ArrayList;
import java.util.List;

public class pacificAtlanticWaterFlow {

    /*
    Brute force: check every index to see if there is a path to both the pacific and atlantic oceans.

    Pacific ocean is defined as -1 ,_  and _, -1.
    Atlantic ocean is defined as matrix.length, _ and _, matrix[0].length

    Use DFS to see if there is a path from one side of the pacific to the atlantic, starting at their corresponding
    sides.

    Time: O(n*m) for size of the given matrix.
    Space: O(2*n*m) = O(n*m) for the two boolean arrays.
     */


    // Saving number of columns and rows in the matrix globally.
    int col = 0;
    int row = 0;
    // Storing matrix globally to reduce memory usage in recursive calls.
    int[][] globalMatrix;

    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        globalMatrix = matrix;
        List<List<Integer>> results = new ArrayList<>();

        if(matrix.length == 0 || matrix == null)
            return results;

        col = matrix.length;
        row = matrix[0].length;

        // 2D array for each ocean to mark if an index has been visited already:
        boolean[][] pacific = new boolean[col][row];
        boolean[][] atlantic = new boolean[col][row];

        // Start DFS on both pacific and atlantic oceans from their side of the array and see if they reach the other side.
        for(int i=0; i<col; i++) {
            dfs(i,0,pacific);
            dfs(i,row-1,atlantic);
        }
        for(int i=0;i<row;i++) {
            dfs(0,i,pacific);
            dfs(col-1,i,atlantic);
        }

        for(int i=0;i<col;i++) {
            for(int j=0;j<row;j++) {
                // If both the pacific and atlantic dfs searches reached this index, then the flow is possible.
                if(pacific[i][j] && atlantic[i][j]) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(i);
                    temp.add(j);
                    results.add(temp);
                }
            }
        }

        return results;
    }

    public void dfs(int i, int j, boolean[][] visited) {
        // checking for out of bounds instances or if this index has already been visited.
        if(i <0 || j<0 || i>=globalMatrix.length || j>=globalMatrix[0].length ||  visited[i][j])
            return;

        // Mark as visited now.
        visited[i][j] = true;

        // Checking for possible water flows in all directions.
        if(i< col-1 && globalMatrix[i][j] <= globalMatrix[i+1][j])
            dfs(i+1,j,visited);
        if(i > 0 && globalMatrix[i][j] <= globalMatrix[i-1][j])
            dfs(i-1,j,visited);
        if(j < row-1 && globalMatrix[i][j] <= globalMatrix[i][j+1])
            dfs(i,j+1,visited);
        if(j > 0 && globalMatrix[i][j] <= globalMatrix[i][j-1])
            dfs(i,j-1,visited);

    }
}
