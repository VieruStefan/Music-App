package com.pos.spotify.controller;

import com.pos.spotify.component.ArtistModelAssembler;
import com.pos.spotify.component.RecordModelAssembler;
import com.pos.spotify.entity.Artist;
import com.pos.spotify.entity.ArtistPortfolio;
import com.pos.spotify.entity.Record;
import com.pos.spotify.security.JwtService;
import com.pos.spotify.service.ArtistPortfolioService;
import com.pos.spotify.service.ArtistService;
import com.pos.spotify.service.RecordService;
import com.pos.spotify.service.UserService;
import com.pos.spotify.userdetails.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/songcollection")
@RequiredArgsConstructor
public class PortfolioController {
    private final ArtistPortfolioService artistPortfolioService;
    private final ArtistService artistService;
    private final RecordService recordService;
    private final ArtistModelAssembler artistAssembler;
    private final RecordModelAssembler recordAssembler;

    @GetMapping("/artists/{uuid}/songs")
    public CollectionModel<EntityModel<Record>> getRecords(@PathVariable("uuid") String uuid){
        List<EntityModel<Record>> records = artistPortfolioService.getRecordsByArtist(uuid)
                .stream()
                .map(recordAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(records,
                linkTo(methodOn(PortfolioController.class).getRecords(uuid)).withSelfRel());
    }

    @GetMapping("/songs/{id}/artist")
    public EntityModel<Artist> getArtist(@PathVariable("id") int id){
        return artistAssembler.toModel(artistPortfolioService.getArtistByRecord(id));

    }

    @PutMapping ("/artists/{uuid}/songs")
    @Secured({"ROLE_CONTENT_MANAGER", "ROLE_ARTIST", "ADMINISTRATOR_APP"})
    public ArtistPortfolio savenew(@PathVariable("uuid") String uuid, @RequestBody Record record){
        return artistPortfolioService
                .save(new ArtistPortfolio(artistService.getArtistById(uuid),
                        recordService.getRecordById(record.getId())));
    }

}
