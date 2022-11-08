package com.example.murange.Controller;

import com.example.murange.Domain.Music;
import com.example.murange.Domain.EmotionType;
import com.example.murange.Dto.MusicResponseDto;
import com.example.murange.Service.LikeService;
import com.example.murange.Service.MusicService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins="*")
@RestController
@RequiredArgsConstructor
public class MusicController {

    private final MusicService musicService;
    private final LikeService likeService;

    @ApiOperation(value = "음악 검색", notes = "메인 page - 음악 검색")
    @GetMapping("/search")
    @ResponseBody
    public ResponseEntity searchMusicSinger() {

        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "랜덤 감정명", notes = "메인 page - 감정별 음악 조회시 감정명")
    @GetMapping("/random/title")
    @ResponseBody
    public ResponseEntity<String> getRandomMusicTitle() {
        String result = musicService.getMusicByEmotionTitle();
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @ApiOperation(value = "감정별 음악 조회", notes = "메인 page - 감정별 음악 조회")
    @GetMapping("/random/{emotion}")
    @ResponseBody
    public ResponseEntity<List<Music>> getRandomMusic(
            @PathVariable(value = "emotion") String emotion
    ) {
        List<Music> musicList= musicService.getMusicByEmotion(emotion);
        List<MusicResponseDto> result = new ArrayList<>();
        for (Music music : musicList) {
            result.add(new MusicResponseDto(music));
        }
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @ApiOperation(value = "표정 분석 후 음악 조회", notes = "표정 분석 page - 해당 주/부감정에 맞는 음악 조회")
    @GetMapping("/music/{main-emotion}/{sub-emotion}")
    @ResponseBody
    public ResponseEntity<List<Music>> getMusicByEmotion(
            @PathVariable(value = "main-emotion") String mainEmotion,
            @PathVariable(value = "sub-emotion") String subEmotion
    ) {
        List<Music> musicList= musicService.getMusicByDetection(mainEmotion, subEmotion);
        List<MusicResponseDto> result = new ArrayList<>();
        for (Music music : musicList) {
            result.add(new MusicResponseDto(music));
        }
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @ApiOperation(value = "좋아요한 음악 조회", notes = "프로필 page - 유저가 좋아요한 음악 조회")
    @GetMapping("/like/{user-id}")
    @ResponseBody
    public ResponseEntity<List<Music>> getLikeMusic(
            @PathVariable(value = "user-id") String userId
    ) {
        List<Music> musicList = musicService.getMusicByUserLike(userId);
        List<MusicResponseDto> result = new ArrayList<>();
        for (Music music : musicList) {
            result.add(new MusicResponseDto(music));
        }
        return new ResponseEntity(result, HttpStatus.OK);
    }
}
