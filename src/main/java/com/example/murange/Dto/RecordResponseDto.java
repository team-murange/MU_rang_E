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

    private String parsedDate;
    private String colorCode;

    public RecordResponseDto(Record record) {
        this.parsedDate = record.getDate().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        this.colorCode = record.getColorCode();
    }
}
