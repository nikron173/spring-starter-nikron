CREATE TABLE company (
    id serial primary key,
    name varchar(64) not null unique
);
CREATE TABLE company_locales (
    company_id int REFERENCES company(id) ON DELETE CASCADE,
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
    company_id int REFERENCES company(id),
    image varchar(128),
    password varchar(128) DEFAULT '{noop}123'
);
CREATE TABLE payment (
    id bigserial primary key,
    amount int,
    receiver_id bigint REFERENCES users(id) ON DELETE CASCADE
);
CREATE TABLE chat (
    id bigint primary key,
    name varchar(64)
);
CREATE TABLE users_chat (
    id bigint primary key,
    user_id bigint not null REFERENCES users(id) ON DELETE CASCADE,
    chat_id bigint not null REFERENCES chat(id) ON DELETE CASCADE,
    UNIQUE (user_id, chat_id)
);