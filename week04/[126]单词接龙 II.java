//给定两个单词（beginWord 和 endWord）和一个字典 wordList，找出所有从 beginWord 到 endWord 的最短转换序列。转换
//需遵循如下规则： 
//
// 
// 每次转换只能改变一个字母。 
// 转换后得到的单词必须是字典中的单词。 
// 
//
// 说明: 
//
// 
// 如果不存在这样的转换序列，返回一个空列表。 
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
//输出:
//[
//  ["hit","hot","dot","dog","cog"],
//  ["hit","hot","lot","log","cog"]
//]
// 
//
// 示例 2: 
//
// 输入:
//beginWord = "hit"
//endWord = "cog"
//wordList = ["hot","dot","dog","lot","log"]
//
//输出: []
//
//解释: endWord "cog" 不在字典中，所以不存在符合要求的转换序列。 
// Related Topics 广度优先搜索 数组 字符串 回溯算法 
// 👍 358 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public List<List<String>> findLadders(String start, String end, List<String> wordList) {
        HashSet<String> dict = new HashSet<String>(wordList);
        List<List<String>> res = new ArrayList<List<String>>();
        // 每个节点对应的可以变换的节点集合
        HashMap<String, ArrayList<String>> nodeNeighbors = new HashMap<String, ArrayList<String>>();
        // 每个节点到 start 的距离（深度）
        HashMap<String, Integer> distance = new HashMap<String, Integer>();
        ArrayList<String> solution = new ArrayList<String>();

        dict.add(start);
        bfs(start, end, dict, nodeNeighbors, distance);

        dfs(start, end, nodeNeighbors, distance, solution, res);
        return res;
    }

    private void bfs(String start, String end, Set<String> dict, HashMap<String, ArrayList<String>> nodeNeighbors, HashMap<String, Integer> distance) {
        Queue<String> queue = new LinkedList<String>();
        queue.offer(start);
        distance.put(start, 0);

        while (!queue.isEmpty()) {
            int count = queue.size();
            boolean foundEnd = false;
            for (int i = 0; i < count; i++) {
                String cur = queue.poll();
                ArrayList<String> neighbors = getNeighbors(cur, dict);
                List<String> nodeNeighborList = nodeNeighbors.get(cur);
                if (nodeNeighborList == null) {
                    nodeNeighborList = new ArrayList<>();
                    nodeNeighbors.put(cur, neighbors);
                }
                nodeNeighborList.addAll(neighbors);

                // 当前 node 距离 start 的距离
                int curDistance = distance.get(cur);
                for (String neighbor : neighbors) {
                    if (!distance.containsKey(neighbor)) {
                        // 只存储下层节点
                        distance.put(neighbor, curDistance + 1);
                        if (end.equals(neighbor))
                            // 当前层找到最短路径
                            foundEnd = true;
                        else
                            queue.offer(neighbor);
                    }
                }
            }

            if (foundEnd)
                break;
        }
    }

    /**
     * 查找 node 节点所有可能的变换
     *
     * @param node
     * @param dict
     * @return
     */
    private ArrayList<String> getNeighbors(String node, Set<String> dict) {
        ArrayList<String> res = new ArrayList<String>();
        char[] chs = node.toCharArray();

        for (int i = 0; i < chs.length; i++) {
            char old = chs[i];
            for (char ch = 'a'; ch <= 'z'; ch++) {
                if (ch != old) {
                    chs[i] = ch;
                    if (dict.contains(new String(chs))) {
                        res.add(new String(chs));
                    }
                }
            }
            chs[i] = old;
        }
        return res;
    }

    private void dfs(String cur, String end, HashMap<String, ArrayList<String>> nodeNeighbors, HashMap<String, Integer> distance, ArrayList<String> solution, List<List<String>> res) {
        solution.add(cur);
        if (end.equals(cur)) {
            res.add(new ArrayList<String>(solution));
            return;
        }
        if (nodeNeighbors.containsKey(cur)) {
            for (String next : nodeNeighbors.get(cur)) {
                // next 是 cur 的下层节点（过滤掉上层、同层）
                if (distance.get(next) == distance.get(cur) + 1) {
                    dfs(next, end, nodeNeighbors, distance, solution, res);
                    solution.remove(solution.size() - 1);
                }
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
