---
name: spring-boot-kotlin-hexagonal
description: Scaffold and verify a new Spring Boot Kotlin project from Hyunk3l's hexagonal-architecture-kotlin-template. Use when the user wants this specific template or asks to use its fork or local checkout.
---

# Spring Boot Kotlin Hexagonal Template

Use Copier with [the upstream template](https://github.com/Hyunk3l/hexagonal-architecture-kotlin-template) or a fork or local checkout supplied by the user. Use the latest stable tag selected by Copier by default and pass `--vcs-ref` when the user specifies a version, branch, or commit.

1. Determine the destination and the values for `tld`, `group_name`, and `project_name` from the request. Ask only for missing choices that affect the destination or package name. Read the selected template's `copier.yaml` when using its defaults. If the destination already contains files, inspect it for collisions before rendering.
2. Check that Copier is available. If it is missing, follow the template's installation instructions or report the missing prerequisite. Run `copier copy` with the resolved values so the command can run unattended.

   ```sh
   copier copy \
     --data "tld=$tld" \
     --data "group_name=$group_name" \
     --data "project_name=$project_name" \
     "$template_source" "$destination"
   ```

3. Check the generated Gradle project name, Kotlin package paths, and Gradle wrapper. Run `./gradlew unitTest` from the generated directory when Java is available. Use the integration and component test tasks when full validation is requested and Docker is available.
4. Report the destination, the template version or ref used, and the validation result.
