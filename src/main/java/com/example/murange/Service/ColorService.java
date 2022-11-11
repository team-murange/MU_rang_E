package com.example.murange.Service;

import com.example.murange.Domain.EmotionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ColorService {

    // 두 가지 감정(String)의 각각의 컬러코드 얻어내고, 최종으로 하나의 컬러코드(String) 도출
    public String getColorCodeByTwoEmotion (EmotionType mainEmotion, EmotionType subEmotion) {
        int firstColorCode = getColorCodeByEmotion(mainEmotion);
        int secondColorCode = getColorCodeByEmotion(subEmotion);
        return calcColorCode(firstColorCode,secondColorCode);
    }

    public int getColorCodeByEmotion (EmotionType emotion) {

        int colorCode = 0;

        // 감정별 컬러코드
        switch (emotion) {
            case happy :
                colorCode = 0xFFEF95; // 노랑
                break;
            case surprised:
                colorCode = 0xFF8850; // 주황
                break;
            case neutral :
                colorCode = 0xD2D1C9; // 회색
                break;
            case sad :
                colorCode = 0x7A8BBE; // 블루
                break;
            case fearful :
                colorCode = 0xDBBCFF; // 보라
                break;
            case angry :
                colorCode = 0xFF6F6F; // 빨강
                break;
            case disgust :
                colorCode = 0x9092A7; // 남색
                break;
            default :
                colorCode = 0xf8f8d9; // 미색
                break;
        }
        return colorCode;
    }

    //  주감정, 주감정에 의해 최종 컬러코드 계산하기
    public String calcColorCode(int firstColorCode, int secondColorCode) {

        int resultColor = (firstColorCode + secondColorCode)/2;
        String result = Integer.toHexString(resultColor);

        return result;
    }
}
