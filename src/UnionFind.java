public class UnionFind {
    private int numIslands;
    private int[] size;
    private int[] parent;

    public UnionFind(int rows, int cols) {
        // right shift by one to avoid ambiguity
        // parent == 0 means not an island
        this.size = new int[rows * cols + 1];
        this.parent = new int[rows * cols + 1];
        this.numIslands = 0;
    }

    public int getRoot(int p) { // O(log N)
        // 优化1: 压缩高度 两步并一步
        int cur = p;
        while (parent[cur] != cur) {
            parent[cur] = parent[parent[cur]];
            cur = parent[cur];
        }
        // 小优化3: 根据use case先find后union, union的时间复杂度变为O(1)
        parent[p] = cur;
        return cur;
    }

    public boolean find(int p, int q) {
        return getRoot(p) == getRoot(q);
    }

    public void union(int p, int q) { // 如果已经find过就是O(1) 否则还要getRoot是O(log N)
        // 优化2: 小树接在大树根上, 尽可能不增加tree的高度
        int rootP = getRoot(p);
        int rootQ = getRoot(q);
        if (size[rootP] >= size[rootQ]) { // rootP is bigger tree
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
            this.numIslands--;
        } else {
            union(q, p);
        }
    }

    public void addIsland(int p) {
        if (isIsland(p)) {
            return;
        }
        parent[p] = p;
        size[p] = 1;
        this.numIslands++;
    }

    public boolean isIsland(int p) {
        return parent[p] > 0;
    }

    public int getNumIslands() {
        return numIslands;
    }
}
