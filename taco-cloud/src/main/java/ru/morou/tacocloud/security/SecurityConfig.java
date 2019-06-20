package ru.morou.tacocloud.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

/**
 * настройка Spring Security
 */

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * Метод configure () принимает объект HttpSecurity, который можно использовать для определения способа управления
     * безопасностью на веб-уровне.
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            // Вызов authorizeRequests () возвращает объект (ExpressionInterceptUrlRegis- try), для которого вы
            // можете указать пути и шаблоны URL, а также требования безопасности для этих путей.
            .authorizeRequests()
            .antMatchers("/design", "/orders")
            // Запросы на / design и / orders должны быть для пользователей с предоставленными полномочиями ROLE_USER.
            .access("hasRole('ROLE_USER')")
            // Все запросы должны быть разрешены для всех пользователей.
            .antMatchers("/", "/**").access("permitAll")

            // Переход к форме login
            .and()
                .formLogin()
                    .loginPage("/login")

            .and()
                .logout()
                    .logoutSuccessUrl("/")

            // Make H2-Console non-secured; for debug purpose
            .and()
                .csrf()
                    .ignoringAntMatchers("/h2-console/**")

             // Allow pages to be loaded in frames from the same origin; needed for H2-Console
            .and()
                .headers()
                    .frameOptions()
                        .sameOrigin();
    }

    /**
     * Нужно настроить кодировщик паролей, чтобы пароль можно было закодировать в базе данных. Вы сделаете это, сначала
     * объявив bean-компонент типа PasswordEncoder, а затем внедрив его в конфигурацию службы сведений о пользователе,
     * вызвав passwordEncoder ():
     * @return
     */
    @Bean
    public PasswordEncoder encoder() {
        return new StandardPasswordEncoder ("53cr3t");
    }

    /**
     * Похоже, что вы вызываете метод encoder () и передаете его возвращаемое значение в passwordEncoder ().
     * В действительности, однако, поскольку метод encoder () аннотирован @Bean, он будет использоваться для
     * объявления bean-компонента PasswordEncoder в контексте приложения Spring. Любые вызовы encoder () будут
     * перехвачены для возврата экземпляра компонента из контекста приложения.
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(userDetailsService)
            .passwordEncoder(encoder());
    }
}