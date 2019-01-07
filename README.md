## SPRING REACTIVE

### Example of simple reactive application

**IMPORTANT** cassandra database must be running to successfully start application.

#### To start cassandra execute:
```bash
docker run --name reactive-cassandra -v $(pwd)/cassandra:/docker-entrypoint-initdb.d/" -p 9042:9042 -d cassandra:3.11
```

#### To start application execute
```bash
./gradlew bootRun
```

#### Application will start on 8888 port and exposes such endpoints
   * `http://localhost:8888/users` - GET and POST requests
   * `http://localhost:8888/users/{username}` - GET requests
