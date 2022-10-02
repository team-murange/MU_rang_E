package com.example.murange.Repository;

import com.example.murange.Domain.*;
import com.example.murange.Domain.EmotionType;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.List;

import static com.example.murange.Domain.QEmotion.emotion;
import static com.example.murange.Domain.QLike.*;
import static com.example.murange.Domain.QMusic.music;

public class MusicRepositoryImpl implements MusicRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public MusicRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }


    // + 동적 쿼리
    @Override
    public List<Music> getMusicByEmotion(EmotionType emotion) {

        return queryFactory
                .select(music)
                .from(music)
                .join(music.emotion, QEmotion.emotion)
                .orderBy(QEmotion.emotion.angry.desc())
                .fetch();
    }

    // + 동적 쿼리
    @Override
    public List<Music> getMusicByTwoEmotion(String mainEmotion, String secondEmotion) {
        return queryFactory
                .select(music)
                .from(music)
                .join(music.emotion, emotion)
                .orderBy(QEmotion.emotion.angry.desc(), emotion.disgust.desc())
                .fetch();
    }

    // 유저가 좋아요한 음악 조회
    @Override
    public List<Music> getMusicByUserLike(String userId) {
        return queryFactory
                .select(music)
                .from(music)
                .join(music.like, like)
                .where(like.user.id.eq(userId))
                .fetch();
    }
}
