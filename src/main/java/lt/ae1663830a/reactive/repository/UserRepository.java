package lt.ae1663830a.reactive.repository;

import lt.ae1663830a.reactive.domain.cassandra.User;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ReactiveCassandraRepository<User, String> {
}
