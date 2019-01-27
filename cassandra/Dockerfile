FROM cassandra:3.11

WORKDIR /docker-entrypoint-initdb.d

COPY cassandra.cql ./cassandra.cql

EXPOSE 9042/tcp