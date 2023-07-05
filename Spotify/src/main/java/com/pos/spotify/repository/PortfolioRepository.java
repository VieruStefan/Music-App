package com.pos.spotify.repository;

import com.pos.spotify.entity.ArtistPortfolio;
import com.pos.spotify.entity.ArtistPortfolioAssociationId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortfolioRepository extends CrudRepository<ArtistPortfolio, ArtistPortfolioAssociationId> {
}
