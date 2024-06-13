package com.example.sport_shop.controller;

import com.example.sport_shop.model.User;
import com.example.sport_shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminUserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "admin/user_list";
    }

    @GetMapping("/user/{id}")
    public String getUser(@PathVariable Long id, Model model) {
        Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            return "admin/user_detail";
        } else {
            return "admin/user_not_found";
        }
    }

    @PostMapping("/user/block/{id}")
    public String blockUser(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
            User userToBlock = user.get();
            userToBlock.setBlocked(true);
            userService.save(userToBlock);
        }
        return "redirect:/admin/users";
    }

    @PostMapping("/user/unblock/{id}")
    public String unblockUser(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
            User userToUnblock = user.get();
            userToUnblock.setBlocked(false);
            userService.save(userToUnblock);
        }
        return "redirect:/admin/users";
    }

    @DeleteMapping("/user/{id}")
    @ResponseBody
    public String deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return "User with ID " + id + " has been deleted";
    }
}
