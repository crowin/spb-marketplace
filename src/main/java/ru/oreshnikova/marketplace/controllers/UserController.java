package ru.oreshnikova.marketplace.controllers;

import ru.oreshnikova.marketplace.entity.User;
import ru.oreshnikova.marketplace.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller @AllArgsConstructor
public class UserController {
    private UserService userService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "register.html";
    }

    @PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        var existingUser = userService.findUserByName(user.getName());

        if (existingUser.isPresent()) {
            result.rejectValue("name", null, "Введенное имя уже занято, выберите другое");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register.html";
        }
        userService.saveUser(user);

        return "redirect:/login";
    }
}
