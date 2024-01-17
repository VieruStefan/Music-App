package com.pos.spotify.repository;

import com.pos.spotify.entity.Record;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecordRepository  extends CrudRepository<Record, Integer> {
    Optional<Record> findRecordByName(String name);
}
