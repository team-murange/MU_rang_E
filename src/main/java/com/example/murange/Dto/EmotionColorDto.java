package com.example.murange.Dto;

import com.example.murange.Domain.EmotionType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class EmotionColorDto {
    private String colorCode;
    private EmotionType mainEmotion;
    private EmotionType subEmotion;

    @Builder
    public EmotionColorDto(String colorCode, EmotionType mainEmotion, EmotionType subEmotion) {
        this.colorCode = colorCode;
        this.mainEmotion = mainEmotion;
        this.subEmotion = subEmotion;
    }
}
