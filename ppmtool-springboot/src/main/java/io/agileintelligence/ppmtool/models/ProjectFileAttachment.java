package io.agileintelligence.ppmtool.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;

@Entity
public class ProjectFileAttachment {

    @Id /*Primary Key Field*/
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable=false, unique=true)
    private Long id;

    /*Foreign Key Field*/
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="prjIdFk", referencedColumnName = "id")
    @JsonIgnore
    private Project prjId;

    private String fileName;

    @Lob
    private byte[] fileData;
    private Long filesize;
    private String fileType;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdOn;

    @PrePersist
    protected void onCreate() {
        this.createdOn = LocalDateTime.now();
    }

    /*Contructor*/
    public ProjectFileAttachment(String fileName, Long filesize, String fileType, byte[] fileData) {
        this.fileName = fileName;
        this.filesize = filesize;
        this.fileType = fileType;
        this.fileData = fileData;
    }

    //default constructor required
    public ProjectFileAttachment() {
    }

    /*Getters & Setters*/

    public Project getPrjId() {
        return prjId;
    }

    public void setPrjId(Project prjId) {
        this.prjId = prjId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    public Long getFilesize() {
        return filesize;
    }

    public void setFilesize(Long filesize) {
        this.filesize = filesize;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

}
