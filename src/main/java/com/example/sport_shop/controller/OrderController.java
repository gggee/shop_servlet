package com.example.sport_shop.controller;
import com.example.sport_shop.model.Order;
import com.example.sport_shop.model.User;
import com.example.sport_shop.service.OrderService;
import com.example.sport_shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String listOrders(Model model, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        List<Order> orders = orderService.findByUser(user);
        model.addAttribute("orders", orders);
        return "orders";
    }

    @GetMapping("/{id}")
    public String getOrder(@PathVariable Long id, Model model) {
        Optional<Order> order = orderService.findById(id);
        if (order.isPresent()) {
            model.addAttribute("order", order.get());
            return "order";
        } else {
            return "order_not_found";
        }
    }

    @PostMapping
    public String addOrder(@ModelAttribute Order order) {
        orderService.save(order);
        return "redirect:/orders";
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public String deleteOrder(@PathVariable Long id) {
        orderService.deleteById(id);
        return "Order with ID " + id + " has been deleted";
    }
}
