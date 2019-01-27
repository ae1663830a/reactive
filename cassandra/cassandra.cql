CREATE KEYSPACE IF NOT EXISTS reactive WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1 };

CREATE TABLE IF NOT EXISTS reactive.user (username text PRIMARY KEY, age int, email text, password text);
INSERT INTO reactive.user (username, age, email, password) VALUES ( 'root', 12, 'a@a.lt', 'root');