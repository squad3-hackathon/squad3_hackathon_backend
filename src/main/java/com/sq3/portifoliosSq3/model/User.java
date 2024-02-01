package com.sq3.portifoliosSq3.model;

import com.sq3.portifoliosSq3.model.DTO.UserDTO;
import com.sq3.portifoliosSq3.model.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

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
    private String lastName;
    @NotNull
    @NotBlank
    @Column(unique = true)
    private String email;
    @NotNull
    @NotBlank
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=]).{8,}$", message = "A senha deve conter pelo menos 8 caracteres, 1 letra, 1 número e 1 caractere especial")
    private String password;
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private Set<Role> roles = new HashSet<>();

    public User(){

    }

    public User(String name, String lastName, String email, String password){
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.setRoles(Set.of(Role.USER));
    }

    public User (UserDTO userDTO){
        this.name = userDTO.name();
        this.lastName = userDTO.lastName();
        this.email = userDTO.email();
        this.password = userDTO.password();
        this.setRoles(Set.of(Role.USER));
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
        return lastName;
    }

    public void setLastname(){
        this.lastName = lastName;
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

    public Set<Role> getRoles(){ return roles; }

    public void setRoles(Set<Role> roles) { this.roles = roles;}
}
