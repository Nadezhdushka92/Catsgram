package ru.yandex.practicum.catsgram.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;

@Data
@EqualsAndHashCode(of = {"id"})
public class Post {
    Long id; //— уникальный идентификатор сообщения,
    Long authorId; //— пользователь, который создал сообщение,
    String description; //— текстовое описание сообщения,
    Instant postDate; //— дата и время создания сообщения.
}
