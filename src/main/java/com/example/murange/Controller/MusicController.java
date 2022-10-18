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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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

    @ApiOperation(value = "인기 음악 조회", notes = "메인 page - 인기 음악 조회")
    @GetMapping("/top")
    @ResponseBody
    public ResponseEntity<List<Music>> getTopMusic() {
        List<Music> musicList= musicService.getMusicByStreamingCnt();
        List<MusicResponseDto> result = new ArrayList<>();
        for (Music music : musicList) {
            result.add(new MusicResponseDto(music));
        }
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @ApiOperation(value = "감정별 음악 조회", notes = "메인 page - 감정별 음악 조회")
    @GetMapping("/random")
    @ResponseBody
    public ResponseEntity<List<Music>> getRandomMusic() {
        // EmotionType 랜덤하게 바꾸기
        String emotionType = EmotionType.randomEmotionType();
        List<Music> musicList= musicService.getMusicByEmotion(emotionType);
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
