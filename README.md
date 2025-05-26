# README

# club_wm_tabellenrechner
Tabellenrechner zur FIFA-Klub WM



# Club WM - Web Application

## Überblick
Die Anwendung simuliert eine die FIFA Klub-WM. 
Mannschaften, Stadien und Spiele können verwaltet und ausgewertet werden, um die Ranglisten von Teams innerhalb ihrer Gruppen zu generieren.

Dieses Projekt findet im Rahmen des Moduls B3.1 Webtechnologie statt.

Das Projekt ist in die folgenden Meilensteine aufgeteilt:
M1: Thema überlegt, Paar gefunden, GitHubRepo erstellt, Entity-Klasse überlegt, Spring App
mit GET-Route gepusht → Deadline: 20. April

M2: Vue.js-App zu GitHub gepusht, mind. 1 eigene
Unterkomponente, die mit etwas mit v-for rendert
→ Deadline: 11. Mai

M3: Frontend und Backend auf Render deployed,
Frontend ruft GET-Route auf → Deadline: 25. Mai

M4: Backend hat REST-Schnittstelle und kann
Daten in Datenbank speichern, Frontend ruft
POST-Route auf → Deadline: 15. Juni

Finale Projekt-Präsentation: 30. Juni- 3. Juli
→ Deadline: Sonntag, 29. Juni, 23:59 Uhr

Projektmitglieder Johannes Mateyka (s0584167) und James Kuck (s0584170)

## Technologien
- Java 21
- Spring Boot
- PostgreSQL
- Flyway für Datenbankmigration
- H2 für Tests
- Docker für Containerisierung

## Installation & Ausführung

### Voraussetzungen
- Java 21
- Docker

### Lokale Entwicklung
1. Klone das Repository.
2. Setze die Datenbankverbindungsparameter in der `.env`-Datei.
3. Starte die Anwendung mit Docker:
   ```bash
   docker-compose up
   ```

## API-Dokumentation
Die API bietet Endpunkte, um Teams, Stadien, Matches und Gruppen zu verwalten.

- **GET /api/v1/teams**: Gibt eine Liste aller Teams zurück.
- **PUT /api/v1/matches/batch**: Aktualisiert mehrere Matches gleichzeitig.
- **GET /api/v1/groups**: Gibt eine Liste aller Gruppen mit den Teams zurück.

## Tests
Tests werden mittels JUnit im CI/CD-Prozess auf GitHub Actions ausgeführt.

## CI/CD
GitHub Actions wird verwendet, um Builds und Tests automatisch auszuführen.


