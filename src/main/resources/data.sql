INSERT INTO article(id, title, content) VALUES (1,'가가가가','1111');
INSERT INTO article(id, title, content) VALUES (2,'나나나나','2222');
INSERT INTO article(id, title, content) VALUES (3,'다다다다','3333');

INSERT INTO article(id, title, content) VALUES (4,'영화','댓글');
INSERT INTO article(id, title, content) VALUES (5,'푸드','마니');
INSERT INTO article(id, title, content) VALUES (6,'취미','부탁');
---4번 게시글 댓글
INSERT INTO comment(id, article_id, nickname, body) VALUES (1,4,'Park','굿 윌 헌팅');
INSERT INTO comment(id, article_id, nickname, body) VALUES (2,4,'Kim','아이 앰 샘');
INSERT INTO comment(id, article_id, nickname, body) VALUES (3,4,'Lee','쇼생크 탈출');
---5번 게시글 댓글
INSERT INTO comment(id, article_id, nickname, body) VALUES (4,5,'Park','치킨');
INSERT INTO comment(id, article_id, nickname, body) VALUES (5,5,'Kim','햄버거');
INSERT INTO comment(id, article_id, nickname, body) VALUES (6,5,'Lee','피자');
---6번 게시글 댓글
INSERT INTO comment(id, article_id, nickname, body) VALUES (7,6,'Park','게임');
INSERT INTO comment(id, article_id, nickname, body) VALUES (8,6,'Kim','영화');
INSERT INTO comment(id, article_id, nickname, body) VALUES (9,6,'Lee','독서');