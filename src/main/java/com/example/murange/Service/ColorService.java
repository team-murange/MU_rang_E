package com.example.murange.Service;

import com.example.murange.Domain.Music;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ColorService {

    public int getColorCodeByEmotion (String emotion) {

        int colorCode = 0;

        // 감정별 컬러코드
        switch (emotion) {
            case "happiness" :
                colorCode = 0xFFEF95; // 노랑
                break;
            case "surprised" :
                colorCode = 0xFF8850; // 주황
                break;
            case "neutral" :
                colorCode = 0xD2D1C9; // 회색
                break;
            case "sad" :
                colorCode = 0x7A8BBE; // 블루
                break;
            case "scared" :
                colorCode = 0xDBBCFF; // 보라
                break;
            case "angry" :
                colorCode = 0xFF6F6F; // 빨강
                break;
            case "disgust" :
                colorCode = 0x9092A7; // 남색
                break;
            default:
                break;
        }
        return colorCode;
    }

    //  주감정, 주감정에 의해 최종 컬러코드 도출
    public String calcColorCode(int firstColorCode, int secondColorCode) {

        int resultColor = (firstColorCode + secondColorCode)/2;
        String result = Integer.toHexString(resultColor);


        return result;
    }


}
