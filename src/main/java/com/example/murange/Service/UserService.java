package com.example.murange.Service;

import com.example.murange.Domain.User;
import com.example.murange.Dto.UserJoinRequest;
import com.example.murange.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // 회원 가입
    public User createUser(UserJoinRequest userJoinRequest) {
        User user = userRepository.save(
                User.builder()
                        .username(userJoinRequest.getName())
                        .role(userJoinRequest.getUserRole().toString())
                        .password(bCryptPasswordEncoder.encode(userJoinRequest.getPassWord())).build());
        return user;
    }

    // 계정 조회
    @Transactional(readOnly = true)
    public User findUser(Long userId) {
        validateUser(userId);
        User user = userRepository.getById(userId);
        return user;
    }

    // 회원이 있는 지 검증
    public void validateUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
    }
}
