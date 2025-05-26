-- Тестовые данные для таблиц
-- Таблица учителей
INSERT INTO teachers (first_name, last_name, patronymic) VALUES
                                                             ('Алексей', 'Иванов', 'Генадьевич'),
                                                             ('Мария', 'Петрова', 'Александровна'),
                                                             ('Ольга', 'Сидорова', 'Викторовна');
-- Таблица классы школьников
INSERT INTO school_classes (name) VALUES
                                      ('1А'),
                                      ('2Б');

-- Таблица аудиторий
INSERT INTO classrooms (number) VALUES
                                    ('101'),
                                    ('102'),
                                    ('103');

INSERT INTO subject (id, name) VALUES
                                   (1, 'Математика'),
                                   (2, 'Русский язык'),
                                   (3, 'Литература'),
                                   (4, 'Информатика'),
                                   (5, 'География');


-- Таблица студента
INSERT INTO students (surname, patronymic, first_name, class_id) VALUES
                                                                     ('Григорьев', 'Петрович', 'Алексей', 1),
                                                                     ('Васильева', 'Александровна', 'Мария', 1),
                                                                     ('Зайцева', 'Алексеевна', 'Виктория', 1),
                                                                     ('Корнеева', 'Викторовна', 'Ольга', 2);

-- Таблица учителей для каждого класса
INSERT INTO class_teacher (class_id, teacher_id) VALUES
                                                     (1, 1),
                                                     (1, 2),
                                                     (2, 2),
                                                     (2, 3);

-- Таблица предмет каждого учителя
INSERT INTO teacher_subject (teacher_id, subject_id) VALUES
                                                         (1, 1),
                                                         (1, 2),
                                                         (2, 2),
                                                         (3, 3),
                                                         (5, 5),
                                                         (4, 4);

-- Таблица расписание
INSERT INTO timetable (teacher_id, subject_id, class_id, classroom_id, day_of_week, start_time, lesson_number) VALUES
                                                                                                                   (1, 1, 1, 1, 'Понедельник', '08:30', 1),
                                                                                                                   (2, 2, 1, 2, 'Понедельник', '09:30', 2),
                                                                                                                   (2, 2, 2, 1, 'Вторник', '08:30', 1),
                                                                                                                   (3, 3, 2, 3, 'Среда', '10:30', 3);
