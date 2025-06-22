package jdbctests;

import org.junit.jupiter.api.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JdbcAdditionalTest {

    private static JdbcTemplate jdbcTemplate;

    @BeforeAll
    static void setUp() {
        DataSource dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:schema.sql")
                .addScript("classpath:test-data.sql")
                .build();

        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Test
    @Order(1)
    @DisplayName("Test add animal to zoo table")
    void addAnimalTest() {
        // Получаем начальное количество записей
        Integer initialCount = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM zoo", Integer.class);
        assertNotNull(initialCount);

        // Добавляем новое животное
        String animalName = "Слон";
        Integer animalAge = 12;
        String animalType = "Травоядное";

        jdbcTemplate.update(
                "INSERT INTO zoo (name, age, type) VALUES (?, ?, ?)",
                animalName, animalAge, animalType);

        // Проверяем, что общее количество записей увеличилось
        Integer newCount = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM zoo", Integer.class);
        assertEquals(initialCount + 1, newCount, "Количество записей должно увеличиться на 1");

        // Проверяем, что животное добавлено с правильными данными
        List<Map<String, Object>> results = jdbcTemplate.queryForList(
                "SELECT * FROM zoo WHERE name = ? AND age = ? AND type = ?",
                animalName, animalAge, animalType);

        assertEquals(1, results.size(), "Должно быть найдено ровно одно животное");
        Map<String, Object> addedAnimal = results.get(0);

        assertEquals(animalName, addedAnimal.get("name"), "Имя животного должно совпадать");
        assertEquals(animalAge, addedAnimal.get("age"), "Возраст животного должен совпадать");
        assertEquals(animalType, addedAnimal.get("type"), "Тип животного должен совпадать");
    }

    @Test
    @Order(2)
    @DisplayName("Test update animal in zoo table")
    void updateAnimalTest() {
        // Добавляем животное для теста обновления
        String originalName = "Жираф";
        Integer originalAge = 7;
        String originalType = "Травоядное";

        jdbcTemplate.update(
                "INSERT INTO zoo (name, age, type) VALUES (?, ?, ?)",
                originalName, originalAge, originalType);

        // Получаем ID добавленного животного
        List<Map<String, Object>> animals = jdbcTemplate.queryForList(
                "SELECT * FROM zoo WHERE name = ?", originalName);

        assertFalse(animals.isEmpty(), "Должно быть найдено добавленное животное");
        Map<String, Object> addedAnimal = animals.get(0);
        Integer animalId = ((Number) addedAnimal.get("id")).intValue();

        // Обновляем данные животного
        String updatedName = "Большой Жираф";
        Integer updatedAge = 8;
        String updatedType = "Обновленное Травоядное";

        jdbcTemplate.update(
                "UPDATE zoo SET name = ?, age = ?, type = ? WHERE id = ?",
                updatedName, updatedAge, updatedType, animalId);

        // Проверяем, что данные обновились
        Map<String, Object> updatedAnimalMap = jdbcTemplate.queryForMap(
                "SELECT * FROM zoo WHERE id = ?", animalId);

        assertEquals(updatedName, updatedAnimalMap.get("name"), "Имя должно обновиться");
        assertEquals(updatedAge, updatedAnimalMap.get("age"), "Возраст должен обновиться");
        assertEquals(updatedType, updatedAnimalMap.get("type"), "Тип должен обновиться");
    }

    @Test
    @Order(3)
    @DisplayName("Test delete animal from zoo table")
    void deleteAnimalTest() {
        // Добавляем животное для теста удаления
        String animalName = "Для удаления";
        Integer animalAge = 3;
        String animalType = "Тестовое";

        jdbcTemplate.update(
                "INSERT INTO zoo (name, age, type) VALUES (?, ?, ?)",
                animalName, animalAge, animalType);

        // Получаем ID добавленного животного
        List<Map<String, Object>> animals = jdbcTemplate.queryForList(
                "SELECT * FROM zoo WHERE name = ?", animalName);

        assertFalse(animals.isEmpty(), "Должно быть найдено добавленное животное");
        Map<String, Object> addedAnimal = animals.get(0);
        Integer animalId = ((Number) addedAnimal.get("id")).intValue();

        // Запоминаем общее количество животных до удаления
        Integer initialCount = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM zoo", Integer.class);

        // Удаляем животное
        jdbcTemplate.update("DELETE FROM zoo WHERE id = ?", animalId);

        // Проверяем, что общее количество записей уменьшилось
        Integer newCount = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM zoo", Integer.class);
        assertEquals(initialCount - 1, newCount, "Количество записей должно уменьшиться на 1");

        // Проверяем, что животное действительно удалено
        List<Map<String, Object>> results = jdbcTemplate.queryForList(
                "SELECT * FROM zoo WHERE id = ?", animalId);
        assertTrue(results.isEmpty(), "Не должно быть найдено удаленное животное");
    }
}