CREATE TABLE IF NOT EXISTS matches (
                                       id SERIAL PRIMARY KEY,
                                       home_team_id INT REFERENCES teams(id),
                                       visitor_team_id INT REFERENCES teams(id),
                                       home_score INT,
                                       visitor_score INT,
                                       stadium_id INT REFERENCES stadiums(id)
);