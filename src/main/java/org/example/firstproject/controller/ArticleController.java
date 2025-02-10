package org.example.firstproject.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.firstproject.dto.ArticleForm;
import org.example.firstproject.entity.Article;
import org.example.firstproject.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j // 로깅을 위한 골뱅이 (어노테이션)
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;
    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
        //System.out.println("dto : "+form.toString()); -> 로깅 기능으로 대체
        log.info(form.toString());
        //1. Dto를 변환 Entity!
        Article article = form.toEntity();
        //System.out.println("entity로 변환 : "+article.toString());
        log.info(article.toString());
        //2. Repository에게 Entity를 DB안에 저장하게 함!
        Article saved = articleRepository.save(article);
       // System.out.println("db에 저장 : "+saved.toString());
        log.info(saved.toString());
        return "";
    }
    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){
        log.info("id = "+id);

        // 1: id로 데이터를 가져옴!
        Article articleEntity= articleRepository.findById(id).orElse(null);
        //2 : 가져온 데이터를 모델에 등록!
        model.addAttribute("article",articleEntity);
        //3 : 보여줄 페이지를 설정!
        return "articles/show";
    }
}
