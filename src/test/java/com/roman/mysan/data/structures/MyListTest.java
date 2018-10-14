package com.roman.mysan.data.structures;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.format;

public class MyListTest {

    @Test
    public void shouldCreateArrayList() {
        //given
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5));
        int capacity = 17;
        int expectedCapacity = 32;
        int defaultCapacity = 16;

        //when
        MyList<Integer> myList = new MyList<>();
        MyList<Integer> myListWithCollection = new MyList<>(list);
        MyList<Integer> myListWithProvidedSize = new MyList<>(capacity);

        //then
        assertEquals(defaultCapacity, myList.capacity());
        assertEquals(expectedCapacity, myListWithCollection.capacity());
        assertEquals(expectedCapacity, myListWithProvidedSize.capacity());
    }

    @Test
    public void shouldInsertAndPrependIntoList() {
        //given
        MyList<Integer> list = new MyList<>();
        Integer[] elements = new Integer[] {1, 2, 3, 4, 99};
        list.push(elements[0]);
        list.push(elements[1]);
        list.push(elements[2]);

        //when
        list.insert(0, elements[3]);
        //then
        assertEquals(4, list.size());
        assertEquals(elements[3], list.at(0));

        //when
        list.insert(2, elements[2]);
        //then
        assertEquals(5, list.size());
        assertEquals(elements[2], list.at(2));

        //when
        list.prepend(elements[4]);
        //then
        assertEquals(6, list.size());
        assertEquals(elements[4], list.at(0));
    }

    @Test
    public void shouldResizeArray() {
        //given
        MyList<Integer> myList = new MyList<>();
        int expectedCapacity = 32;

        //when
        for (int i = 0; i < 18; i++) {
            myList.push(i);
        }

        //then
        assertEquals(expectedCapacity, myList.capacity());
    }

    @Test
    public void shouldDeleteItem() {
        //given
        MyList<Integer> myList = new MyList<>();
        Integer[] items = new Integer[] {1, 2, 3, 4, 2, 7, 4, 5, 2, 9};
        int size = items.length;
        for (Integer item : items) {
            myList.push(item);
        }

        //when
        myList.delete(0);

        //then
        assertEquals(items[1], myList.at(0));
        assertEquals(size-1, myList.size());

        //when
        myList.remove(items[1]);

        //then
        assertEquals(items[3], myList.at(1));
        assertEquals(size-3, myList.size());
    }
}
