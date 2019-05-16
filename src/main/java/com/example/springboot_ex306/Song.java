package com.example.springboot_ex306;

import javax.persistence.*;

@Entity
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private long year;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="album_id")
    private Album album;

    public Song() {
    }

    public Song(String name, long year, Album album) {
        this.name = name;
        this.year = year;
        this.album = album;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getYear() {
        return year;
    }

    public void setYear(long year) {
        this.year = year;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}
