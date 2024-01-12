package com.pos.spotify.component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.pos.spotify.controller.ArtistController;
import com.pos.spotify.controller.PortfolioController;
import com.pos.spotify.controller.RecordController;
import com.pos.spotify.entity.Record;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class RecordModelAssembler implements RepresentationModelAssembler<Record, EntityModel<Record>> {

    @Override
    public EntityModel<Record> toModel(Record entity) {
        return (entity.getParent() != null)?
                EntityModel.of(entity,
                        linkTo(methodOn(RecordController.class).selectRecord(entity.getId())).withSelfRel(),
                        linkTo(methodOn(RecordController.class)
                                .selectRecord(entity.getParent().getId())).withRel("parent"),
                        linkTo(methodOn(PortfolioController.class).getArtist(entity.getId()) ).withRel("artist"),
                        linkTo(methodOn(RecordController.class).getRecords()).withRel("root"))
                :
                EntityModel.of(entity,
                        linkTo(methodOn(RecordController.class).selectRecord(entity.getId())).withSelfRel(),
                        linkTo(methodOn(PortfolioController.class).getArtist(entity.getId())).withRel("artist"),
                        linkTo(methodOn(RecordController.class).getRecords()).withRel("root"));
    }
}
