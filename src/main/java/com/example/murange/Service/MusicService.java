package com.example.murange.Service;

import com.example.murange.Domain.Music;
import com.example.murange.Domain.EmotionType;
import com.example.murange.Repository.MusicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MusicService {

    private final MusicRepository musicRepository;

    // 인기 music 조회
    public List<Music> getMusicByStreamingCnt () {
        List<Music> musicList = null; // musicRepository.findAllOrderByStreamingCntDesc();
        return musicList;
    }

    // 감정별 music 조회
    public List<Music> getMusicByEmotion (EmotionType emotion) {
        List<Music> musicList = musicRepository.getMusicByEmotion(emotion);
        return musicList;
    }

    // 감정 분석 페이지 - 표정 분석 후 음악 조회
    public List<Music> getMusicByDetection (String mainEmotion, String subEmotion) {
        List<Music> musicList = musicRepository.getMusicByTwoEmotion(mainEmotion, subEmotion);
        return musicList;
    }

    // 프로필 페이지 - 유저가 좋아요한 음악 조회
    public List<Music> getMusicByUserLike (String userId) {
        List<Music> musicList = musicRepository.getMusicByUserLike(userId);
        return musicList;
    }


}
