CREATE TABLE students (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    patronymic VARCHAR(255) NOT NULL,
    classes_id BIGINT NOT NULL,
    users_id BIGINT NOT NULL,
    parents_id BIGINT,
    CONSTRAINT fk_students_class FOREIGN KEY (classes_id) REFERENCES school_classes(id),
    CONSTRAINT fk_students_user FOREIGN KEY (users_id) REFERENCES users(id),
    CONSTRAINT fk_students_parent FOREIGN KEY (parents_id) REFERENCES parents(id)
);