package com.example.murange.Service;


import com.example.murange.Domain.Like;
import com.example.murange.Domain.Music;
import com.example.murange.Domain.User;
import com.example.murange.Repository.LikeRepository;
import com.example.murange.Repository.MusicRepository;
import com.example.murange.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final MusicRepository musicRepository;

    // 노래 좋아요 저장
    public void createLike (Long user_id, Long music_id) {
        User user = userRepository.getReferenceById(user_id);
        Music music = musicRepository.getReferenceById(music_id);
        Like like = Like.builder().user(user).music(music).build();
        likeRepository.save(like);
    }

}
