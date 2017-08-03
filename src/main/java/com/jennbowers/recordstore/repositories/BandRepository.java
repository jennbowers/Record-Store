package com.jennbowers.recordstore.repositories;

import com.jennbowers.recordstore.models.Band;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BandRepository extends CrudRepository<Band, Long>{
    List<Band> findByName(String name);
}
