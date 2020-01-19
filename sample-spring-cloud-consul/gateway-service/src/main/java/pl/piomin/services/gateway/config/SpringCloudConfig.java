package pl.piomin.services.gateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.piomin.services.gateway.filter.factory.AuthorizationGatewayFilterFactory;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Configuration
public class SpringCloudConfig {
    @Bean
    KeyResolver hostnameKeyResolver() {
        return exchange -> Mono.just(
                Objects.requireNonNull(exchange.getRequest().getRemoteAddress()).getHostName());
    }

    @Bean
    AuthorizationGatewayFilterFactory authorizationGlobalFilterFactory() {
        return new AuthorizationGatewayFilterFactory();
    }
}
