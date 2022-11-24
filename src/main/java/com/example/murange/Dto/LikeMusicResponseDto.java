package com.example.murange.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LikeMusicResponseDto {

    private Long likeId;

    private Long musicId;

    private String title;

    private String music_url;

    private String img_url;

    private String soundcloud_url;
}