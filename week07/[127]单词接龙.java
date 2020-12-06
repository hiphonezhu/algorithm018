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
// 👍 659 👎 0


import java.util.HashSet;
import java.util.List;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // 方法一、BFS
//        Set<String> wordListSet = new HashSet<>(wordList);
//        Queue<String> queue = new LinkedList<>();
//        Set<String> visited = new HashSet<>();
//        queue.add(beginWord);
//        visited.add(beginWord);
//        int depth = 0;
//        while (!queue.isEmpty()) {
//            int levelSize = queue.size();
//            while (levelSize-- > 0) {
//                String node = queue.poll();
//                if (node.equals(endWord)) {
//                    return depth + 1;
//                }
//                char[] nodeArray = node.toCharArray();
//                for (int i = 0; i < node.length(); i++) {
//                    char temp = nodeArray[i];
//                    for (char ch = 'a'; ch <= 'z'; ch++) {
//                        nodeArray[i] = ch;
//                        String newNode = new String(nodeArray);
//                        if (wordListSet.contains(newNode) && !visited.contains(newNode)) {
//                            queue.add(newNode);
//                            visited.add(newNode);
//                        }
//                    }
//                    nodeArray[i] = temp;
//                }
//            }
//            depth++;
//        }
//        return 0;

        // 方法二、双向BFS
        Set<String> wordListSet = new HashSet<>(wordList);
        if (!wordListSet.contains(endWord)) {
            return 0;
        }
        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        Set<String> visited = new HashSet<>();
        beginSet.add(beginWord);
        endSet.add(endWord);
        visited.add(beginWord);
        visited.add(endWord);
        int depth = 1;
        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            if (beginSet.size() > endSet.size()) {
                Set<String> temp = beginSet;
                beginSet = endSet;
                endSet = temp;
            }

            Set<String> nextLevelSet = new HashSet<>();
            for (String word : beginSet) {
                char[] wordArray = word.toCharArray();
                for (int i = 0; i < wordArray.length; i++) {
                    char old = wordArray[i];
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        wordArray[i] = ch;
                        String newWord = new String(wordArray);
                        if (endSet.contains(newWord)) {
                            return depth + 1;
                        }

                        if (wordListSet.contains(newWord) && !visited.contains(newWord)) {
                            nextLevelSet.add(newWord);
                            visited.add(newWord);
                        }
                    }
                    wordArray[i] = old;
                }
            }
            beginSet = nextLevelSet;

            depth++;
        }

        return 0;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
