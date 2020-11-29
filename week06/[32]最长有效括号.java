//给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。 
//
// 示例 1: 
//
// 输入: "(()"
//输出: 2
//解释: 最长有效括号子串为 "()"
// 
//
// 示例 2: 
//
// 输入: ")()())"
//输出: 4
//解释: 最长有效括号子串为 "()()"
// 
// Related Topics 字符串 动态规划 
// 👍 1086 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int longestValidParentheses(String s) {
        int[] dp = new int[s.length()];
        int maxLength = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') { // ...(), 2 + '(' 左侧的有效子串
                    // ()(), i == 3
                    dp[i] = 2 + (i - 2 >= 0 ? dp[i - 2] : 0);
                } else if (s.charAt(i - 1) == ')' && dp[i - 1] > 0) { // ...))，倒数第二个括号必须是子串的一部分
                    // ((()), i = 4
                    if (i - 1 - dp[i - 1] >= 0 && s.charAt(i - 1 - dp[i - 1]) == '(') {
                        dp[i] = 2 + dp[i - 1] + (i - 2 - dp[i - 1] >= 0 ? dp[i - 2 - dp[i - 1]] : 0);
                    }
                }
                maxLength = Math.max(maxLength, dp[i]);
            }
        }
        return maxLength;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
