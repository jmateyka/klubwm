CREATE TABLE IF NOT EXISTS stadiums (
                                        id BIGINT PRIMARY KEY,
                                        name VARCHAR(255) NOT NULL,
                                        location VARCHAR(255) NOT NULL,
                                        capacity INT NOT NULL
);