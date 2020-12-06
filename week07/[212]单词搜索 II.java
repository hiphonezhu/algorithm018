//给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words，找出所有同时在二维网格和字典中出现的单词。 
//
// 单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使
//用。 
//
// 
//
// 示例 1： 
//
// 
//输入：board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l"
//,"v"]], words = ["oath","pea","eat","rain"]
//输出：["eat","oath"]
// 
//
// 示例 2： 
//
// 
//输入：board = [["a","b"],["c","d"]], words = ["abcb"]
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// m == board.length 
// n == board[i].length 
// 1 <= m, n <= 12 
// board[i][j] 是一个小写英文字母 
// 1 <= words.length <= 3 * 104 
// 1 <= words[i].length <= 10 
// words[i] 由小写英文字母组成 
// words 中的所有字符串互不相同 
// 
// Related Topics 字典树 回溯算法 
// 👍 289 👎 0


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    class Trie {
        private Trie[] links;
        private boolean isEnd;

        /**
         * Initialize your data structure here.
         */
        public Trie() {
            links = new Trie[26];
        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            Trie node = this;
            for (int i = 0; i < word.length(); i++) {
                char currentChar = word.charAt(i);
                if (node.links[currentChar - 'a'] == null) {
                    node.links[currentChar - 'a'] = new Trie();
                }
                node = node.links[currentChar - 'a'];
            }
            node.isEnd = true;
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            Trie node = searchPrefix(word);
            return node != null && node.isEnd;
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            Trie node = searchPrefix(prefix);
            return node != null;
        }

        private Trie searchPrefix(String word) {
            Trie node = this;
            for (int i = 0; i < word.length(); i++) {
                char curLetter = word.charAt(i);
                if (curLetter - 'a' >= 0 && curLetter - 'a' < 26 && node.links[curLetter - 'a'] != null) {
                    node = node.links[curLetter - 'a'];
                } else {
                    return null;
                }
            }
            return node;
        }
    }

    Trie root = new Trie();
    int[] dx = new int[]{-1, 1, 0, 0};
    int[] dy = new int[]{0, 0, 1, -1};


    public List<String> findWords(char[][] board, String[] words) {
        for (String word : words) {
            root.insert(word);
        }

        int rowCount = board.length;
        int columnCount = board[0].length;
        Set<String> ans = new HashSet<>();
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                dfs(i, j, rowCount, columnCount, board, new StringBuilder(), ans);
            }
        }
        return new ArrayList<>(ans);
    }

    void dfs(int rowIndex, int columnIndex, int rowCount, int columnCount, char[][] board, StringBuilder stringBuilder, Set<String> ans) {
        if (rowIndex < 0 || rowIndex > rowCount - 1 || columnIndex < 0 || columnIndex > columnCount - 1) {
            return;
        }

        stringBuilder.append(board[rowIndex][columnIndex]);

        if (!root.startsWith(stringBuilder.toString())) {
            return;
        }

        if (root.search(stringBuilder.toString())) {
            ans.add(stringBuilder.toString());
        }

        char temp = board[rowIndex][columnIndex];
        board[rowIndex][columnIndex] = '@';
        for (int i = 0; i < 4; i++) {
            int newRowIndex = rowIndex + dx[i];
            int newColumnIndex = columnIndex + dy[i];
            if (newRowIndex < 0 || newRowIndex > rowCount - 1 || newColumnIndex < 0 || newColumnIndex > columnCount - 1) {
                continue;
            }
            dfs(newRowIndex, newColumnIndex, rowCount, columnCount, board, stringBuilder, ans);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        board[rowIndex][columnIndex] = temp;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
