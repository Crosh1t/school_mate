CREATE TABLE teachers_subjects (
     teacher_id BIGINT NOT NULL,
     subject_id BIGINT NOT NULL,
     PRIMARY KEY (teacher_id, subject_id),
     CONSTRAINT fk_teacher_subject_teacher FOREIGN KEY (teacher_id) REFERENCES teachers(id),
     CONSTRAINT fk_teacher_subject_subject FOREIGN KEY (subject_id) REFERENCES subject(id)
);