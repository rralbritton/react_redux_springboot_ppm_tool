package io.agileintelligence.ppmtool.repositories;

import io.agileintelligence.ppmtool.models.ProjectTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectTaskRepository extends CrudRepository<ProjectTask, Long> {

    List<ProjectTask>findByProjectIdentifierOrderByPriority(String id);

    ProjectTask findByProjectSequence(String pt_id);
}
