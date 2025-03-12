import com.example.Feline;
import com.example.Lion;
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
    private Feline feline;

    @Before
    public void setUp() {
        feline = mock(Feline.class);
        lion = new Lion(feline); //создаем обьект Lion с моком Feline
    }

    //Тест конструктора с полом "Самец"
    @Test
    public void testMaleLionConstructor() throws Exception {
        Lion maleLion = new Lion("Самец");
        assertTrue(maleLion.doesHaveMane());
    }

    //Тест конструктора с полом "Самка"
    @Test
    public void testFemaleLionConstructor() throws Exception {
        Lion femaleLion = new Lion("Самка");
        assertFalse(femaleLion.doesHaveMane());
    }

    //Тест конструктора с недопустимым полом
    @Test(expected = Exception.class) //принимаем класс исключения, которое ожидаем увидеть
    public void testInvalidSexConstructor() throws Exception {
        new Lion("Неизвестный пол");
    }

    //Тест для метода getKittens
    @Test
    public void testGetKittens() {
        //настраиваем мок
        when(feline.getKittens()).thenReturn(3);
        //проверяем результат
        assertEquals(3, lion.getKittens());
        //вызван ли был метод
        verify(feline, times(1)).getKittens();
    }

    //Тест для метода doesHaveMane
    @Test
    public void testDoesHaveMane() throws Exception {
        Lion maleLion = new Lion("Самец");
        assertTrue(maleLion.doesHaveMane());
        Lion femaleLion = new Lion("Самка");
        assertFalse(femaleLion.doesHaveMane());
    }

    //Тест для метода getFood
    @Test
    public void testGetFood() throws Exception {
        //настраиваем мок
        when(feline.getFood("Хищник")).thenReturn(List.of("Животные", "Птицы", "Рыба"));
        //проверяем результат
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        assertEquals(expectedFood, lion.getFood());
        //вызван ли был метод
        verify(feline, times(1)).getFood("Хищник");
    }

    //Тест для исключения в методе getFood
    @Test(expected = Exception.class) //принимаем класс исключения, которое ожидаем увидеть
    public void testGetFoodException() throws Exception {
        //настраиваем мок, чтобы выпало исключение
        when(feline.getFood("Хищник")).thenThrow(new Exception("Ошибка"));
        //вызываем метод, с выпавшим исключением
        lion.getFood();
    }
}