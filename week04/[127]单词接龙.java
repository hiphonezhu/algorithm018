//给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
// 
//
// 
// 每次转换只能改变一个字母。 
// 转换过程中的中间单词必须是字典中的单词。 
// 
//
// 说明: 
//
// 
// 如果不存在这样的转换序列，返回 0。 
// 所有单词具有相同的长度。 
// 所有单词只由小写字母组成。 
// 字典中不存在重复的单词。 
// 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。 
// 
//
// 示例 1: 
//
// 输入:
//beginWord = "hit",
//endWord = "cog",
//wordList = ["hot","dot","dog","lot","log","cog"]
//
//输出: 5
//
//解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
//     返回它的长度 5。
// 
//
// 示例 2: 
//
// 输入:
//beginWord = "hit"
//endWord = "cog"
//wordList = ["hot","dot","dog","lot","log"]
//
//输出: 0
//
//解释: endWord "cog" 不在字典中，所以无法进行转换。 
// Related Topics 广度优先搜索 
// 👍 638 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordListSet = new HashSet<>(wordList);
        // 记录访问过的节点
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        visited.add(beginWord);

        // 广度优先遍历
        int length = 0;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            while (levelSize-- > 0) {
                String node = queue.poll();

                if (node.equals(endWord)) {
                    // 找到 endWord，深度就是最短路径
                    return length + 1;
                }

                /**
                 * 依次替换 node 中的每个字符，找到下一层合法的节点（不重复 & 在 wordList 中）
                 */
                for (int i = 0; i < node.length(); i++) {
                    char[] nodeCharArray = node.toCharArray();
                    char old = nodeCharArray[i];
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        nodeCharArray[i] = ch;
                        String childNode = new String(nodeCharArray);
                        if (!visited.contains(childNode) && wordListSet.contains(childNode)) {
                            queue.offer(childNode);
                            visited.add(childNode);
                        }
                    }
                    nodeCharArray[i] = old;
                }
            }
            length++;
        }

        return 0;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
