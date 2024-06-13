package ru.yandex.practicum.catsgram.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.yandex.practicum.catsgram.exception.ParameterNotValidException;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.service.PostService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/posts")
//@RequiredArgsConstructor
public final class PostController {
    private final PostService postService;
    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping//("/posts")
    public List<Post> findAll(
            @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(value = "size", defaultValue = "10", required = false) Integer size,
            @RequestParam(value = "sort", defaultValue = "desc", required = false) String sort) {

        if(!(sort.equals("asc") || sort.equals("desc"))){
            throw new ParameterNotValidException(sort, "Получено: " + sort + " должно быть: ask или desc");
        }
        if(size <= 0){
            throw new ParameterNotValidException(size.toString(), "Некорректный размер выборки. Размер должен быть больше нуля");
        }
        if(page < 0) {
            throw new ParameterNotValidException(page.toString(), "Начало выборки должно быть положительным числом");
        }

        Integer from = page * size;
        return postService.findAll(size, from, sort).stream().toList();
    }

    @PostMapping//(value = "/posts")
    @ResponseStatus(HttpStatus.OK)
    public Post create (@RequestBody Post post ) {
        return postService.create(post);
    }

    @PutMapping
    public Post update (@RequestBody Post newPost ) {
        return postService.update(newPost);
    }

    @GetMapping("/post/{postId}")
    public Post findPost(@PathVariable("postId") Long postId){
        return postService.findPostById(postId);
    }

}