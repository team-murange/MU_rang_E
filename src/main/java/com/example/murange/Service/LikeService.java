package com.example.murange.Service;


import com.example.murange.Domain.Like;
import com.example.murange.Domain.Music;
import com.example.murange.Domain.User;
import com.example.murange.Dto.LikeDto;
import com.example.murange.Repository.LikeRepository;
import com.example.murange.Repository.MusicRepository;
import com.example.murange.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final MusicRepository musicRepository;

    // 노래 좋아요 저장
    public void createLike (String user_id, Long music_id) {
        User user = userRepository.getReferenceById(user_id);
        Music music = musicRepository.getReferenceById(music_id);
        Like like = Like.builder().user(user).music(music).build();
        likeRepository.save(like);
    }

    // 좋아요한 music-id 조회
    public List<Long> getLike (String user_id) {
        User user = userRepository.getById(user_id);
        List<Like> likesList = likeRepository.findAllByUser(user);

        List<Long> musicIdList = likesList.stream().map(x -> x.getMusic().getId()).collect(Collectors.toList());

        return musicIdList;
    }


}
