`textBoard`#DB 생성
DROP DATABASE IF EXISTS textBoard;
CREATE DATABASE textBoard;
USE textBoard;

#게시물 테이블 생성
CREATE TABLE article (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    title CHAR(200) NOT NULL,
    `body` TEXT NOT NULL,
    memberId INT(10) UNSIGNED NOT NULL,
    boardId INT(10) UNSIGNED NOT NULL
    );
    
DESC article;
#게시물 데이터 3개 생성
INSERT INTO article
SET regDate = NOW(),
    updateDate = NOW(),
    title = '제목1',
    `body` = '내용1',
    memberId = 1,
    boardId = 1;
INSERT INTO article
SET regDate = NOW(),
    updateDate = NOW(),
    title = '제목2',
    `body` = '내용2',
    memberId = 1,
    boardId = 1;
INSERT INTO article
SET regDate = NOW(),
    updateDate = NOW(),
    title = '제목4',
    `body` = '내용4',
    memberId = 1,
    boardId = 1;


#회원 테이블 생성
CREATE TABLE `member` (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    loginId CHAR(30) NOT NULL,
    loginPw VARCHAR(50) NOT NULL,
    `name` CHAR(10) NOT NULL
);

#회원 데이터 생성
INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'test1',
loginPw = '1234',
`name` = '테스터1';

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'test2',
loginPw = '1234',
`name` = '테스터2';

SELECT * FROM `member`;
