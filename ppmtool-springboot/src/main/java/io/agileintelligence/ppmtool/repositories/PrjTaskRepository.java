package io.agileintelligence.ppmtool.repositories;

import io.agileintelligence.ppmtool.models.PrjTask;
import io.agileintelligence.ppmtool.models.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrjTaskRepository extends CrudRepository<PrjTask, Long> {

    List<PrjTask> findByPrjId(Project prjId);

    PrjTask findByTaskId(Long taskId);

}