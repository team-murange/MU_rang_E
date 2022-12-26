package com.example.murange.Service;

import com.example.murange.Domain.EmotionCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ColorService {

    public String getColorCodeByTwoEmotion (EmotionCategory mainEmotion, EmotionCategory subEmotion) {
        return EmotionCategory.calcColorCodeByTwoEmotion(mainEmotion,subEmotion);
    }
}
