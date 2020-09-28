package de.ohnet.feedtube.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.ohnet.feedtube.domain.Channel;
import lombok.extern.slf4j.Slf4j;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FeedTubeServiceTest {

   public static MockWebServer mockBackEnd;

   @Autowired
   public FeedTubeServiceImpl feedTubeServiceImpl;

   @BeforeAll
   static void setUp() throws IOException {
       mockBackEnd = new MockWebServer();
       mockBackEnd.start();
   }

   @AfterAll
   static void tearDown() throws IOException {
       mockBackEnd.shutdown();
   }

  /*
   @BeforeEach
   void initialize() {
      String baseUrl = String.format("http://localhost:%s",
                              mockBackEnd.getPort());
       feedTubeService = new FeedTubeServiceImpl(baseUrl);
    }*/

    public void receiveChannelDataAsJsonTest() {
        ResponseEntity<String> jsonData = feedTubeServiceImpl.receiveChannelDataAsJson();

        assertThat(jsonData, notNullValue());
    }

    @Test
    public void contextLoads() throws Exception {
        assertThat(feedTubeServiceImpl, notNullValue());
    }

    @Test
    public void receiveChannelData_success_test() {
       ResponseEntity<String> responseEntity =  feedTubeServiceImpl.receiveChannelData();

       assertThat(responseEntity, notNullValue());

    }

    @Test
    public void receiveChannelDataAsJson_success_test() {
        ResponseEntity<String> responseEntity =  feedTubeServiceImpl.receiveChannelDataAsJson();

        assertThat(responseEntity, notNullValue());
        final String response = responseEntity.getBody();
        assertThat(response, isJSONValid(response));
    }

    @Test
    public void addChannel_success_test() {
        ResponseEntity<String> responseEntity =  feedTubeServiceImpl.addChannel("UC6VkhPuCCwR_kG0GExjoozg", "Just me and Opensource");

        assertThat(responseEntity, notNullValue());
        assertThat(HttpStatus.OK.toString(), responseEntity.getStatusCode().is2xxSuccessful());
    }


    private boolean isJSONValid(String jsonInString ) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.readTree(jsonInString);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
