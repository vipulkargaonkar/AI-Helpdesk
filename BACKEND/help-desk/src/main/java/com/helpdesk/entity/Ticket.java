package com.helpdesk.entity;

import com.helpdesk.enums.Priority;
import com.helpdesk.enums.Status;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

import static java.util.Objects.isNull;

@Entity
@Table(name = "help_desk_tickets")
public class Ticket implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String summary;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    private String category;

    @Column(length = 1000)
    private String description;

    @Column(unique = true)
    private String email;

    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;

    @Enumerated(EnumType.STRING)
    private Status status;

    @PrePersist
    void preSave() {
        if (isNull(this.createdOn)) {
            this.createdOn = LocalDateTime.now();
        }
        this.updatedOn = LocalDateTime.now();
    }

    @PreUpdate
    void preUpdate() {
        this.updatedOn = LocalDateTime.now();
    }

    public Ticket() {
    }

    public Ticket(Long id, String summary, Priority priority, String category, String description, String email, LocalDateTime createdOn, LocalDateTime updatedOn, Status status) {
        this.id = id;
        this.summary = summary;
        this.priority = priority;
        this.category = category;
        this.description = description;
        this.email = email;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
