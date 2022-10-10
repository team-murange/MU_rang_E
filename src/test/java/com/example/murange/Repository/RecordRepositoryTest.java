package com.example.murange.Repository;

import com.example.murange.Domain.EmotionType;
import com.example.murange.Domain.Record;
import com.example.murange.Domain.User;
import com.example.murange.Dto.RecordResponseDto;
import com.example.murange.Service.ColorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

import static com.example.murange.Domain.QRecord.record;
import static com.example.murange.Domain.QUser.user;

@SpringBootTest
@Transactional
@RunWith(SpringRunner.class)
// @Commit // 확인용!, 없으면 자동 rollback 되어서 디비에서 확인 못함!
public class RecordRepositoryTest {

    @Autowired
    RecordRepository recordRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ColorService colorService;


    // 회원의 이번 달 모든 감정
    @Test
    public void get() {
        User userA = User.builder().id("1111").name("userA").build();
        userRepository.save(userA);

        String colorCode = colorService.getFinalColorCodeByTwoEmotion(EmotionType.randomEmotionType(), EmotionType.randomEmotionType());

        Record record1 = Record.builder().user(userA).colorCode(colorCode).date(LocalDate.now()).build();
        Record record2 = Record.builder().user(userA).colorCode(colorCode).date(LocalDate.now()).build();
        Record record3 = Record.builder().user(userA).colorCode(colorCode).date(LocalDate.now()).build();
        Record record4 = Record.builder().user(userA).colorCode(colorCode).date(LocalDate.now()).build();

        recordRepository.save(record1);
        recordRepository.save(record2);
        recordRepository.save(record3);
        recordRepository.save(record4);

        List<RecordResponseDto> recordList = recordRepository.getRecordByUserId(userA.getId());


//        for (RecordResponseDto tmp : recordList ) {
//            System.out.println("record = " + tmp );
//        }
    }

}