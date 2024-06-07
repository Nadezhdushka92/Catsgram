package ru.yandex.practicum.catsgram.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.model.User;
import ru.yandex.practicum.catsgram.service.UserService;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController ( UserService userService ) {
        this.userService = userService;
    }

    @GetMapping
    public Collection<User> findAll () {
        return userService.findAllUsers();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public User create (@RequestBody User user) {
        return userService.create(user);
    }

    @PutMapping
    public User update (@RequestBody User newUser) {
        return userService.update(newUser);
    }

    @GetMapping("/user/{userId}")
    public Optional<User> getUser( @PathVariable("userId") Long userId){
        return userService.findUserById(userId);
    }
}
