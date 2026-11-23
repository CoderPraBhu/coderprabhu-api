# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Spring Boot REST API for https://coderprabhu.com. It records site visits in MongoDB and serves visitor stats to the UI.

Related repositories:
- UI: https://github.com/CoderPraBhu/coderprabhu-ui (default branch `master`)
- K8S: https://github.com/CoderPraBhu/coderprabhu-k8s

Default branch of this repo: `main`.

## Stack

- Spring Boot 4.x (web, data-mongodb, security, actuator), Lombok, `uap-java` for User-Agent parsing
- Gradle via the wrapper (`./gradlew`); `sourceCompatibility = '21'`, runs fine on newer JDKs
- Container image built with Jib (`jib` block in `build.gradle`)

## Commands

```bash
./gradlew build                  # compile + tests
./gradlew build --rerun-tasks    # force tests to run (they can be UP-TO-DATE otherwise)
./gradlew bootRun                # run locally on :8080 with the default profile
./gradlew dependencyUpdates -Drevision=release   # list available dependency/plugin updates
./gradlew --stop                 # stop Gradle daemons when done
```

## Configuration

- `src/main/resources/application.yaml` (default profile): port 8080, MongoDB at `mongodb://localhost:27017/CoderPraBhuApi`.
  A local MongoDB is all that is needed to run and test.
- `application-prod.yaml` (`SPRING_PROFILES_ACTIVE=prod`): MongoDB URI is built from the env vars
  `MONGODB_USER`, `MONGODB_PASSWORD`, `MONGODB_HOST`, `MONGODB_DB_NAME`, `MONGODB_OPTIONS`.
  Never commit values for these, and never point local runs at the production database.
- `SecurityConfiguration` permits all requests; CORS is opened per endpoint with `@CrossOrigin(origins = "*")`.

## Endpoints

| Path      | Returns                                                          |
|-----------|------------------------------------------------------------------|
| `/`       | `Home` (text)                                                    |
| `/hello`  | `Moto: Build. Ship. Repeat.` (text)                               |
| `/visit`  | Records a visit; returns the visitor JSON (`device`, `ip`, `id`, `zonedDateTime`) |
| `/count`  | Total visits (number)                                            |
| `/unique` | Unique visitors (number)                                         |

Code layout: `controllers/` (IndexController, VisitorController), `services/VisitorService`,
`dao/` (VisitorRepository, VisitorTemplate aggregation), `data/` (Visitor, ZonedDateTime converters), `MongoConfig`.

## Testing

- Integration tests use `@SpringBootTest(webEnvironment = RANDOM_PORT)` with `RestTestClient` +
  `@AutoConfigureRestTestClient`. Do not use `TestRestTemplate`; it is deprecated for removal in Spring Boot 4.
- Manual check after changes: `./gradlew bootRun`, then `curl` `/`, `/hello`, `/count`, `/visit`, `/unique`.
- Verify persistence through the API, not the database: read `/count` before and after a `/visit`
  (it should go up by one). The API abstracts MongoDB, so no `mongosh` checks are needed.
- End-to-end with the UI: run this API with `bootRun`, run the UI dev server (`npm start` in coderprabhu-ui, port 3000),
  and drive the page with Playwright; see the UI repo's CLAUDE.md.

## Dependency upgrades

- Use `./gradlew dependencyUpdates -Drevision=release` (ben-manes versions plugin, id `io.github.ben-manes.versions`;
  pre-releases are rejected via `rejectVersionIf`). Do not look versions up on the web.
- Upgrade Spring Boot and the Gradle plugins; let the Spring Boot BOM manage library versions (do not pin them).
- Gradle wrapper: `./gradlew wrapper --gradle-version latest`.
- A major-version jump (e.g. Spring Boot 4 -> 5) is its own PR.

## Conventions

- Work on a branch (`deps/...`, `tests/...`, `docs/...`) and open a PR to `main`.
- No passwords, tokens or other secrets in code, config or Markdown.
