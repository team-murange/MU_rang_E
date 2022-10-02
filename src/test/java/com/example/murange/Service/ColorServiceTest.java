package com.example.murange.Service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@RunWith(SpringRunner.class)
// @Commit // 확인용!, 없으면 자동 rollback 되어서 디비에서 확인 못함!
public class ColorServiceTest {

    @Autowired
    ColorService colorService;

    @Test
    public void calcColorCode() {
        int firstColor = colorService.getColorCodeByEmotion("sad");
        int secondColor = colorService.getColorCodeByEmotion("happiness");

        String resultColorCode = colorService.calcColorCode(firstColor, secondColor);

        System.out.println(firstColor);
        System.out.println(secondColor);
        System.out.println(resultColorCode);
    }


}