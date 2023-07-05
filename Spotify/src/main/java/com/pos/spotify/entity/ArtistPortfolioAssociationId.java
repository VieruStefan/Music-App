package com.pos.spotify.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtistPortfolioAssociationId implements Serializable {
    private String uuid;
    private int recordId;
}
