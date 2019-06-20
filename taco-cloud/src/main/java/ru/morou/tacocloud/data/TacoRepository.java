package ru.morou.tacocloud.data;

import org.springframework.data.repository.CrudRepository;
import ru.morou.tacocloud.Taco;

public interface TacoRepository extends CrudRepository<Taco, Long> {

}