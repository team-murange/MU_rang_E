package com.example.murange.Dto;

import com.example.murange.Domain.Record;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@Setter
@Getter
@Data
public class RecordResponseDto {

    private LocalDate date;
    private String colorCode;

    public RecordResponseDto(Record record) {
        this.date = record.getDate();
        this.colorCode = record.getColorCode();
    }
}
