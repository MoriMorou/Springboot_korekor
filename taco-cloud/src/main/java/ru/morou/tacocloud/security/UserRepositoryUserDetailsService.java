package ru.morou.tacocloud.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.morou.tacocloud.User;
import ru.morou.tacocloud.data.UserRepository;

/**
 * UserRepositoryUserDetailsService внедряется с экземпляром UserRepository через его конструктор.
 * Затем в своем методе loadByUsername () он вызывает findByUser- name () в репозитории UserRepository
 * для поиска пользователя.
 */

@Service
public class UserRepositoryUserDetailsService
        implements UserDetailsService {

    private UserRepository userRepo;

    @Autowired
    public UserRepositoryUserDetailsService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user != null) {
            return user;
        }
        throw new UsernameNotFoundException(
                "User '" + username + "' not found");
    }

}
