package io.agileintelligence.ppmtool.repositories;

import io.agileintelligence.ppmtool.models.security.PpmUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PpmUserRepository extends CrudRepository<PpmUser, Long> {

    PpmUser findByUsername(String username);
    PpmUser getById(Long id);

}
