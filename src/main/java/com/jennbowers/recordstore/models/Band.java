package com.jennbowers.recordstore.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "band")
public class Band {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String genre;
    @Column(name = "year_formed")
    private int yearFormed;
    @Column(name = "img_url")
    private String imgUrl;

    @OneToMany(mappedBy = "band", cascade = CascadeType.ALL)
    private List<Album> albums;

    @OneToMany(mappedBy = "band", cascade = CascadeType.ALL)
    private List<Song> songs;

    public Band() {}

    public Band(String name, String genre, int yearFormed, String imgUrl, List<Album> albums, List<Song> songs) {
        this.name = name;
        this.genre = genre;
        this.yearFormed = yearFormed;
        this.imgUrl = imgUrl;
        this.albums = albums;
        this.songs = songs;
    }

    public long getId() {
        return id;
    }

//    public void setId(long id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYearFormed() {
        return yearFormed;
    }

    public void setYearFormed(int yearFormed) {
        this.yearFormed = yearFormed;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}
