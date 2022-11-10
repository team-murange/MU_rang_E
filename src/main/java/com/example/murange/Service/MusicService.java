package com.example.murange.Service;

import com.example.murange.Domain.Figure;
import com.example.murange.Domain.Music;
import com.example.murange.Domain.EmotionType;
import com.example.murange.Dto.MusicResponseDto;
import com.example.murange.Repository.MusicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MusicService {

    private final MusicRepository musicRepository;

    // 랜덤 감정명
    public String getMusicByEmotionTitle () {
        String emotionType = EmotionType.randomEmotionType().toString();
        return emotionType;
    }

    // 감정별 music 조회
    public Page<MusicResponseDto> getMusicByEmotion (String emotion, Pageable pageable) {
        EmotionType emotionType = EmotionType.valueOf(emotion);
        Page<Music> musicList = musicRepository.getMusicByEmotionType(emotionType, pageable);
        return musicList.map(music -> {
            MusicResponseDto dto = new MusicResponseDto();
            return dto.toMusicResponseDto(music);
        });
    }

    // 감정 분석 페이지 - 표정 분석 후 음악 조회
    public Page<MusicResponseDto> getMusicByDetection (String mainEmotion, String subEmotion, Pageable pageable) {
        Page<Music> musicList = musicRepository.getMusicByTwoEmotion(mainEmotion, subEmotion, pageable);
        return musicList.map(music -> {
            MusicResponseDto dto = new MusicResponseDto();
            return dto.toMusicResponseDto(music);
        });
    }

    // 프로필 페이지 - 유저가 좋아요한 음악 조회
    public Page<MusicResponseDto> getMusicByUserLike (Long userId, Pageable pageable) {
        Page<Music> musicList = musicRepository.getMusicByUserLike(userId, pageable);
        return musicList.map(music -> {
            MusicResponseDto dto = new MusicResponseDto();
            return dto.toMusicResponseDto(music);
        });
    }

    // 감정 수치 업데이트 - 좋아요 시 수치 업데이트
    public void updateFigureOfMusic(Long musicId, String likeEmotion) {
        EmotionType emotionType = EmotionType.valueOf(likeEmotion);
        Figure figure = musicRepository.getFigureByMusic(musicId);
        figure.updateFigure(emotionType);
    }
}
