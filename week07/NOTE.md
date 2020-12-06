一、双向 BFS 模板

void bfs(begin, end, bank) {
    if (end 在 bank 中不存在) {
        return;
    }
    Set beginSet;
    Set endSet;
    beginSet.add(begin);
    endSet.add(end);
    while (beginSet 和 endSet 都不为空) {
        if (beginSet.size() > endSet.size()) {
            // 交换 beginSet 和 endSet
        }

        Set nextLevelSet;
        for (遍历 beginSet) {
            if (与 endSet 相交，找到答案) {
                return;
            }

            if (下一层节点合法，加入到集合) {
                nextLevelSet.add(...);
            }
        }
        beginSet = nextLevelSet;
    }
}



二、AVL和红黑树

1、引入的原因：二叉搜索树查找的时间复杂度取决于深度。极端情况下，二叉搜索树会退化成链表，所以要保证左右子树的平衡才能保证查找的效率

2、AVL 树
2.1 平衡因子 -1、0、1，左右子树高度差不超过1
2.2 左旋、右旋、左右旋、右左旋

3、红黑树
3.1 原因：AVL 在插入和删除的时候都需要做自旋操作以保证平衡性，这个操作的频次比较高，所以插入和删除的成本比较高
3.2 红黑树保证左右子树的高度差小于2倍
3.3 满足如下条件的二叉搜索树
3.3.1 根节点是黑色
3.3.2 空的叶子节点是黑色
3.3.3 红色节点不能相连
3.3.4 从任意节点到它的所有叶子节点都包含相同数量的黑色节点

4、比较
4.1 AVL 查询速度快于红黑树
4.2 红黑树插入和删除速度快于 AVL
4.3 AVL 每个节点需要存储平衡因子，而红黑树只需要一个 Bit 表示是黑色或者红色，因此更加节约空间
4.4 红黑树经常用在各个语言的 API 中，比如 Map，而 AVL 一般用于数据库

