CREATE TABLE teachers_subjects (
                                   teachers_id BIGINT NOT NULL,
                                   subjects_id BIGINT NOT NULL,
                                   PRIMARY KEY (teachers_id, subjects_id),
                                   CONSTRAINT fk_ts_teacher FOREIGN KEY (teachers_id) REFERENCES teachers(id),
                                   CONSTRAINT fk_ts_subject FOREIGN KEY (subjects_id) REFERENCES subjects(id)
);