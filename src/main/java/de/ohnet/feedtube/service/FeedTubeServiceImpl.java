package de.ohnet.feedtube.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import de.ohnet.feedtube.domain.Channel;
import de.ohnet.feedtube.domain_repository.ChannelRepository;
import de.ohnet.feedtube.domain_rss.Feed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(value = "/api")
public class FeedTubeServiceImpl implements FeedTubeService {


    @Autowired
    @Qualifier("webClientYouTube")
    private WebClient webclient;

    @Autowired
    ChannelRepository channelRepository;

    @Override
    public ResponseEntity<String> receiveChannelData() {
        log.info("entered receiveChannelData...");
        String response = webclient.get().uri("feeds/videos.xml?channel_id=UCxVMLv0XwgrIvWBjf4lWRBA").retrieve().bodyToMono(String.class).block();
        log.info("got response from channel: {}", response);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<String> receiveChannelDataAsJson() {
        log.info("entered receiveChannelData...");
        String response = webclient.get().uri("feeds/videos.xml?channel_id=UCxVMLv0XwgrIvWBjf4lWRBA").retrieve().bodyToMono(String.class).block();
        log.info("got response from channel: {}", response);

        String json = new String();
        try {
            // trying to convert to json
            XmlMapper xmlMapper = new XmlMapper();
            Feed feed = xmlMapper.readValue(response, Feed.class);

            ObjectMapper mapper = new ObjectMapper();
            json = mapper.writeValueAsString(feed);
        } catch(Exception ex) {
            log.error("Exception while converting to JSON {}", ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("uups....");
        }

        return ResponseEntity.status(HttpStatus.OK).body(json);
    }

    @Override
    public ResponseEntity<String> addChannel(String channelId, String channelName) {
        if(channelId != null && !channelId.isEmpty() && channelName != null && !channelName.isEmpty()) {
            try {
                channelRepository.save(new Channel(channelId, channelName));
                return ResponseEntity.status(HttpStatus.OK).body(String.format("successfully saved channel %s", channelId));
            } catch(Exception ex) {
                log.error("could not SAVE: {}", ex);
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.format("error in saving %s", channelId));
    }

    @Override
    public ResponseEntity<String> deleteChannel(String channelId) {
        if(channelId != null && !channelId.isEmpty()) {
            try {
                Optional<Channel> channel = channelRepository.findById(channelId);
                if(channel.isPresent()) {
                    channelRepository.delete(channel.get());
                    return ResponseEntity.status(HttpStatus.OK).body(String.format("successfully deleted channel %s", channelId));
                }
            } catch(Exception ex) {
                log.error("could not delete: {}", ex);
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.format("could not delete %s", channelId));
    }


    @Override
    public ResponseEntity<List<Channel>> getChannelList() {
        List<Channel> channelList = null;
        try {
            channelList = channelRepository.findAll();

            return new ResponseEntity<List<Channel>>(channelList, HttpStatus.OK);
        } catch (Exception ex) {
            log.error("could receiving data: {}", ex);
        }
        return new ResponseEntity<List<Channel>>(channelList, HttpStatus.BAD_REQUEST);
    }
}
