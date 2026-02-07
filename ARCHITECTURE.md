# Architektura - java-ac4y-indicator-rest-service-otf

## Attekintes

Onallo (on-the-fly) HTTP szolgaltatas az indikator muveletekhez beagyazott szerver tamogatassal.

## Szerkezet

```
src/main/java/ac4y/indicator/service/http/
  Ac4yIndicatorHttpService.java                 - Onallo HTTP szolgaltatas belepesi pont
src/main/assembly/
  assembly.xml                                  - Disztribucios csomagolas konfiguracio
src/main/resources/
  log4j2.xml                                    - Log4j 2 konfiguracio
src/main/scripts/
  run.bat                                       - Indito szkript
```

## Fuggosegek

- ac4y-indicator-object-service (szolgaltatas reteg)
- ac4y-http-handler (HTTP kezeles)
- ac4y-base4jsonandxml (szerializacio)
- Jersey 1.x, Log4j 2, MySQL/MSSQL csatlakozok

## Eredet

Az `IJIndicatorModule/Ac4yIndicatorRestServiceOnTheFly` modulbol kinyerve.
