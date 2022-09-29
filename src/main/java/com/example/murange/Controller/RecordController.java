package com.example.murange.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RecordController {

    // 감정 분석 페이지 - 감정 분석 후 주감정/부감정 저장
    @GetMapping("/figure/{user-id}/{main-emotion}/{sub-emotion}")
    @ResponseBody
    public ResponseEntity saveEmotion(
            @PathVariable(value = "user-id") Long userId,
            @PathVariable(value = "main-emotion") String mainEmotion,
            @PathVariable(value = "sub-emotion") String subEmotion
    ) {

        return new ResponseEntity(HttpStatus.OK);
    }

    // 프로필 페이지 - 감정 달력 조회
    @GetMapping("/calendar/{user-id}")
    @ResponseBody
    public ResponseEntity getCalendar() {

        return new ResponseEntity(HttpStatus.OK);
    }
}
