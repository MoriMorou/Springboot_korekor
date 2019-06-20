package ru.morou.tacocloud;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * Establishing the domain
 * @Ingrediemt - класс включает в себя такие объекты, как проекты Taco, компоненты, из которых состоят эти проекты,
 * клиенты, и заказы тако, сделанные клиентами.
 * Чтобы объявить это как сущность JPA, Ingredient должен быть помечен @Entity. И его свойство id должно быть
 * аннотировано @Id, чтобы обозначить его как свойство, которое будет уникально идентифицировать сущность в базе данных.
 *
 * @NoArgsConstructor от Lombok - конструктор без аргументов так как AccessLevel.PRIVATE (ограничивает доступ)
 * @Data неявно добавляет необходимый конструктор аргументов, но когда используется @NoArgsConstructor, этот конструктор
 * получает статус удален.
 * @RequiredArgsConstructor гарантирует, что у нас все еще будет необходимый конструктор аргументов в дополнение к
 * частному конструктору без аргументов.
 */

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
@Entity
public class Ingredient {

    @Id
    private final String id;
    private final String name;
    private final Type type;

    public static enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }

}

