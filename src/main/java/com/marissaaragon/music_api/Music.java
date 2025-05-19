package com.marissaaragon.music_api;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Music {
    @Id
    Long id;
    String title;
    String artist;
    String album;
    String genre;
    int releaseYear;
    String format;
}
