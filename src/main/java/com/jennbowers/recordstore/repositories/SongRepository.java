package com.jennbowers.recordstore.repositories;

import com.jennbowers.recordstore.models.Song;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends CrudRepository<Song, Long>{
}
