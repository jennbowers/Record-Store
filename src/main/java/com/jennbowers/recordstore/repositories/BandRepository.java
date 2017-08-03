package com.jennbowers.recordstore.repositories;

import com.jennbowers.recordstore.models.Band;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BandRepository extends CrudRepository<Band, Long>{
}
