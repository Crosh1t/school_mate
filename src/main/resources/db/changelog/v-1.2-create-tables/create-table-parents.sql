CREATE TABLE parents (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    patronymic VARCHAR(255) NOT NULL,
    users_id BIGINT,
    contacts VARCHAR(255) NOT NULL,
    CONSTRAINT fk_parents_user FOREIGN KEY (users_id) REFERENCES users(id)
);