package ru.morou.tacocloud.web;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import ru.morou.tacocloud.Order;
import ru.morou.tacocloud.User;
import ru.morou.tacocloud.data.OrderRepository;

/**
 * @RequestMapping указывает, что любые методы обработки запросов в этом контроллере будут обрабатывать запросы,
 * путь которых начинается с / orders. В сочетании с уровнем метода @GetMapping он указывает, что метод orderForm ()
 * будет обрабатывать HTTP-запросы GET для / orders / current.
 */

@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

    private OrderRepository orderRepo;

    public OrderController(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    @GetMapping("/current")
    public String orderForm(@AuthenticationPrincipal User user,
                            @ModelAttribute Order order) {
        if (order.getDeliveryName() == null) {
            order.setDeliveryName(user.getFullname());
        }
        if (order.getDeliveryStreet() == null) {
            order.setDeliveryStreet(user.getStreet());
        }
        if (order.getDeliveryCity() == null) {
            order.setDeliveryCity(user.getCity());
        }
        if (order.getDeliveryState() == null) {
            order.setDeliveryState(user.getState());
        }
        if (order.getDeliveryZip() == null) {
            order.setDeliveryZip(user.getZip());
        }

        return "orderForm";
    }

    /**
     * processOrder() -processOrder () вызывается для обработки отправленного заказа, ему присваивается объект Order,
     * свойства которого привязаны к отправленным полям формы.
     * @param order
     * @param errors
     * @return
     */
    @PostMapping
    public String processOrder(@Valid Order order, Errors errors,
                               SessionStatus sessionStatus,
                               @AuthenticationPrincipal User user) {

        if (errors.hasErrors()) {
            return "orderForm";
        }

        order.setUser(user);

        orderRepo.save(order);
        sessionStatus.setComplete();

        return "redirect:/";
    }
}