//给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。 
//
// 示例: 
//
// 输入: n = 4, k = 2
//输出:
//[
//  [2,4],
//  [3,4],
//  [2,3],
//  [1,2],
//  [1,3],
//  [1,4],
//] 
// Related Topics 回溯算法 
// 👍 430 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(1, n, k, new ArrayList<>(), ans);
        return ans;
    }

    void dfs(int index, int n, int k, List<Integer> path, List<List<Integer>> ans) {
        if (path.size() == k) {
            // 找到了 k 个数字
            ans.add(new ArrayList<>(path));
            return;
        }

        // index...n，index 起始为 1
        for (int i = index; i <= n; i++) {
            path.add(i);
            // i + 1 进入下一层（！！！注意不是 index + 1，回溯之后 index 还是上一层的值）
            dfs(i + 1, n, k, path, ans);
            path.remove(path.size() - 1);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
