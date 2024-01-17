package com.pos.spotify.service;

import com.pos.spotify.entity.Record;

import java.util.List;

public interface RecordService {
    //create
    void saveRecord(Record record);

    //update
    void updateRecord(Record record);

    //read
    List<Record> getAll();

    //read by id
    Record getRecordById(int id);

    //read by id
    Record getRecordByName(String name);

    //delete
    void deleteSongById(int id);
}
