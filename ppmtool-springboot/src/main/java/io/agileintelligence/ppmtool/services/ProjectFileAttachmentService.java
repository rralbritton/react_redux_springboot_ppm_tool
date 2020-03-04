package io.agileintelligence.ppmtool.services;

import io.agileintelligence.ppmtool.models.Project;
import io.agileintelligence.ppmtool.models.ProjectFileAttachment;
import io.agileintelligence.ppmtool.repositories.ProjectFileAttachmentRepository;
import io.agileintelligence.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectFileAttachmentService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectFileAttachmentRepository attachmentRepository;

    /*public fileAttachment getFileById (Integer formIdFk, Integer attachmentIdPk){

        deqReactDev record =  reactDevRepository.findAllByRecordId(formIdFk);
        fileAttachment file = attachmentRepository.findAllById(attachmentIdPk);

        if(record == null){
            throw new gov.utah.deq.lhd.lhd.services.customErrors.InvalidIdService("Record " + formIdFk + " does not exist.");
        }

        if(file == null){
            throw new gov.utah.deq.lhd.lhd.services.customErrors.InvalidIdService("File Attachment " + attachmentIdPk + " does not exist");
        }

        if(!file.getDeqReactDev().getRecordId().equals(formIdFk)){
            throw new gov.utah.deq.lhd.lhd.services.customErrors.InvalidIdService("Attachment " + attachmentIdPk + " does not belong to record id " + formIdFk);
        }

        return file;
    }*/

    public ProjectFileAttachment uploadFile (Long prjId, ProjectFileAttachment file) {
        //set foregin key if null
        if(file.getPrjId() == null) {
            Project record = projectRepository.findAllById(prjId);
            file.setPrjId(record);
        }
        return attachmentRepository.save(file);
    }

}
