insert into app_user(name, surname, email, username, password)
values ('Mateusz', 'Sapala', 'sapala.mateusz@gmail.com', 'admin', 'pass'),
       ('Jan', 'Nowak', 'jan.nowak@gmail.com', 'user', 'pass');

insert into user_authority(authority, user_id)
values (0, 1),
       (1, 1),
       (2, 1),
       (0, 2);