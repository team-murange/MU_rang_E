package com.example.murange.Controller;

import com.example.murange.Domain.EmotionCategory;
import com.example.murange.Dto.EmotionColorDto;
import com.example.murange.Dto.RecordResponseDto;
import com.example.murange.Service.RecordService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="*")
@RestController
@RequiredArgsConstructor
public class RecordController {

    private final RecordService recordService;

    @ApiOperation(value = "표정 분석 후 주/부감정 저장", notes = "표정 분석 page - 표정 분석 후 주/부감정 (컬러코드로) 저장")
    @GetMapping("/figure/{user-id}/{main-emotion}/{sub-emotion}")
    @ResponseBody
    public ResponseEntity saveEmotion(
            @PathVariable(value = "user-id") Long userId,
            @PathVariable(value = "main-emotion") String mainEmotion,
            @PathVariable(value = "sub-emotion") String subEmotion
    ) {

        if (mainEmotion.equals("none")) mainEmotion = "neutral";
        if (subEmotion.equals("none")) subEmotion = "neutral";

        EmotionCategory main = EmotionCategory.valueOf(mainEmotion);
        EmotionCategory sub = EmotionCategory.valueOf(subEmotion);

        EmotionColorDto emotionColorDto = EmotionColorDto.builder().mainEmotion(main).subEmotion(sub).build();
        recordService.saveEmotion(userId, emotionColorDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "감정 달력 조회", notes = "프로필 page - 유저의 감정 기록 달력 조회")
    @GetMapping("/calendar/{user-id}")
    @ResponseBody
    public ResponseEntity<List<RecordResponseDto>> getCalendar(
            @PathVariable(value = "user-id") Long userId
    ) {
        List<RecordResponseDto> result = recordService.getRecordByUserId(userId);
        return new ResponseEntity(result, HttpStatus.OK);
    }
}
