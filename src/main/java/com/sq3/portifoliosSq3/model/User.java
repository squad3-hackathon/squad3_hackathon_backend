package com.sq3.portifoliosSq3.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String lastname;

    @NotNull
    @NotBlank
    @Column(unique = true)
    private String email;

    @NotNull
    @NotBlank
    private String password;


    public User(){

    }

    public User(String name, String lastname, String email, String password){
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname(){
        return lastname;
    }

    public void setLastname(){
        this.lastname = lastname;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(){
        this.email = email;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(){
        this.password = password;
    }
}
