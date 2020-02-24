package com.ppmtool.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_projects")
@Setter
@Getter
@ToString
public class Project {

    private static final Logger LOGGER = LoggerFactory.getLogger(Project.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private Long id;

    @NotBlank(message = "Project Name is required")
    @Column(name = "project_name", nullable = false)
    private String projectName;

    @NotBlank(message = "Project Identifier is required")
    @Size(min = 4, max = 5, message = "Please use 4-5 characters")
    @Column(name = "project_identifier", columnDefinition = "char(5)", unique = true, updatable = false, nullable = false)
    private String projectIdentifier;

    @NotBlank(message = "Project Description is required")
    @Column(name = "project_description", nullable = false)
    private String description;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "last_updated_date")
    private LocalDateTime lastUpdatedDate;

    @PrePersist
    private void prePersistProject() {
        LOGGER.info("inside prePersistProject method");
        this.createdDate = LocalDateTime.now();
        LOGGER.info(this.toString());
    }

    @PreUpdate
    private void preUpdateProject() {
        LOGGER.info("inside preUpdateProject method");
        this.lastUpdatedDate = LocalDateTime.now();
        LOGGER.info(this.toString());
    }

}
