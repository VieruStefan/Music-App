package com.pos.spotify.service;

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
    public List<Record> getById(String uuid) {
        List<ArtistPortfolio> portfolioList = getAll();
        return portfolioList.stream().filter(c-> c.getArtist().getUuid().equals(uuid)).map(ArtistPortfolio::getRecord).collect(Collectors.toList());
    }

    @Override
    public void delete(ArtistPortfolioAssociationId id) {
        portfolioRepository.deleteById(id);
    }
}
