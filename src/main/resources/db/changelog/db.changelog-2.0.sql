--liquibase formatted sql

--changeset nikron:1
INSERT INTO company (name)
VALUES ('Google'),
       ('Meta'),
       ('Amazon');

INSERT INTO company_locales (company_id, lang, description)
VALUES ((SELECT id FROM company WHERE name = 'Google'), 'en', 'Google description'),
       ((SELECT id FROM company WHERE name = 'Google'), 'ru', 'Google описание'),
       ((SELECT id FROM company WHERE name = 'Meta'), 'en', 'Meta description'),
       ((SELECT id FROM company WHERE name = 'Meta'), 'ru', 'Meta описание'),
       ((SELECT id FROM company WHERE name = 'Amazon'), 'en', 'Amazon description'),
       ((SELECT id FROM company WHERE name = 'Amazon'), 'ru', 'Amazon описание');

INSERT INTO users (birth_date, firstname, lastname, role, username, company_id)
VALUES ('1990-01-10', 'Ivan', 'Ivanov', 'ADMIN', 'ivan@gmail.com', (SELECT id FROM company WHERE name = 'Google')),
       ('1995-10-19', 'Petr', 'Petrov', 'USER', 'petr@gmail.com', (SELECT id FROM company WHERE name = 'Google')),
       ('2001-12-23', 'Sveta', 'Svetikova', 'USER', 'sveta@gmail.com', (SELECT id FROM company WHERE name = 'Meta')),
       ('1984-03-14', 'Vlad', 'Vladikov', 'USER', 'vlad@gmail.com', (SELECT id FROM company WHERE name = 'Amazon')),
       ('1984-03-14', 'Kate', 'Smith', 'ADMIN', 'kate@gmail.com', (SELECT id FROM company WHERE name = 'Amazon'));

INSERT INTO payment (amount, receiver_id)
VALUES (100, (SELECT id FROM users WHERE username = 'ivan@gmail.com')),
       (300, (SELECT id FROM users WHERE username = 'ivan@gmail.com'));