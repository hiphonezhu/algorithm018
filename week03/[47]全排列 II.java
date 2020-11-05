//给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,1,2]
//输出：
//[[1,1,2],
// [1,2,1],
// [2,1,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3]
//输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 8 
// -10 <= nums[i] <= 10 
// 
// Related Topics 回溯算法 
// 👍 517 👎 0


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        // 用 Set 去重（比集合判重提升20倍效率）
        Set<List<Integer>> ans = new HashSet<>();
        // 记录当前层已经添加过的索引
        boolean[] used = new boolean[nums.length];
        dfs(nums, used, new ArrayList<>(), ans);
        return new ArrayList<>(ans);
    }

    void dfs(int[] nums, boolean[] used, List<Integer> path, Set<List<Integer>> ans) {
        if (path.size() == nums.length) {
            if (!ans.contains(path)) {
                // 去除重复的结果
                ans.add(new ArrayList<>(path));
            }
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                // 当前层已经添加过这个元素（46题没有重复元素，偷懒可以用 path 判重）
                continue;
            }
            path.add(nums[i]);
            used[i] = true;
            dfs(nums, used, path, ans);
            path.remove(path.size() - 1);
            used[i] = false;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
