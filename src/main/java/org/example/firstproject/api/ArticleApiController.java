package org.example.firstproject.api;

import lombok.extern.slf4j.Slf4j;
import org.example.firstproject.dto.ArticleForm;
import org.example.firstproject.entity.Article;
import org.example.firstproject.repository.ArticleRepository;
import org.example.firstproject.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController //RestAPI용 컨트롤러!
public class ArticleApiController {
    @Autowired//DI, 생성 객체를 가져와 연결
    private ArticleService articleSercvice;
    //GET
    @GetMapping("/api/articles")
    public List<Article> index(){
        return articleSercvice.index();
    }
//
    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id){
        return articleSercvice.show(id);
    }
    //POST
    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto){
        Article create = articleSercvice.create(dto);
        return (create != null) ? ResponseEntity.status(HttpStatus.OK).body(create) :ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    //PATCH
    @PatchMapping("/api/articles/{id}") //ResponseEntity 상태코드를 같이 보내줄 수 있음
    public ResponseEntity<Article> update (@PathVariable Long id, @RequestBody ArticleForm dto){
       Article updated =  articleSercvice.update(id, dto);
        return (updated != null) ?ResponseEntity.status(HttpStatus.OK).body(updated) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //DELETE
    @DeleteMapping("/api/articles/{id}")
    public  ResponseEntity<Article> delete(@PathVariable Long id){

     Article deleted = articleSercvice.delete(id);
        return (deleted != null)?ResponseEntity.status(HttpStatus.OK).body(null) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //트랜잭션 (반드시 성공해야하는 일련의 과정 -> 실패시 롤백
    @PostMapping("/api/transaction-test")
    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm> dtos){

        List<Article> creatList = articleSercvice.creatArticles(dtos);
        return (creatList !=null)?ResponseEntity.status(HttpStatus.OK).body(creatList):ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
