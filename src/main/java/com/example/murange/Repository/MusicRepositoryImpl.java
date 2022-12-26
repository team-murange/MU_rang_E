package com.example.murange.Repository;

import com.example.murange.Domain.*;
import com.example.murange.Dto.LikeMusicResponseDto;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
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

    public OrderSpecifier<?> getOrderSpecifierByEmotion(String fieldStr) {
        Path<Object> fieldPath = Expressions.path(Emotion.class, fieldStr);
        return new OrderSpecifier(Order.DESC, fieldPath) ;
    }

    @Override
    public Page<Music> getMusicByEmotionType(String fieldStr, Pageable pageable) {

        List<Music> result =  queryFactory
                .select(music)
                .from(music)
                .leftJoin(QEmotion.emotion).on(music.emotion.id.eq(QEmotion.emotion.id))
                .orderBy(getOrderSpecifierByEmotion(fieldStr))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(result, pageable, result.size());
    }


    @Override
    public Page<Music> getMusicByTwoEmotion(String mainEmotion, String subEmotion, Pageable pageable) {

        List<Music> result =  queryFactory
                .select(music)
                .from(music)
                .leftJoin(QEmotion.emotion).on(music.emotion.id.eq(QEmotion.emotion.id))
                .orderBy(getOrderSpecifierByEmotion(mainEmotion), getOrderSpecifierByEmotion(subEmotion))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(result, pageable, result.size());
    }

    // 유저가 좋아요한 음악 조회
    @Override
    public Page<LikeMusicResponseDto> getMusicByUserLike(Long userId, Pageable pageable) {
        List<LikeMusicResponseDto> result = queryFactory
                .select(Projections.constructor(LikeMusicResponseDto.class,
                        like.id.as("likeId"),
                        music.id.as("musicId"),
                        music.title,
                        music.music_url,
                        music.img_url,
                        music.soundcloud_url
                ))
                .from(music)
                .join(music.like, like)
                .where(like.user.id.eq(userId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return new PageImpl<>(result, pageable, result.size());
    }

    @Override
    public Emotion getFigureByMusic(Long musicId) {
        return queryFactory
                .select(QEmotion.emotion)
                .from(QEmotion.emotion)
                .join(music.emotion, QEmotion.emotion)
                .where(QEmotion.emotion.music.id.eq(musicId))
                .fetchOne();
    }
}
