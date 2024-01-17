package com.pos.spotify.controller;

import com.pos.spotify.component.RecordModelAssembler;
import com.pos.spotify.entity.Record;
import com.pos.spotify.security.JwtService;
import com.pos.spotify.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/songcollection/songs")
@RequiredArgsConstructor
public class RecordController {
    private final RecordService recordService;
    private final RecordModelAssembler assembler;
    @GetMapping()
    public CollectionModel<EntityModel<Record>> getRecords() {
        List<EntityModel<Record>> records = recordService.getAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(records,
                linkTo(methodOn(RecordController.class).getRecords()).withSelfRel());
    }
    @GetMapping("/{id}")
    public EntityModel<Record> selectRecord(@PathVariable(value = "id") int id){
        return assembler.toModel(recordService.getRecordById(id));
    }
    @PostMapping
    @Secured({"ROLE_CONTENT_MANAGER", "ROLE_ARTIST", "ROLE_ADMINISTRATOR_APP"})
    public EntityModel<Record> newRecord(@RequestBody Record record){
        if(record.getParent()!=null & record.getParent().getName()!=null){
        record.setParent(recordService.getRecordByName(record.getParent().getName()));}
        recordService.saveRecord(record);
        return assembler.toModel(recordService.getRecordById(record.getId()));
    }

    @PutMapping("/{id}")
    @Secured({"ROLE_CONTENT_MANAGER", "ROLE_ARTIST", "ROLE_ADMINISTRATOR_APP"})
    public EntityModel<Record> updateRecord(@RequestBody Record record, @PathVariable(value="id") int id){
        record.setId(id);
        if(record.getParent()!=null && record.getParent().getName()!=null){
            record.setParent(recordService.getRecordByName(record.getParent().getName()));}
        recordService.updateRecord(record);
        return assembler.toModel(recordService.getRecordById(record.getId()));
    }

    @DeleteMapping("/{rid}")
    @Secured({"ROLE_CONTENT_MANAGER", "ROLE_ARTIST", "ROLE_ADMINISTRATOR_APP"})
    public void deleteRecord(@PathVariable String rid){
        recordService.deleteSongById(Integer.parseInt(rid));
    }
}
