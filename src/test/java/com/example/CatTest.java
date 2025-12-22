package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CatTest {

    @Mock
    private Feline feline;

    @Test
    void getSoundReturnsMeow() {
        Cat cat = new Cat(feline);
        assertEquals("Мяу", cat.getSound());
    }

    @Test
    void getFoodCallsEatMeat() throws Exception {
        Cat cat = new Cat(feline);
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");

        Mockito.when(feline.eatMeat()).thenReturn(expectedFood);

        List<String> actualFood = cat.getFood();
        assertEquals(expectedFood, actualFood);
        Mockito.verify(feline, Mockito.times(1)).eatMeat();
    }

    @Test
    void getFoodPropagatesException() throws Exception {
        Cat cat = new Cat(feline);
        Mockito.when(feline.eatMeat()).thenThrow(new Exception("Ошибка"));

        assertThrows(Exception.class, cat::getFood);
    }
}