//给定一个字符串，逐个翻转字符串中的每个单词。 
//
// 说明： 
//
// 
// 无空格字符构成一个 单词 。 
// 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。 
// 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。 
// 
//
// 
//
// 示例 1： 
//
// 输入："the sky is blue"
//输出："blue is sky the"
// 
//
// 示例 2： 
//
// 输入："  hello world!  "
//输出："world! hello"
//解释：输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
// 
//
// 示例 3： 
//
// 输入："a good   example"
//输出："example good a"
//解释：如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
// 
//
// 示例 4： 
//
// 输入：s = "  Bob    Loves  Alice   "
//输出："Alice Loves Bob"
// 
//
// 示例 5： 
//
// 输入：s = "Alice does not even like bob"
//输出："bob like even not does Alice"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 104 
// s 包含英文大小写字母、数字和空格 ' ' 
// s 中 至少存在一个 单词 
// 
//
// 
// 
//
// 
//
// 进阶： 
//
// 
// 请尝试使用 O(1) 额外空间复杂度的原地解法。 
// 
// Related Topics 字符串 
// 👍 248 👎 0


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String reverseWords(String s) {
//        String[] words = s.trim().split(" +");
//        List<String> list = Arrays.asList(words);
//        Collections.reverse(list);
//        return String.join(" ", list);

        // 去除多余的空格
        s = trimSpaces(s).toString();
        // 翻转整个字符串
        char[] chArray = s.toCharArray();
        int left = 0;
        int right = chArray.length - 1;
        while (left < right) {
            char temp = chArray[left];
            chArray[left] = chArray[right];
            chArray[right] = temp;
            left++;
            right--;
        }

        // 翻转每个单词
        int wordStart = 0;
        while (wordStart < chArray.length) {
            // 查找单词的结尾
            int wordEnd = wordStart;
            while (wordEnd < chArray.length && chArray[wordEnd] != ' ') {
                wordEnd++;
            }
            wordEnd--;

            int currentWordEnd = wordEnd;
            // 翻转单词
            while (wordStart < wordEnd) {
                char temp = chArray[wordStart];
                chArray[wordStart] = chArray[wordEnd];
                chArray[wordEnd] = temp;
                wordStart++;
                wordEnd--;
            }

            wordStart = currentWordEnd + 2;
        }

        return new String(chArray);
    }

    public StringBuilder trimSpaces(String s) {
        int left = 0, right = s.length() - 1;
        // 去掉字符串开头的空白字符
        while (left <= right && s.charAt(left) == ' ') {
            ++left;
        }

        // 去掉字符串末尾的空白字符
        while (left <= right && s.charAt(right) == ' ') {
            --right;
        }

        // 将字符串间多余的空白字符去除
        StringBuilder sb = new StringBuilder();
        while (left <= right) {
            char c = s.charAt(left);

            if (c != ' ') {
                sb.append(c);
            } else if (sb.charAt(sb.length() - 1) != ' ') {
                sb.append(c);
            }

            ++left;
        }
        return sb;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
