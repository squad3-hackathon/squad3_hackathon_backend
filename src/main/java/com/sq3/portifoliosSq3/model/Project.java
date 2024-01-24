package com.sq3.portifoliosSq3.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @NotNull
    @NotBlank
    @Column(unique = true)
    private String link;

    @Column(length = 1000)
    private String description;

    @Column(name = "imagedata")
    private byte[] data;

    public Project(){

    }

    public Project(String title, String description,String link, byte[] data){
        this.title = title;
        this.link = link;
        this.description = description;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String titulo) {
        this.title = title;
    }

    public String getLink(String link) {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String descricao) {
        this.description = description;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
