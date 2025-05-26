CREATE TABLE homeworks (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    date TIMESTAMP NOT NULL,
    description_homeworks VARCHAR(1024) NOT NULL,
    subjects_id BIGINT NOT NULL,
    classes_id BIGINT NOT NULL,
    CONSTRAINT fk_homeworks_subject FOREIGN KEY (subjects_id) REFERENCES subjects(id),
    CONSTRAINT fk_homeworks_class FOREIGN KEY (classes_id) REFERENCES school_classes(id)
);