package com.example.murange.Dto;

import com.example.murange.Domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponseDto {
    private Long id;
    private String name;
    private String picture;
    private String email;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.picture = user.getPicture();
        this.name = user.getUsername();
    }
}
