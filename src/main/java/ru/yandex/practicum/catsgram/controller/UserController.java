package ru.yandex.practicum.catsgram.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.exception.ConditionsNotMetException;
import ru.yandex.practicum.catsgram.exception.DuplicateDataException;
import ru.yandex.practicum.catsgram.exception.NotFoundException;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.model.User;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final Map<Long, User> users = new HashMap<>();

    @GetMapping
    public Collection<User> findAllUsers() {
        return users.values();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public User create(@RequestBody User user) {
        // проверяем выполнение необходимых условий
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new ConditionsNotMetException("Имейл должен быть указан");
        }

            if (users.containsKey(user.getEmail())) {
                throw new DuplicateDataException("Этот имейл уже используется");
            }
        // формируем дополнительные данные
        user.setId(getNextIdUser());
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        String[] listData = user.getRegistrationDate().split("-");
//        LocalDate instant = LocalDate.of(Integer.parseInt(listData[0]), Integer.parseInt(listData[1]), Integer.parseInt(listData[2]));
//        String data = instant.format(formatter);
//        System.out.println(data);
        user.setRegistrationDate(Instant.now().toString());
        // сохраняем новую публикацию в памяти приложения
        users.put(user.getId(), user);
        return user;
    }

    @GetMapping("/{authorId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Optional<User>findUserById(@PathVariable long authorId) {
        return users.values()
                .stream()
                .filter(x -> x.getId() == authorId)
                .findFirst();
    }
    @PutMapping
    public User update(@RequestBody User newUser) {
        if (newUser.getId() == null) {
            throw new ConditionsNotMetException("Id должен быть указан");
        }

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        String[] listData = newUser.getRegistrationDate().split("-");
//        LocalDate localDate = LocalDate.of(Integer.parseInt(listData[0]), Integer.parseInt(listData[1]), Integer.parseInt(listData[2]));
//        String data = localDate.format(formatter);
        newUser.setRegistrationDate(Instant.now().toString());
//        if (users.containsKey(newUser.getEmail())) {
        if (users.containsKey(newUser.getId())) {
            User oldUser = users.get(newUser.getId());
            if (newUser.getEmail() == null || newUser.getEmail().isBlank()) {
                throw new ConditionsNotMetException("Адрес электронной почты не может быть пустым.");
//            } else if (newUser.getUsername() == null || newUser.getUsername().isBlank()) {
//                throw new ConditionsNotMetException("Имя пользователя не может быть пустым.");
//            } else if (newUser.getPassword() == null || newUser.getPassword().isBlank()) {
//                throw new ConditionsNotMetException("Пароль не может быть пустым.");
            }

            oldUser.setEmail(newUser.getEmail());
            oldUser.setUsername(newUser.getUsername());
            oldUser.setPassword(newUser.getPassword());
            oldUser.setRegistrationDate(newUser.getRegistrationDate());

            users.put(oldUser.getId(), oldUser);
            return oldUser;
        }

        throw new NotFoundException("Адрес электронной почты = " + newUser.getEmail() + " не найден");
    }

    // вспомогательный метод для генерации идентификатора нового поста
    private long getNextIdUser() {
        long currentMaxId = users.keySet().size();
        return ++currentMaxId;
    }
}
