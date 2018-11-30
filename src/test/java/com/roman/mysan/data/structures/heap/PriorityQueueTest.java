package com.roman.mysan.data.structures.heap;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;

public class PriorityQueueTest {

    @Test
    public void should_properly_insert_items() {
        //given
        Integer[] arr = {0, 1, 4, 3, 5, 2};
        Integer[] expectedArray = {5, 4, 2, 0, 3, 1};
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        //when
        for (int i : arr) {
            queue.insert(i);
        }

        //then
        Iterator<Integer> iterator = queue.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            assertEquals(expectedArray[i++], iterator.next());
        }
    }

    @Test
    public void should_extract_max() {
        //given
        Integer[] arr = {0, 1, 4, 3, 5, 2};
        Integer[] expectedArray = {5, 4, 3, 2, 1, 0};
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        //and
        for (int i : arr) {
            queue.insert(i);
        }

        //when
        int i = 0;
        while (i < queue.size()) {
            //then
            assertEquals(expectedArray[i++], queue.extractMax());
        }
    }

    @Test
    public void should_sort_array() {
        //given
        Integer[] arr = {0, 1, 4, 3, 5, 2};
        Integer[] expectedArray = {0, 1, 2, 3, 4, 5};
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        //when
        queue.sort(arr);

        //then
        int i = 0;
        while (i < queue.size()) {
            assertEquals(expectedArray[i], arr[i]);
            i++;
        }
    }
}
