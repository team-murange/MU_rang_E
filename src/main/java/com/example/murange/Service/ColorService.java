package com.example.murange.Service;

import com.example.murange.Domain.EmotionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ColorService {

    public String getColorCodeByTwoEmotion (EmotionType mainEmotion, EmotionType subEmotion) {
        return EmotionType.calcColorCodeByTwoEmotion(mainEmotion,subEmotion);
    }
}
