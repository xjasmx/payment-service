create table if not exists clients
(
    id serial not null
        constraint clients_pk
            primary key,
    first_name varchar(255) not null,
    last_name varchar(255) not null
);

alter table clients owner to app;