CREATE TABLE IF NOT EXISTS stadiums (
                                        id SERIAL PRIMARY KEY,
                                        name VARCHAR(255) NOT NULL,
                                        location VARCHAR(255) NOT NULL,
                                        capacity INT NOT NULL
);