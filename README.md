# LabSeq Demo Test

LabSeq API using Quarkus and Angular.

## Build

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw clean install
```

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw quarkus:dev
```

The following endpoints will be available at:

- Frontend: <http://localhost:4200>

- Swagger UI: <http://localhost:8080/swagger-ui>

### Testing

Run the test suite:

```shell script
./mvnw test
```

### Docker Deployment

The project includes a ready-to-use Docker configuration for JVM mode.

Package the application:

```shell script
./mvnw clean package
```

Build the Docker image:

```shell script
docker build -f src/main/docker/Dockerfile.jvm -t quarkus/labseq-quarkus-jvm .
```

Run the container:

```shell script
docker run -i --rm -p 8080:8080 quarkus/labseq-quarkus-jvm
```

<!-- Copy

Execute

The application will be available at:

Frontend: http://localhost:8080
Swagger UI: http://localhost:8080/swagger-ui
API endpoint: http://localhost:8080/api/v1/labseq/{n}
Features
REST API with OpenAPI documentation
Angular frontend with Material UI
Caching for sequence calculations
Input validation (0 to 20000)
Docker support for JVM mode -->
























<!-- ## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/labseq-quarkus-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/maven-tooling>.

## Related Guides

- Cache ([guide](https://quarkus.io/guides/cache)): Enable application data caching in CDI beans
- RESTEasy Reactive ([guide](https://quarkus.io/guides/resteasy-reactive)): A Jakarta REST implementation utilizing build time processing and Vert.x. This extension is not compatible with the quarkus-resteasy extension, or any of the extensions that depend on it.
- Quinoa ([guide](https://quarkiverse.github.io/quarkiverse-docs/quarkus-quinoa/dev/index.html)): Develop, build, and serve your npm-compatible web applications such as React, Angular, Vue, Lit, Svelte, Astro, SolidJS, and others alongside Quarkus.
- Swagger UI ([guide](https://quarkus.io/guides/openapi-swaggerui)): Swagger UI

## Provided Code

### Quinoa

Quinoa codestart added a tiny Vite app in src/main/webui. The page is configured to be visible on <a href="/quinoa">/quinoa</a>.

[Related guide section...](https://quarkiverse.github.io/quarkiverse-docs/quarkus-quinoa/dev/index.html)


### REST

Easily start your REST Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources) -->
