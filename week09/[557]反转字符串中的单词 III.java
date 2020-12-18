//给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。 
//
// 
//
// 示例： 
//
// 输入："Let's take LeetCode contest"
//输出："s'teL ekat edoCteeL tsetnoc"
// 
//
// 
//
// 提示： 
//
// 
// 在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。 
// 
// Related Topics 字符串 
// 👍 260 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String reverseWords(String s) {
        char[] chArray = s.toCharArray();
        int nextWordStart = 0;
        while (nextWordStart < chArray.length) {
            // 找到每个单词的首尾
            int start = nextWordStart;
            int end = start;
            while (end < chArray.length && chArray[end] != ' ') {
                end++;
            }
            end--;

            int currentWordEnd = end;
            // 翻转单词
            while (start < end) {
                char temp = chArray[start];
                chArray[start] = chArray[end];
                chArray[end] = temp;
                start++;
                end--;
            }

            nextWordStart = currentWordEnd + 2;
        }

        return new String(chArray);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
