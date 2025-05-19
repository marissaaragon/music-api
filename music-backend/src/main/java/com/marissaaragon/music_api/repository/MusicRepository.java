package com.marissaaragon.music_api.repository;

import com.marissaaragon.music_api.entity.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicRepository extends JpaRepository<Music, Long>{

}
