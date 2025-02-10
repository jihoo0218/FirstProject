package org.example.firstproject.dto;

import lombok.AllArgsConstructor;
import lombok.ToString;
import org.example.firstproject.entity.Article;

@AllArgsConstructor
@ToString
public class ArticleForm {

    private String title;
    private String content;

    public Article toEntity() {
        return new Article(
                null, title, content);
    }
}
