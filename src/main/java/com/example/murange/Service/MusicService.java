package com.example.murange.Service;

import com.example.murange.Domain.Like;
import com.example.murange.Domain.Music;
import com.example.murange.Domain.User;
import com.example.murange.Dto.LikeDto;
import com.example.murange.Repository.MusicRepository;
import com.example.murange.Repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MusicService {

    private final MusicRepository musicRepository;

    // music-id 리스트로 music 조회
    public List<Music> getMusicByMusicId (List<Long> musicIdList) {
        List<Music> musicList = musicIdList.stream().map( x -> musicRepository.getReferenceById(x) ).collect(Collectors.toList());
        return musicList;
    }

    // 인기 music 조회
    public List<Music> getMusicByStreamingCnt () {
        List<Music> musicList = musicRepository.findAllByOrderByCntDesc();
        return musicList;
    }

    // ----- querydsl ------
    // 감정별 music 조회
    public List<Music> getMusicByEmotion (Long emotionId) {
        List<Music> musicList = null;
        return musicList;
    }

    // 감정 분석 페이지 - 표정 분석 후 음악 조회
    public List<Music> getMusicByDetection (String mainEmotion, String subEmotion) {
        List<Music> musicList = null;
        return musicList;
    }


}
