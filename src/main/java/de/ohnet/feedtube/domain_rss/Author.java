package de.ohnet.feedtube.domain_rss;

import java.net.URI;

public class Author {

    private String name;
    private URI uri;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }
}
