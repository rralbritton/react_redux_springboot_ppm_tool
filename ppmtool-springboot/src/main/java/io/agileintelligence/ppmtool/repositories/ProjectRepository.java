package io.agileintelligence.ppmtool.repositories;

import io.agileintelligence.ppmtool.models.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

        Project findByProjectIdentifier (String projectId);

        Project findAllById(Long Id);

        @Override
        Iterable<Project> findAll();

        Iterable<Project> findAllByProjectLeader(String username);
}
