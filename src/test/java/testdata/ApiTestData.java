package testdata;

import models.User;

public class ApiTestData {
    public static final User DEFAULT_USER = new User(0, "Test_User", "Ivan", "Ivanov", "test@mail.ru", "password", "12345678", 0);
    public static final User UPDATE_DEFAULT_USER = new User(0, "Test_User1", "IvanS", "IvanovS", "testqew@mail.ru", "password", "12345678", 0);
    public static final User DEFAULT_USER_BUILDER = User.builder()
            .id(0)
            .username("Test_User")
            .firstName("Ivan")
            .lastName("Ivanov")
            .email("test@mail.ru")
            .password("password")
            .phone("12345678")
            .userStatus(0)
            .build();

    public static final User TEST = User.builder()
            .id(1)
            .username("test")
            .build();
}
