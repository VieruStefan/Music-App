package com.pos.spotify.component;

import com.pos.spotify.controller.ArtistController;
import com.pos.spotify.controller.PortfolioController;
import com.pos.spotify.entity.Artist;
import com.pos.spotify.entity.Record;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ArtistModelAssembler implements RepresentationModelAssembler<Artist, EntityModel<Artist>> {
    @Override
    public EntityModel<Artist> toModel(Artist entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(ArtistController.class).selectArtist(entity.getUuid())).withSelfRel(),
                linkTo(methodOn(PortfolioController.class).getRecords(entity.getUuid())).withRel("songs"),
        linkTo(methodOn(ArtistController.class).getArtists()).withRel("root"));

    }
}
