package io.agileintelligence.ppmtool.services;

import io.agileintelligence.ppmtool.models.Backlog;
import io.agileintelligence.ppmtool.models.Project;
import io.agileintelligence.ppmtool.models.ProjectTask;
import io.agileintelligence.ppmtool.repositories.BacklogRepository;
import io.agileintelligence.ppmtool.repositories.ProjectRepository;
import io.agileintelligence.ppmtool.repositories.ProjectTaskRepository;
import io.agileintelligence.ppmtool.services.exceptionServices.BacklogIdExceptionService;
import io.agileintelligence.ppmtool.services.exceptionServices.ProjectIdExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectTaskService {

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask, String username){
        //Need to make sure the the projectLeader is the same individual trying to create the task
        //Get project by project identifier
        //If projectLeader != current user, throw an exception

        Project project = projectRepository.findByProjectIdentifier(projectIdentifier);
        if(!project.getProjectLeader().equals(username)){
            throw new ProjectIdExceptionService("Project not found in your account");
        } else {
            try {
                //All PTs to be added to a specific project
                Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
                //Set the backlog to the projectTask
                projectTask.setBacklog(backlog);
                //Structure project sequence to be like : projectId1, projectId2 etc - #s should be sequential
                Integer backlogSequence = backlog.getPTSequence();
                //Update the backlog sequence by 1
                backlogSequence++;
                backlog.setPTSequence(backlogSequence++);
                //Add sequence to project task
                projectTask.setProjectSequence(backlog.getProjectIdentifier() + "-" + backlogSequence);
                //Set project identifier
                projectTask.setProjectIdentifier(projectIdentifier);
                //Set Initial Priority (if left null)
                if (projectTask.getPriority() == null) {
                    projectTask.setPriority(3); //lowest priority
                }
                //Set Initial Status (can't be null)
                if (projectTask.getStatus() == "" || projectTask.getStatus() == null) {
                    projectTask.setStatus("TO_DO");
                }
                return projectTaskRepository.save(projectTask);
            } catch (Exception e) { //No Project Found Exception
                throw new BacklogIdExceptionService("Project Not Found");
            }
        }
    }

    public Iterable<ProjectTask>findBacklogById(String id, String username){
        Project project = projectRepository.findByProjectIdentifier(id);
        if(project == null){throw new ProjectIdExceptionService("Project " +id + " Does Not Exist");}
        if(!project.getProjectLeader().equals(username)){
            throw new ProjectIdExceptionService("Project not found in your account");
        }

        return projectTaskRepository.findByProjectIdentifierOrderByPriority(id);
    }

    public ProjectTask findPTByProjectSequence(String backlog_id, String pt_id, String username){

        //Make sure project exists (backlog_id)
        Backlog backlog = backlogRepository.findByProjectIdentifier(backlog_id.toUpperCase());
        if(backlog == null){throw new BacklogIdExceptionService("Project " + backlog_id + " does not exist" );}

        //If the project exists, make sure it belongs to the current user
        Project project = projectRepository.findByProjectIdentifier(backlog.getProjectIdentifier());
        if(!project.getProjectLeader().equals(username)){
            throw new ProjectIdExceptionService("Project not found in this account");
        }

        //Make sure task exists
        ProjectTask projectTask = projectTaskRepository.findByProjectSequence(pt_id.toUpperCase());
        if(projectTask == null){
            throw new BacklogIdExceptionService("Project Task " + pt_id + " not found" );
        }

        //Make sure stated task exists within the valid project
        if(!projectTask.getProjectIdentifier().equals(backlog_id.toUpperCase())){
            throw new BacklogIdExceptionService("Project Task " + pt_id + " does not exist within project " + backlog_id);
        }
        return projectTaskRepository.findByProjectSequence(pt_id);
    }

    public ProjectTask updateProjectTaskByPTSequence (ProjectTask updatedTask, String backlog_id, String pt_id, String username){
        ProjectTask projectTask = findPTByProjectSequence(backlog_id, pt_id, username);
        projectTask = updatedTask;
        return projectTaskRepository.save(projectTask);
    }

    public void deleteProjectTaskByProjectSequence(String backlog_id, String pt_id, String username){
        ProjectTask deletedProjectTask = findPTByProjectSequence(backlog_id, pt_id, username);
        projectTaskRepository.delete(deletedProjectTask);
    }

}
