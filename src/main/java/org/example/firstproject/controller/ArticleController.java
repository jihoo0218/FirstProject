package org.example.firstproject.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.firstproject.dto.ArticleForm;
import org.example.firstproject.dto.CommentDto;
import org.example.firstproject.entity.Article;
import org.example.firstproject.repository.ArticleRepository;
import org.example.firstproject.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j // 로깅을 위한 골뱅이 (어노테이션)
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CommentService commentService;
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
        return "redirect:/articles/"+ saved.getId();
    }
    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){
        log.info("id = "+id);

        // 1: id로 데이터를 가져옴!
        Article articleEntity= articleRepository.findById(id).orElse(null);
        List<CommentDto> commentDtos= commentService.comments(id);
        //2 : 가져온 데이터를 모델에 등록!
        model.addAttribute("article",articleEntity);
        model.addAttribute("commentDtos",commentDtos);
        //3 : 보여줄 페이지를 설정!
        return "articles/show";
    }
    @GetMapping("/articles")
    public String index(Model model){
        //1. 모든 article 가져오기
        List<Article> articleEntityList = articleRepository.findAll();
        //2. 가져온 article 묶음을 뷰로 전달!
        model.addAttribute("articleList",articleEntityList);
        //3. 뷰 페이지로 전달
        return "articles/index";
    }
    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        //수정할 데이터를 가져오기!
         Article articleEntity= articleRepository.findById(id).orElse(null);

         //모델에 데이터를 등록!

        model.addAttribute("article",articleEntity);

        //뷰페이지 설정!
        return "articles/edit";
    }
    @PostMapping("/articles/update")
    public String update(ArticleForm form){
        log.info(form.toString());

        //1. DTO를 entity로 변환
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());
        //2. entity를 DB로 저장한다.
            //2-1. DB에 기존 데이터를 가져온다
            Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
            //2-2. 기존 데이터에 값을 갱신한다.
        if (target!=null) {
            articleRepository.save(articleEntity);
        }
        //3. 수정 결과 페이지로 리다이렉트 한다.
        return"redirect:/articles/"+articleEntity.getId();
    }
    @GetMapping("articles/{id}/delete")
    public String deltet(@PathVariable Long id, RedirectAttributes rttr){
        log.info("삭제 요청이 들어왔습니다");

        //1. 삭제 대상을 가져온다.
        Article target = articleRepository.findById(id).orElse(null);
        log.info(target.toString());
        //2. 그 대상을 삭제한다.
        if (target != null){
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg","삭제가 완료되었습니다.");//휘발성 데이터
        }
        //3. 결과 페이지로 Redirect
        return "redirect:/articles";
    }
}
