package com.jennbowers.recordstore.controllers;

import com.jennbowers.recordstore.models.Album;
import com.jennbowers.recordstore.models.Band;
import com.jennbowers.recordstore.models.Song;
import com.jennbowers.recordstore.repositories.AlbumRepository;
import com.jennbowers.recordstore.repositories.BandRepository;
import com.jennbowers.recordstore.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    BandRepository bandRepo;

    @Autowired
    AlbumRepository albumRepo;

    @Autowired
    SongRepository songRepo;

    @RequestMapping("/")
    public String index (Model model) {
        Iterable<Band> bands = bandRepo.findAll();
        model.addAttribute("bands", bands);

        Iterable<Album> albums = albumRepo.findAll();
        model.addAttribute("albums", albums);

        Iterable<Song> songs = songRepo.findAll();
        model.addAttribute("songs", songs);

        return "index";
    }

    @RequestMapping("/bands")
    public String bands (Model model) {
        Iterable<Band> bands = bandRepo.findAll();
        model.addAttribute("bands", bands);
        return "bands";
    }

    @RequestMapping(value = "/bands", method = RequestMethod.POST)
    public String bandsSearch (@RequestParam("search") String nameSearch,
                               Model model) {
        List<Band> bands = bandRepo.findByName(nameSearch);
        model.addAttribute("bands", bands);
        return "bands";
    }

    @RequestMapping("/bands/add")
    public String addBands () {

        return "addBands";
    }

    @RequestMapping(value = "/bands/add", method = RequestMethod.POST)
    public String addBandsPost (@RequestParam("name") String name,
                            @RequestParam("genre") String genre,
                            @RequestParam("yearFormed") String yearFormedString,
                            @RequestParam("imgUrl") String imgUrl) {
        try {
            int yearFormed = Integer.parseInt(yearFormedString);
            Band newBand = new Band();
            newBand.setName(name);
            newBand.setGenre(genre);
            newBand.setYearFormed(yearFormed);
            newBand.setImgUrl(imgUrl);
            bandRepo.save(newBand);
        } catch (Exception ex) {

        }
        return "redirect:/bands";
    }

    @RequestMapping("/albums")
    public String albums (Model model) {
        Iterable<Album> albums = albumRepo.findAll();
        model.addAttribute("albums", albums);
        return "albums";
    }

    @RequestMapping("/albums/add")
    public String addAlbums (Model model) {
        Iterable<Band> bands = bandRepo.findAll();
        model.addAttribute("bands", bands);
        return "addAlbums";
    }

    @RequestMapping(value = "/albums/add", method = RequestMethod.POST)
    public String addAlbumsPost (@RequestParam("band") long bandId,
                                 @RequestParam("name") String name,
                                 @RequestParam("genre") String genre,
                                 @RequestParam("yearReleased") int yearReleased,
                                 @RequestParam("label") String label,
                                 @RequestParam("imgUrl") String imgUrl) {
        Band band = bandRepo.findOne(bandId);
        Album newAlbum = new Album();
        newAlbum.setBand(band);
        newAlbum.setName(name);
        newAlbum.setGenre(genre);
        newAlbum.setYearReleased(yearReleased);
        newAlbum.setLabel(label);
        newAlbum.setImgUrl(imgUrl);
        albumRepo.save(newAlbum);

        return "redirect:/albums";

    }

    @RequestMapping("/songs")
    public String songs (Model model) {
        Iterable<Song> songs = songRepo.findAll();
        model.addAttribute("songs", songs);
        return "songs";
    }

    @RequestMapping("/songs/add")
    public String addSongs (Model model) {
        Iterable<Band> bands = bandRepo.findAll();
        model.addAttribute("bands", bands);

        Iterable<Album> albums = albumRepo.findAll();
        model.addAttribute("albums", albums);

        return "addSongs";
    }

    @RequestMapping(value = "/songs/add", method = RequestMethod.POST)
    public String addSongsPost (@RequestParam("band") long bandId,
                                @RequestParam("album") long albumId,
                                @RequestParam("name") String name,
                                @RequestParam("length") String length) {
        Band band = bandRepo.findOne(bandId);
        Album album = albumRepo.findOne(albumId);

        Song newSong = new Song();
        newSong.setName(name);
        newSong.setLength(length);
        newSong.setBand(band);
        newSong.setAlbum(album);

        songRepo.save(newSong);

        return "redirect:/songs";
    }
}
