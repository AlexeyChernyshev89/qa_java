package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FelineTest {

    @Test
    void eatMeatReturnsPredatorFood() throws Exception {
        Feline feline = new Feline();
        assertEquals(List.of("Животные", "Птицы", "Рыба"), feline.eatMeat());
    }

    @Test
    void getFamilyReturnsFelidae() {
        Feline feline = new Feline();
        assertEquals("Кошачьи", feline.getFamily());
    }

    @Test
    void getKittensWithoutParametersReturnsOne() {
        Feline feline = new Feline();
        assertEquals(1, feline.getKittens());
    }

    @ParameterizedTest
    @CsvSource({"1,1", "3,3", "5,5", "0,0"})
    void getKittensWithParameterReturnsCorrectCount(int input, int expected) {
        Feline feline = new Feline();
        assertEquals(expected, feline.getKittens(input));
    }
}