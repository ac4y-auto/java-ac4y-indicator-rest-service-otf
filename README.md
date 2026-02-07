# java-ac4y-indicator-rest-service-otf

Standalone on-the-fly HTTP service for indicator operations with embedded server support.

## Coordinates

- **GroupId**: `ac4y`
- **ArtifactId**: `ac4y-indicator-rest-service-otf`
- **Version**: `1.0.0`

## Description

On-the-fly (standalone) variant of the indicator REST service. Runs as a standalone JAR with embedded HTTP server, supporting GET-based request handling. Includes assembly configuration for distribution packaging with dependencies, config files, and startup scripts.

### Key Classes

- `Ac4yIndicatorHttpService` - Standalone HTTP service entry point

## Dependencies

- `ac4y-indicator-object-service` (service layer)
- `ac4y-http-handler` (HTTP handling)
- `ac4y-base4jsonandxml` (serialization)
- Jersey 1.x, Log4j 2, MySQL/MSSQL connectors

## Build

```bash
mvn clean package
```

## Origin

Extracted from `IJIndicatorModule/Ac4yIndicatorRestServiceOnTheFly`.
