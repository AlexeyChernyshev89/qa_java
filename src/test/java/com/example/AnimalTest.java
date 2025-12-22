package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List; //
import java.util.Arrays; //

class AnimalTest {

    @Test
    void getFamilyReturnsCorrectString() {
        Animal animal = new Animal();
        String expected = "Существует несколько семейств: заячьи, беличьи, мышиные, кошачьи, псовые, медвежьи, куньи";
        assertEquals(expected, animal.getFamily());
    }

    @Test
    void getFoodForHerbivoreReturnsPlants() throws Exception {
        Animal animal = new Animal();
        assertEquals(List.of("Трава", "Различные растения"), animal.getFood("Травоядное"));
    }

    @Test
    void getFoodForPredatorReturnsMeat() throws Exception {
        Animal animal = new Animal();
        assertEquals(List.of("Животные", "Птицы", "Рыба"), animal.getFood("Хищник"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Всеядное", "Насекомоядное", ""})
    void getFoodForUnknownKindThrowsException(String animalKind) {
        Animal animal = new Animal();
        Exception exception = assertThrows(Exception.class, () -> animal.getFood(animalKind));
        assertEquals("Неизвестный вид животного, используйте значение Травоядное или Хищник",
                exception.getMessage());
    }
}