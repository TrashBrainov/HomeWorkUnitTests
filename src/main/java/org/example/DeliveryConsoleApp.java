package org.example;

import java.util.Scanner;

public class DeliveryConsoleApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Калькулятор стоимости доставки");
        System.out.println("------------------------------");

        System.out.print("Введите расстояние до пункта назначения (км): ");
        double distance = scanner.nextDouble();

        System.out.print("Груз имеет большие габариты? (true/false): ");
        boolean isLargeSize = scanner.nextBoolean();

        System.out.print("Груз хрупкий? (true/false): ");
        boolean isFragile = scanner.nextBoolean();

        scanner.nextLine(); // очистка буфера

        System.out.print("Уровень загруженности (очень высокая/высокая/повышенная/нормальная): ");
        String loadLevel = scanner.nextLine();

        try {
            int cost = DeliveryCalculator.calculateDeliveryCost(
                    distance, isLargeSize, isFragile, loadLevel);
            System.out.println("Стоимость доставки: " + cost + " рублей");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        scanner.close();
    }


}