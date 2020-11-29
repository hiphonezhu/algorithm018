//一条包含字母 A-Z 的消息通过以下方式进行了编码： 
//
// 
//'A' -> 1
//'B' -> 2
//...
//'Z' -> 26
// 
//
// 给定一个只包含数字的非空字符串，请计算解码方法的总数。 
//
// 题目数据保证答案肯定是一个 32 位的整数。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "12"
//输出：2
//解释：它可以解码为 "AB"（1 2）或者 "L"（12）。
// 
//
// 示例 2： 
//
// 
//输入：s = "226"
//输出：3
//解释：它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
// 
//
// 示例 3： 
//
// 
//输入：s = "0"
//输出：0
// 
//
// 示例 4： 
//
// 
//输入：s = "1"
//输出：1
// 
//
// 示例 5： 
//
// 
//输入：s = "2"
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 100 
// s 只包含数字，并且可能包含前导零。 
// 
// Related Topics 字符串 动态规划 
// 👍 564 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private int ans = 0;

    public int numDecodings(String s) {
        // 一、递归，可以找出所有组合（超时）
//        helper(s, 0, new ArrayList<>());
//        return ans;

        // 二、dp：
        // dp[i] = dp[i-1] + dp[i-2]（二者需要满足条件）
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) != '0' ? 1 : 0;
        for (int i = 2; i <= n; i++) {
            int first = Integer.valueOf(s.substring(i - 1, i));
            int second = Integer.valueOf(s.substring(i - 2, i));
            if (first != 0) { // 当前元素不等于0，表示可以单独解码，解码次数加上 dp[i-1]
                dp[i] += dp[i-1];
            }
            if (second >= 10 && second <= 26) { // 当前元素和上一个元素组合起来范围是[10,26]，表示可以合并起来解码，解码次数加上 dp[i-2]
                dp[i] += dp[i-2];
            }
        }
        return dp[n];
    }

    void helper(String s, int sIndex, List<String> temp) {
        if (sIndex == s.length()) {
            System.out.println(temp);
            ans++;
            return;
        }

        for (int i = 1; i <= 2; i++) {
            if (sIndex + i > s.length()) {
                continue;
            }

            String str = s.substring(sIndex, sIndex + i);
            int strInt = Integer.parseInt(str);
            if (!str.startsWith("0") && strInt <= 26 && strInt > 0) {
                temp.add(str);
                helper(s, sIndex + i, temp);
                temp.remove(temp.size() - 1);
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
