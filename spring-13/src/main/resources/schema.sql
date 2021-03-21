CREATE TABLE AUTHOR
(
    ID   BIGINT PRIMARY KEY,
    NAME VARCHAR(255)
);

CREATE TABLE BOOK
(
    ID        BIGINT PRIMARY KEY,
    NAME      VARCHAR(255),
    AUTHOR_ID BIGINT,
    GENRE_ID  BIGINT
);

CREATE TABLE GENRE
(
    ID   BIGINT PRIMARY KEY,
    NAME VARCHAR(255)
);

CREATE TABLE COMMENT
(
    ID      BIGINT PRIMARY KEY,
    BOOK_ID BIGINT,
    COMMENT VARCHAR(255)
);

CREATE TABLE BOOK_COMMENTS
(
    BOOK_ID    BIGINT,
    COMMENT_ID BIGINT
);

ALTER TABLE BOOK
    ADD CONSTRAINT FK_BOOK_AUTHOR FOREIGN KEY (AUTHOR_ID)
        REFERENCES AUTHOR (ID)
;
ALTER TABLE BOOK
    ADD CONSTRAINT FK_BOOK_GENRE FOREIGN KEY (GENRE_ID)
        REFERENCES GENRE (ID)
;


CREATE TABLE users
(
    username VARCHAR(50)  NOT NULL,
    password VARCHAR(100) NOT NULL,
    enabled  TINYINT      NOT NULL DEFAULT 1,
    PRIMARY KEY (username)
);

CREATE TABLE authorities
(
    username  VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    FOREIGN KEY (username) REFERENCES users (username)
);

CREATE UNIQUE INDEX ix_auth_username
    on authorities (username, authority);