INSERT INTO students (first_name, last_name, email, age, created_at)
VALUES
    ('Nureke', 'Dev', 'nureke@test.com', 22, NOW()),
    ('Alex', 'Smith', 'alex@test.com', 25, NOW());

INSERT INTO users (username, email, password, age)
VALUES
    ('admin', 'admin@test.com', 'password123', 30);