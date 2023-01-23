package com.example.murange.Dto;

import com.example.murange.Domain.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserJoinRequest {

    private String name;

    private String email;

    private String passWord;

    private Role role;
}
