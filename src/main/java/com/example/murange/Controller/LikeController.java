package com.example.murange.Controller;

import com.example.murange.Domain.Music;
import com.example.murange.Dto.LikeDto;
import com.example.murange.Service.LikeService;
import com.example.murange.Service.MusicService;
import com.example.murange.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;
    private final MusicService musicService;


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

    // 유저가 좋아요한 노래 조회
    @GetMapping("/like/{user-id}")
    @ResponseBody
    public ResponseEntity<List<Music>> getLikeMusic(
            @PathVariable(value = "user-id") String userId
    ) {
        List<Long> musicIdList = likeService.getLike(userId);
        List<Music> musicList = musicService.getMusicByMusicId(musicIdList);

        return new ResponseEntity(musicList, HttpStatus.OK);
    }
}
