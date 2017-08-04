package com.jennbowers.recordstore.controllers;

import com.jennbowers.recordstore.models.Album;
import com.jennbowers.recordstore.models.Band;
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
public class AlbumController {

    @Autowired
    BandRepository bandRepo;

    @Autowired
    AlbumRepository albumRepo;

    @Autowired
    SongRepository songRepo;

    @RequestMapping(value = "/albums", method = RequestMethod.GET)
    public String albums (Model model) {
        Iterable<Album> albums = albumRepo.findAll();
        model.addAttribute("albums", albums);
        return "albums";
    }

    @RequestMapping(value = "/albums", method = RequestMethod.POST)
    public String albumsSearch (@RequestParam("search") String nameSearch,
                                Model model) {
        List<Album> albums = albumRepo.findByName(nameSearch);
        model.addAttribute("albums", albums);
        return "albums";
    }

    @RequestMapping(value = "/albums/add", method = RequestMethod.GET)
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

    @RequestMapping(value = "/albums/edit/{albumId}", method = RequestMethod.GET)
    public String editAlbum (@PathVariable("albumId") long albumId,
                             Model model) {
        Album album = albumRepo.findOne(albumId);

        model.addAttribute("album", album);
        return "editAlbum";
    }

}
