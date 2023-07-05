package com.pos.spotify.service;

import com.pos.spotify.entity.Artist;

import java.util.List;

public interface ArtistService {
    //create
    void saveArtist(Artist artist);

    //update
    void updateArtist(Artist artist);

    //read
    List<Artist> getAll();

    //read by uuid
    Artist getArtistById(String uuid);

    //delete
    void deleteArtistById(String uuid);

}
