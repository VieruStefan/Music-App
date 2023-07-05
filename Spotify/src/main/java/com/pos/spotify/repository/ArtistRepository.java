package com.pos.spotify.repository;

import com.pos.spotify.entity.Artist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends CrudRepository<Artist, String> {

}
