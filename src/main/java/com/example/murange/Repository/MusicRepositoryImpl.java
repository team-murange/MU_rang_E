package com.example.murange.Repository;

import com.example.murange.Domain.*;
import com.example.murange.Domain.EmotionType;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import static com.example.murange.Domain.QLike.*;
import static com.example.murange.Domain.QMusic.music;

public class MusicRepositoryImpl implements MusicRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public MusicRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<Music> getMusicByEmotionType(EmotionType emotion) {

        BooleanBuilder builder = getEmotion(emotion);
        return queryFactory
                .select(music)
                .from(music)
                .join(music.figure, QFigure.figure)
                .where(builder)
                .fetch();
    }

    @Override
    public List<Music> getMusicByTwoEmotion(String mainEmotion, String subEmotion) {

        EmotionType main = EmotionType.valueOf(mainEmotion);
        EmotionType sub = EmotionType.valueOf(subEmotion);
        BooleanBuilder builderMain = getEmotion(main);
        BooleanBuilder builderSub = getEmotion(sub);

        return queryFactory
                .select(music)
                .from(music)
                .join(music.figure, QFigure.figure)
                .where(builderMain, builderSub)
                .fetch();
    }

    // 유저가 좋아요한 음악 조회
    @Override
    public Page<Music> getMusicByUserLike(String userId, Pageable pageable) {
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
                .select(QFigure.figure)
                .from(QFigure.figure)
                .join(music.figure, QFigure.figure)
                .where(QFigure.figure.music.id.eq(musicId))
                .fetchOne();
    }

    public BooleanBuilder getEmotion(EmotionType emotion) {
        BooleanBuilder builder = new BooleanBuilder();
        switch (emotion) {
            case disgust:
                return builder.and((Predicate) QFigure.figure.disgust.desc());
            case happy:
                return builder.and((Predicate) QFigure.figure.happiness.desc());
            case sad:
                return builder.and((Predicate) QFigure.figure.sad.desc());
            case surprised:
                return builder.and((Predicate) QFigure.figure.surprised.desc());
            case fearful:
                return builder.and((Predicate) QFigure.figure.scared.desc());
            case neutral:
                return builder.and((Predicate) QFigure.figure.neutral.desc());
            case angry:
                return builder.and((Predicate) QFigure.figure.angry.desc());
            default:
                throw new IllegalArgumentException("존재하지 않는 감정명입니다.");
        }
    }
}
