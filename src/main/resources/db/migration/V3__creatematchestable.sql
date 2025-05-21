CREATE TABLE matches (
                         id SERIAL PRIMARY KEY,
                         home_team_id BIGINT REFERENCES teams(id) ON DELETE CASCADE,
                         visitor_team_id BIGINT REFERENCES teams(id) ON DELETE CASCADE,
                         home_score INT,
                         visitor_score INT,
                         stadium_id BIGINT REFERENCES stadiums(id) ON DELETE CASCADE
);