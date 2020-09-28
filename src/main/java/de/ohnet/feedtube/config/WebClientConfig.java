package de.ohnet.feedtube.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean(name = "webClientYouTube")
    public WebClient webClientYoutube() {
        return WebClient.builder().baseUrl("https://www.youtube.com")
                                  .build();
    }
}