import edu.princeton.cs.algs4.Merge;
import edu.princeton.cs.algs4.Queue;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestSortAlgs {

    @Test
    public void testQuickSort() {
        Queue<Integer> q1 = new Queue<>();
        Integer[] nums1 = {1, 3, 5, 2, 8, 7, 6};
        Queue<Integer> q2 = new Queue<>();
        Integer[] nums2 = {9, 8, 7, 6, 5, 4, 3};
        Queue<Integer> q3 = new Queue<>();
        Integer[] nums3 = {3, 4, 5, 6, 7, 9, 10};
        for (int i = 0; i < 7; i++) {
            q1.enqueue(nums1[i]);
            q2.enqueue(nums2[i]);
            q3.enqueue(nums3[i]);
        }
        Queue<Integer> q4 = new Queue<>();
        q4.enqueue(1);
        Queue<Integer> q11 = QuickSort.quickSort(q1);
        Queue<Integer> q22 = QuickSort.quickSort(q2);
        Queue<Integer> q33 = QuickSort.quickSort(q3);
        Queue<Integer> q44 = QuickSort.quickSort(q4);

        assertTrue(isSorted(q11));
        assertTrue(isSorted(q22));
        assertTrue(isSorted(q33));
        assertTrue(isSorted(q44));
    }

    @Test
    public void testMergeSort() {
        String[] words = {"dog", "bird", "cat", "monkey", "giraffe", "eagle", "apple", "elephant"};
        Queue<String> tas = new Queue<>();
        for (String word : words)
            tas.enqueue(word);

        Integer[] nums = {1, 3, 5, 2, 8, 7, 6};
        Queue<Integer> tas2 = new Queue<>();
        for (Integer num : nums)
            tas2.enqueue(num);

        assertTrue(isSorted(MergeSort.mergeSort(tas)));
        assertTrue(isSorted(MergeSort.mergeSort(tas2)));

    }

    /**
     * Returns whether a Queue is sorted or not.
     *
     * @param items A Queue of items
     * @return true/false - whether "items" is sorted
     */
    private <Item extends Comparable> boolean isSorted(Queue<Item> items) {
        if (items.size() <= 1) {
            return true;
        }
        Item curr = items.dequeue();
        Item prev = curr;
        while (!items.isEmpty()) {
            prev = curr;
            curr = items.dequeue();
            if (curr.compareTo(prev) < 0) {
                return false;
            }
        }
        return true;
    }
}
