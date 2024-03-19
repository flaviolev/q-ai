package ch.zuehlke.qai.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Configuration
public class WebClientConfig {

    @Value("${chatgpt.url}")
    private String baseUrl;

    @Value("${chatgpt.api-key}")
    private String apiToken;

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient
                .builder()
                .baseUrl(baseUrl)
                .filter(bearerTokenFilter(apiToken));
    }

    private ExchangeFilterFunction bearerTokenFilter(String token) {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            if (token == null || token.isEmpty()) {
                return Mono.just(clientRequest);
            }
            return Mono.just(ClientRequest.from(clientRequest)
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                    .build());
        });
    }
}
