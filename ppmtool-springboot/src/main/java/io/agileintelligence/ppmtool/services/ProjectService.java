package io.agileintelligence.ppmtool.services;

import io.agileintelligence.ppmtool.models.Backlog;
import io.agileintelligence.ppmtool.models.Project;
import io.agileintelligence.ppmtool.models.security.PpmUser;
import io.agileintelligence.ppmtool.repositories.BacklogRepository;
import io.agileintelligence.ppmtool.repositories.PpmUserRepository;
import io.agileintelligence.ppmtool.services.exceptionServices.ProjectIdExceptionService;
import io.agileintelligence.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository  projectRepository;

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private PpmUserRepository ppmUserRepository;

    public Project saveOrUpdateProject(Project project, String username){

        String projectIdentifier = project.getProjectIdentifier().toUpperCase();

        if (project.getId() != null) {
            Project existingProject = projectRepository.findByProjectIdentifier(project.getProjectIdentifier());
            if(!existingProject.getProjectLeader().equals(username)){
                throw new ProjectIdExceptionService("Project not found in your account");
            }
        }

        try{

            if(project.getProjectLeader() != null){
                PpmUser ppmUser = ppmUserRepository.findByUsername(project.getProjectLeader());
                project.setPpmUser(ppmUser);
            } else {
                PpmUser ppmUser = ppmUserRepository.findByUsername(username);
                project.setPpmUser(ppmUser);
                project.setProjectLeader(ppmUser.getUsername());
            }
            project.setProjectIdentifier(projectIdentifier);
            return projectRepository.save(project);
        } catch (Exception e){
            throw new ProjectIdExceptionService("Project ID '" + project.getProjectIdentifier().toUpperCase() + "' already exists");
        }
    }

    public Project findByProjectIdentifier(String projectId, String username){
        Project project = projectRepository.findByProjectIdentifier(projectId);
        if(project == null){
            throw new ProjectIdExceptionService("Project ID '" + projectId + "' does NOT exist");
        }

        if(!project.getProjectLeader().equals(username)){
            throw new ProjectIdExceptionService("Project not found in your account");
        }

        return projectRepository.findByProjectIdentifier(projectId);
    }

    public Iterable<Project> findAllProjects(String username){
        return projectRepository.findAllByProjectLeader(username);
    }

    public void deleteProjectById (Long projectId, String username){
        projectRepository.delete(projectRepository.findAllById(projectId));
    }
}
