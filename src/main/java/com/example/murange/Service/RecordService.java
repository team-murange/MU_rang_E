package com.example.murange.Service;

import com.example.murange.Domain.Record;
import com.example.murange.Domain.User;
import com.example.murange.Dto.EmotionColorDto;
import com.example.murange.Dto.RecordResponseDto;
import com.example.murange.Repository.RecordRepository;
import com.example.murange.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RecordService {

    private final RecordRepository recordRepository;
    private final UserRepository userRepository;
    private final ColorService colorService;

    // 유저의 이번달 감정 기록 다 가져오기
    // R 유저 조회
    @Transactional(readOnly = true)
    public List<RecordResponseDto> getRecordByUserId(String userId) {
        return recordRepository.getRecordByUserId(userId);
    }

    // 표정 분석 후 해당 컬러코드 저장
    public void saveEmotion (String userId, EmotionColorDto emotionColorDto) {

        LocalDate today = LocalDate.now();

        User user = userRepository.getReferenceById(userId);
        String colorCode = colorService.getColorCodeByTwoEmotion(emotionColorDto.getMainEmotion(), emotionColorDto.getSubEmotion());
        Record record = Record.builder()
                .user(user)
                .mainEmotion(emotionColorDto.getMainEmotion())
                .subEmotion(emotionColorDto.getSubEmotion())
                .colorCode(colorCode)
                .date(today)
                .build();
        recordRepository.save(record);
    }
}
