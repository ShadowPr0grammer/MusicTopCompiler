package com.soft.data;

import org.jsoup.nodes.Document;

import java.util.List;

public abstract class Album extends Entry {

    public Album(String url) {
        super(url);
    }

    protected abstract String parseTitle(Document document);

    protected abstract List<Track> parseTracks(Document document);

    protected abstract Artist parseArtist(Document document);

    protected abstract String parseReleaseDate(Document document);

    @Override
    public String type() {
        return "album";
    }

    @Override
    public String toString() {
        return artist() + " - " + title();
    }

    @Override
    public boolean isLoaded() {
        return artist().isLoaded() && title().isLoaded();
    }

    public String getTitle() {
        return title().get();
    }

    public List<Track> getTracks() {
        return tracks().get();
    }

    public Artist getArtist() {
        return artist().get();
    }

    public String getReleaseDate() {
        return releaseDate().get();
    }

    public Value<String> title() {
        return getValue("title", this::parseTitle);
    }

    public Value<List<Track>> tracks() {
        return getValue("tracks", this::parseTracks);
    }

    public Value<Artist> artist() {
        return getValue("artist", this::parseArtist);
    }

    public Value<String> releaseDate() {
        return getValue("releaseDate", this::parseReleaseDate);
    }
}