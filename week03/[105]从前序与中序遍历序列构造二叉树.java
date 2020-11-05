//根据一棵树的前序遍历与中序遍历构造二叉树。 
//
// 注意: 
//你可以假设树中没有重复的元素。 
//
// 例如，给出 
//
// 前序遍历 preorder = [3,9,20,15,7]
//中序遍历 inorder = [9,3,15,20,7] 
//
// 返回如下的二叉树： 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7 
// Related Topics 树 深度优先搜索 数组 
// 👍 743 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.HashMap;
import java.util.Map;

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
    Map<Integer, Integer> inorderMap = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 存储中序遍历每个节点在数组 inorder 中的索引
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return helper(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    TreeNode helper(int[] preorder, int preLeft, int preRight, int[] inorder, int inLeft, int inRight) {
        if (preLeft > preRight) {
            return null;
        }

        // 根节点的值
        int rootVal = preorder[preLeft];
        // 根节点在中序遍历中的索引
        int rootIndexInOrder = inorderMap.get(rootVal);
        // 根节点的左子树节点个数（！！！注意减去 inLeft）
        int leftNodeCount = rootIndexInOrder - inLeft;
        // 根节点的右子树节点个数（！！！注意用 inRight 去减，而不是 inorder.length）
        int rightNodeCount = inRight - rootIndexInOrder;

        TreeNode root = new TreeNode(rootVal);
        root.left = helper(preorder, preLeft +  1, preRight - rightNodeCount, inorder, inLeft, rootIndexInOrder - 1);
        root.right = helper(preorder, preLeft + leftNodeCount + 1, preRight, inorder, rootIndexInOrder + 1, inRight);
        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
