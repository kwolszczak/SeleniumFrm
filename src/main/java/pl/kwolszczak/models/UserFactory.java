package pl.kwolszczak.models;

import com.github.javafaker.Faker;

public class UserFactory {
    private static final Faker faker = new Faker();

    private UserFactory() {
    }

    public static User getRandomUser() {

        var firstName = faker.name().firstName();
        var lastName = faker.name().lastName();
        var email = faker.internet().emailAddress();
        var password = faker.internet().password(5, 8);

        return User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(password)
                .build();
    }

    public static User getAlreadyRegistredUser() {
        return User.builder()
                .firstName(System.getProperty("myStore.firstName"))
                .lastName(System.getProperty("myStore.lastName"))
                .password(System.getProperty("myStore.password"))
                .email(System.getProperty("myStore.email"))
                .build();
    }

}
