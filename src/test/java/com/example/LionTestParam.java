package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LionTest {

    @Mock
    private Feline feline;

    @ParameterizedTest
    @CsvSource({"Самец,true", "Самка,false"})
    void lionConstructorSetsManeCorrectly(String sex, boolean expectedHasMane) throws Exception {
        Lion lion = new Lion(sex, feline);
        assertEquals(expectedHasMane, lion.doesHaveMane());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Мужской", "Женский", "unknown", ""})
    void lionConstructorThrowsExceptionForInvalidSex(String invalidSex) {
        Exception exception = assertThrows(Exception.class,
                () -> new Lion(invalidSex, feline));
        assertTrue(exception.getMessage().contains("Используйте допустимые значения пола животного"));
    }

    @Test
    void getKittensCallsFelineMethod() {
        Lion lion;
        try {
            lion = new Lion("Самец", feline);
        } catch (Exception e) {
            fail("Не должно быть исключения");
            return;
        }

        Mockito.when(feline.getKittens()).thenReturn(3);

        assertEquals(3, lion.getKittens());
        Mockito.verify(feline, Mockito.times(1)).getKittens();
    }

    @Test
    void getFoodCallsEatMeat() throws Exception {
        Lion lion = new Lion("Самец", feline);
        List<String> expectedFood = List.of("Мясо");

        Mockito.when(feline.eatMeat()).thenReturn(expectedFood);

        List<String> actualFood = lion.getFood();
        assertEquals(expectedFood, actualFood);
        Mockito.verify(feline, Mockito.times(1)).eatMeat();
    }
}