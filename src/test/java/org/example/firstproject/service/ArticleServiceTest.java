package org.example.firstproject.service;

import org.example.firstproject.dto.ArticleForm;
import org.example.firstproject.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest //해당 클래스는 스프링 부트와 연동되어 테스팅 된다
class ArticleServiceTest {
    @Autowired ArticleService articleService;
    @Test
    void index() {

        // 예상 시나리오
        Article a = new Article(1L, "가가가가","1111");
        Article b = new Article(2L, "나나나나","2222");
        Article c = new Article(3L, "다다다다","3333");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a,b,c));
        // 실제 결과
        List<Article> articles = articleService.index();
        // 비교 검증
        assertEquals(expected.toString(), articles.toString());
    }

    @Test
    void show_success_존재하는_id_입력() {
        //예상
    Long id = 1L;
    Article expected = new Article(id, "가가가가","1111");
        //실제
    Article article = articleService.show(id);
        //비교
    assertEquals(expected.toString(),article.toString());
    }
    @Test
    void show_fail__id_null() {
        //예상
        Long id = -1L;
        Article expected = null;
        //실제
        Article article = articleService.show(id);
        //비교
        assertEquals(expected,article);
    }

    @Test
    @Transactional
    void create_성공_title_content() {
        //예상
        String title ="라라라라";
        String content ="4444";
        ArticleForm dto = new ArticleForm(null, title,content);
        Article expected = new Article(4L, title,content);

        //실제
        Article article = articleService.create(dto);
        //비교
        assertEquals(expected.toString(),article.toString());
    }
    @Test
    @Transactional
    void create_실패_id() {
        //예상
        String title ="라라라라";
        String content ="4444";
        ArticleForm dto = new ArticleForm(4L, title,content);
        Article expected = null;

        //실제
        Article article = articleService.create(dto);
        //비교
        assertEquals(expected,article);
    }

    @Test
    @Transactional
    @Rollback (false)
    void update_성공_존재하는_id와_title만있는_DTO() {
        //예상
        Long id = 1L;
        String title ="title";
        ArticleForm dto = new ArticleForm(id, title,null);
        Article expected = new Article(id, title,"1111");

        //실제
        Article article = articleService.update(id, dto);
        //비교
        assertEquals(expected.toString(),article.toString());
    }
    @Test
    @Transactional

    void update_실패____존재하지_않는_id의_dto() {
        //예상
        Long id = -1L;
        String title ="title";
        ArticleForm dto = new ArticleForm(id, title,null);
        Article expected = null;

        //실제
        Article article = articleService.update(id, dto);
        //비교
        assertEquals(expected,article);
    }
    @Test
    @Transactional
    void update_실패__id만_있는_dto() {
        //예상
        Long id = 1L;
        ArticleForm dto = new ArticleForm(id, null,null);
        Article expected = new Article(id,"가가가가","1111");

        //실제
        Article article = articleService.update(id, dto);
        //비교
        assertEquals(expected.toString(),article.toString());
    }

    @Test
    @Transactional
    @Rollback (false)
    void delete_성공_존재하는id입력() {
        //예상
        Long id = 1L;
        Article expected = new Article(id,"가가가가","1111");

        //실제
        Article article = articleService.delete(id);
        //비교
        assertEquals(expected.toString(),article.toString());
    }
    @Test
    @Transactional
    void delete_실패_존재하지않는id입력() {
        //예상
        Long id = -1L;
        Article expected = null;

        //실제
        Article article = articleService.delete(id);
        //비교
        assertEquals(expected,article);
    }
}