//一条基因序列由一个带有8个字符的字符串表示，其中每个字符都属于 "A", "C", "G", "T"中的任意一个。 
//
// 假设我们要调查一个基因序列的变化。一次基因变化意味着这个基因序列中的一个字符发生了变化。 
//
// 例如，基因序列由"AACCGGTT" 变化至 "AACCGGTA" 即发生了一次基因变化。 
//
// 与此同时，每一次基因变化的结果，都需要是一个合法的基因串，即该结果属于一个基因库。 
//
// 现在给定3个参数 — start, end, bank，分别代表起始基因序列，目标基因序列及基因库，请找出能够使起始基因序列变化为目标基因序列所需的最少变
//化次数。如果无法实现目标变化，请返回 -1。 
//
// 注意: 
//
// 
// 起始基因序列默认是合法的，但是它并不一定会出现在基因库中。 
// 所有的目标基因序列必须是合法的。 
// 假定起始基因序列与目标基因序列是不一样的。 
// 
//
// 示例 1: 
//
// 
//start: "AACCGGTT"
//end:   "AACCGGTA"
//bank: ["AACCGGTA"]
//
//返回值: 1
// 
//
// 示例 2: 
//
// 
//start: "AACCGGTT"
//end:   "AAACGGTA"
//bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]
//
//返回值: 2
// 
//
// 示例 3: 
//
// 
//start: "AAAAACCC"
//end:   "AACCCCCC"
//bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"]
//
//返回值: 3
// 
// 👍 63 👎 0


import java.util.HashSet;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minMutation(String start, String end, String[] bank) {
        // 方法一、BFS
//        Set<String> wordListSet = new HashSet<>();
//        for (String b : bank) {
//            wordListSet.add(b);
//        }
//        char[] charArray = new char[]{'A', 'C', 'G', 'T'};
//        Queue<String> queue = new LinkedList<>();
//        Set<String> visited = new HashSet<>();
//        queue.add(start);
//        visited.add(start);
//        int depth = 0;
//        while (!queue.isEmpty()) {
//            int levelSize = queue.size();
//            while (levelSize-- > 0) {
//                String node = queue.poll();
//                if (node.equals(end)) {
//                    return depth;
//                }
//                char[] nodeArray = node.toCharArray();
//                for (int i = 0; i < node.length(); i++) {
//                    char temp = nodeArray[i];
//                    for (char ch : charArray) {
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
//        return -1;

        // 方法二：双向BFS
        Set<String> wordListSet = new HashSet<>();
        for (String b : bank) {
            wordListSet.add(b);
        }
        if (!wordListSet.contains(end)) {
            return -1;
        }
        char[] charArray = new char[]{'A', 'C', 'G', 'T'};
        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        Set<String> visited = new HashSet<>();
        beginSet.add(start);
        endSet.add(end);
        visited.add(start);
        visited.add(end);
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
                    for (char ch : charArray) {
                        wordArray[i] = ch;
                        String newWord = new String(wordArray);
                        if (endSet.contains(newWord)) {
                            return depth;
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

        return -1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
