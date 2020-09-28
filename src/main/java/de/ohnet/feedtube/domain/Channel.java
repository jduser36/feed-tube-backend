package de.ohnet.feedtube.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

/**
 * Defines an channel and its properties in a rss feed of
 * an youtube channel.
 */
@Document(collection="channelData")
public class Channel {

    @Id
    private String id;
    private String name;
    private Date insertDate;

    public Channel(String id, String name) {
        this.id = id;
        this.name = name;
        Date in = new Date();
        LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
        insertDate = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%s, name='%s', insertDate='%s']",
                id, name, insertDate);
    }
}
