## SPRING REACTIVE

Example of simple reactive application

### Run application with docker-compose

* Build project
```bash
./gradlew assemble
```

* Start application with docker-compose
```bash
docker-compose up --build
```

### Run application manually
**IMPORTANT** cassandra database must be running to successfully start application.

#### To start cassandra execute:
```bash
docker run --name reactive-cassandra -v "$(pwd)/cassandra:/docker-entrypoint-initdb.d/" -p 9042:9042 -d cassandra:3.11
```

#### To start application execute
```bash
./gradlew bootRun
```

#### Application will start on 8888 port and exposes such endpoints
   * `http://localhost:8888/` - GET requests
   * `http://localhost:8888/users` - GET and POST requests
   * `http://localhost:8888/users/{username}` - GET requests

### Actuator

#### Get actuator endpoints
```bash
curl -X GET http://localhost:8888/management
```

#### Check applications health status

```bash
curl -X GET http://localhost:8888/management/health
```
* Output example
```code
{
  "status": "UP",
  "details": {
    "cassandra": {
      "status": "UP",
      "details": {
        "version": "3.11.3"
      }
    },
    "diskSpace": {
      "status": "UP",
      "details": {
        "total": 55410178988,
        "free": 7593392108,
        "threshold": 10485760
      }
    }
  }
}
```

#### Get application info
```bash
curl -X GET http://localhost:8888/management/info | jq
```
* Output
```code
"build": {
    "artifact": "reactive",
    "name": "reactive",
    "time": 1548515536.45,
    "version": "0.0.1",
    "group": "lt.ae1663830a"
  }
```
#### Get http endpoint metrics
```bash
curl -X GET http://localhost:8888/management/metrics/http.server.requests?tag=uri:/management/health | jq
```

### SPRING CONFIG SERVER CLIENT

**IMPORTANT** - config server must be running

* Refresh properties without restarting server (this part is not dockerized yet)

  * Push updated properties to config-server-resources repository
  * Send post request to configured endpoint - `${hostname:port}/${management.endpoints.web.base-path}/refresh`

```bash
curl -X POST http://localhost:8081/management/refresh
```

### USEFUL LINKS

* [Reactor project](https://github.com/reactor/reactor-core)
* [Docker-compose](https://docs.docker.com/compose/)
* [Git-properties plugin](https://github.com/n0mer/gradle-git-properties)