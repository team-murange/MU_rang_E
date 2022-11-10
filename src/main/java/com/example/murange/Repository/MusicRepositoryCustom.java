package com.example.murange.Repository;

import com.example.murange.Domain.Figure;
import com.example.murange.Domain.Music;
import com.example.murange.Domain.EmotionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MusicRepositoryCustom {

    // 메인페이지 감정별 음악 조회
    Page<Music> getMusicByEmotionType(EmotionType emotion, Pageable pageable);

    // 주,부감정 결과로 음악 조회
    Page<Music> getMusicByTwoEmotion(String mainEmotion, String secondEmotion, Pageable pageable);

    // 유저가 좋아요한 음악 조회
    Page<Music> getMusicByUserLike(Long userId, Pageable pageable);

    // 음악의 감정 조회
    Figure getFigureByMusic(Long musicId);

}
