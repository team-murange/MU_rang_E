package com.example.murange.Repository;

import com.example.murange.Domain.*;
import com.example.murange.Domain.EmotionCategory;
import com.example.murange.Dto.LikeMusicResponseDto;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
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

    public OrderSpecifier<?> itemSort(EmotionCategory emotionCategory) {
        switch (emotionCategory) {
            case disgusted:
                return QEmotion.emotion.disgusted.desc();
            case happy:
                return QEmotion.emotion.happy.desc();
            case sad:
                return QEmotion.emotion.sad.desc();
            case surprised:
                return QEmotion.emotion.surprised.desc();
            case fearful:
                return QEmotion.emotion.fearful.desc();
            case neutral:
                return QEmotion.emotion.neutral.desc();
            case angry:
                return QEmotion.emotion.angry.desc();
            default:
                throw new IllegalArgumentException("존재하지 않는 감정명입니다.");
        }

    }

    @Override
    public Page<Music> getMusicByEmotionType(EmotionCategory emotion, Pageable pageable) {

        List<Music> result =  queryFactory
                .select(music)
                .from(music)
                .leftJoin(QEmotion.emotion).on(music.emotion.id.eq(QEmotion.emotion.id))
                .orderBy(itemSort(emotion))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(result, pageable, result.size());
    }

    @Override
    public Page<Music> getMusicByTwoEmotion(String mainEmotion, String subEmotion, Pageable pageable) {

        EmotionCategory main = EmotionCategory.valueOf(mainEmotion);
        EmotionCategory sub = EmotionCategory.valueOf(subEmotion);

        List<Music> result =  queryFactory
                .select(music)
                .from(music)
                .leftJoin(QEmotion.emotion).on(music.emotion.id.eq(QEmotion.emotion.id))
                .orderBy(itemSort(main), itemSort(sub))
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
