package io.agileintelligence.ppmtool.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.agileintelligence.ppmtool.models.security.PpmUser;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Project name is required")
    private String projectName;

    @NotBlank(message = "Project Identifier is required")
    @Column(updatable = false, unique = true)
    private String projectIdentifier;

    @NotBlank(message = "Project description is required")
    private String description;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date startDate;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date endDate;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date createdOn;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date updatedOn;

    /**project leader is automatically assigned as the person creating the project.
     * This can be changed.
     * Currently, only project leaders can view there own projects
     */
    private String projectLeader;
    private BigDecimal estimatedHours;
    private BigDecimal hourRemaining;
    private String projectStatus;
    private String productOwnerName;
    private String productOwnerDept;

    /*@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "project") //1:1 Relationship with Backlog
    @JsonIgnore
    private Backlog backlog;*/

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "prjId")
   private List<PrjTask> projectTasks = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore /*Prevents infinite recursion problem */
    private PpmUser ppmUser;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "prjId")
    private List<ProjectFileAttachment> projectFileAttachments = new ArrayList<>();

    /*Getters and Setters*/
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public PpmUser getPpmUser() {
        return ppmUser;
    }

    public void setPpmUser(PpmUser ppmUser) {
        this.ppmUser = ppmUser;
    }

    public String getProjectLeader() {
        return projectLeader;
    }

    public void setProjectLeader(String projectLeader) {
        this.projectLeader = projectLeader;
    }

    public BigDecimal getEstimatedHours() {
        return estimatedHours;
    }

    public void setEstimatedHours(BigDecimal estimatedHours) {
        this.estimatedHours = estimatedHours;
    }

    public BigDecimal getHourRemaining() {
        return hourRemaining;
    }

    public void setHourRemaining(BigDecimal hourRemaining) {
        this.hourRemaining = hourRemaining;
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }

    public String getProductOwnerName() {
        return productOwnerName;
    }

    public void setProductOwnerName(String productOwnerName) {
        this.productOwnerName = productOwnerName;
    }

    public String getProductOwnerDept() {
        return productOwnerDept;
    }

    public void setProductOwnerDept(String productOwnerDept) {
        this.productOwnerDept = productOwnerDept;
    }

    public List<ProjectFileAttachment> getProjectFileAttachments() {
        return projectFileAttachments;
    }

    public void setProjectFileAttachments(List<ProjectFileAttachment> projectFileAttachments) {
        this.projectFileAttachments = projectFileAttachments;
    }

    public List<PrjTask> getProjectTasks() {
        return projectTasks;
    }

    public void setProjectTasks(List<PrjTask> projectTasks) {
        this.projectTasks = projectTasks;
    }

    /*Constructor Argument*/
    public Project(){}

    /*Automatically append created on and updated on dates to records*/
    @PrePersist
    protected  void onCreate(){
        this.createdOn = new Date();
    }

    @PreUpdate
    protected void onUpdate(){
        this.updatedOn = new Date();
    }
}
