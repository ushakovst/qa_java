import com.example.Animal;
import com.example.Feline;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

@RunWith(Parameterized.class) //используем парамметризацию
public class FelineTest {

    private Feline feline;

    @Mock
    private Animal animal;

    private AutoCloseable closeable;

    //Параметры для тестирования getKittens
    @Parameterized.Parameter
    public int inputKittensCount; //поле, которое будет принимать первый параметр из набора данных

    @Parameterized.Parameter(1)
    public int expectedKittensCount; //поле, которое будет принимать второй параметр из набора данных

    //Метод для установки параметров
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            {0, 0},
            {1, 1},
            {3, 3},
            {5, 5}
        });
    }

    @Before
    public void setUp() {
        closeable = openMocks(this); //idea ругается на initMocks. Пишет, что устарел
        animal = mock(Animal.class);
        feline = new Feline(animal);
    }

    @After // Закрытие ресурсов
    public void tearDown() throws Exception {
        closeable.close();
    }

    // Тест для метода eatMeat
    @Test
    public void testEatMeat() throws Exception {
        // настраиваем мок
        when(animal.getFood("Хищник")).thenReturn(List.of("Животные", "Птицы", "Рыба"));
        // Проверяем результат
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        assertEquals(expectedFood, feline.eatMeat());
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

    // Параметризованный тест для метода getKittens(int kittensCount)
    @Test
    public void testGetKittensWithParameter() {
        assertEquals(expectedKittensCount, feline.getKittens(inputKittensCount));
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