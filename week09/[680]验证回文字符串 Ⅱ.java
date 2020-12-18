//给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。 
//
// 示例 1: 
//
// 
//输入: "aba"
//输出: True
// 
//
// 示例 2: 
//
// 
//输入: "abca"
//输出: True
//解释: 你可以删除c字符。
// 
//
// 注意: 
//
// 
// 字符串只包含从 a-z 的小写字母。字符串的最大长度是50000。 
// 
// Related Topics 字符串 
// 👍 297 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean validPalindrome(String s) {
        if (s.length() == 0) {
            return true;
        }
        char[] chArray = s.toCharArray();
        int left = 0;
        int right = chArray.length - 1;

        boolean hasDeleteLeft = false;
        boolean hasDeleteRight = false;
        int lastDeleteLeft = 0;
        int lastDeleteRight = 0;

        while (left < right) {
            if (chArray[left] == chArray[right]) {
                left++;
                right--;
            } else {
                if (hasDeleteLeft && hasDeleteRight) {
                    return false;
                } else if (hasDeleteLeft || hasDeleteRight) {
                    // 回滚到第一次删除的下标
                    left = lastDeleteLeft;
                    right = lastDeleteRight;
                }

                if (!hasDeleteLeft && chArray[left + 1] == chArray[right]) {
                    hasDeleteLeft = true;
                    lastDeleteLeft = left;
                    lastDeleteRight = right;
                    left++;
                } else if (!hasDeleteRight && chArray[left] == chArray[right - 1]) {
                    hasDeleteRight = true;
                    lastDeleteLeft = left;
                    lastDeleteRight = right;
                    right--;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
