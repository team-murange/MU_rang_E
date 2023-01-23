package com.example.murange.Config.oauth;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class SessionUser implements Serializable {

    private String name;
    private String email;
    private String picture;
    private Long id;

    public SessionUser(String name, String email, String picture, Long id) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.id = id;
    }
}