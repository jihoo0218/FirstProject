package org.example.firstproject.repository;

import org.example.firstproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(value =
            "SELECT * " +
                    "FROM comment " +
                    "WHERE article_id = :articleId",
            nativeQuery = true)
    List<Comment> findByArticleId(Long articleId);

    List<Comment> findByNickname(String nickname);

    @Query("SELECT c FROM Comment c WHERE c.nickname LIKE %:nickname%")
    List<Comment> findByNicknameContaining(@Param ("nickname") String nickname);

}
