create type payment_status as enum ('OK', 'ERROR');

alter type payment_status owner to app;

create table if not exists payments
(
    id serial not null
        constraint payments_pk
            primary key,
    id_account_source integer not null
        constraint payments_accounts_id_fk_2
            references accounts,
    id_account_destination integer
        constraint payments_accounts_id_fk
            references accounts,
    payment_amount numeric(15,2) not null,
    payment_reason varchar(255),
    payment_on timestamp not null,
    payment_status payment_status not null
);

alter table payments owner to app;

