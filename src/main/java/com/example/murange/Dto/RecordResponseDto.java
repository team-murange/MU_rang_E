package com.example.murange.Dto;

import com.example.murange.Domain.Record;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Setter
@Getter
@Data
public class RecordResponseDto {

    private LocalDate date;
    private String color;  // 컬러 코드

    public RecordResponseDto(Record record) {
        this.color = record.getEmotion();
        this.date = record.getDate();
    }
}
