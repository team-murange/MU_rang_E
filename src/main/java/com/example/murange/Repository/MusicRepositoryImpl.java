package com.example.murange.Repository;

import com.example.murange.Domain.*;
import com.example.murange.Domain.EmotionType;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.example.murange.Domain.QFigure.*;
import static com.example.murange.Domain.QLike.*;
import static com.example.murange.Domain.QMusic.music;

public class MusicRepositoryImpl implements MusicRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public MusicRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public OrderSpecifier<?> itemSort(EmotionType emotion) {
        switch (emotion) {
            case disgust:
                return figure.disgusted.desc();
            case happy:
                return figure.happy.desc();
            case sad:
                return figure.sad.desc();
            case surprised:
                return figure.surprised.desc();
            case fearful:
                return figure.fearful.desc();
            case neutral:
                return figure.neutral.desc();
            case angry:
                return figure.angry.desc();
            default:
                throw new IllegalArgumentException("존재하지 않는 감정명입니다.");
        }

    }

    @Override
    public Page<Music> getMusicByEmotionType(EmotionType emotion, Pageable pageable) {

        List<Music> result =  queryFactory
                .select(music)
                .from(music)
                .leftJoin(figure).on(music.figure.id.eq(figure.id))
                .orderBy(itemSort(emotion))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(result, pageable, result.size());
    }

    @Override
    public Page<Music> getMusicByTwoEmotion(String mainEmotion, String subEmotion, Pageable pageable) {

        EmotionType main = EmotionType.valueOf(mainEmotion);
        EmotionType sub = EmotionType.valueOf(subEmotion);

        List<Music> result =  queryFactory
                .select(music)
                .from(music)
                .leftJoin(figure).on(music.figure.id.eq(figure.id))
                .orderBy(itemSort(main), itemSort(sub))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(result, pageable, result.size());
    }

    // 유저가 좋아요한 음악 조회
    @Override
    public Page<Music> getMusicByUserLike(Long userId, Pageable pageable) {
        List<Music> result = queryFactory
                .select(music)
                .from(music)
                .join(music.like, like)
                .where(like.user.id.eq(userId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return new PageImpl<>(result, pageable, result.size());
    }

    @Override
    public Figure getFigureByMusic(Long musicId) {
        return queryFactory
                .select(figure)
                .from(figure)
                .join(music.figure, figure)
                .where(figure.music.id.eq(musicId))
                .fetchOne();
    }
}
