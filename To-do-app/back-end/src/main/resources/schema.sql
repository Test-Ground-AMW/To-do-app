CREATE TABLE IF NOT EXISTS task (
                                    id INT AUTO_INCREMENT PRIMARY KEY,
                                    description VARCHAR(200) NOT NULL,
                                    status BOOLEAN NOT NULL
);