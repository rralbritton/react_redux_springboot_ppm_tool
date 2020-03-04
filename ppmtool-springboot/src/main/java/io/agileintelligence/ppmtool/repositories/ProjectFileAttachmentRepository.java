package io.agileintelligence.ppmtool.repositories;

import io.agileintelligence.ppmtool.models.ProjectFileAttachment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectFileAttachmentRepository extends CrudRepository<ProjectFileAttachment, Integer> {

    ProjectFileAttachment findAllById(Integer prjIdFk);

}
