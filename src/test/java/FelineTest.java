import com.example.Animal;
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
public class FelineTest {

    private Feline feline;

    @Mock
    private Animal animal;

    @Before
    public void setUp() {
        animal = mock(Animal.class);
        feline = new Feline(animal);
    }

    // Тест для метода eatMeat
    @Test
    public void testEatMeatReturnsCorrectList() throws Exception {
        // настраиваем мок
        when(animal.getFood("Хищник")).thenReturn(List.of("Животные", "Птицы", "Рыба"));
        // Проверяем результат
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        assertEquals(expectedFood, feline.eatMeat());
    }

    @Test
    public void testEatMeatCallsGetFoodOnce() throws Exception {
        // настраиваем мок
        when(animal.getFood("Хищник")).thenReturn(List.of("Животные", "Птицы", "Рыба"));
        //вызываем метод
        feline.eatMeat();
        // вызван ли был метод
        verify(animal, times(1)).getFood("Хищник");
    }

    // Тест для метода getFamily
    @Test
    public void testGetFamily() {
        assertEquals("Кошачьи", feline.getFamily());
    }

    // Тест для метода getKittens() без параметров
    @Test
    public void testGetKittens() {
        assertEquals(1, feline.getKittens());
    }

    // Тест для исключения в методе eatMeat
    @Test(expected = Exception.class)
    public void testEatMeatException() throws Exception {
        // настраиваем мок
        when(animal.getFood("Хищник")).thenThrow(new Exception("Ошибка"));
        // метод с исключением
        feline.eatMeat();
    }
}