package com.pos.spotify.controller;

import com.pos.spotify.component.ArtistModelAssembler;
import com.pos.spotify.entity.Artist;
import com.pos.spotify.security.JwtService;
import com.pos.spotify.service.ArtistService;
import com.pos.spotify.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/songcollection/artists")
@RequiredArgsConstructor
public class ArtistController {
    private final ArtistService artistService;
    private final ArtistModelAssembler assembler;
    private final JwtService jwtService;
    private final UserService userService;
    @GetMapping
    //@Secured("ROLE_CLIENT")
    public CollectionModel<EntityModel<Artist>> getArtists() {
        List<EntityModel<Artist>> artists = artistService.getAll().stream()
                 .map(assembler::toModel)
                 .collect(Collectors.toList());
        return CollectionModel.of(artists,
                linkTo(methodOn(ArtistController.class).getArtists()).withSelfRel());
    }
    @GetMapping("/{uuid}")
    //@Secured("ROLE_CLIENT")
    public EntityModel<Artist> selectArtist(@PathVariable String uuid){
        Artist artist = artistService.getArtistById(uuid);

        return assembler.toModel(artist);
    }
    @PutMapping("/{uuid}")
    @Secured({"ROLE_CONTENT_MANAGER", "ROLE_ADMINISTRATOR_APP"})
    public EntityModel<Artist> newArtist(@RequestBody Artist artist,
                                         @PathVariable String uuid){
        artist.setUuid(uuid);
        artistService.saveArtist(artist);
        return assembler.toModel(artistService.getArtistById(uuid));
    }

    @DeleteMapping("/{uuid}")
    @Secured({"ROLE_CONTENT_MANAGER", "ROLE_ADMINISTRATOR_APP"})
    public void deleteArtist(@PathVariable String uuid){
        artistService.deleteArtistById(uuid);
    }
}
