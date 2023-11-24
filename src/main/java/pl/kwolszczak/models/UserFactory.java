package pl.kwolszczak.models;

public class UserFactory {
    private String firstName;
    private String lastName;
    private  String email;
    private String password;

    public static User getRandomUser() {
        return null;
    }

    public static User getAlreadyRegistredUser() {

        User user = User.builder()
                .firstName(System.getProperty("myStore.firstName"))
                .lastName(System.getProperty("myStore.lastName"))
                .password(System.getProperty("myStore.password"))
                .email(System.getProperty("myStore.email"))
                .build();
     //   System.out.println(user);
        return user;
    }

}
