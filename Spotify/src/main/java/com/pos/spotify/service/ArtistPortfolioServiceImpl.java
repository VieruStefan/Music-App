package com.pos.spotify.service;

import com.pos.spotify.entity.Artist;
import com.pos.spotify.entity.ArtistPortfolio;
import com.pos.spotify.entity.ArtistPortfolioAssociationId;
import com.pos.spotify.entity.Record;
import com.pos.spotify.repository.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArtistPortfolioServiceImpl implements ArtistPortfolioService {
    @Autowired
    PortfolioRepository portfolioRepository;
    @Override
    public List<ArtistPortfolio> getAll() {
        return (List<ArtistPortfolio>) portfolioRepository.findAll();
    }

    @Override
    public ArtistPortfolio save(ArtistPortfolio artistPortfolio) {
        return portfolioRepository.save(artistPortfolio);
    }

    @Override
    public List<Record> getRecordsByArtist(String artist_id) {
        List<ArtistPortfolio> portfolioList = getAll();
        return portfolioList.stream().filter(
                c-> c.getArtist().
                        getUuid()
                        .equals(artist_id))
                .map(ArtistPortfolio::getRecord)
                .toList();
    }

    @Override
    public Artist getArtistByRecord(int record_id){
        List<ArtistPortfolio> portfolioList = getAll();
        return portfolioList.stream().filter(
                c -> c.getRecord().getId() == record_id)
                .map(ArtistPortfolio::getArtist)
                .toList().get(0);
    }
    @Override
    public void delete(ArtistPortfolioAssociationId id) {
        portfolioRepository.deleteById(id);
    }
}
