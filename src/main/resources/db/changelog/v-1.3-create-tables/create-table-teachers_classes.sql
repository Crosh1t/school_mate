CREATE TABLE teachers_classes (
      teachers_id BIGINT NOT NULL,
      classes_id BIGINT NOT NULL,
      PRIMARY KEY (teachers_id, classes_id),
      CONSTRAINT fk_tc_teacher FOREIGN KEY (teachers_id) REFERENCES teachers(id),
      CONSTRAINT fk_tc_class FOREIGN KEY (classes_id) REFERENCES school_classes(id)

);