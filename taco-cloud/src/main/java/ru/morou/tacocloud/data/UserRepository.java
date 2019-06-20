package ru.morou.tacocloud.data;

import org.springframework.data.repository.CrudRepository;
import ru.morou.tacocloud.User;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

}
