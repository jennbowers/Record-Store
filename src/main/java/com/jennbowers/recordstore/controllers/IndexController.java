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

    @RequestMapping("/albums")
    public String albums (Model model) {
        Iterable<Album> albums = albumRepo.findAll();
        model.addAttribute("albums", albums);
        return "albums";
    }

    @RequestMapping("/songs")
    public String songs (Model model) {
        Iterable<Song> songs = songRepo.findAll();
        model.addAttribute("songs", songs);
        return "songs";
    }
}
