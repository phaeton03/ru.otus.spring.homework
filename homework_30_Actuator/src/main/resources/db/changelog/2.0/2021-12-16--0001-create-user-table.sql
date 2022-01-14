--liquibase formatted sql

--changeset nikolskiy:2021-12-16--0010-create-user-table dbms:h2
DROP TABLE IF EXISTS USERS CASCADE;
--changeset nikolskiy:2021-12-16--0011-create-user-table dbms:h2
create table users
(
    id   IDENTITY,
    name varchar(255),
    password varchar(255),
    role varchar(255),
    primary key (id)
);