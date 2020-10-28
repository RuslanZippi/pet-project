package main.handler;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class CustomWebFilter implements WebFilter {

    @Override
    public Mono<Void> filter(final ServerWebExchange exchange, final WebFilterChain chain) {

        final ServerHttpRequest req = exchange.getRequest();

        return req.getURI().getPath().equals("/")
                ? chain.filter(exchange.mutate().request(req.mutate()
                .path("/index.html")
                .build())
                .build())
                : chain.filter(exchange);
    }
}