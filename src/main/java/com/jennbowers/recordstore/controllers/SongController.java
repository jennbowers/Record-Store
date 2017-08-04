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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SongController {

    @Autowired
    BandRepository bandRepo;

    @Autowired
    AlbumRepository albumRepo;

    @Autowired
    SongRepository songRepo;

    @RequestMapping(value = "/songs", method = RequestMethod.GET)
    public String songs (Model model) {
        Iterable<Song> songs = songRepo.findAll();
        model.addAttribute("songs", songs);
        return "songs";
    }

    @RequestMapping(value = "/songs", method = RequestMethod.POST)
    public String songsSearch (@RequestParam("search") String nameSearch,
                               Model model) {
        List<Song> songs = songRepo.findByName(nameSearch);
        model.addAttribute("songs", songs);
        return "songs";
    }



    @RequestMapping(value = "/songs/add", method = RequestMethod.GET)
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

    @RequestMapping(value = "/songs/edit/{songId}", method = RequestMethod.GET)
    public String editSong (@PathVariable("songId") long songId,
                            Model model) {
        Iterable<Band> bands = bandRepo.findAll();
        model.addAttribute("bands", bands);

        Iterable<Album> albums = albumRepo.findAll();
        model.addAttribute("albums", albums);

        Song song = songRepo.findOne(songId);
        model.addAttribute("song", song);
        return "editSong";
    }

    @RequestMapping(value = "/songs/edit/{songId}", method = RequestMethod.POST)
    public String editAlbumPost (@PathVariable("songId") long songId,
                                 @RequestParam("band") long bandId,
                                 @RequestParam("album") long albumId,
                                 @RequestParam("name") String name,
                                 @RequestParam("length") String length,
                                 Model model) {
        Band band = bandRepo.findOne(bandId);
        Album album = albumRepo.findOne(albumId);
        Song song = songRepo.findOne(songId);
        song.setBand(band);
        song.setAlbum(album);
        song.setName(name);
        song.setLength(length);
        songRepo.save(song);

        return "redirect:/songs";
    }
}
