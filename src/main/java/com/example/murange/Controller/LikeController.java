package com.example.murange.Controller;

import com.example.murange.Domain.Music;
import com.example.murange.Dto.LikeDto;
import com.example.murange.Service.LikeService;
import com.example.murange.Service.MusicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    // 감정 분석 페이지
    // 노래 좋아요 저장
    @GetMapping("/like/{user-id}/{music-id}")
    @ResponseBody
    public ResponseEntity getCalendar(
            @PathVariable(value = "user-id") String userId,
            @PathVariable(value = "music-id") Long musicId
            ) {
        likeService.createLike(userId, musicId);

        return new ResponseEntity(HttpStatus.OK);
    }

}
