CREATE TABLE IF NOT EXISTS matches (
                                       id SERIAL PRIMARY KEY,
                                       hometeamid BIGINT REFERENCES teams (id) ON DELETE CASCADE,
                                       visitorteamid BIGINT REFERENCES teams (id) ON DELETE CASCADE,
                                       home_score INT,
                                       visitor_score INT,
                                       stadium_id BIGINT REFERENCES stadiums (id) ON DELETE CASCADE
);