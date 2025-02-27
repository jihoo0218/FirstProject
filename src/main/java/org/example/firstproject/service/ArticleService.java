package org.example.firstproject.service;

import lombok.extern.slf4j.Slf4j;
import org.example.firstproject.dto.ArticleForm;
import org.example.firstproject.entity.Article;
import org.example.firstproject.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service // 서비스 선언! ( 서비스 객체를 스프링 부트에 생성)
public class ArticleService {
    @Autowired //DI
    private ArticleRepository articleRepository;

    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();
        if(article.getId() != null){
            return null;
        }
        return  articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto) {
        // 1: DTO -> 엔티티
        Article article = dto.toEntity();
        log.info("id: {}, article: {}", id, article.toString());

        // 2: 타겟 조회
        Article target = articleRepository.findById(id).orElse(null);

        // 3: 잘못된 요청 처리
        if (target == null || id != article.getId()) {
            // 400, 잘못된 요청 응답!
            log.info("잘못된 요청! id: {}, article: {}", id, article.toString());
            return null;
        }

        // 4: 업데이트
        target.patch(article);
        log.info("target : "+target.toString());
        Article updated = articleRepository.save(target);
        log.info("updated 내용 : "+updated.toString());
        return updated;
    }


    public Article delete(Long id) {
        //대상 찾기
        Article target = articleRepository.findById(id).orElse(null);
        //잘못된 요청 처리
        if (target == null) {
            return null;
        }
        // 대상 삭제
        articleRepository.delete(target);
        //데이터 변환
        log.info("삭제 완료"+target.toString());
        return target;
    }
    @Transactional // 해당메소드를 트랜잭션으로 묶는다.
    public List<Article> creatArticles(List<ArticleForm> dtos) {
        //dto 묶음을 entity 묶음으로 전환
        List<Article> articlList = dtos.stream().map(dto -> dto.toEntity()).collect(Collectors.toList());
        //entity  묶음을 DB로 저장
        articlList.stream().forEach(article -> articleRepository.save(article));
        //강제 예외 발생
        articleRepository.findById(-1L).orElseThrow(()-> new IllegalArgumentException("결재 실패!"));
        //결과 값 반환

        return articlList;
    }
}
