import com.example.Lion;
import com.example.Predator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LionTest {

    @Mock
    private Lion lion;
    private Predator predator;

    @Before
    public void setUp() throws Exception {
        predator = mock(Predator.class);
        lion = new Lion("Самец", predator);
    }

    //Тест конструктора с полом "Самец"
    @Test
    public void testMaleLionConstructor() throws Exception {
        Lion maleLion = new Lion("Самец",predator);
        assertTrue(maleLion.doesHaveMane());
    }

    //Тест конструктора с полом "Самка"
    @Test
    public void testFemaleLionConstructor() throws Exception {
        Lion femaleLion = new Lion("Самка",predator);
        assertFalse(femaleLion.doesHaveMane());
    }

    //Тест конструктора с недопустимым полом
    @Test(expected = Exception.class) //принимаем класс исключения, которое ожидаем увидеть
    public void testInvalidSexConstructor() throws Exception {
        new Lion("Неизвестный пол",predator);
    }

    //Тест для метода getKittens
    @Test
    public void testGetKittensReturnsCorrectInt() {
        //настраиваем мок
        when(predator.getKittens()).thenReturn(3);
        //проверяем результат
        assertEquals(3, lion.getKittens());
    }

    @Test
    public void testGetKittensCallsOnce() {
        //настраиваем мок
        when(predator.getKittens()).thenReturn(3);
        //вызываем метод
        lion.getKittens();
        //вызван ли был метод
        verify(predator, times(1)).getKittens();
    }

    //Тест для метода doesHaveMane
    @Test
    public void testDoesHaveMane() throws Exception {
        Lion maleLion = new Lion("Самец",predator);
        assertTrue(maleLion.doesHaveMane());
        Lion femaleLion = new Lion("Самка",predator);
        assertFalse(femaleLion.doesHaveMane());
    }

    //Тест для метода getFood
    @Test
    public void testGetFoodReturnsCorrectList() throws Exception {
        //настраиваем мок
        when(predator.eatMeat()).thenReturn(List.of("Животные", "Птицы", "Рыба"));
        //проверяем результат
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        assertEquals(expectedFood, lion.getFood());
    }

    @Test
    public void testGetFoodCallsOnce() throws Exception {
        //настраиваем мок
        when(predator.eatMeat()).thenReturn(List.of("Животные", "Птицы", "Рыба"));
        //вызываем метод
        lion.getFood();
        //вызван ли был метод
        verify(predator, times(1)).eatMeat();
    }

    //Тест для исключения в методе getFood
    @Test(expected = Exception.class) //принимаем класс исключения, которое ожидаем увидеть
    public void testGetFoodException() throws Exception {
        //настраиваем мок, чтобы выпало исключение
        when(predator.eatMeat()).thenThrow(new Exception("Ошибка"));
        //вызываем метод, с выпавшим исключением
        lion.getFood();
    }
}