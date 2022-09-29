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
public class LikeController {

    // 감정 분석 페이지
    @GetMapping("/like/{user-id}/{music-id}")
    @ResponseBody
    public ResponseEntity getCalendar(
            @PathVariable(value = "user-id") Long userId,
            @PathVariable(value = "music-id") Long musicId
            ) {

        return new ResponseEntity(HttpStatus.OK);
    }
}
