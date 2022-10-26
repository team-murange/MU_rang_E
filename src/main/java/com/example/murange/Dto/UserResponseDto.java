package com.example.murange.Dto;

import com.example.murange.Domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponseDto {
    private String id;
    private String name;
    private String img_url;
    private String email;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.img_url = user.getImg_url();
        this.name = user.getName();
    }
}
