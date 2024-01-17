package com.pos.spotify.entity;

import jakarta.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Record {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "genre")
    private GenreType genre;
    @Basic
    @Column(name = "year")
    private Date year;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private RecordType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_name", referencedColumnName = "name")
    private Record parent;

    @OneToMany(mappedBy = "parent")
    private List<Record> songsOfAlbum = new ArrayList<Record>();

    @OneToMany(mappedBy = "record")
    private List<ArtistPortfolio> portfolioList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GenreType getGenre() {
        return genre;
    }

    public void setGenre(GenreType genre) {
        this.genre = genre;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public RecordType getType() {
        return type;
    }

    public void setType(RecordType type) {
        this.type = type;
    }

    public Record getParent() {
        return parent;
    }

    public void setParent(Record parent) {
        this.parent = parent;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return id == record.id && Objects.equals(name, record.name) && Objects.equals(genre, record.genre) && Objects.equals(year, record.year) && Objects.equals(type, record.type) && Objects.equals(parent, record.parent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, genre, year, type, parent);
    }
}
