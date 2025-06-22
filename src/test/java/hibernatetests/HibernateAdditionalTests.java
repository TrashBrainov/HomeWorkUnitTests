package hibernatetests;

import database.hibernate.models.Zoo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HibernateAdditionalTests {

    private static SessionFactory sessionFactory;

    @BeforeAll
    static void setUp() {
        sessionFactory = new Configuration()
                .configure("hibernate-test.cfg.xml")
                .addAnnotatedClass(Zoo.class)
                .buildSessionFactory();
    }

    @AfterAll
    static void tearDown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    @Test
    @Order(1)
    @DisplayName("Test add animal using Hibernate")
    void testAddAnimal() {
        Session session = null;
        Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            // Создаем новое животное
            Zoo newAnimal = new Zoo();
            newAnimal.setName("Бегемот");

            // Сохраняем животное
            session.save(newAnimal);
            tx.commit();

            // Проверяем, что ID присвоен
            assertNotNull(newAnimal.getId(), "ID должен быть присвоен после сохранения");

            // Проверяем, что животное действительно сохранено
            session = sessionFactory.openSession();
            Zoo savedAnimal = session.get(Zoo.class, newAnimal.getId());

            assertNotNull(savedAnimal, "Животное должно быть найдено по ID");
            assertEquals("Бегемот", savedAnimal.getName(), "Имя должно совпадать");

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            fail("Ошибка при сохранении животного: " + e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Test
    @Order(2)
    @DisplayName("Test update animal using Hibernate")
    void testUpdateAnimal() {
        Session session = null;
        Transaction tx = null;
        Integer animalId = null;

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            // Создаем новое животное для последующего обновления
            Zoo animal = new Zoo();
            animal.setName("Леопард");

            // Сохраняем животное и получаем ID
            session.save(animal);
            animalId = animal.getId();
            tx.commit();
            session.close();

            // Создаем новую сессию для обновления
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            // Загружаем животное для обновления
            Zoo animalToUpdate = session.get(Zoo.class, animalId);

            // Обновляем данные животного
            animalToUpdate.setName("Большой Леопард");


            // Сохраняем изменения
            session.update(animalToUpdate);
            tx.commit();
            session.close();

            // Проверяем, что данные обновились
            session = sessionFactory.openSession();
            Zoo updatedAnimal = session.get(Zoo.class, animalId);
            session.close();

            assertEquals("Большой Леопард", updatedAnimal.getName(), "Имя должно быть обновлено");


        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            fail("Ошибка при обновлении животного: " + e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Test
    @Order(3)
    @DisplayName("Test delete animal using Hibernate")
    void testDeleteAnimal() {
        Session session = null;
        Transaction tx = null;
        Integer animalId = null;

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            // Создаем новое животное для последующего удаления
            Zoo animal = new Zoo();
            animal.setName("Животное для удаления");

            // Сохраняем животное
            session.save(animal);
            animalId = animal.getId();
            tx.commit();
            session.close();

            // Создаем новую сессию для удаления
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            // Загружаем животное для удаления
            Zoo animalToDelete = session.get(Zoo.class, animalId);
            assertNotNull(animalToDelete, "Животное должно существовать перед удалением");

            // Удаляем животное
            session.delete(animalToDelete);
            tx.commit();
            session.close();

            // Проверяем, что животное удалено
            session = sessionFactory.openSession();
            Zoo deletedAnimal = session.get(Zoo.class, animalId);
            session.close();

            assertNull(deletedAnimal, "Животное не должно существовать после удаления");

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            fail("Ошибка при удалении животного: " + e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}