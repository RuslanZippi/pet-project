insert into posts value
    (1, true, "NEW", "текст первого поста", "2021-08-10", "Заголовок первого поста", 1, null, 2),
    (2, true, "NEW", "текст второго поста", "2021-08-09", "Заголовок второго поста", 1, null, 3),
    (3, true, "NEW", "текст третьего поста", "2021-08-08", "Заголовок третьего поста", 1, null, 1),
    (4, true, "ACCEPTED", "текст четвертого поста", "2021-08-07", "Заголовок четвертого поста", 1, null, 4),
    (5, true, "NEW", "текст пятого поста", "2021-08-06", "Заголовок пятого поста", 1, null, 5),
    (6, true, "NEW", "текст шестого поста", "2021-08-05", "Заголовок шестого поста", 1, null, 5),
    (7, true, "ACCEPTED", "текст седьмого поста", "2021-08-04", "Заголовок седьмого поста", 1, null, 6),
    (8, true, "ACCEPTED", "текст восьмого поста", "2021-07-03", "Заголовок восьмого поста", 1, null, 1),
    (9, true, "NEW", "текст девятого поста", "2021-06-03", "Заголовок девятого поста", 1, null, 4),
    (10, true, "ACCEPTED", "текст десятого поста", "2021-07-03", "Заголовок десятого поста", 1, null, 1),
    (11, true, "ACCEPTED", "текст одиннадцатого поста", "2021-07-03", "Заголовок одиннадцатого поста", 1, null, 1),
    (12, true, "ACCEPTED", "текст двенадцатого поста", "2021-07-03", "Заголовок двенадцатого поста", 1, null, 1),
    (13, true, "ACCEPTED", "текст тринадцатого поста", "2021-08-13", "Заголовок тринадцатого поста", 1, null, 1),
    (14, true, "ACCEPTED", "текст четырнадцатого поста", "2021-08-14", "Заголовок четырнадцатого поста", 1, null, 1),
    (15, true, "ACCEPTED", "текст пятнадцатого поста", "2021-07-14", "Заголовок пятнадцатого поста", 1, null, 1),
    (16, true, "ACCEPTED", "текст шестандцатого поста", "2021-08-14", "Заголовок шестандцатого поста", 1, null, 1),
    (17, true, "ACCEPTED", "текст семнадцатого поста", "2021-08-14", "Заголовок семнадцатого поста", 1, null, 1),
    (18, true, "ACCEPTED", "текст весемнадцатого поста", "2021-08-10", "Заголовок весемнадцатого поста", 1, null, 1),
    (19, true, "ACCEPTED", "текст девятнадцатого поста", "2021-08-11", "Заголовок девятнадцатого поста", 1, null, 1);



insert into post_comments value
    (1,"текст коментария", "2021-08-10", null, 1, 1),
    (2,"текст коментария", "2021-08-09", null, 1, 1),
    (3,"текст коментария", "2021-08-07", null, 2, 2),
    (4,"текст коментария", "2021-08-06", null, 3, 2),
    (5,"текст коментария", "2021-08-05", null, 3, 3),
    (6,"текст коментария", "2021-08-04", null, 4, 4),
    (7,"текст коментария", "2021-08-03", null, 5, 5),
    (8,"текст коментария", "2021-08-02", null, 6, 6),
    (9,"текст коментария", "2021-08-01", null, 7, 7),
    (10,"текст коментария", "2021-07-03", null, 7, 8),
    (11,"текст коментария", "2021-06-03", null, 7, 8),
    (12,"текст коментария", "2021-05-03", null, 7, 9);



insert into users value
    (1, null, "email первого пользователя", false, "Первый пользователь", "хеш пароль", null, "2021-08-03"),
    (2, null, "email второго пользователя", false, "Второй пользователь", "хеш пароль", null, "2021-08-03"),
    (3, null, "email третьего пользователя", false, "Третий пользователь", "хеш пароль", null, "2021-08-03"),
    (4, null, "email четвертого пользователя", false, "Четвертый пользователь", "хеш пароль", null, "2021-08-03"),
    (5, null, "email пятого пользователя", false, "Пятый пользователь", "хеш пароль", null, "2021-08-03"),
    (6, null, "email шестого пользователя", false, "Шестой пользователь", "хеш пароль", null, "2021-08-03"),
    (7, null, "email седьмого пользователя", false, "Седьмой пользователь", "хеш пароль", null, "2021-08-03"),
    (8, null, "email восьмого пользователя", false, "Восьмой пользователь", "хеш пароль", null, "2021-08-03"),
    (9, null, "email девятого пользователя", false, "Девятый пользователь", "хеш пароль", null, "2021-08-03"),
    (10, null, "email десятого пользователя", false, "Десятый пользователь", "хеш пароль", null, "2021-08-03"),
    (11, null, "email одиннадцатого пользователя", false, "Одиннадцатый пользователь", "хеш пароль", null, "2021-08-03"),
    (12, null, "email двенадцатого пользователя", false, "Двенадцатый пользователь", "хеш пароль", null, "2021-08-03"),
    (13, null, "mail@mail.ru", false, "Одиннадцатый пользователь", "$2a$12$RAH21lhWwrbZ7xVKz1M4L.GvbwMPj4z9mfM4oeYog/SS8Ksn8btry", null, "2021-08-03");

insert into tags value
    (1,"первый тэг"),
    (2,"второй тэг"),
    (3,"третий тэг"),
    (4,"четвертый тэг"),
    (5,"пятый тэг"),
    (6,"шестой тэг"),
    (7,"седьмой тэг"),
    (8,"восьмой тэг"),
    (9,"девятый тэг");

insert into post_votes value
    (1,"2021-08-09", 1,4,1),
    (2,"2021-08-09", 1,8,1),
    (3,"2021-08-09", 1,7,1),
    (4,"2021-08-09", -1,4,4),
    (5,"2021-08-09", -1,4,3),
    (6,"2021-08-09", 1,4,2),
    (7,"2021-08-09", 1,4,5),
    (8,"2021-08-09", 1,7,2),
    (9,"2021-08-09", -1,7,3),
    (10,"2021-08-09", 1,7,2),
    (11,"2021-08-09", -1,7,1),
    (12,"2021-08-09", 1,7,2),
    (13,"2021-08-09", -1,8,3),
    (14,"2021-08-09", -1,8,4),
    (15,"2021-08-09", -1,8,5),
    (16,"2021-08-09", -1,8,6),
    (17,"2021-08-09", -1,8,1),
    (34,"2021-08-09", 1,7,2);

