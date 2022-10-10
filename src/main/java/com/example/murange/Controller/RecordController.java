package com.example.murange.Controller;

import com.example.murange.Dto.RecordResponseDto;
import com.example.murange.Service.RecordService;
import io.swagger.annotations.ApiOperation;
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
public class RecordController {

    private final RecordService recordService;

    @ApiOperation(value = "표정 분석 후 주/부감정 저장", notes = "표정 분석 page - 표정 분석 후 주/부감정 저장")
    @GetMapping("/figure/{user-id}/{main-emotion}/{sub-emotion}")
    @ResponseBody
    public ResponseEntity saveEmotion(
            @PathVariable(value = "user-id") String userId,
            @PathVariable(value = "main-emotion") String mainEmotion,
            @PathVariable(value = "sub-emotion") String subEmotion
    ) {

        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "감정 달력 조회", notes = "프로필 page - 유저의 감정 기록 달력 조회")
    @GetMapping("/calendar/{user-id}")
    @ResponseBody
    public ResponseEntity<List<RecordResponseDto>> getCalendar(
            @PathVariable(value = "user-id") String userId
    ) {
        List<RecordResponseDto> result = recordService.getRecordByUserId(userId);
        return new ResponseEntity(result, HttpStatus.OK);
    }
}
