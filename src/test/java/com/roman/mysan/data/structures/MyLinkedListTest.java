package com.roman.mysan.data.structures;

import com.roman.mysan.data.structures.list.linked.List;
import com.roman.mysan.data.structures.list.linked.MyLinkedList;
import com.roman.mysan.data.structures.list.linked.Node;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class MyLinkedListTest {

    @Test
    public void should_return_value_at_index() {
        //given
        List<Integer> list = createList();
        int indexStart = 0;
        int indexMiddle = 34;
        int indexEnd = 99;
        Integer zero = 0;
        Integer midValue = 34;
        Integer endValue = 99;

        //when
        Integer actualZero = list.valueAt(indexStart);
        Integer actualMiddle = list.valueAt(indexMiddle);
        Integer actualEnd = list.valueAt(indexEnd);

        //then
        assertEquals(zero, actualZero);
        assertEquals(midValue, actualMiddle);
        assertEquals(endValue, actualEnd);
    }

    @Test
    public void should_add_to_the_front() {
        //given
        List<Integer> linkedList = createList();
        Integer value = 999;
        Integer oldValue = 0;
        int expectedSize = 101;

        //when
        linkedList.pushFront(value);

        //then
        assertEquals(value, linkedList.valueAt(0));
        assertEquals(oldValue, linkedList.valueAt(1));
        assertEquals(expectedSize, linkedList.size());
    }

    @Test
    public void should_pop_front() {
        //given
        List<Integer> linkedList = createList();
        Integer expectedValue = 0;
        int expectedSize = 99;

        //when
        Integer value = linkedList.popFront();

        //then
        assertEquals(expectedValue, value);
        assertEquals(expectedSize, linkedList.size());
    }

    @Test
    public void should_pop_back() {
        //given
        List<Integer> linkedList = createList();
        List<Integer> oneElementList = new MyLinkedList<>();
        Integer value = 48343024;
        Integer expectedOne = 99;
        Integer expectedTwo = 48343024;
        oneElementList.pushFront(value);
        int sizeOne = 99;
        int sizeTwo = 0;

        //when
        Integer v = linkedList.popBack();
        Integer v2 = oneElementList.popBack();

        //then
        assertEquals(expectedOne, v);
        assertEquals(expectedTwo, v2);
        assertEquals(sizeOne, linkedList.size());
        assertEquals(sizeTwo, oneElementList.size());
    }

    @Test
    public void should_insert_at_index() {
        //given
        List<Integer> linkedList = createList();
        Integer value = 43853;
        int index = 34;
        int expSize = 101;

        //when
        linkedList.insert(index, value);

        //then
        assertEquals(value, linkedList.valueAt(index));
        assertEquals(expSize, linkedList.size());
    }

    @Test
    public void should_delete_at_index() {
        //given
        List<Integer> linkedList = createList();
        Integer value = 34;
        int index = 34;
        int expSize = 99;

        //when
        linkedList.erase(index);

        //then
        assertNotEquals(value, linkedList.valueAt(index));
        assertEquals(expSize, linkedList.size());
    }

    @Test
    public void should_return_value_n_from_end() {
        //given
        List<Integer> linkedList = createList();
        Integer valueOne = 99;
        Integer valueTwo = 35;
        int[] n = new int[] {0, 64};

        //when
        Integer actualValueOne = linkedList.valueNfromEnd(n[0]);
        Integer actualValueTwo = linkedList.valueNfromEnd(n[1]);

        //then
        assertEquals(valueOne, actualValueOne);
        assertEquals(valueTwo, actualValueTwo);
    }

    @Test
    public void should_reverse_list() {
        //given
        List<Integer> linkedList = createList();

        //when
        linkedList.reverse();

        //then
        int j = 0;
        for (Integer i = linkedList.size()-1; i >= 0; i--) {
            assertEquals(i, linkedList.valueAt(j++));
        }
    }

    @Test
    public void should_pop_back_without_tail() {
        //given
        List<Integer> list = createList();
        Integer expected = 99;

        //when
        Integer actual = list.popBackWithNoTail();

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void should_get_middle_element_with_one_element_list() {
        //given
        Integer expectedMiddle = 0;
        List<Integer> list = new MyLinkedList<>();
        list.pushBack(expectedMiddle);

        //when
        Node<Integer> middle = list.getMiddle();

        //then
        assertEquals(expectedMiddle, middle.getValue());
    }

    @Test
    public void should_get_middle_element_with_two_elements_list() {
        //given
        Integer expectedMiddle = 1;
        List<Integer> list = new MyLinkedList<>();
        list.pushBack(0);
        list.pushBack(1);

        //when
        Node<Integer> middle = list.getMiddle();

        //then
        assertEquals(expectedMiddle, middle.getValue());
    }

    @Test
    public void should_get_middle_element_with_three_elements_list() {
        //given
        Integer expectedMiddle = 1;
        List<Integer> list = new MyLinkedList<>();
        list.pushBack(0);
        list.pushBack(1);
        list.pushBack(2);

        //when
        Node<Integer> middle = list.getMiddle();

        //then
        assertEquals(expectedMiddle, middle.getValue());
    }

    @Test
    public void should_get_middle_with_four_elements_list() {
        //given
        Integer expectedMiddle = 2;
        List<Integer> list = new MyLinkedList<>();
        list.pushBack(0);
        list.pushBack(1);
        list.pushBack(2);
        list.pushBack(3);

        //when
        Node<Integer> middle = list.getMiddle();

        //then
        assertEquals(expectedMiddle, middle.getValue());
    }

    @Test
    public void should_get_middle() {
        //given
        Integer expectedMiddle = 50;
        List<Integer> list = createList();

        //when
        Node<Integer> middle = list.getMiddle();

        //then
        assertEquals(expectedMiddle, middle.getValue());
    }

    private static List<Integer> createList() {
        List<Integer> linkedList = new MyLinkedList<>();
        for (int i = 0; i < 100; i++) {
            linkedList.pushBack(i);
        }
        return linkedList;
    }
}
