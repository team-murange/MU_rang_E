package com.example.murange.Domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="user_id")
    private Long id;

    private String username;

    private String picture;

    private String email;

    private String role;

    @OneToMany(mappedBy = "user")
    private List<Like> like = new ArrayList<Like>();

    @OneToMany(mappedBy = "user")
    private List<Record> record = new ArrayList<Record>();

    @Builder
    public User(Long id, String username, String picture, String email, String role) {
        this.id = id;
        this.username = username;
        this.picture = picture;
        this.email = email;
        this.role = role;
    }
}