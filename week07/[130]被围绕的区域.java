//给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。 
//
// 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。 
//
// 示例: 
//
// X X X X
//X O O X
//X X O X
//X O X X
// 
//
// 运行你的函数后，矩阵变为： 
//
// X X X X
//X X X X
//X X X X
//X O X X
// 
//
// 解释: 
//
// 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被
//填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。 
// Related Topics 深度优先搜索 广度优先搜索 并查集 
// 👍 426 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int[] dx = new int[]{-1, 1, 0, 0};
    int[] dy = new int[]{0, 0, -1, 1};

    public void solve(char[][] board) {
        // 将四周为 O 的以及和 O 相邻的先标记为 #
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (i == 0 || i == board.length - 1
                        || j == 0 || j == board[0].length - 1) {
                    if (board[i][j] == 'O') {
                        dfs(board, i, j);
                    }
                }
            }
        }

        // 剩下的 O 肯定是被 X 围绕的
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }

        // 再把没有被 X 围绕的 O 还原
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    void dfs(char[][] board, int rowIndex, int columnIndex) {
        if (board[rowIndex][columnIndex] == 'X'
                || board[rowIndex][columnIndex] == '#') {
            return;
        }

        if (board[rowIndex][columnIndex] == 'O') {
            board[rowIndex][columnIndex] = '#';
        }

        for (int i = 0; i < 4; i++) {
            int newRowIndex = rowIndex + dx[i];
            int newColumnIndex = columnIndex + dy[i];
            if (newRowIndex < 0 || newRowIndex > board.length - 1
                    || newColumnIndex < 0 || newColumnIndex > board[0].length - 1) {
                continue;
            }
            dfs(board, newRowIndex, newColumnIndex);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
