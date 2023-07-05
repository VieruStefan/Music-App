package com.pos.spotify.entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity(name = "artist_portfolio")
public class ArtistPortfolio {

    @EmbeddedId private ArtistPortfolioAssociationId id;
    @ManyToOne(cascade=CascadeType.ALL)
    @MapsId("uuid")
    private Artist artist;
    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("recordId")
    private Record record;

    public ArtistPortfolio() {
        id = new ArtistPortfolioAssociationId();
    }
    public ArtistPortfolio(Artist artist, Record record) {
        id = new ArtistPortfolioAssociationId(artist.getUuid(), record.getId());
        this.artist = artist;
        this.record = record;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtistPortfolio that = (ArtistPortfolio) o;
        return record == that.record && Objects.equals(artist, that.artist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(artist, record);
    }
}
