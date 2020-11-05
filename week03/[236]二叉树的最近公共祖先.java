//给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。 
//
// 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（
//一个节点也可以是它自己的祖先）。” 
//
// 例如，给定如下二叉树: root = [3,5,1,6,2,0,8,null,null,7,4] 
//
// 
//
// 
//
// 示例 1: 
//
// 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
//输出: 3
//解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
// 
//
// 示例 2: 
//
// 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
//输出: 5
//解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
// 
//
// 
//
// 说明: 
//
// 
// 所有节点的值都是唯一的。 
// p、q 为不同节点且均存在于给定的二叉树中。 
// 
// Related Topics 树 
// 👍 815 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    TreeNode ans;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);
        return ans;
    }

    /**
     * 深度优先查找
     *
     * @param root
     * @param p
     * @param q
     * @return root 是否包含 p 或者 q
     */
    boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return false;
        }
        // 当前节点是否是 p 或者 q
        boolean current = root.val == p.val || root.val == q.val;
        // 左子树是否包含 p 或者 q
        boolean left = dfs(root.left, p, q);
        // 右子树是否包含 p 或者 q
        boolean right = dfs(root.right, p, q);
        if (left && right) {
            // 左子树包含 p 或者 q，且右子树包含 p 或者 q
            ans = root;
        } else if (current && (left || right)) {
            // 当前节点就是 p 或者 q，且左、右子树包含 p 或者 q
            ans = root;
        }

        // root 是否包含 p 或者 q
        return current || left || right;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
