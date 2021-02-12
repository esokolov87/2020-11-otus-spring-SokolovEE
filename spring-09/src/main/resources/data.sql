insert into genre (id, name)
values (1, 'Фантастика'),
       (2, 'Научная литература'),
       (3, 'Ужасы'),
       (4, 'Классика');

insert into author (id, name)
values (1, 'Пушкин А.С.'),
       (2, 'Стивен Кинг'),
       (3, 'Джорж Мартин'),
       (4, 'Стивен Хокинг');

insert into comment (id, book_id, comment)
values (1, 1, 'Книга Пушкина А.С. "Руслан и Людмила", жанр Классика'),
       (2, 3, 'Стивен Хокинга "Краткая история времени", жанр Научная литература'),
       (3, 4, 'Книга Стивен Кинга "Оно", жанр Ужасы'),
       (4, 2, 'Джорж Мартина "Игра престолов", жанр Фантастика');

insert into book (id, name, author_id, genre_id)
values (1, 'Руслан и Людмила', 1, 4),
       (2, 'Игра престолов', 3, 1),
       (3, 'Краткая история времени', 4, 2),
       (4, 'Оно', 2, 3);


CREATE SEQUENCE BOOK_SEQ START WITH 5 INCREMENT BY 1;
CREATE SEQUENCE AUTHOR_SEQ START WITH 5 INCREMENT BY 1;
CREATE SEQUENCE GENRE_SEQ START WITH 5 INCREMENT BY 1;
CREATE SEQUENCE COMMENT_SEQ START WITH 5 INCREMENT BY 1;