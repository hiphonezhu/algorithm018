//给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。 
//
// 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。 
//
// 
//
// 示例 1： 
//
// 输入："abc"
//输出：3
//解释：三个回文子串: "a", "b", "c"
// 
//
// 示例 2： 
//
// 输入："aaa"
//输出：6
//解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa" 
//
// 
//
// 提示： 
//
// 
// 输入的字符串长度不会超过 1000 。 
// 
// Related Topics 字符串 动态规划 
// 👍 441 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int countSubstrings(String s) {
        // 一、暴力解法
        int count = 0;
//        for (int i = 0; i < s.length(); i++) { // 循环长度
//            for (int j = 0; j < s.length() - i; j++) { // 循环开始位置
//                String str = s.substring(j, j + i + 1);
//                if (str.equals(new StringBuffer(str).reverse().toString())) { // 正反一样表示回文
//                    count++;
//                }
//            }
//        }

        /**
         * 二、dp[i][j] 表示字符串 s 在 [i, j] 区间的子串是否是回文子串
         * dp[i][j] = s[i] == s[j] && (j - i < 2 || dp[i+1][j-1])
         */
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int j = 0; j < s.length(); j++) {
            for (int i = 0; i <= j; i++) {
                if (s.charAt(i) == s.charAt(j) && (j - i < 2 || dp[i+1][j-1])) {
                    dp[i][j] = true;
                    count++;
                }
            }
        }

        return count;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
