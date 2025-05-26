CREATE TABLE class_teachers (
   class_id BIGINT NOT NULL,
   teachers_id BIGINT NOT NULL,
   PRIMARY KEY (class_id, teachers_id),
   CONSTRAINT fk_class_teachers_class FOREIGN KEY (class_id) REFERENCES school_classes(id),
   CONSTRAINT fk_class_teachers_teacher FOREIGN KEY (teachers_id) REFERENCES teachers(id)
);