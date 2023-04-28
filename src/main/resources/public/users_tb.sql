create table users_tb
(
    id       serial
        primary key,
    username text,
    gender   text,
    address  text
);

alter table users_tb
    owner to postgres;

