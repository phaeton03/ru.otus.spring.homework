--liquibase formatted sql

--changeset nikolskiy:2021-10-04--0010-create-tables dbms:h2
DROP TABLE IF EXISTS AUTHORS CASCADE;
--changeset nikolskiy:2021-10-04--0011-create-tables dbms:h2
create table authors (
    id   IDENTITY,
    name varchar(255),
    primary key (id)
);
--changeset nikolskiy:2021-10-04--0012-create-tables dbms:h2
DROP TABLE IF EXISTS GENRE CASCADE;
--changeset nikolskiy:2021-10-04--0013-create-tables dbms:h2
CREATE TABLE GENRE (
    ID   IDENTITY PRIMARY KEY,
    NAME VARCHAR(255)
);
--changeset nikolskiy:2021-10-04--0014-create-tables dbms:h2
DROP TABLE IF EXISTS BOOKS;
--changeset nikolskiy:2021-10-04--0015-create-tables dbms:h2
CREATE TABLE BOOKS (
    ID        IDENTITY PRIMARY KEY,
    NAME      VARCHAR(255),
    AUTHOR_ID BIGINT,
    GENRE_ID  BIGINT,
    FOREIGN KEY (AUTHOR_ID)
        REFERENCES AUTHORS (ID)
        ON DELETE CASCADE,
    FOREIGN KEY (GENRE_ID)
        REFERENCES GENRE (ID)
        ON DELETE CASCADE
);