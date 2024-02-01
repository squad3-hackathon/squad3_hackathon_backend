package com.sq3.portifoliosSq3.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.*;
import java.util.stream.Collectors;

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
    @Column(length = 500)
    @Pattern(regexp = "^(http|https)://.*|$", message = "URL informada inválida.") // este regex verifica se a url começa com http ou https
    private String link;
    @JsonIgnore
    @Lob
    @NotNull
    @Column(name = "imagedata", nullable = false)
    private byte[] data;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "project_tags",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();

    public Project(){

    }

    public Project(String title, String description,String link, byte[] data, Date creationDate, User user, Set<Tag> tags){
        this.title = title;
        this.link = link;
        this.description = description;
        this.data = data;
        this.creationDate = creationDate;
        this.user = user;
        this.tags = (tags == null) ? new HashSet<>() : tags;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setLink(String link) { this.link = link; }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
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

    @JsonProperty("userId")
    public Long getUserId() {
        return user != null ? user.getId() : null;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    @JsonProperty("tagIds")
    public Set<Long> getTagIds() {
        if (tags == null) {
            return Collections.emptySet();
        }
        return tags.stream().map(Tag::getId).collect(Collectors.toSet());
    }
}