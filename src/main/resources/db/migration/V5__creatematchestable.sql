CREATE TABLE IF NOT EXISTS matches (
                                       id BIGINT PRIMARY KEY,
                                       hometeamid BIGINT,
                                       visitorteamid BIGINT,
                                       homescore INT CHECK (homescore >= 0),
                                       visitorscore INT CHECK (visitorscore >= 0),
                                       stadiumid BIGINT,
                                       FOREIGN KEY (hometeamid) REFERENCES teams(id) ON DELETE CASCADE,
                                       FOREIGN KEY (visitorteamid) REFERENCES teams(id) ON DELETE CASCADE,
                                       FOREIGN KEY (stadiumid) REFERENCES stadiums(id) ON DELETE CASCADE
);