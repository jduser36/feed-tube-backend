package de.ohnet.feedtube.domain_repository;

import de.ohnet.feedtube.domain.Channel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ChannelRepository extends MongoRepository<Channel, String> {

    public Optional<Channel> findById(String id);
    public Optional<Channel> findByName(String name);

}
