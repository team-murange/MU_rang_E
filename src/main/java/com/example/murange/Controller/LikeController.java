package com.example.murange.Controller;

import com.example.murange.Service.LikeService;
import com.example.murange.Service.MusicService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins="*")
@RestController
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;
    private final MusicService musicService;

    @GetMapping("/like/{user-id}/{music-id}/{emotion}")
    @ResponseBody
    @ApiOperation(value = "음악 좋아요 저장", notes = "감정 분석 page - 해당 음악 좋아요 저장")
    public ResponseEntity getCalendar(
            @PathVariable(value = "user-id") String userId,
            @PathVariable(value = "music-id") Long musicId,
            @PathVariable(value = "emotion") String emotion
            ) {
        likeService.createLike(userId, musicId);
        musicService.updateFigureOfMusic(musicId, emotion);

        return new ResponseEntity(HttpStatus.OK);
    }
}
