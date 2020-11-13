//给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。 
//
// 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。 
//
// 此外，你可以假设该网格的四条边均被水包围。 
//
// 
//
// 示例 1： 
//
// 
//输入：grid = [
//  ["1","1","1","1","0"],
//  ["1","1","0","1","0"],
//  ["1","1","0","0","0"],
//  ["0","0","0","0","0"]
//]
//输出：1
// 
//
// 示例 2： 
//
// 
//输入：grid = [
//  ["1","1","0","0","0"],
//  ["1","1","0","0","0"],
//  ["0","0","1","0","0"],
//  ["0","0","0","1","1"]
//]
//输出：3
// 
//
// 
//
// 提示： 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 300 
// grid[i][j] 的值为 '0' 或 '1' 
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集 
// 👍 849 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int numIslands(char[][] grid) {
        int totalIsland = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    // 岛屿+1
                    totalIsland++;
                    helper(grid, i, j);
                }
            }
        }
        return totalIsland;
    }

    /**
     * 将当前1周围（上、下、左、右）的1全部变成0
     *
     * @param grid
     * @param row
     * @param column
     */
    void helper(char[][] grid, int row, int column) {
        if (row < 0 || row > grid.length - 1 || column < 0 || column > grid[0].length - 1) {
            return;
        }

        if (grid[row][column] == '0') {
            return;
        }

        if (grid[row][column] == '1') {
            grid[row][column] = '0';
        }

        helper(grid, row - 1, column);
        helper(grid, row + 1, column);
        helper(grid, row, column - 1);
        helper(grid, row, column + 1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
