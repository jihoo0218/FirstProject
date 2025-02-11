package org.example.firstproject.entity;

import lombok.*;

import javax.persistence.*;

@Entity // DB가 해당 객체를 인식 가능(해당 클래스로 테이블을 만듬)
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Getter
@Setter
public class Article {
    @Id// 대표값
    @GeneratedValue(strategy = GenerationType.IDENTITY)//DB가 id를 자동생성
    private Long id;

    @Column
    private String title;
    @Column
    private String content;


    public void patch(Article article) {
        if(article.title != null)
            this.title = article.title;
        if(article.content != null)
            this.content = article.content;
    }
}

