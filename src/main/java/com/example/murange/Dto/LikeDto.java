package com.example.murange.Dto;


import com.example.murange.Domain.Like;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class LikeDto {

    private Long id;
    private Long userId;
    private Long musicId;


    public LikeDto(Like like) {
        this.id = like.getId();
        this.userId = like.getUser().getId();
        this.musicId = like.getMusic().getId();
    }
}