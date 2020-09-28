package de.ohnet.feedtube.config;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RSSFeedChannelTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void getInfoTest() {
        log.info("start testing");
        WebTestClient.BodySpec<String, ?> welcome_to_elastic = webTestClient.get().uri("https://www.youtube.com/feeds/videos.xml?channel_id=UCxVMLv0XwgrIvWBjf4lWRBA")
                                                                            .exchange()
                                                                            .expectStatus()
                                                                            .isOk()
                                                                            .expectBody(String.class);

        log.info("result: {}", welcome_to_elastic.returnResult().getResponseBody());
       assertThat(welcome_to_elastic.returnResult().getResponseBody(), is(notNullValue()));
    }

}
