package ru.morou.tacocloud.data;


import org.springframework.data.repository.CrudRepository;
import ru.morou.tacocloud.Ingredient;

/**
 * CrudRepository объявляет о дюжине методов для операций CRUD (создание, чтение, обновление, удаление). Обратите
 * внимание, что он параметризован, причем первый параметр является типом сущности, хранилище должно сохраняться,
 * а второй параметр является типом свойства идентификатора сущности. Для IngredientRepository параметры должны быть
 * Ingredient и String.
 *
 * Классу "хранилищу ингредиентов" необходимо выполнить следующие операции:
 * - Запрос всех ингредиентов в коллекцию ингредиентов.
 * - Запрос одного ингредиента по его идентификатору
 * - Сохранить объект ингредиент
 */

public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}