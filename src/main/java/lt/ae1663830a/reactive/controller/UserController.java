package lt.ae1663830a.reactive.controller;

import lt.ae1663830a.reactive.domain.cassandra.User;
import lt.ae1663830a.reactive.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.web.reactive.function.server.ServerResponse.*;

@Configuration
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<ServerResponse> users(ServerRequest request) {
        return ok()
                .body(userRepository.findAll().take(10), User.class);
    }

    public Mono<ServerResponse> userByUsername(ServerRequest request) {
        String username = request.pathVariable("username");

        return ok()
                .body(userRepository.findById(username), User.class)
                .switchIfEmpty(notFound().build());
    }

    public Mono<ServerResponse> saveUser(ServerRequest request) {
        return request.bodyToMono(User.class)
                .flatMap(user -> userRepository.save(user))
                .flatMap(saved -> created(URI.create(String.format("/users/%s", saved.getUsername()))).build());
    }

    public Mono<ServerResponse> hello(ServerRequest request) {
        return ok().body(Mono.just("HELLO"), String.class);
    }
}