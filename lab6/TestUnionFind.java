import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class TestUnionFind {
    @Test
    public void testBasics() {
        UnionFind uf = new UnionFind(16);
        uf.union(3, 2);
        uf.union(4, 5);
        uf.union(3, 4);
        uf.union(6, 7);
        uf.union(8, 9);
        uf.union(6, 9);
        uf.union(2, 9);
        uf.union(0, 9);
        uf.printParentOf();


    }
}
