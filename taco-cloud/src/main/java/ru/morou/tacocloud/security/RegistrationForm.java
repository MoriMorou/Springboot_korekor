package ru.morou.tacocloud.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.Data;
import ru.morou.tacocloud.User;


@Data
public class RegistrationForm {

    private String username;
    private String password;
    private String fullname;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phone;

    /**
     * метод toUser () использует эти свойства для создания нового объекта User, который будет сохранен
     * processRegistration () с использованием введенного UserRepository.
     * @param passwordEncoder Это тот же компонент PasswordEncoder, который вы объявили ранее. При обработке отправки
     * формы RegistrationController передает ее методу toUser (), который использует его для кодирования пароля перед
     * его сохранением в базе данных.
     * @return
     */
    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(
                username, passwordEncoder.encode(password),
                fullname, street, city, state, zip, phone);
    }
}
