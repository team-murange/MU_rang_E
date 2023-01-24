package com.example.murange.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LikeRequestDto {

    private Long userId;

    private Long musicId;

    private String emotion;
}