CREATE TABLE IF NOT EXISTS task (
    id INT PRIMARY KEY,
    description VARCHAR(400) NOT NULL,
    status boolean NOT NULL
);