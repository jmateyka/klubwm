CREATE TABLE IF NOT EXISTS matches (
                                       id SERIAL PRIMARY KEY,
                                       hometeamid BIGINT REFERENCES teams (id) ON DELETE CASCADE,
                                       visitorteamid BIGINT REFERENCES teams (id) ON DELETE CASCADE,
                                       homescore INT NOT NULL CHECK (homescore >= 0),
                                       visitorscore INT NOT NULL CHECK (visitorscore >= 0),
                                       stadiumid BIGINT REFERENCES stadiums (id) ON DELETE CASCADE
);