package io.agileintelligence.ppmtool.repositories.utilitiesRepository;

import io.agileintelligence.ppmtool.models.utilities.MenuDevelopersList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuDeveloperRepository extends CrudRepository<MenuDevelopersList, String> {

    List<MenuDevelopersList> findAllBy();
}
