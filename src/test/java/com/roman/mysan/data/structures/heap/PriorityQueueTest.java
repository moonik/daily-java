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
}
