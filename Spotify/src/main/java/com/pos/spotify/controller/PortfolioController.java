package com.pos.spotify.controller;

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
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/songcollection/artists")
@RequiredArgsConstructor
public class PortfolioController {
    private final ArtistPortfolioService artistPortfolioService;
    private final ArtistService artistService;
    private final RecordService recordService;

    @GetMapping("/{uuid}/songs")
    public List<Record> getById(@PathVariable("uuid") String uuid){
        return artistPortfolioService.getById(uuid);
    }

    @PutMapping ("/{uuid}/songs")
    @Secured({"ROLE_CONTENT_MANAGER", "ROLE_ARTIST"})
    public ArtistPortfolio savenew(@PathVariable("uuid") String uuid, @RequestBody Record record){
        return artistPortfolioService
                .save(new ArtistPortfolio(artistService.getArtistById(uuid),
                        recordService.getRecordById(record.getId())));
    }

}
