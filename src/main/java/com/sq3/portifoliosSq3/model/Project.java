package com.sq3.portifoliosSq3.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @NotBlank
    @Column(nullable = false)
    private String title;
    @NotNull
    @NotBlank
    @Column(length = 1000)
    private String description;

    //@NotNull
    //@NotBlank
    @Column(length = 500)
    @Pattern(regexp = "^(http|https)://.*|$", message = "URL informada inválida.") // este regex verifica se a url começa com http ou https
    private String link;


    @NotNull
    @Column(name = "imagedata", nullable = false)
    private byte[] data;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Project(){

    }

    public Project(String title, String description,String link, byte[] data, User user){
        this.title = title;
        this.link = link;
        this.description = description;
        this.data = data;
        this.user = user;
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

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }
}
