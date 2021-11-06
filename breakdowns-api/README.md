# Breakdown API

## Run locally

This spring boot app is built atop of JDK 17. Please make sure you have JDK 17 installed.

You can run the api directly in terminal by using following command

```bash
./gradlew bootRun
```

Alternatively, a Dockerfile is supplied and you can build a docker image with following command

```bash
docker build . -t lichader/breakdown-api

docker run -p 8080:8080 lichader/breakdown-api
```

## Test

Use curl to test the api: 

```bash
curl --location --request GET 'localhost:8080/api/v1/breakdown/year/11YVCHAR001'
```

## Decisions

* The path provided in the document is not versioned so I added a version in the base path. e.g /api/v1/breakdown/
