create type account_type as enum ('CARD', 'SIMPLE');

alter type account_type owner to app;

create table if not exists accounts
(
    id serial not null
        constraint accounts_pk
            primary key,
    id_client integer not null
        constraint accounts_clients_id_fk
            references clients,
    account_number varchar(255) not null,
    account_type account_type not null,
    balance numeric(15,2) not null
);

alter table accounts owner to app;
