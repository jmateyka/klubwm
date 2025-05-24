CREATE TABLE IF NOT EXISTS groups (
                                      id BIGSERIAL PRIMARY KEY,
                                      name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS group_team (
                                          id BIGSERIAL PRIMARY KEY,
                                          team_id BIGINT REFERENCES teams(id) ON DELETE CASCADE,
                                          group_id BIGINT REFERENCES groups(id) ON DELETE CASCADE,
                                          matches INT DEFAULT 0,
                                          wins INT DEFAULT 0,
                                          draws INT DEFAULT 0,
                                          losses INT DEFAULT 0,
                                          goal_difference INT DEFAULT 0,
                                          goal_scored INT DEFAULT 0,
                                          points INT DEFAULT 0
);