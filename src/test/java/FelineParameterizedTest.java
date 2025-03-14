import com.example.Animal;
import com.example.Feline;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(Parameterized.class)
public class FelineParameterizedTest {

    private Feline feline;

    @Mock
    private Animal animal;

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
        MockitoAnnotations.initMocks(this);
        animal = mock(Animal.class);
        feline = new Feline(animal);
    }

    // Параметризованный тест для метода getKittens(int kittensCount)
    @Test
    public void testGetKittensWithParameter() {
        assertEquals(expectedKittensCount, feline.getKittens(inputKittensCount));
    }
}