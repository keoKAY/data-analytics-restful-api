create table user_account_tb
(
    id           serial
        primary key,
    user_id      integer
        constraint user_account_tb_users_tb_id_fk
            references users_tb,
    account_id   integer
        constraint user_account_tb_account_tb_id_fk
            references account_tb,
    created_date timestamp,
    is_disable   boolean
);

alter table user_account_tb
    owner to postgres;

