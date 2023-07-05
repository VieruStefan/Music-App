package com.pos.spotify.service;

import com.pos.spotify.entity.ArtistPortfolio;
import com.pos.spotify.entity.ArtistPortfolioAssociationId;
import com.pos.spotify.entity.Record;

import java.util.List;

public interface ArtistPortfolioService {

    List<ArtistPortfolio> getAll();
    //create
    ArtistPortfolio save(ArtistPortfolio artistPortfolio);
    List<Record> getById(String uuid);

    void delete(ArtistPortfolioAssociationId id);
}
