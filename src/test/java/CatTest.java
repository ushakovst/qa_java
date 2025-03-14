import com.example.Cat;
import com.example.Feline;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CatTest {

    @Mock
    private Feline feline;
    private Cat cat;

    @Before
    public void setUp() {
        cat = new Cat(feline);
    }

    //Тест для метода getSound
    @Test
    public void testGetSound() {
        assertEquals("Мяу", cat.getSound());
    }

    //Тест метода getFood
    @Test
    public void testGetFoodReturnsCorrectList() throws Exception {
        //настраиваем мок
        when(feline.eatMeat()).thenReturn(List.of("Животные", "Птицы", "Рыба"));
        //проверяем результат
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        assertEquals(expectedFood, cat.getFood());
    }

    @Test
    public void testGetFoodCallsEatOnce() throws Exception {
        //настройка мока
        when(feline.eatMeat()).thenReturn(List.of("Животные", "Птицы", "Рыба"));
        //вызов метода
        cat.getFood();
        //вызван ли был метод
        verify(feline, times(1)).eatMeat();
    }

    //Тест исключения метода getFood#
    @Test(expected = Exception.class)
    public void testGetFoodException() throws Exception {
        //настраиваем мок
        when(feline.eatMeat()).thenThrow(new Exception("Ошибка"));
        //вызываем метод с исключением
        cat.getFood();
    }
}