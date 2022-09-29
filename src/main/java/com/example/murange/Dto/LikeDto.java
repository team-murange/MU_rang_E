package com.example.murange.Dto;


import com.example.murange.Domain.Like;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Data
public class LikeDto {

    private Long id;
    private String userId;
    private Long musicId;


    public LikeDto(Like like) {
        this.id = like.getId();
        this.userId = like.getUser().getId();
        this.musicId = like.getMusic().getId();
    }
}