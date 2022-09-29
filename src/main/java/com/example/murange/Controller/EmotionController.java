package com.example.murange.Controller;

import com.example.murange.Domain.Emotion;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class EmotionController {

    // 감정 분석 페이지 - 이 음악의 2가지 감정 수치 update
    @GetMapping("/result/{user-id}/{music-id}")
    @ResponseBody
    public ResponseEntity updateLike(
            @PathVariable(value = "user-id") Long userId,
            @PathVariable(value = "music-id") Long musicId
    ) {

        return new ResponseEntity(HttpStatus.OK);
    }

}
