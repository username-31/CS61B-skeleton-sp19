package es.datastructur.synthesizer;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Tests the ArrayRingBuffer class.
 *
 * @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void testBasics() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer(10);
        assertEquals(arb.capacity(), 10);
        assertTrue(arb.isEmpty());
        for (int i = 0; i < 10; i++) {
            arb.enqueue(i);
            System.out.println(arb.peek());
        }

//        System.out.println(arb.peek());
    }

    @Test
    public void testIterator() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(10);
        for (int i = 0; i < 10; i++) {
            arb.enqueue(10 - i);
        }

        for (int i : arb) {
            System.out.println(i);
        }
    }

    @Test
    public void testEquals() {
        ArrayRingBuffer<Integer> arb1 = new ArrayRingBuffer<>(10);
        for (int i = 0; i < 10; i++) {
            arb1.enqueue(10 - i);
        }

        ArrayRingBuffer<Integer> arb2 = new ArrayRingBuffer<>(10);
        for (int i = 0; i < 10; i++) {
            arb2.enqueue(10 - i);
        }

        ArrayRingBuffer<Float> arb3 = new ArrayRingBuffer<>(10);
        assertEquals(arb2, arb1);
        assertNotEquals(arb3, arb1);
    }
}
