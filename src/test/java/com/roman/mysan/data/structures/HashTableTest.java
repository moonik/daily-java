package com.roman.mysan.data.structures;

import com.roman.mysan.data.structures.hashtable.HashTable;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ThreadLocalRandom;

import static junit.framework.TestCase.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class HashTableTest {

    @Test
    @Parameters({"Cat, Meow, Meow", "Dog, Woof, Woof", "aCt, woem, woem"})
    public void should_insert_with_string_keys_to_table(String key, String value, String expected) {
        //given
        HashTable<String, String> table = new HashTable<>();

        //when
        table.add(key, value);

        //then
        assertEquals(expected, table.get(key));
    }

    @Test
    @Parameters({"Cat, true", "Pigeon, false"})
    public void should_get_successfully_check_if_exists(String key, Boolean expected) {
        //given
        HashTable<String, String> table = create();

        //when
        Boolean actual = table.exists(key);

        //then
        assertEquals(actual, expected);
    }

    @Test
    @Parameters({"1, Cat, Cat", "2, Dog, Dog"})
    public void should_insert_with_integer_keys(Integer key, String value, String expected) {
        //given
        HashTable<Integer, String> table = new HashTable<>();

        //when
        table.add(key, value);

        //then
        assertEquals(expected, table.get(key));
    }

    @Test
    @Parameters({"c, Cat, Cat", "d, Dog, Dog"})
    public void should_insert_with_char_key(Character key, String value, String expected) {
        //given
        HashTable<Character, String> table = new HashTable<>();

        //when
        table.add(key, value);

        //then
        assertEquals(expected, table.get(key));
    }

    @Test
    public void should_resize_array_when_reached_load_factor() {
        //given
        HashTable<Integer, Integer> table = new HashTable<>();
        int expectedCapacity = 26;
        int expectedSize = 15;

        //when
        for (int i = 0; i < 15; i++) {
            table.add(ThreadLocalRandom.current().nextInt(0, 1000), i);
        }

        //then
        assertEquals(expectedCapacity, table.getCapacity());
        assertEquals(expectedSize, table.size());
    }

    @Test
    public void should_update_existing_item() {
        //given
        HashTable<String, String> table = create();
        String key = "Cat";
        String newValue = "Cat says Meow";
        int expectedSize = 2; //becuase form start it equals to 2 cat and dog

        //when
        table.add(key, newValue);

        //then
        assertEquals(expectedSize, table.size());
        assertEquals(newValue, table.get(key));
    }

    @Test
    public void should_successfully_remove_item() {
        //given
        String cat = "Cat";
        boolean notExists = false;
        HashTable<String, String> table = create();

        //when
        table.remove(cat);

        //then
        assertEquals(notExists, table.exists(cat));
    }

    private static HashTable<String, String> create() {
        HashTable<String, String> table = new HashTable<>();
        table.add("Cat", "Meow");
        table.add("Dog", "Woof");
        return table;
    }
}
