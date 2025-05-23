WITH group_data AS (
    INSERT INTO groups (name) VALUES
                                  ('Gruppe A'), ('Gruppe B'), ('Gruppe C'), ('Gruppe D'),
                                  ('Gruppe E'), ('Gruppe F'), ('Gruppe G'), ('Gruppe H')
        ON CONFLICT (name) DO NOTHING
        RETURNING id, name
)
INSERT INTO group_teams (group_id, team_id)
SELECT g.id, t.id
FROM group_data g
         JOIN teams t ON (
    (g.name = 'Gruppe A' AND t.name IN ('Al Ahly FC', 'Inter Miami', 'SE Palmeiras São Paulo', 'FC Porto')) OR
    (g.name = 'Gruppe B' AND t.name IN ('Atletico Madrid', 'Botafogo Rio de Janeiro', 'FC Paris Saint-Germain', 'Seattle Sounders FC')) OR
    (g.name = 'Gruppe C' AND t.name IN ('Auckland City FC', 'FC Bayern München', 'Benfica Lissabon', 'CA Boca Juniors')) OR
    (g.name = 'Gruppe D' AND t.name IN ('FC Chelsea', 'Esperance Tunis', 'Flamengo Rio de Janeiro', 'Hertha BSC')) OR
    (g.name = 'Gruppe E' AND t.name IN ('Inter Mailand', 'CF Monterrey', 'CA River Plate', 'Urawa Red Diamonds')) OR
    (g.name = 'Gruppe F' AND t.name IN ('Borussia Dortmund', 'Fluminense Rio de Janeiro', 'Mamelodi Sundowns FC', 'Ulsan HD FC')) OR
    (g.name = 'Gruppe G' AND t.name IN ('Al-Ain FC', 'Juventus Turin', 'Manchester City', 'Wydad Casablanca')) OR
    (g.name = 'Gruppe H' AND t.name IN ('Al-Hilal SFC', 'CF Pachuca', 'Real Madrid', 'FC Red Bull Salzburg'))
    );