package lt.ae1663830a.reactive.config;

import lt.ae1663830a.reactive.controller.UserController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@EnableWebFlux
public class WebConfig {

    @Bean
    public WebClient webClient() {
        return WebClient.builder().baseUrl("http://localhost:8888")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_STREAM_JSON_VALUE)
                .build();
    }

    @Bean
    public RouterFunction<?> routerFunction(UserController userController) {
        return route(GET("/users"), userController::users)
                .andRoute(GET("/users/{username}"), userController::userByUsername)
                .andRoute(POST("/users"), userController::saveUser)
                .andRoute(GET("/"), userController::hello);
    }
}
