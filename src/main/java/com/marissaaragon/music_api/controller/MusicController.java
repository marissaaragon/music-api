package com.marissaaragon.music_api.controller;

import com.marissaaragon.music_api.entity.Music;
import com.marissaaragon.music_api.repository.MusicRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("api/music")
public class MusicController {
    @Autowired
    private MusicRepository musicRepository;

    @PostMapping
    public ResponseEntity<Music>
    createMusic(@Valid @RequestBody Music music) {
        Music savedMusic = musicRepository.save(music);
        return new
                ResponseEntity<>(savedMusic, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Music>>
    getAllMusic(){
        List<Music> musicList = musicRepository.findAll();
        return new
                ResponseEntity<>(musicList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Music> getMusicByID(@PathVariable Long id) {
        Optional<Music> music = musicRepository.findById(id);
        return music.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Music> updateMusic(@PathVariable Long id, @RequestBody Music updatedMusic) {
        Optional<Music> existingMusicOptional = musicRepository.findById(id);
        if (existingMusicOptional.isPresent()) {
            Music existingMusic = existingMusicOptional.get();

            existingMusic.setTitle(updatedMusic.getTitle());
            existingMusic.setArtist(updatedMusic.getArtist());
            existingMusic.setAlbum(updatedMusic.getAlbum());
            existingMusic.setGenre(updatedMusic.getGenre());
            existingMusic.setReleaseYear(updatedMusic.getReleaseYear());
            existingMusic.setFormat(updatedMusic.getFormat());

            Music savedMusic = musicRepository.save(existingMusic);
            return new ResponseEntity<>(savedMusic, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMusic(@PathVariable Long id) {
        if (musicRepository.existsById(id)) {
            musicRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
