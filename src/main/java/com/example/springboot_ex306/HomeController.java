package com.example.springboot_ex306;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {

    @Autowired
    AlbumRepository albumRepository;

    @Autowired
    SongRepository songRepository;

    @RequestMapping("/")
    public String listSongs(Model model) {
        model.addAttribute("songs", songRepository.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String songForm(Model model) {
        model.addAttribute("albums", albumRepository.findAll());
        model.addAttribute("song", new Song());
        return "songform";
    }

    @PostMapping("/process")
    public String processForm(@Valid Song song, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("albums", albumRepository.findAll());
            return "songform";
        }
        songRepository.save(song);
        return "redirect:/";
    }

    @GetMapping("/addAlbum")
    public String albumForm(Model model) {
        model.addAttribute("album", new Album());
        return "albumform";
    }

    @PostMapping("/processAlbum")
    public String processAlbum(@Valid Album album, BindingResult result) {
        if (result.hasErrors()) {
            return "albumform";
        }
        albumRepository.save(album);
        return "redirect:/";
    }

    @RequestMapping("/detail/{id}")
    public String showSong(@PathVariable("id") long id, Model model) {
        model.addAttribute("song", songRepository.findById(id).get());
        return "show";
    }

    @RequestMapping("/update/{id}")
    public String updateSong(@PathVariable("id") long id, Model model) {
        model.addAttribute("albums", albumRepository.findAll());
        model.addAttribute("song", songRepository.findById(id));
        return "songform";
    }

    @RequestMapping("/delete/{id}")
    public String delSong(@PathVariable("id") long id) {
        songRepository.deleteById(id);
        return "redirect:/";
    }

//    @RequestMapping("/")
//    public String index(Model model) {
//        //First let's create Album
//        Album album = new Album();
//        album.setGenre("hip hop");
//        album.setName("Purple Rain");
//
//
//        //Now let's create a Song
//        Song song = new Song();
//        song.setName("Wake me up");
//        song.setYear(1988);
//
//        //Add the song to an empty list
//        Set<Song> songs = new HashSet<Song>();
//        songs.add(song);
//
//        song = new Song();
//        song.setName("Material Girl");
//        song.setYear(2001);
//        songs.add(song);
//
//        //Add the list of songs to the album
//        album.setSongs(songs);
//
//        //Save the album to the db
//        albumRepository.save(album);
//
//        //Grab all the songs from the Dbase and send to template
//        model.addAttribute("albums", albumRepository.findAll());
//
//        return "index";
//
//    }

}
