package com.pos.spotify.entity;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Artist {
    @Id
    @Column(name = "uuid")
    private String uuid;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "active")
    private boolean active;


    @OneToMany(mappedBy = "artist")
    private List<ArtistPortfolio> portfolioList;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artist artist = (Artist) o;
        return active == artist.active && Objects.equals(uuid, artist.uuid) && Objects.equals(name, artist.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, name, active);
    }
/*
    public Artists(String uuid, String name, boolean active) {
        this.uuid = uuid;
        this.name = name;
        this.active = active;
    }*/

    public Artist() {
    }
}
