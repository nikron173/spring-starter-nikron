CREATE TABLE company (
    id serial primary key,
    name varchar(64) not null unique
);
CREATE TABLE company_locales (
    company_id int REFERENCES company(id),
    lang varchar(2),
    description varchar(255) not null,
    PRIMARY KEY (company_id, lang)
);
CREATE TABLE users (
    id bigserial primary key,
    username varchar(64),
    birth_date date,
    firstname varchar(64),
    lastname varchar(64),
    role varchar(32),
    company_id int REFERENCES company(id)
);
CREATE TABLE payment (
    id bigserial primary key,
    amount int,
    receiver_id bigint REFERENCES users(id)
);
CREATE TABLE chat (
    id bigint primary key,
    name varchar(64)
);
CREATE TABLE users_chat (
    id bigint primary key,
    user_id bigint not null REFERENCES users(id),
    chat_id bigint not null REFERENCES chat(id),
    UNIQUE (user_id, chat_id)
);