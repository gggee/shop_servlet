package com.example.sport_shop.controller;

import com.example.sport_shop.model.User;
import com.example.sport_shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        userService.save(user);
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String showUserProfile(Model model, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        model.addAttribute("user", user);
        return "profile";
    }

    @GetMapping("/profile/edit")
    public String showEditProfileForm(Model model, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        model.addAttribute("user", user);
        return "edit_profile";
    }

    @PostMapping("/profile/edit")
    public String editUserProfile(@ModelAttribute User user, Principal principal) {
        User existingUser = userService.findByEmail(principal.getName());
        existingUser.setFullName(user.getFullName());
        existingUser.setCity(user.getCity());
        existingUser.setCountry(user.getCountry());
        existingUser.setPhoneNumber(user.getPhoneNumber());
        existingUser.setEmail(user.getEmail());
        userService.save(existingUser);
        return "redirect:/profile";
    }

    @GetMapping("/admin/users")
    public String listUsers(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user_list";
    }

    @GetMapping("/admin/user/{id}")
    public String getUser(@PathVariable Long id, Model model) {
        Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            return "user_detail";
        } else {
            return "user_not_found";
        }
    }

    @PostMapping("/admin/user/block/{id}")
    public String blockUser(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
            User userToBlock = user.get();
            userToBlock.setBlocked(true);
            userService.save(userToBlock);
        }
        return "redirect:/admin/users";
    }

    @PostMapping("/admin/user/unblock/{id}")
    public String unblockUser(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
            User userToUnblock = user.get();
            userToUnblock.setBlocked(false);
            userService.save(userToUnblock);
        }
        return "redirect:/admin/users";
    }

    @DeleteMapping("/admin/user/{id}")
    @ResponseBody
    public String deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return "User with ID " + id + " has been deleted";
    }
}
