package io.agileintelligence.ppmtool.services;

import io.agileintelligence.ppmtool.models.PrjTask;
import io.agileintelligence.ppmtool.models.Project;
import io.agileintelligence.ppmtool.repositories.PrjTaskRepository;
import io.agileintelligence.ppmtool.repositories.ProjectRepository;
import io.agileintelligence.ppmtool.services.exceptionServices.ProjectIdExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrjTaskService {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    PrjTaskRepository taskRepository;

    public PrjTask getPrjTaskById (Long prjId, Long taskId, String username){
        Project project = projectRepository.findAllById(prjId);
        if(!project.getProjectLeader().equals(username)){
            throw new ProjectIdExceptionService("Project not found in your account");
        } else {
            return taskRepository.findByTaskId(taskId);
        }
    }

    public List<PrjTask> getAllPrjTasks (Long prjId, String username){
        Project project = projectRepository.findAllById(prjId);
        if(!project.getProjectLeader().equals(username)){
            throw new ProjectIdExceptionService("Project not found in your account");
        } else {
            return taskRepository.findByPrjId(project);
        }
    }

    public PrjTask addProjectTask(Long prjId, PrjTask projectTask, String username){
        //Need to make sure the the projectLeader is the same individual trying to create the task
        //Get project by project identifier
        //If projectLeader != current user, throw an exception
        Project project = projectRepository.findAllById(prjId);
        if(!project.getProjectLeader().equals(username)){
            throw new ProjectIdExceptionService("Project not found in your account");
        } else {
            //Save Task
            projectTask.setPrjId(project);
            return taskRepository.save(projectTask);
        }
    }

    public PrjTask updatePrjTask(Long prjId, Long taskId, PrjTask updateProjectTask, String username){
        Project project = projectRepository.findAllById(prjId);
        if(!project.getProjectLeader().equals(username)){
            throw new ProjectIdExceptionService("Project not found in your account");
        } else {
            PrjTask oldTask = taskRepository.findByTaskId(taskId);
            updateProjectTask.setPrjId(project);
            updateProjectTask.setTaskId(taskId);
            updateProjectTask.setCreatedOn(oldTask.getCreatedOn());

            return taskRepository.save(updateProjectTask);
        }
    }

    public void deleteTask (Long prjId, Long taskId, String username) {
        Project project = projectRepository.findAllById(prjId);
        if (!project.getProjectLeader().equals(username)) {
            throw new ProjectIdExceptionService("Project not found in your account");
        } else {
            taskRepository.delete(getPrjTaskById(prjId, taskId, username));
        }
    }
}

