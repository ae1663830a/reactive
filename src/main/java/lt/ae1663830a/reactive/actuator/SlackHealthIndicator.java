package lt.ae1663830a.reactive.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
public class SlackHealthIndicator implements HealthIndicator {

    private WebClient webClient;

    public SlackHealthIndicator(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Health health() {
        Optional<String> res = Optional.ofNullable(isOk().block());

        boolean ok = res.map(s -> s.contains("\"status\":\"ok\"")).orElse(false);

        return ok ? Health.up().build() : Health.down().build();
    }

    private Mono<String> isOk() {
        return webClient.get()
                .uri("https://status.slack.com/api/current")
                .exchange()
                .flatMap(cr -> cr.statusCode().is2xxSuccessful() ? cr.bodyToMono(String.class) : Mono.empty());
    }
}
