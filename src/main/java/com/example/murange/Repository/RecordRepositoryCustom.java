package com.example.murange.Repository;

import com.example.murange.Dto.RecordResponseDto;

import java.util.List;

public interface RecordRepositoryCustom {
    List<RecordResponseDto> getRecordByUserId(Long UserId);
}
