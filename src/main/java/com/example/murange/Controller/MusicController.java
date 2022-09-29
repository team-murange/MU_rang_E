package com.example.murange.Controller;

import com.example.murange.Domain.Music;
import com.example.murange.Service.MusicService;
import com.example.murange.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MusicController {

    private final MusicService musicService;


    // 메인 페이지 - 검색
    @GetMapping("/search")
    @ResponseBody
    public ResponseEntity searchMusicSinger() {

        return new ResponseEntity(HttpStatus.OK);
    }

    // 인기 music 조회
    @GetMapping("/top")
    @ResponseBody
    public ResponseEntity<List<Music>> getTopMusic() {
        List<Music> musicList= musicService.getMusicByStreamingCnt();
        return new ResponseEntity(musicList, HttpStatus.OK);
    }

    // 감정별 music 조회
    @GetMapping("/random")
    @ResponseBody
    public ResponseEntity<List<Music>> getRandomMusic() {
        List<Music> musicList= null;
        return new ResponseEntity(musicList, HttpStatus.OK);
    }

    // 감정 분석 페이지 - 표정 분석 후 음악 조회
    @GetMapping("/music/{main-emotion}/{sub-emotion}")
    @ResponseBody
    public ResponseEntity<List<Music>> getMusicByEmotion(
            @PathVariable(value = "main-emotion") String mainEmotion,
            @PathVariable(value = "sub-emotion") String subEmotion
    ) {
        List<Music> musicList= null;
        return new ResponseEntity(musicList, HttpStatus.OK);
    }

}
