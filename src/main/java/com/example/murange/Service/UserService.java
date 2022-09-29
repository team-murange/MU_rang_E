package com.example.murange.Service;

import com.example.murange.Domain.User;
import com.example.murange.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 회원 가입
    public void joinUser(User user) {
        userRepository.save(user);
    }

    // 계정 조회
    @Transactional(readOnly = true)
    public User findUser(String userId) {
        validateUser(userId);
        User user = userRepository.getById(userId);
        return user;
    }

    // 회원이 있는 지 검증
    public void validateUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
    }
}
