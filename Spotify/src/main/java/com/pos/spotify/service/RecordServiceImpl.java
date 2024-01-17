package com.pos.spotify.service;

import com.pos.spotify.entity.Record;
import com.pos.spotify.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class RecordServiceImpl implements RecordService{

    @Autowired
    private RecordRepository recordRepository;

    @Override
    public void saveRecord(Record record) {
        recordRepository.save(record);
    }

    @Override
    public void updateRecord(Record record) {
        int id = record.getId();
        Record recordDB = recordRepository.findById(id).get();
        if(!("".equals(record.getName()) &&
                recordDB.getName().equals(record.getName()))){
            recordDB.setName(record.getName());
        }
        if(recordDB.getGenre()!=record.getGenre()){
            recordDB.setGenre(record.getGenre());
        }
        if(recordDB.getType()!=record.getType()){
            recordDB.setType(record.getType());
        }
        if(recordDB.getYear() != record.getYear()){
            recordDB.setYear(record.getYear());
        }
        if(record.getParent()!=null && record.getParent().getName()!=null){
            recordDB.setParent(record.getParent());
        }
        recordRepository.save(recordDB);
    }

    @Override
    public List<Record> getAll() {
        return (List<Record>) recordRepository.findAll();
    }

    @Override
    public Record getRecordById(int id) {
        return recordRepository.findById(id).get();
    }

    @Override
    public Record getRecordByName(String name) {
        return recordRepository.findRecordByName(name).get();
    }


    @Override
    public void deleteSongById(int id) {
        recordRepository.deleteById(id);
    }
}
