package de.ohnet.feedtube;

import de.ohnet.feedtube.domain.Channel;
import de.ohnet.feedtube.domain_repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Slf4j
@SpringBootApplication
public class FeedTubeApplication implements CommandLineRunner {

	@Autowired
	ChannelRepository channelRepository;

	public static void main(String[] args) {
		log.info("Starting FEED-Tube Backend.");
		SpringApplication.run(FeedTubeApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		// addin initially some testdata...

		channelRepository.deleteAll();

		// save a couple of customers
		channelRepository.save(new Channel("UCxVMLv0XwgrIvWBjf4lWRBA", "Rob Rides EMTB"));
		channelRepository.save(new Channel("UC6VkhPuCCwR_kG0GExjoozg", "Just me and Opensource"));

		// fetch all customers
		System.out.println("Channel found with findAll():");
		System.out.println("-------------------------------");
		for (Channel channel : channelRepository.findAll()) {
			System.out.println(channel);
		}
		System.out.println();

		// fetch an individual customer
		System.out.println("Channel found with findByName('Rob Rides EMTB'):");
		System.out.println("--------------------------------");
		System.out.println(channelRepository.findByName("Rob Rides EMTB"));
	}
}
