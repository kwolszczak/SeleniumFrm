package pl.kwolszczak.models;

import lombok.Getter;

@Getter
public class User {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private char sex;

    private User() {
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private char sex;


        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder sex(char sex) {
            this.sex = sex;
            return this;
        }

        public User build() {
            if (firstName == null || firstName.isEmpty()) {
                throw new IllegalStateException("Firstname cannot be empty");
            }
            if (lastName == null || lastName.isEmpty()) {
                throw new IllegalStateException("Lastname cannot be empty");
            }
            if (email == null || email.isEmpty()) {
                throw new IllegalStateException("Email cannot be empty");
            }
            if (password == null || password.isEmpty()) {
                throw new IllegalStateException("password cannot be empty");
            }

            User user = new User();
            user.firstName = this.firstName;
            user.lastName = this.lastName;
            user.email = this.email;
            user.password = this.password;
            user.sex = this.sex;
            return user;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                '}';
    }
}
