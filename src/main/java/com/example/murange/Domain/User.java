package com.example.murange.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @Column(name="user_id")
    private String id;

    @Column(nullable = false)
    private String name;

    private String img_url;

    @Column(nullable = false)
    private String email;

    @OneToMany(mappedBy = "user")
    private List<Like> like = new ArrayList<Like>();

    @OneToMany(mappedBy = "user")
    private List<Record> record = new ArrayList<Record>();


    @Builder
    public User(String id, String name, String img_url, String email) {
        this.id = id;
        this.name = name;
        this.img_url = img_url;
        this.email = email;
    }
}
