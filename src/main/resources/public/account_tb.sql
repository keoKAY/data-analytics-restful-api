create table account_tb
(
    id             serial
        primary key,
    account_no     text,
    account_name   text,
    profile        text,
    pin            integer,
    passcode       text,
    phonenumber    text,
    transfer_limit integer,
    account_type   integer
        constraint account_tb_accounttype_tb_id_fk
            references accounttype_tb
);

alter table account_tb
    owner to postgres;

