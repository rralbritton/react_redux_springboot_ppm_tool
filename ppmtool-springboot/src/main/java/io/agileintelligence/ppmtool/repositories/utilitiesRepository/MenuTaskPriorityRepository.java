package io.agileintelligence.ppmtool.repositories.utilitiesRepository;

import io.agileintelligence.ppmtool.models.utilities.MenuTaskPriority;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuTaskPriorityRepository extends CrudRepository<MenuTaskPriority, String> {
    List<MenuTaskPriority> findAllBy();
}
