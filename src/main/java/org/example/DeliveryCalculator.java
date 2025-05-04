
package org.example;

public class DeliveryCalculator {

    // Константы для минимальной суммы и коэффициентов
    private static final int MINIMUM_DELIVERY_COST = 400;

    // Коэффициенты загруженности
    private static final double VERY_HIGH_LOAD_FACTOR = 1.6;
    private static final double HIGH_LOAD_FACTOR = 1.4;
    private static final double INCREASED_LOAD_FACTOR = 1.2;
    private static final double NORMAL_LOAD_FACTOR = 1.0;

    /**
     * Рассчитывает стоимость доставки
     *
     * @param distance расстояние до пункта назначения в километрах
     * @param isLargeSize true если габариты большие, false если маленькие
     * @param isFragile true если груз хрупкий, false если нет
     * @param loadLevel уровень загруженности службы (строка: "очень высокая", "высокая", "повышенная", или любая другая)
     * @return стоимость доставки в рублях
     * @throws IllegalArgumentException если хрупкий груз доставляется на расстояние более 30 км
     */
    public static int calculateDeliveryCost(double distance, boolean isLargeSize, boolean isFragile, String loadLevel) {
        // Проверка возможности доставки хрупкого груза
        if (isFragile && distance > 30) {
            throw new IllegalArgumentException("Хрупкие грузы нельзя возить на расстояние более 30 км");
        }

        // Расчет стоимости по расстоянию
        int distanceCost;
        if (distance > 30) {
            distanceCost = 300;
        } else if (distance > 10) {
            distanceCost = 200;
        } else if (distance > 2) {
            distanceCost = 100;
        } else {
            distanceCost = 50;
        }

        // Расчет стоимости по габаритам
        int sizeCost = isLargeSize ? 200 : 100;

        // Расчет стоимости по хрупкости
        int fragileCost = isFragile ? 300 : 0;

        // Базовая стоимость
        int baseCost = distanceCost + sizeCost + fragileCost;

        // Определение коэффициента загруженности
        double loadFactor;
        switch (loadLevel) {
            case "очень высокая":
                loadFactor = VERY_HIGH_LOAD_FACTOR;
                break;
            case "высокая":
                loadFactor = HIGH_LOAD_FACTOR;
                break;
            case "повышенная":
                loadFactor = INCREASED_LOAD_FACTOR;
                break;
            default:
                loadFactor = NORMAL_LOAD_FACTOR;
        }

        // Применение коэффициента загруженности
        int totalCost = (int) Math.ceil(baseCost * loadFactor);

        // Проверка минимальной стоимости
        return Math.max(totalCost, MINIMUM_DELIVERY_COST);

    }



}
