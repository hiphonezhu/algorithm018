//给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。 
//
// 说明：每次只能向下或者向右移动一步。 
//
// 
//
// 示例 1： 
//
// 
//输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
//输出：7
//解释：因为路径 1→3→1→1→1 的总和最小。
// 
//
// 示例 2： 
//
// 
//输入：grid = [[1,2,3],[4,5,6]]
//输出：12
// 
//
// 
//
// 提示： 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 200 
// 0 <= grid[i][j] <= 100 
// 
// Related Topics 数组 动态规划 
// 👍 722 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minPathSum(int[][] grid) {
        int rowCount = grid.length;
        int columnCount = grid[0].length;
        int[][] dp = new int[rowCount][columnCount];
        dp[rowCount - 1][columnCount - 1] = grid[rowCount - 1][columnCount - 1];
        // 初始化最后一行
        for (int i = columnCount - 2; i >= 0; i--) {
            dp[rowCount - 1][i] = grid[rowCount - 1][i] + dp[rowCount - 1][i + 1];
        }
        // 初始化最后一列
        for (int i = rowCount - 2; i >= 0; i--) {
            dp[i][columnCount - 1] = grid[i][columnCount - 1] + dp[i + 1][columnCount - 1];
        }

        // dp方程：dp[row][column] = grid[row][column] + Math.min(dp[row + 1][column], dp[row][column + 1]);
        for (int row = rowCount - 2; row >= 0; row--) {
            for (int column = columnCount - 2; column >= 0; column--) {
                dp[row][column] = grid[row][column] + Math.min(dp[row + 1][column], dp[row][column + 1]);
            }
        }

        return dp[0][0];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
