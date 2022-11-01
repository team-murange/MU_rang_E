package com.example.murange.Repository;

import com.example.murange.Domain.QRecord;
import com.example.murange.Domain.QUser;
import com.example.murange.Domain.Record;
import com.example.murange.Dto.RecordResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

import static com.example.murange.Domain.QRecord.record;
import static com.example.murange.Domain.QUser.user;

public class RecordRepositoryImpl implements RecordRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public RecordRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<RecordResponseDto> getRecordByUserId(String UserId) {

        // 이번 년도 가져오기!
        LocalDate start = LocalDate.of(2022, 1,1);
        LocalDate end   = YearMonth.now().atEndOfMonth();

        return queryFactory
                .select(Projections.bean(RecordResponseDto.class,
                        record.colorCode,
                        record.date))
                .from(record)
                .join(record.user, user)
                .where(user.id.eq(UserId))
                .where(record.date.between(start,end))
                .fetch();
    }
}
