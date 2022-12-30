## Dev Requirements

Install GraalVM Java 17 with sdkman, then install native-image with gu

```
sdk install java 22.3.r17-grl
sdk list java
sdk default java 22.3.r17-grl 
gu install native-image
```

Alternatively you can also directly open a Github codespace to code online 

https://docs.github.com/en/codespaces/setting-up-your-project-for-codespaces/setting-up-your-project-for-codespaces#step-1:-open-your-project-in-a-codespace

## Ecowatt requirements

In the module core, create an `application-dev.yml` file in `resources/` with the following
content

```
ecowatt-api:
  encoded-credentials: changeMe
```

The encoded credentials are the OAuth clientId:clientSecret encoded as base 64

- Try to subscribe to the Ecowatt API https://data.rte-france.com/catalog/-/api/consumption/Ecowatt/v4.0
- An account creation is required, then a new client application must be created (application type web server)
- This client application can now subscribe to the Ecowatt Api and you can get the encoded base64 credentials (see "Mes
  applications")

## Build and run

### Steps for jvm

Run with the profile dev to use the access token

### Steps for native

Build using `./gradlew :log-ecowatt-signals-task:nativeCompile`

Run using

```
export ECOWATT_API_ENCODED_CREDENTIALS=changeMe
export ECOWATT_API_SANDBOX_MODE=true
./log-ecowatt-signals-task/build/native/nativeCompile/log-ecowatt-signals-task
```

### Steps for native container image run

With docker running, build container image using
`./gradlew :log-ecowatt-signals-task:bootBuildImage`

Run using

```
export ECOWATT_API_ENCODED_CREDENTIALS=changeMe
export ECOWATT_API_SANDBOX_MODE=true
docker run \
-e "ECOWATT_API_ENCODED_CREDENTIALS=${ECOWATT_API_ENCODED_CREDENTIALS}" \
-e "ECOWATT_API_SANDBOX_MODE=${ECOWATT_API_SANDBOX_MODE}" \
docker.io/ecowatt-data/tasks/log-ecowatt-signals-task:latest
```


