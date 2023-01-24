package com.example.murange.Controller;

import com.example.murange.Dto.RecordRequestDto;
import com.example.murange.Dto.RecordResponseDto;
import com.example.murange.Service.RecordService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="*")
@RestController
@RequiredArgsConstructor
public class RecordController {

    private final RecordService recordService;

    @ApiOperation(value = "표정 분석 후 주/부감정 저장", notes = "표정 분석 page - 표정 분석 후 주/부감정 (컬러코드로) 저장")
    @PostMapping("/figure")
    @ResponseBody
    public ResponseEntity saveEmotion(
            @RequestBody @Validated RecordRequestDto recordRequestDto
    ) {
        Long userId = recordRequestDto.getUserId();
        String mainEmotion = recordRequestDto.getMainEmotion();
        String subEmotion = recordRequestDto.getSubEmotion();

        recordService.saveEmotion(userId, mainEmotion, subEmotion);
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
