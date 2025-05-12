package org.example;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ClassA {
    public static void sort(int[] array){
        Arrays.sort(array);
    }
}

public class TestClassA {

    @Test
    public void testSort() {
        int[] input = {10 ,3 ,9};
        int[] expected = {3, 9 , 10};
        ClassA.sort(input);
        assertArrayEquals(expected, input);
    }
}
