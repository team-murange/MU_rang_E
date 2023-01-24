package com.example.murange.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RecordRequestDto {

    private Long userId;

    private String mainEmotion;

    private String subEmotion;
}
