package com.example.murange.Repository;

import com.example.murange.Domain.*;
import com.example.murange.Repository.UserRepository;
import com.example.murange.Service.LikeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
@RunWith(SpringRunner.class)
@Commit // 확인용!, 없으면 자동 rollback 되어서 디비에서 확인 못함!
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MusicRepository musicRepository;

    @Autowired
    LikeRepository likeRepository;

    @Autowired
    LikeService likeService;

    @Test
    public void getStub() {
        User user = User.builder().id("9999").name("mem1").build();
        userRepository.save(user);
    }

}