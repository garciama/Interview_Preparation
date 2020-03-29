package leetcode;

/*
Leetcode 200. Number of Islands

Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

Input:
11110
11010
11000
00000

Output: 1
Example 2:

Input:
11000
11000
00100
00011

Output: 3
 */

/*
Takeaways:
- Need to think of the big picture of a problem.
- Sometimes a simple solution like this is available.
- Know DFS and BFS.

Time: O(n*m) where n and m are the size of the grid.
Space: O(1) because we are only keeping track of the number of islands and the given grid.
 */
public class numOfIslands {

    /*
    Solution for this one involves recursively removing islands as you find them.
    A scan of the whole array will be performed, and the number of islands will be counted.
     */
    public int numIslands(char[][] grid) {
        int islands = 0;

        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == '1') {
                    removeIsland(i, j, grid);
                    islands++;
                }
            }
        }
        return islands;
    }

    private void removeIsland(int i, int j, char[][] grid){
        // Checking for out of bounds indices:
        if(i < 0 || i > grid.length -1 || j < 0 || j > grid[0].length -1)
            return;

        if(grid[i][j] == '1'){
            grid[i][j] = '0';
            removeIsland(i, j-1, grid);
            removeIsland(i, j+1, grid);
            removeIsland(i-1, j, grid);
            removeIsland(i+1, j, grid);
        }
    }


}
