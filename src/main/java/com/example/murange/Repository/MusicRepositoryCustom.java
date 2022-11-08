package com.example.murange.Repository;

import com.example.murange.Domain.Figure;
import com.example.murange.Domain.Music;
import com.example.murange.Domain.EmotionType;

import java.util.List;

public interface MusicRepositoryCustom {

    // 메인페이지 감정별 음악 조회
    List<Music> getMusicByEmotionType(EmotionType emotion);

    // 주,부감정 결과로 음악 조회
    List<Music> getMusicByTwoEmotion(String mainEmotion, String secondEmotion);

    // 유저가 좋아요한 음악 조회
    List<Music> getMusicByUserLike(String userId);

    // 음악의 감정 조회
    Figure getFigureByMusic(Long musicId);

}
