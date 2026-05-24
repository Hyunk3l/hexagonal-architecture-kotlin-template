# Template Overview

## Source of truth

Use the public GitHub template repository as the source of truth, unless the user explicitly provides a fork or local path.

- `build.gradle.kts`
- `settings.gradle.kts.jinja`
- `docker-compose.yml`
- `gradle/`, `gradlew`, `gradlew.bat`
- `src/main/kotlin/{{ tld }}/{{ group_name }}/{{ project_name }}/.../*.kt.jinja`
- `src/main/resources/application.yaml.jinja`
- `src/main/resources/db/migration/V1___init.sql`
- `src/test/kotlin/{{ tld }}/{{ group_name }}/{{ project_name }}/.../*.kt.jinja`

## Exclusions (from copier.yaml)

Do not copy these into the generated project:

- `.github*`
- `.git*`
- `copier.yaml`
- `README.md`
- `runTests.sh`
- `assets/`
- `requirements.txt`

## Path placeholders

Most Kotlin sources and tests live under:

`src/(main|test)/kotlin/{{ tld }}/{{ group_name }}/{{ project_name }}/...`

Rendered files remove the `.jinja` suffix.
