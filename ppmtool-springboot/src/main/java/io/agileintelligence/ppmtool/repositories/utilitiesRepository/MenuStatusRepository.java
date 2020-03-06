package io.agileintelligence.ppmtool.repositories.utilitiesRepository;

import io.agileintelligence.ppmtool.models.utilities.MenuStatusList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuStatusRepository extends CrudRepository<MenuStatusList, String> {

    List<MenuStatusList> findAllBy();
}
