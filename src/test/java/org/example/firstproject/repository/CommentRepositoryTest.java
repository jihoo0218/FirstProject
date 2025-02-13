package org.example.firstproject.repository;

import org.example.firstproject.entity.Article;
import org.example.firstproject.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest //JPA와 연동한 테스트
class CommentRepositoryTest {
    @Autowired
    CommentRepository commentRepository;

    @Test
    @DisplayName ("특정 게시길의 모든 댓글 조회")
    void findByArticleId() {
        /*Case 1 : 4번 게시글의 모든 댓글 조회*/
        {//입력 데이터 준비
            Long articleId = 4L;
            //실제 수행 과정
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            //예상 과정
            Article article = new Article(4L, "영화", "댓글");
            Comment a = new Comment(1L, article, "Park", "굿 윌 헌팅");
            Comment b = new Comment(2L, article, "Kim", "아이 앰 샘");
            Comment c = new Comment(3L, article, "Lee", "쇼생크 탈출");

            List<Comment> expected = Arrays.asList(a, b, c);
            //검증
            assertEquals(expected.toString(), comments.toString(), "4번 글의 모든 댓글을 출력하는 테스트");
        }
        /*Case 2 : 1번 게시글의 모든 댓글 조회*/
        {
            //입력 데이터 준비
            Long articleId = 1L;
            //실제 수행 과정
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            //예상 과정
            Article article = new Article(1L, "가가가가", "1111");
            List<Comment> expected = Arrays.asList();
            //검증
            assertEquals(expected.toString(), comments.toString(), "1번 글은 댓글이 없음");
        }
        /*Case 3 : 9번 게시글의 모든 댓글 조회*/
        {
            //입력 데이터 준비
            Long id = 9L;
            //실제 수행 과정
            List<Comment> comments = commentRepository.findByArticleId(id);

            //예상 과정
            Article article = new Article(null,null,null);
            List<Comment> expected= Arrays.asList();
            //검증
            assertEquals(expected.toString(),comments.toString(),"9번 글이 존재하지 않습니다.");
        }
        /*Case 4 : 9999번 게시글의 모든 댓글 조회*/
        {
            //입력 데이터 준비
            Long id = 9999L;
            //실제 수행 과정
            List<Comment> comments = commentRepository.findByArticleId(id);

            //예상 과정
            Article article = new Article(null,null,null);
            List<Comment> expected= Arrays.asList();
            //검증
            assertEquals(expected.toString(),comments.toString(),"9999번 글이 존재하지 않습니다.");
        }
        /*Case 5 : -1번 게시글의 모든 댓글 조회*/
        {
            //입력 데이터 준비
            Long id = -1L;
            //실제 수행 과정
            List<Comment> comments = commentRepository.findByArticleId(id);

            //예상 과정
            Article article = new Article(null,null,null);
            List<Comment> expected= Arrays.asList();
            //검증
            assertEquals(expected.toString(),comments.toString(),"-1번 글이 존재하지 않습니다.");
        }
    }

    @Test
    @DisplayName("특정 닉네임의 모든 댓글 조회")
    void findByNickname() {
        /*Case 1 : "Park"의 모든 댓글 조회*/
        {
            //입력데이터를 준비
            String nickname = "Park";
            // 실제 수행
            List<Comment> comments= commentRepository.findByNickname(nickname);
            // 예상 결과
            Comment a = new Comment(1L, new Article(4L, "영화", "댓글"), nickname, "굿 윌 헌팅");
            Comment b = new Comment(4L, new Article(5L, "푸드", "마니"), nickname, "치킨");
            Comment c = new Comment(7L, new Article(6L, "취미", "부탁"), nickname, "게임");
            List<Comment> expected = Arrays.asList(a,b,c);
            // 검증
            assertEquals(expected.toString(),comments.toString(),"Park의 모든 댓글 출력");
        }
        /*Case 2 : "Kim"의 모든 댓글 조회*/
        {
            //입력데이터를 준비
            String nickname = "Kim";
            // 실제 수행
            List<Comment> comments= commentRepository.findByNickname(nickname);
            // 예상 결과
            Comment a = new Comment(2L, new Article(4L, "영화", "댓글"), nickname, "아이 앰 샘");
            Comment b = new Comment(5L, new Article(5L, "푸드", "마니"), nickname, "햄버거");
            Comment c = new Comment(8L, new Article(6L, "취미", "부탁"), nickname, "영화");
            List<Comment> expected = Arrays.asList(a,b,c);
            // 검증
            assertEquals(expected.toString(),comments.toString(),"Kim의 모든 댓글 출력");
        }
        /*Case 3 : "null"의 모든 댓글 조회*/
        {
            //입력데이터를 준비
            String nickname = null;
            // 실제 수행
            List<Comment> comments= commentRepository.findByNickname(nickname);
            // 예상 결과
            List<Comment> expected = Arrays.asList();
            // 검증
            assertEquals(expected.toString(),comments.toString(),"null 모든 댓글 출력");
        }
        /*Case 4 : 공백이 있는 모든 댓글 조회*/
        {
            //입력데이터를 준비
            String nickname = " ";
            // 실제 수행
            List<Comment> comments= commentRepository.findByNickname(nickname);
            // 예상 결과
            List<Comment> expected = Arrays.asList();
            // 검증
            assertEquals(expected.toString(),comments.toString(),"공백이 있는 모든 댓글 출력");
        }
        /*Case 5 : 단어에 i를 포함한 댓글 찾기*/
        {
            //입력데이터를 준비
            String nickname = "i";
            // 실제 수행
            List<Comment> comments= commentRepository.findByNicknameContaining(nickname);
            // 예상 결과
            Comment a = new Comment(2L, new Article(4L, "영화", "댓글"), "Kim", "아이 앰 샘");
            Comment b = new Comment(5L, new Article(5L, "푸드", "마니"), "Kim", "햄버거");
            Comment c = new Comment(8L, new Article(6L, "취미", "부탁"), "Kim", "영화");
            List<Comment> expected = Arrays.asList(a,b,c);
            // 검증
            assertEquals(expected.toString(),comments.toString(),"i를 포함한 모든 댓글 출력");
        }
    }
}