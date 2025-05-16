CREATE TABLE IF NOT EXISTS stadiums (
                                        id SERIAL PRIMARY KEY,
                                        name VARCHAR(255) NOT NULL,
                                        location VARCHAR(255),
                                        capacity INT
); CREATE TABLE IF NOT EXISTS matches (
                                          id SERIAL PRIMARY KEY,
                                          hometeamid INT REFERENCES teams(id),
                                          visitorteamid INT REFERENCES teams(id),
                                          home_score INT,
                                          visitor_score INT,
                                          stadium_id INT REFERENCES stadiums(id)
   );