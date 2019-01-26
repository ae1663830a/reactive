## SPRING REACTIVE

### Example of simple reactive application

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
