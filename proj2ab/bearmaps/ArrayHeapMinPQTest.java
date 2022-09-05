package bearmaps;

import org.junit.Test;

import java.util.PriorityQueue;

import static org.junit.Assert.*;

public class ArrayHeapMinPQTest {
    @Test
    public void testBasics() {
        ArrayHeapMinPQ<String> a = new ArrayHeapMinPQ<>();
        ArrayHeapMinPQ<Integer> a1 = new ArrayHeapMinPQ<>(8);
        a.add("a", 1.0);
        a.add("c", 2.0);
        a.add("9", 1.0);
        a.add("b", 1.5);
        for (int i = 0; i < 4; i++) {
            System.out.println(a.removeSmallest());
        }
    }

    @Test
    public void testAddRemove() {
        ArrayHeapMinPQ<String> a = new ArrayHeapMinPQ<>(4);
        for (int i = 0; i < 31; i++) {
            String str = "OK[" + i + "]";
            a.add(str, 1.0 * i);
        }

        for (int i = 0; i < 24; i++) {
            System.out.println(a.removeSmallest());
        }
    }


    @Test
    public void testContains() {
        ArrayHeapMinPQ<String> a = new ArrayHeapMinPQ<>();
        for (int i = 0; i < 16; i++) {
            a.add("OK[" + i + "]", 8 - 1.0 * i);
        }

        a.removeSmallest();
        assertFalse(a.contains("OK[15]"));
        a.removeSmallest();
        assertFalse(a.contains("OK[14]"));
        for (int i = 0; i < 14; i++) {
//            System.out.println(a.contains("OK[" + i + "]"));
            assertTrue(a.contains("OK[" + i + "]"));
        }
    }

    @Test
    public void testChangePriority() {
        ArrayHeapMinPQ<String> a = new ArrayHeapMinPQ<>(4);
        for (int i = 0; i < 8; i++) {
            String str = "OK[" + i + "]";
            a.add(str, 8 - 1.0 * i);
        }

        a.changePriority("OK[3]", -17.0);
        assertEquals(a.getSmallest(), "OK[3]");
    }

    @Test
    public void testArrayHeapMinPQSpeed() {
        ArrayHeapMinPQ<String> a = new ArrayHeapMinPQ<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i += 1) {
            a.add("i = " + i, i * i - 100000.0 * i);
        }
        long end = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end - start) / 1000.0 + " seconds.");
    }
    @Test
    public void testPriorityQueueSpeed() {
        PriorityQueue<String> pq = new PriorityQueue<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i += 1) {
            pq.add("i = " + i);
        }
        long end = System.currentTimeMillis();
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < 100000; i += 1) {
            pq.contains("i = " + i);
        }
        long end1 = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end1 - start1) / 1000.0 + " seconds.");
    }
}
