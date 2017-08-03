package com.jennbowers.recordstore.repositories;

import com.jennbowers.recordstore.models.Album;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends CrudRepository<Album, Long>{
    List<Album> findByName(String name);
}
