import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.example.DeliveryCalculator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeliveryCalculatorTest {
    private final DeliveryCalculator calculator = new DeliveryCalculator();

    @Test
    @DisplayName("Проверка выбрасывания исключения при доставке хрупкого груза на расстояние более 30 км")
    void shouldThrowException() {
        // Arrange
        double distance = 31;
        boolean isLargeSize = false;
        boolean isFragile = true;
        String loadLevel = "нормальная";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.calculateDeliveryCost(distance, isLargeSize, isFragile, loadLevel)
        );
        assertEquals("Хрупкие грузы нельзя возить на расстояние более 30 км", exception.getMessage());
    }

    @Test
    @Tag("Smoke")
    @DisplayName("Проверка подсчета доставки на максимальное расстояние и нормальной загруженностью")
    void calculateMaxDistanceAndNormalLoad(){
        double distance = 31;
        boolean isLargeSize = false;
        boolean isFragile = false;
        String loadLevel = "нормальная";

        int result = DeliveryCalculator.calculateDeliveryCost(distance, isLargeSize, isFragile, loadLevel);;
        assertEquals(400, result);
    }

    @Test
    @Tag("Smoke")
    @DisplayName("Проверка подсчета доставки на минимальное расстояние и нормальной загруженностью")
    void calculateMinDistanceAndNormalLoad(){
        double distance = 1;
        boolean isLargeSize = false;
        boolean isFragile = false;
        String loadLevel = "нормальная";

        int result = DeliveryCalculator.calculateDeliveryCost(distance, isLargeSize, isFragile, loadLevel);;
        assertEquals(400, result);
    }

    @Test
    @Tag("Smoke")
    @DisplayName("Проверка подсчета доставки на минимальное расстояние и нормальной загруженностью")
    void calculateMinDistanceAndVeryHighLoadAndFragile(){
        double distance = 1;
        boolean isLargeSize = false;
        boolean isFragile = true;
        String loadLevel = "очень высокая";

        int result = DeliveryCalculator.calculateDeliveryCost(distance, isLargeSize, isFragile, loadLevel);;
        assertEquals(720, result);
    }
    @Test
    @Tag("Smoke")
    @DisplayName("Проверка подсчета доставки на среднее расстояние и высокой загруженностью и большие габариты")
    void calculateMiddleDistanceAndHighLoadAndLarge(){
        double distance = 15;
        boolean isLargeSize = true;
        boolean isFragile = false;
        String loadLevel = "высокая";

        int result = DeliveryCalculator.calculateDeliveryCost(distance, isLargeSize, isFragile, loadLevel);;
        assertEquals(560, result);
    }

    @Test
    @DisplayName("Проверка расчета стоимости с повышенной загруженностью")
    void calculateCostWithIncreasedLoad() {

        double distance = 40;
        boolean isLargeSize = true;
        boolean isFragile = false;
        String loadLevel = "повышенная";

        int result = calculator.calculateDeliveryCost(distance, isLargeSize, isFragile, loadLevel);


        assertEquals(600, result);
    }


    @ParameterizedTest
    @CsvSource({
            "1, false, false, нормальная, 400",
            "5, false, false, нормальная, 400",
            "15, false, false, нормальная, 400",
            "40, false, false, нормальная, 400",
            "40, true, false, нормальная, 500",
            "40, true, false, повышенная, 600",
            "40, true, false, высокая, 700",
            "40, true, false, очень высокая, 800",
            "5, true, true, высокая, 840",
            "30, false, true, нормальная, 600",
            "15, true, true, очень высокая, 1120"
    })
    @DisplayName("Параметризованные тесты для различных комбинаций параметров")
    void parameterizedTests(double distance, boolean isLargeSize, boolean isFragile,
                            String loadLevel, int expected) {
        assertEquals(expected, calculator.calculateDeliveryCost(
                distance, isLargeSize, isFragile, loadLevel));
    }

    @Test
    @DisplayName("Проверка граничных значений расстояния")
    void testDistanceBoundaries() {
        // Граница 2 км
        assertEquals(400, calculator.calculateDeliveryCost(2, false, false, "нормальная"));
        assertEquals(400, calculator.calculateDeliveryCost(2.1, false, false, "нормальная"));

        // Граница 10 км
        assertEquals(400, calculator.calculateDeliveryCost(10, false, false, "нормальная"));
        assertEquals(400, calculator.calculateDeliveryCost(10.1, false, false, "нормальная"));

        // Граница 30 км
        assertEquals(400, calculator.calculateDeliveryCost(30, false, false, "нормальная"));
        assertEquals(400, calculator.calculateDeliveryCost(30.1, false, false, "нормальная"));

        // Проверка для хрупких грузов на границе 30 км
        assertEquals(600, calculator.calculateDeliveryCost(30, false, true, "нормальная"));
        assertThrows(IllegalArgumentException.class,
                () -> calculator.calculateDeliveryCost(30.1, false, true, "нормальная"));
    }

}
