---
name: spring-boot-kotlin-hexagonal
description: Generate a Spring Boot Kotlin hexagonal architecture project that matches the Hyunk3l hexagonal-architecture-kotlin-template (structure, Gradle config, tests, Docker, and Copier variables). Use when asked to scaffold, recreate, or align a project to this exact template.
---

# Spring Boot Kotlin Hexagonal Template (Claude Code)

## Quick start

- Confirm the target directory and the template source path (use the GitHub template repo by default).
- Collect template variables: `tld`, `group_name`, `project_name`.
- Prefer using Copier to render the template.

## Workflow

1. Ask for:
   - Target output directory (absolute or repo-relative).
   - `tld`, `group_name`, `project_name` values.
   - Template source path. Default to the public GitHub template repo unless the user provides another path.
2. If Copier is available, run it with the local template path.
   - If Copier is missing, ask to install it or fall back to manual rendering.
3. Validate the output structure matches the template (see references).
4. If the user asks for tweaks, only change what they request; do not alter template defaults otherwise.

## Copier usage

- Default template path to the GitHub repo:
  ```
  copier https://github.com/Hyunk3l/hexagonal-architecture-kotlin-template.git <target_path>
  ```
- If the user provides a local template path, use:
  `copier <template_path> <target_path>`.
- Answer prompts with collected values.
- Do not copy excluded files (see `references/template-overview.md`).

## Manual rendering fallback

If Copier cannot be used, manually:

- Copy all non-excluded files and directories from the template.
- Replace `{{ tld }}`, `{{ group_name }}`, `{{ project_name }}` in file contents and paths.
- Drop the `.jinja` suffix from rendered files.
- Keep Gradle, Docker, Flyway, and test layout intact.

## References

- Template layout and exclusions: `references/template-overview.md`
- Copier variables and defaults: `references/copier-variables.md`
