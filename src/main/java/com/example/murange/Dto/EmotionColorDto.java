package com.example.murange.Dto;

import com.example.murange.Domain.EmotionCategory;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class EmotionColorDto {
    private String colorCode;
    private EmotionCategory mainEmotion;
    private EmotionCategory subEmotion;

    @Builder
    public EmotionColorDto(String colorCode, EmotionCategory mainEmotion, EmotionCategory subEmotion) {
        this.colorCode = colorCode;
        this.mainEmotion = mainEmotion;
        this.subEmotion = subEmotion;
    }
}
