//让我们一起来玩扫雷游戏！ 
//
// 给定一个代表游戏板的二维字符矩阵。 'M' 代表一个未挖出的地雷，'E' 代表一个未挖出的空方块，'B' 代表没有相邻（上，下，左，右，和所有4个对角线）
//地雷的已挖出的空白方块，数字（'1' 到 '8'）表示有多少地雷与这块已挖出的方块相邻，'X' 则表示一个已挖出的地雷。 
//
// 现在给出在所有未挖出的方块中（'M'或者'E'）的下一个点击位置（行和列索引），根据以下规则，返回相应位置被点击后对应的面板： 
//
// 
// 如果一个地雷（'M'）被挖出，游戏就结束了- 把它改为 'X'。 
// 如果一个没有相邻地雷的空方块（'E'）被挖出，修改它为（'B'），并且所有和其相邻的未挖出方块都应该被递归地揭露。 
// 如果一个至少与一个地雷相邻的空方块（'E'）被挖出，修改它为数字（'1'到'8'），表示相邻地雷的数量。 
// 如果在此次点击中，若无更多方块可被揭露，则返回面板。 
// 
//
// 
//
// 示例 1： 
//
// 输入: 
//
//[['E', 'E', 'E', 'E', 'E'],
// ['E', 'E', 'M', 'E', 'E'],
// ['E', 'E', 'E', 'E', 'E'],
// ['E', 'E', 'E', 'E', 'E']]
//
//Click : [3,0]
//
//输出: 
//
//[['B', '1', 'E', '1', 'B'],
// ['B', '1', 'M', '1', 'B'],
// ['B', '1', '1', '1', 'B'],
// ['B', 'B', 'B', 'B', 'B']]
//
//解释:
//
// 
//
// 示例 2： 
//
// 输入: 
//
//[['B', '1', 'E', '1', 'B'],
// ['B', '1', 'M', '1', 'B'],
// ['B', '1', '1', '1', 'B'],
// ['B', 'B', 'B', 'B', 'B']]
//
//Click : [1,2]
//
//输出: 
//
//[['B', '1', 'E', '1', 'B'],
// ['B', '1', 'X', '1', 'B'],
// ['B', '1', '1', '1', 'B'],
// ['B', 'B', 'B', 'B', 'B']]
//
//解释:
//
// 
//
// 
//
// 注意： 
//
// 
// 输入矩阵的宽和高的范围为 [1,50]。 
// 点击的位置只能是未被挖出的方块 ('M' 或者 'E')，这也意味着面板至少包含一个可点击的方块。 
// 输入面板不会是游戏结束的状态（即有地雷已被挖出）。 
// 简单起见，未提及的规则在这个问题中可被忽略。例如，当游戏结束时你不需要挖出所有地雷，考虑所有你可能赢得游戏或标记方块的情况。 
// 
// Related Topics 深度优先搜索 广度优先搜索 
// 👍 194 👎 0


import java.util.LinkedList;
import java.util.Queue;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public char[][] updateBoard(char[][] board, int[] click) {
        // 广度优先搜索
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(click);
        while (!queue.isEmpty()) {
            int[] coordinate = queue.poll();
            int row = coordinate[0];
            int column = coordinate[1];
            if (board[row][column] == 'M') {
                // 遇到地雷
                board[row][column] = 'X';
            } else {
                // 当前位置不是地雷，计算周围雷的数量（上下左右，左对角，右对角）
                int mCount = 0;
                for (int i = -1; i < 2; i++) { // 行上下
                    for (int j = -1; j < 2; j++) { // 列左右
                        int newRow = row + i;
                        int newColumn = column + j;
                        if (newRow == row && newColumn == column) {
                            // 跳过当前位置
                            continue;
                        }
                        if (newRow < 0 || newRow >= board.length
                                || newColumn < 0 || newColumn >= board[0].length) {
                            // 新的位置越界
                            continue;
                        }

                        if (board[newRow][newColumn] == 'M') {
                            mCount++;
                        }
                    }
                }

                if (mCount > 0) {
                    // 当前位置周围有雷，标记为数字
                    board[row][column] = (char) (mCount + '0');
                } else {
                    // 当前位置周围没有雷，标记为空白
                    board[row][column] = 'B';
                    // 继续搜索当前位置周围没有点击的区域
                    for (int i = -1; i < 2; i++) { // 行上下
                        for (int j = -1; j < 2; j++) { // 列左右
                            int newRow = row + i;
                            int newColumn = column + j;
                            if (newRow == row && newColumn == column) {
                                // 跳过当前位置
                                continue;
                            }
                            if (newRow < 0 || newRow >= board.length
                                    || newColumn < 0 || newColumn >= board[0].length) {
                                // 新的位置越界
                                continue;
                            }

                            int[] newClick = new int[]{newRow, newColumn};
                            if (board[newRow][newColumn] == 'E') {
                                // 未点击的 E 加入队列
                                queue.offer(newClick);
                                // 标记为已访问，后续肯定会被还原成合法的字符
                                board[newRow][newColumn] = 'V';
                            }
                        }
                    }
                }
            }
        }
        return board;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
