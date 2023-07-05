package com.pos.spotify.service;

import com.pos.spotify.entity.Artist;
import com.pos.spotify.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ArtistServiceImpl implements ArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    @Override
    public void saveArtist(Artist artist){
        artistRepository.save(artist);
    }

    @Override
    public void updateArtist(Artist artist) {
        String uuid = artist.getUuid();
        Artist artistDB = artistRepository.findById(uuid).get();
        if(Objects.nonNull(artist.getName()) && !"".equals(artistDB.getName())){
            artistDB.setName(artist.getName());
        }
        if(artistDB.getActive() != artist.getActive())
        {
            artistDB.setActive(artist.getActive());
        }
        artistRepository.save(artistDB);
    }

    @Override
    public List<Artist> getAll() {
        return (List<Artist>) artistRepository.findAll();
    }

    @Override
    public void deleteArtistById(String  uuid) {
        artistRepository.deleteById(uuid);
    }

    @Override
    public Artist getArtistById(String uuid) {
        return artistRepository.findById(uuid).get();
    }


}
