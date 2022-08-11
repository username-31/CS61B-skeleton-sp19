public class UnionFind {

    private int[] parentOf;
    private int[] size;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        parentOf = new int[n];
        size = new int[n];
        for (int i = 0; i < parentOf.length; i++) {
            parentOf[i] = -1;
            size[i] = -1;
        }

    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) throws Exception {
        if (vertex > parentOf.length || vertex < 0) {
            throw new Exception("InvalidIndexException");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        // The parentVertex[find(v1)] is the negative size of the tree for which v1 is the root.
        return -1 * parent(find(v1));
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        return parentOf[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {

        int rootOfV1 = find(v1);
        int rootOfV2 = find(v2);

        if (sizeOf(v1) <= sizeOf(v2)) {
            parentOf[rootOfV2] = -1 * (sizeOf(v1) + sizeOf(v2));
            parentOf[rootOfV1] = v2;

        } else {
            parentOf[rootOfV1] = -1 * (sizeOf(v1) + sizeOf(v2));
            parentOf[rootOfV2] = v1;
        }

    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        int root = vertex;
        while (parentOf[root] >= 0) {
            root = parentOf[root];
        }

        // Begin path-compression.
        /*
        int tmp = vertex;
        int currentVertex = vertex;
        while (parentOf[tmp] != root) {
            parentOf[currentVertex] = root;
            tmp = parentOf[tmp];
            currentVertex = tmp;
        }
        */
        return root;
    }

    public void printParentOf() {
        for (int i = 0; i < parentOf.length; i++) {
            System.out.print(" "+parentOf[i]);
        }
    }

}
