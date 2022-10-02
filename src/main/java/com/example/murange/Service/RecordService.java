package com.example.murange.Service;

import com.example.murange.Dto.RecordResponseDto;
import com.example.murange.Repository.RecordRepository;
import com.example.murange.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RecordService {

    private final RecordRepository recordRepository;

    // 유저의 이번달 감정 기록 다 가져오기
    // R 유저 조회
    @Transactional(readOnly = true)
    public List<RecordResponseDto> getRecordByUserId(String userId) {
        return recordRepository.getRecordByUserId(userId);
    }


}
