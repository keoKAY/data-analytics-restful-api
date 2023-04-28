create table accounttype_tb
(
    id   serial
        primary key,
    name text
);

alter table accounttype_tb
    owner to postgres;

