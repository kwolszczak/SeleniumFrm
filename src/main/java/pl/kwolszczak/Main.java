package pl.kwolszczak;

import pl.kwolszczak.models.User;

public class Main {
    public static void main(String[] args) {

        User user = User.builder()
                .firstName("tom")
                .lastName("ford")
                .password("mickeyMouse1!")
                .email("tom.mickey@mouseass")
                .build();
        System.out.println(user);
    }
}