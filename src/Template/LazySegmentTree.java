package Template;

class LazySegTree {
    private int[] tree, lazy;

    private LazySegTree(int[] arr) {
        tree = new int[4 * arr.length];
        lazy = new int[4 * arr.length];
        buildSegTree(arr, 0, 0, arr.length - 1);
    }

    private void buildSegTree(int[] arr, int treeIndex, int lo, int hi) {
        if (lo == hi) {
            tree[treeIndex] = arr[lo];
            return;
        }
        int mid = lo + (hi - lo) / 2;
        buildSegTree(arr, 2 * treeIndex + 1, lo, mid);
        buildSegTree(arr, 2 * treeIndex + 2, mid + 1, hi);
        tree[treeIndex] = tree[2 * treeIndex + 1] + tree[2 * treeIndex + 2];
    }

    private void updateLazySegTree(int treeIndex, int lo, int hi, int i, int j) {
        if (lazy[treeIndex] != 0) {
            tree[treeIndex] = (hi - lo + 1) - tree[treeIndex];
            if (lo != hi) {
                lazy[2 * treeIndex + 1] ^= 1;
                lazy[2 * treeIndex + 2] ^= 1;
            }
            lazy[treeIndex] = 0;
        }
        if (lo > hi || lo > j || hi < i) {
            return;
        }
        if (i <= lo && hi <= j) {
            tree[treeIndex] = (hi - lo + 1) - tree[treeIndex];
            if (lo != hi) {
                lazy[2 * treeIndex + 1] ^= 1;
                lazy[2 * treeIndex + 2] ^= 1;
            }
            return;
        }
        int mid = lo + (hi - lo) / 2;
        updateLazySegTree(2 * treeIndex + 1, lo, mid, i, j);
        updateLazySegTree(2 * treeIndex + 2, mid + 1, hi, i, j);
        tree[treeIndex] = tree[2 * treeIndex + 1] + tree[2 * treeIndex + 2];
    }

    private long queryLazySegTree(int treeIndex, int lo, int hi, int i, int j) {
        if (lo > j || hi < i) {
            return 0;
        }
        if (lazy[treeIndex] != 0) {
            tree[treeIndex] = (hi - lo + 1) - tree[treeIndex];
            if (lo != hi) {
                lazy[2 * treeIndex + 1] ^= 1;
                lazy[2 * treeIndex + 2] ^= 1;
            }
            lazy[treeIndex] = 0;
        }
        if (i <= lo && j >= hi) {
            return tree[treeIndex];
        }
        int mid = lo + (hi - lo) / 2;
        if (i > mid) {
            return queryLazySegTree(2 * treeIndex + 2, mid + 1, hi, i, j);
        } else if (j <= mid) {
            return queryLazySegTree(2 * treeIndex + 1, lo, mid, i, j);
        }
        long leftQuery = queryLazySegTree(2 * treeIndex + 1, lo, mid, i, mid);
        long rightQuery = queryLazySegTree(2 * treeIndex + 2, mid + 1, hi, mid + 1, j);
        return leftQuery + rightQuery;
    }
}