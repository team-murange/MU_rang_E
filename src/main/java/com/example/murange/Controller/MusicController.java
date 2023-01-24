package com.example.murange.Controller;

import com.example.murange.Domain.Music;
import com.example.murange.Dto.LikeMusicResponseDto;
import com.example.murange.Dto.MusicResponseDto;
import com.example.murange.Service.MusicService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="*")
@RestController
@RequiredArgsConstructor
public class MusicController {

    private final MusicService musicService;

    @ApiOperation(value = "랜덤 감정명 조회", notes = "메인 page - 감정별 음악 조회시 감정명")
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
            @PathVariable(value = "emotion") String emotion,
            @PageableDefault(size = 5) Pageable pageable

    ) throws NoSuchFieldException {
        if (emotion.equals("none")) emotion = "neutral";

        Page<MusicResponseDto> musicDtoList = musicService.getMusicByEmotion(emotion, pageable);
        return new ResponseEntity(musicDtoList, HttpStatus.OK);
    }

    @ApiOperation(value = "표정 분석 후 음악 조회", notes = "표정 분석 page - 해당 주/부감정에 맞는 음악 조회")
    @GetMapping("/music/{main-emotion}/{sub-emotion}")
    @ResponseBody
    public ResponseEntity<List<Music>> getMusicByEmotion(
            @PathVariable(value = "main-emotion") String mainEmotion,
            @PathVariable(value = "sub-emotion") String subEmotion,
            @PageableDefault(size = 10) Pageable pageable

    ) throws NoSuchFieldException {
        if (mainEmotion.equals("none")) mainEmotion = "neutral";
        if (subEmotion.equals("none")) subEmotion = "neutral";

        Page<MusicResponseDto> musicDtoList = musicService.getMusicByDetection(mainEmotion, subEmotion, pageable);
        return new ResponseEntity(musicDtoList, HttpStatus.OK);
    }

    @ApiOperation(value = "좋아요한 음악 조회", notes = "프로필 page - 유저가 좋아요한 음악 조회")
    @GetMapping("/like/{user-id}")
    @ResponseBody
    public ResponseEntity<Page<MusicResponseDto>> getLikeMusic(
            @PathVariable(value = "user-id") Long userId,
            @PageableDefault(size = 5) Pageable pageable
    ) {
        Page<LikeMusicResponseDto> likeMusicDtoList = musicService.getMusicByUserLike(userId, pageable);
        return new ResponseEntity(likeMusicDtoList, HttpStatus.OK);
    }
}
