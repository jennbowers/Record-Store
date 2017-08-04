package com.jennbowers.recordstore.controllers;

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
public class BandController {

    @Autowired
    BandRepository bandRepo;

    @Autowired
    AlbumRepository albumRepo;

    @Autowired
    SongRepository songRepo;

    @RequestMapping(value = "/bands", method = RequestMethod.GET)
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

    @RequestMapping(value = "/bands/add", method = RequestMethod.GET)
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

    @RequestMapping(value = "/bands/edit/{bandId}", method = RequestMethod.GET)
    public String editBand (@PathVariable("bandId") long bandId,
                            Model model) {
        Band band = bandRepo.findOne(bandId);

        model.addAttribute("band", band);
        return "editBand";
    }

    @RequestMapping(value = "/bands/edit/{bandId}", method = RequestMethod.POST)
    public String editBandPost (@PathVariable("bandId") long bandId,
                            @RequestParam("name") String name,
                            @RequestParam("genre") String genre,
                            @RequestParam("yearFormed") int yearFormed,
                            @RequestParam("imgUrl") String imgUrl,
                            Model model) {
        Band band = bandRepo.findOne(bandId);
        band.setName(name);
        band.setGenre(genre);
        band.setYearFormed(yearFormed);
        band.setImgUrl(imgUrl);
        bandRepo.save(band);

        return "redirect:/bands";
    }

}
