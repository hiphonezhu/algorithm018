//编写一个程序，找出第 n 个丑数。 
//
// 丑数就是质因数只包含 2, 3, 5 的正整数。 
//
// 示例: 
//
// 输入: n = 10
//输出: 12
//解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。 
//
// 说明: 
//
// 
// 1 是丑数。 
// n 不超过1690。 
// 
// Related Topics 堆 数学 动态规划 
// 👍 416 👎 0


import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int nthUglyNumber(int n) {
        PriorityQueue<Long> priorityQueue = new PriorityQueue<>();
        Set<Long> added = new HashSet<>();

        priorityQueue.offer(1L);

        int[] nums = new int[1690];
        int[] mulArray = new int[]{2, 3, 5};
        for (int i = 0; i < nums.length; i++) {
            long min = priorityQueue.poll();
            nums[i] = (int) min;

            for (int mul : mulArray) {
                long next = min * mul;
                if (!added.contains(next)) {
                    priorityQueue.offer(next);
                    added.add(next);
                }
            }
        }

        return nums[n - 1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
