//给定一个 N 叉树，返回其节点值的前序遍历。 
//
// 例如，给定一个 3叉树 : 
//
// 
//
// 
//
// 
//
// 返回其前序遍历: [1,3,5,6,2,4]。 
//
// 
//
// 说明: 递归法很简单，你可以使用迭代法完成此题吗? Related Topics 树 
// 👍 111 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

class Solution {
    public List<Integer> preorder(Node root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Stack<Object> stack = new Stack<Object>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Object obj = stack.pop();
            if (obj instanceof Node) {
                Node node = (Node)obj;
                if (node.children != null) {
                    for (int i = node.children.size() - 1; i >=0 ; i--) {
                        stack.push(node.children.get(i));
                    }
                }
                stack.push(node.val);
            } else {
                ans.add((Integer) obj);
            }
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
