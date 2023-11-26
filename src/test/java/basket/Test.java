package basket;

import pl.kwolszczak.models.UserFactory;

public class Test {

    @org.junit.jupiter.api.Test
    void test() {
        var us1 = UserFactory.getAlreadyRegistredUser();
        var us2 = UserFactory.getRandomUser();
        System.out.println(us1);
        System.out.println(us2);
    }
}
