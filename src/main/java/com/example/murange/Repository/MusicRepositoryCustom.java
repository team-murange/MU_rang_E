package com.example.murange.Repository;

import com.example.murange.Domain.Emotion;
import com.example.murange.Domain.Music;
import com.example.murange.Dto.LikeMusicResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MusicRepositoryCustom {

    // 메인페이지 감정별 음악 조회
    Page<Music> getMusicByEmotionType(String emotion, Pageable pageable) throws NoSuchFieldException;

    // 주,부감정 결과로 음악 조회
    Page<Music> getMusicByTwoEmotion(String mainEmotion, String secondEmotion, Pageable pageable) throws NoSuchFieldException;

    // 유저가 좋아요한 음악 조회
    Page<LikeMusicResponseDto> getMusicByUserLike(Long userId, Pageable pageable);

    // 음악의 감정 조회
    Emotion getFigureByMusic(Long musicId);

}
