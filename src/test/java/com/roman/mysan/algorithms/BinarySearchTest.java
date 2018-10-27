package com.roman.mysan.algorithms;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class BinarySearchTest {

    @Test
    public void should_successfuly_find_item_using_iterative_search() {
        //given
        int[] arr = new int[] {1, 2};
        int firstKey = 1;
        int secondKey = 2;
        int firstExpected = 0;
        int secondExpected = 1;

        //when
        int actualFirst = BinarySearch.search(arr, firstKey);
        int actualSecond = BinarySearch.search(arr, secondKey);

        //then
        assertEquals(firstExpected, actualFirst);
        assertEquals(secondExpected, actualSecond);
    }

    @Test
    public void should_successfuly_find_item_using_recursive_search() {
        //given
        int[] arr = new int[] {1, 2};
        int firstKey = 1;
        int secondKey = 2;
        int firstExpected = 0;
        int secondExpected = 1;

        //when
        int actualFirst = BinarySearch.search(arr, firstKey, 0, arr.length-1);
        int actualSecond = BinarySearch.search(arr, secondKey, 0, arr.length-1);

        //then
        assertEquals(firstExpected, actualFirst);
        assertEquals(secondExpected, actualSecond);
    }
}
