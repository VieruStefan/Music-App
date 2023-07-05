package com.pos.spotify.repository;

import com.pos.spotify.entity.Record;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepository  extends CrudRepository<Record, Integer> {
}
