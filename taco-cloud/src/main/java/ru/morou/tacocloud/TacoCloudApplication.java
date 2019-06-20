package ru.morou.tacocloud;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.morou.tacocloud.data.IngredientRepository;
import ru.morou.tacocloud.Ingredient.Type;

/**
 * Аннотация @SpringBootApplication ясно показывает, что это приложение Spring Boot.
 * @SpringBootApplication - составное приложение, которое объединяет три другие аннотации:
 *  @SpringBootConfiguration - обозначает этот класс как класс конфигурации.
 *  @EnableAutoConfiguration - включает автоматическую настройку Spring Boot.
 *  @ComponentScan - включает сканирование компонентов. Это позволяет вам объявить другие
 * классы с аннотациями
 *
 * @author Mori Morou
 */
// FIXME: 2019-05-21 страница 108
@SpringBootApplication
public class TacoCloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(TacoCloudApplication.class, args);
    }

    @Bean
    public CommandLineRunner dataLoader(IngredientRepository repo) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                repo.save(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
                repo.save(new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
                repo.save(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
                repo.save(new Ingredient("CARN", "Carnitas", Type.PROTEIN));
                repo.save(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
                repo.save(new Ingredient("LETC", "Lettuce", Type.VEGGIES));
                repo.save(new Ingredient("CHED", "Cheddar", Type.CHEESE));
                repo.save(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
                repo.save(new Ingredient("SLSA", "Salsa", Type.SAUCE));
                repo.save(new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
            }
        };
    }
}
