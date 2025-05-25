-- Sicherstellen, dass die Einträge in `groups` eindeutige Namen haben
INSERT INTO groups (name)
SELECT 'Gruppe A' WHERE NOT EXISTS (SELECT 1 FROM groups WHERE name = 'Gruppe A');
INSERT INTO groups (name)
SELECT 'Gruppe B' WHERE NOT EXISTS (SELECT 1 FROM groups WHERE name = 'Gruppe B');
INSERT INTO groups (name)
SELECT 'Gruppe C' WHERE NOT EXISTS (SELECT 1 FROM groups WHERE name = 'Gruppe C');
INSERT INTO groups (name)
SELECT 'Gruppe D' WHERE NOT EXISTS (SELECT 1 FROM groups WHERE name = 'Gruppe D');
INSERT INTO groups (name)
SELECT 'Gruppe E' WHERE NOT EXISTS (SELECT 1 FROM groups WHERE name = 'Gruppe E');
INSERT INTO groups (name)
SELECT 'Gruppe F' WHERE NOT EXISTS (SELECT 1 FROM groups WHERE name = 'Gruppe F');
INSERT INTO groups (name)
SELECT 'Gruppe G' WHERE NOT EXISTS (SELECT 1 FROM groups WHERE name = 'Gruppe G');
INSERT INTO groups (name)
SELECT 'Gruppe H' WHERE NOT EXISTS (SELECT 1 FROM groups WHERE name = 'Gruppe H');

-- Die Zuordnungslogik nur dann ausführen, wenn noch nicht vorhanden
INSERT INTO group_team (group_id, team_id)
SELECT g.id, t.id
FROM groups g
         JOIN teams t ON (
    (g.name = 'Gruppe A' AND t.name IN ('Al Ahly FC', 'Inter Miami CF', 'SE Palmeiras São Paulo', 'FC Porto')) OR
    (g.name = 'Gruppe B' AND t.name IN ('Atletico Madrid', 'Botafogo Rio de Janeiro', 'FC Paris Saint-Germain', 'Seattle Sounders FC')) OR
    (g.name = 'Gruppe C' AND t.name IN ('Auckland City FC', 'FC Bayern München', 'Benfica Lissabon', 'CA Boca Juniors')) OR
    (g.name = 'Gruppe D' AND t.name IN ('FC Chelsea', 'Esperance Tunis', 'Flamengo Rio de Janeiro', 'Hertha BSC')) OR
    (g.name = 'Gruppe E' AND t.name IN ('Inter Mailand', 'CF Monterrey', 'CA River Plate', 'Urawa Red Diamonds')) OR
    (g.name = 'Gruppe F' AND t.name IN ('Borussia Dortmund', 'Fluminense Rio de Janeiro', 'Mamelodi Sundowns FC', 'Ulsan HD FC')) OR
    (g.name = 'Gruppe G' AND t.name IN ('Al-Ain FC', 'Juventus Turin', 'Manchester City', 'Wydad Casablanca')) OR
    (g.name = 'Gruppe H' AND t.name IN ('Al-Hilal SFC', 'CF Pachuca', 'Real Madrid', 'FC Red Bull Salzburg'))
    )
WHERE NOT EXISTS (
    SELECT 1 FROM group_team gt
    WHERE gt.group_id = g.id AND gt.team_id = t.id
);