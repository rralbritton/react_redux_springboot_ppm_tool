package io.agileintelligence.ppmtool.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDate;

@Entity
public class PrjTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    /*Foreign Key Field*/
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="prjIdFk", referencedColumnName = "id")
    @JsonIgnore
    private Project prjId;

    private String taskName;
    private String taskAssignedTo;
    private String taskStatus;
    private String taskPriority;
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate taskDueDate;
    private String taskComments;
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate createdOn;
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate updatedOn;

    @PrePersist
    protected void onCreate(){
        this.createdOn = LocalDate.now();
    }

    @PreUpdate
    protected void onUpdate(){
        this.updatedOn = LocalDate.now();
    }

    /*Generic Constructor*/
    public PrjTask() {
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    /** Getters & Setters */



    public Project getPrjId() {
        return prjId;
    }

    public void setPrjId(Project prjId) {
        this.prjId = prjId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskAssignedTo() {
        return taskAssignedTo;
    }

    public void setTaskAssignedTo(String taskAssignedTo) {
        this.taskAssignedTo = taskAssignedTo;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(String taskPriority) {
        this.taskPriority = taskPriority;
    }

    public String getTaskComments() {
        return taskComments;
    }

    public void setTaskComments(String taskComments) {
        this.taskComments = taskComments;
    }

    public LocalDate getTaskDueDate() {
        return taskDueDate;
    }

    public void setTaskDueDate(LocalDate taskDueDate) {
        this.taskDueDate = taskDueDate;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDate getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDate updatedOn) {
        this.updatedOn = updatedOn;
    }
}
