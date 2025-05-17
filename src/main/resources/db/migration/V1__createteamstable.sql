CREATE TABLE teams (
                                     id SERIAL PRIMARY KEY,
                                     name VARCHAR(255) NOT NULL,
                                     country VARCHAR(255),
                                     location VARCHAR(255),
                                     league VARCHAR(255),
                                     average_age DOUBLE PRECISION,
                                     market_value VARCHAR(255)
);