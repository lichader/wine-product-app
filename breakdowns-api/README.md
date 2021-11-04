# Breakdown API

## Run locally

Simply issue following command to run

```bash
./gradlew bootRun
```

A Dockerfile is supplied and you can also run this in container with this command

```bash
./gradlew build
docker build . -t lichader/breakdown-api
docker run -p 8080:8080 lichader/breakdown-api
```

## Decisions

* The path provided in the document is not versioned so I added a version in the base path. e.g /api/v1/breakdown/