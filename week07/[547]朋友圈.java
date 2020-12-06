//班上有 N 名学生。其中有些人是朋友，有些则不是。他们的友谊具有是传递性。如果已知 A 是 B 的朋友，B 是 C 的朋友，那么我们可以认为 A 也是 C 
//的朋友。所谓的朋友圈，是指所有朋友的集合。 
//
// 给定一个 N * N 的矩阵 M，表示班级中学生之间的朋友关系。如果M[i][j] = 1，表示已知第 i 个和 j 个学生互为朋友关系，否则为不知道。你
//必须输出所有学生中的已知的朋友圈总数。 
//
// 
//
// 示例 1： 
//
// 输入：
//[[1,1,0],
// [1,1,0],
// [0,0,1]]
//输出：2 
//解释：已知学生 0 和学生 1 互为朋友，他们在一个朋友圈。
//第2个学生自己在一个朋友圈。所以返回 2 。
// 
//
// 示例 2： 
//
// 输入：
//[[1,1,0],
// [1,1,1],
// [0,1,1]]
//输出：1
//解释：已知学生 0 和学生 1 互为朋友，学生 1 和学生 2 互为朋友，所以学生 0 和学生 2 也是朋友，所以他们三个在一个朋友圈，返回 1 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= N <= 200 
// M[i][i] == 1 
// M[i][j] == M[j][i] 
// 
// Related Topics 深度优先搜索 并查集 
// 👍 377 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    class UnionFind {
        private int count = 0;
        private int[] parent;
        public UnionFind(int n) {
            count = n;
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        public int find(int p) {
            if (p == parent[p]) {
                return p;
            }
            parent[p] = find(parent[p]);
            return parent[p];
//            while (p != parent[p]) {
//                // 路径压缩（可无）
//                parent[p] = parent[parent[p]];
//                p = parent[p];
//            }
//            return p;
        }
        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) return;
            parent[rootP] = rootQ;
            count--;
        }
    }


    public int findCircleNum(int[][] M) {
        // 方法一、dfs
//        boolean[] visited = new boolean[M.length];
//        int circleCount = 0;
//        for (int i = 0; i < M.length; i++) {
//            if (!visited[i]) {
//                dfs(M, visited, i);
//                circleCount++;
//            }
//        }
//        return circleCount;

        // 方法二、并查集
        UnionFind uf = new UnionFind(M.length);
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M.length; j++) {
                if (M[i][j] == 1) { // 有交集，合并起来
                    uf.union(i, j);
                }
            }
        }
        return uf.count;
    }

    void dfs(int[][] M, boolean[] visited, int i) {
        for (int j = 0; j < M.length; j++) {
            if (M[j][i] == 1 && !visited[j]) { // j 和 i 是朋友，且 j 没有访问过
                visited[j] = true;
                dfs(M, visited, j);
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
