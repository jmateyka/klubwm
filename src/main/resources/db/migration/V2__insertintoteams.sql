INSERT INTO teams (name, country, location, league, average_age, market_value)
SELECT * FROM (VALUES
                   ('Manchester City', 'England', 'Manchester', 'Premier League', 26.8, '1.31 Mrd. €'),
                   ('Real Madrid', 'Spanien', 'Madrid', 'La Liga', 27.1, '1.27 Mrd. €'),
                   ('FC Paris Saint-Germain', 'Frankreich', 'Paris', 'Ligue 1', 23.8, '923.50 Mio. €'),
                   ('FC Chelsea', 'England', 'London', 'Premier League', 23.7, '922.00 Mio. €'),
                   ('FC Bayern München', 'Deutschland', 'München', 'Bundesliga', 27.8, '859.00 Mio. €'),
                   ('Inter Mailand', 'Italien', 'Mailand', 'Serie A', 29.5, '663.80 Mio. €'),
                   ('Juventus Turin', 'Italien', 'Turin', 'Serie A', 25.5, '623.20 Mio. €'),
                   ('Atletico Madrid', 'Spanien', 'Madrid', 'La Liga', 29.4, '515.80 Mio. €'),
                   ('Borussia Dortmund', 'Deutschland', 'Dortmund', 'Bundesliga', 25.3, '436.40 Mio. €'),
                   ('Benfica Lissabon', 'Portugal', 'Lissabon', 'Primeira Liga', 25.4, '362.50 Mio. €'),
                   ('FC Porto', 'Portugal', 'Porto', 'Primeira Liga', 25.3, '312.70 Mio. €'),
                   ('SE Palmeiras São Paulo', 'Brasilien', 'São Paulo', 'Brasileirão', 25.9, '238.75 Mio. €'),
                   ('Flamengo Rio de Janeiro', 'Brasilien', 'Rio de Janeiro', 'Brasileirão', 27.1, '219.15 Mio. €'),
                   ('Al-Hilal SFC', 'Saudi-Arabien', 'Riad', 'Saudi Professional League', 28.2, '180.00 Mio. €'),
                   ('FC Red Bull Salzburg', 'Österreich', 'Salzburg', 'Österreichische Bundesliga', 22.9, '149.30 Mio. €'),
                   ('Botafogo Rio de Janeiro', 'Brasilien', 'Rio de Janeiro', 'Brasileirão', 26.0, '135.95 Mio. €'),
                   ('CA River Plate', 'Argentinien', 'Buenos Aires', 'Argentinische Primera División', 29.6, '103.65 Mio. €'),
                   ('CA Boca Juniors', 'Argentinien', 'Buenos Aires', 'Argentinische Primera División', 28.1, '83.63 Mio. €'),
                   ('Fluminense Rio de Janeiro', 'Brasilien', 'Rio de Janeiro', 'Brasileirão', 28.3, '73.60 Mio. €'),
                   ('CF Monterrey', 'Mexiko', 'Monterrey', 'Liga MX', 28.5, '73.20 Mio. €'),
                   ('Inter Miami CF', 'USA', 'Miami', 'Major League Soccer', 26.0, '69.15 Mio. €'),
                   ('Seattle Sounders FC', 'USA', 'Seattle', 'Major League Soccer', 26.9, '54.35 Mio. €'),
                   ('CF Pachuca', 'Mexiko', 'Pachuca', 'Liga MX', 25.8, '51.75 Mio. €'),
                   ('Hertha BSC', 'Deutschland', 'Berlin', '2. Bundesliga', 25.3, '49.15 Mio. €'),
                   ('Al-Ain FC', 'Vereinigte Arabische Emirate', 'Al-Ain', 'UAE Pro League', 25.8, '44.84 Mio. €'),
                   ('Mamelodi Sundowns FC', 'Südafrika', 'Mamelodi', 'DSTV Premiership', 27.7, '35.48 Mio. €'),
                   ('Al Ahly FC', 'Ägypten', 'Kairo', 'Egyptian Premier League', 27.4, '33.90 Mio. €'),
                   ('Urawa Red Diamonds', 'Japan', 'Saitama', 'J1 League', 27.3, '20.53 Mio. €'),
                   ('Esperance Tunis', 'Tunesien', 'Tunis', 'Ligue Professionnelle 1', 25.6, '19.85 Mio. €'),
                   ('Wydad Casablanca', 'Marokko', 'Casablanca', 'Botola Pro', 26.1, '16.45 Mio. €'),
                   ('Ulsan HD FC', 'Südkorea', 'Ulsan', 'K League 1', 27.2, '16.30 Mio. €'),
                   ('Auckland City FC', 'Neuseeland', 'Auckland', 'New Zealand Football Championship', 26.7, '5.32 Mio. €')
              ) AS v(name, country, location, league, average_age, market_value)
WHERE NOT EXISTS (
    SELECT 1 FROM teams t WHERE t.name = v.name
);