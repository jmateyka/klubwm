WITH match_data AS (
    SELECT
        ht.id as hometeamid,
        vt.id as visitorteamid,
        s.id as stadiumid,
        0 as homescore,
        0 as visitorscore
    FROM
        (VALUES
             ('Al Ahly FC', 'Inter Miami CF', 'Hard-Rock-Stadion'),
             ('FC Bayern München', 'Auckland City FC', 'TQL-Stadion'),
             ('FC Paris Saint-Germain', 'Atletico Madrid', 'Rose-Bowl-Stadion'),
             ('SE Palmeiras São Paulo', 'FC Porto', 'MetLife-Stadion'),
             ('Botafogo Rio de Janeiro', 'Seattle Sounders FC', 'Lumen-Field'),
             ('FC Chelsea', 'Los Angeles FC', 'Mercedes-Benz-Stadion'),
             ('CA Boca Juniors', 'Benfica Lissabon', 'Hard-Rock-Stadion'),
             ('Flamengo Rio de Janeiro', 'Esperance Tunis', 'Lincoln-Financial-Field'),
             ('Fluminense Rio de Janeiro', 'Borussia Dortmund', 'MetLife-Stadion'),
             ('CA River Plate', 'Urawa Red Diamonds', 'Lumen-Field'),
             ('Ulsan HD FC', 'Mamelodi Sundowns FC', 'Inter&Co-Stadion'),
             ('CF Monterrey', 'Inter Mailand', 'Rose-Bowl-Stadion'),
             ('Manchester City', 'Wydad Casablanca', 'Lincoln-Financial-Field'),
             ('Real Madrid', 'Al-Hilal SFC', 'Hard-Rock-Stadion'),
             ('CF Pachuca', 'FC Red Bull Salzburg', 'TQL-Stadion'),
             ('Al-Ain FC', 'Juventus Turin', 'Audi-Field'),
             ('SE Palmeiras São Paulo', 'Al Ahly FC', 'MetLife-Stadion'),
             ('Inter Miami CF', 'FC Porto', 'Mercedes-Benz-Stadion'),
             ('Seattle Sounders FC', 'Atletico Madrid', 'Lumen-Field'),
             ('FC Paris Saint-Germain', 'Botafogo Rio de Janeiro', 'Rose-Bowl-Stadion'),
             ('Benfica Lissabon', 'Auckland City FC', 'Inter&Co-Stadion'),
             ('Flamengo Rio de Janeiro', 'FC Chelsea', 'Lincoln-Financial-Field'),
             ('Los Angeles FC', 'Esperance Tunis', 'GEODIS-Park'),
             ('FC Bayern München', 'CA Boca Juniors', 'Hard-Rock-Stadion'),
             ('Mamelodi Sundowns FC', 'Borussia Dortmund', 'TQL-Stadion'),
             ('Inter Mailand', 'Urawa Red Diamonds', 'Lumen-Field'),
             ('Fluminense Rio de Janeiro', 'Ulsan HD FC', 'MetLife-Stadion'),
             ('CA River Plate', 'CF Monterrey', 'Rose-Bowl-Stadion'),
             ('Juventus Turin', 'Wydad Casablanca', 'Lincoln-Financial-Field'),
             ('Real Madrid', 'CF Pachuca', 'Bank-of-America-Stadion'),
             ('FC Red Bull Salzburg', 'Al-Hilal SFC', 'Audi-Field'),
             ('Manchester City', 'Al-Ain FC', 'Mercedes-Benz-Stadion'),
             ('Seattle Sounders FC', 'FC Paris Saint-Germain', 'Lumen-Field'),
             ('Atletico Madrid', 'Botafogo Rio de Janeiro', 'Rose-Bowl-Stadion'),
             ('Inter Miami CF', 'SE Palmeiras São Paulo', 'Hard-Rock-Stadion'),
             ('FC Porto', 'Al Ahly FC', 'MetLife-Stadion'),
             ('Auckland City FC', 'CA Boca Juniors', 'GEODIS-Park'),
             ('Benfica Lissabon', 'FC Bayern München', 'Bank-of-America-Stadion'),
             ('Los Angeles FC', 'Flamengo Rio de Janeiro', 'Camping-World-Stadion'),
             ('Esperance Tunis', 'FC Chelsea', 'Lincoln-Financial-Field'),
             ('Borussia Dortmund', 'Ulsan HD FC', 'TQL-Stadion'),
             ('Mamelodi Sundowns FC', 'Fluminense Rio de Janeiro', 'Hard-Rock-Stadion'),
             ('Inter Mailand', 'CA River Plate', 'Lumen-Field'),
             ('Urawa Red Diamonds', 'CF Monterrey', 'Rose-Bowl-Stadion'),
             ('Juventus Turin', 'Manchester City', 'Camping-World-Stadion'),
             ('Wydad Casablanca', 'Al-Ain FC', 'Audi-Field'),
             ('Al-Hilal SFC', 'CF Pachuca', 'GEODIS-Park'),
             ('FC Red Bull Salzburg', 'Real Madrid', 'Lincoln-Financial-Field')
        ) AS matches(hometeam, visitorteam, stadiumname)
            JOIN teams ht ON ht.name = matches.hometeam
            JOIN teams vt ON vt.name = matches.visitorteam
            JOIN stadiums s ON s.name = matches.stadiumname
)
INSERT INTO matches ( hometeamid, visitorteamid, homescore, visitorscore, stadiumid)
SELECT hometeamid, visitorteamid, homescore, visitorscore, stadiumid
FROM match_data;