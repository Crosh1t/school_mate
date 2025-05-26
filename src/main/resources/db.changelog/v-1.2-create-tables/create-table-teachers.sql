CREATE TABLE teachers (
      id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
      name VARCHAR(255) NOT NULL,
      surname VARCHAR(255) NOT NULL,
      patronymic VARCHAR(255),
      users_id BIGINT,
      CONSTRAINT fk_teachers_user FOREIGN KEY (users_id) REFERENCES users(id)
);