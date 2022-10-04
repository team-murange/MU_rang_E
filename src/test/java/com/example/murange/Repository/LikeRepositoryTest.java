package com.example.murange.Repository;

import com.example.murange.Domain.Like;
import com.example.murange.Domain.Music;
import com.example.murange.Domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@SpringBootTest
@Transactional
@RunWith(SpringRunner.class)
@Commit // 확인용!, 없으면 자동 rollback 되어서 디비에서 확인 못함!
public class LikeRepositoryTest {

    @Autowired
    MusicRepository musicRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    LikeRepository likeRepository;

    @Test
    public void getStub() {
        User user = User.builder().id("111112").name("user1").build();
        userRepository.save(user);

        String url11 = "<iframe width=\"90%\"  scrolling=\"no\" frameborder=\"no\" allow=\"autoplay\" src=\"https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/1027002883&color=%23161820&auto_play=false&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true\"></iframe><div style=\"font-size: 10px; color: #cccccc;line-break: anywhere;word-break: normal;overflow: hidden;white-space: nowrap;text-overflow: ellipsis; font-family: Interstate,Lucida Grande,Lucida Sans Unicode,Lucida Sans,Garuda,Verdana,Tahoma,sans-serif;font-weight: 100;\"><a href=\"https://soundcloud.com/nrcioesrqgcx\" title=\"dlrmdwl\" target=\"_blank\" style=\"color: #cccccc; text-decoration: none;\">dlrmdwl</a> · <a href=\"https://soundcloud.com/nrcioesrqgcx/what-do-i-call-you\" title=\"What Do I Call You\" target=\"_blank\" style=\"color: #cccccc; text-decoration: none;\">What Do I Call You</a></div>\n";
        String url12 = "<iframe width=\"90%\"  scrolling=\"no\" frameborder=\"no\" allow=\"autoplay\" src=\"https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/playlists/1217456059&color=%23161820&auto_play=false&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true\"></iframe><div style=\"font-size: 10px; color: #cccccc;line-break: anywhere;word-break: normal;overflow: hidden;white-space: nowrap;text-overflow: ellipsis; font-family: Interstate,Lucida Grande,Lucida Sans Unicode,Lucida Sans,Garuda,Verdana,Tahoma,sans-serif;font-weight: 100;\"><a href=\"https://soundcloud.com/real-mccoy-618816268\" title=\"actmanly\" target=\"_blank\" style=\"color: #cccccc; text-decoration: none;\">actmanly</a> · <a href=\"https://soundcloud.com/real-mccoy-618816268/sets/ndmmvte5xy7s\" title=\"잔납\" target=\"_blank\" style=\"color: #cccccc; text-decoration: none;\">잔납</a></div>\n";
        String url13 = "<iframe width=\"90%\"  scrolling=\"no\" frameborder=\"no\" allow=\"autoplay\" src=\"https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/1337238421&color=%23161820&auto_play=false&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true\"></iframe><div style=\"font-size: 10px; color: #cccccc;line-break: anywhere;word-break: normal;overflow: hidden;white-space: nowrap;text-overflow: ellipsis; font-family: Interstate,Lucida Grande,Lucida Sans Unicode,Lucida Sans,Garuda,Verdana,Tahoma,sans-serif;font-weight: 100;\"><a href=\"https://soundcloud.com/beerlove4\" title=\"맥주 사랑4\" target=\"_blank\" style=\"color: #cccccc; text-decoration: none;\">맥주 사랑4</a> · <a href=\"https://soundcloud.com/beerlove4/ngrlvpdttzha\" title=\"노을 - 너는 어땠을까\" target=\"_blank\" style=\"color: #cccccc; text-decoration: none;\">노을 - 너는 어땠을까</a></div>\n";
        String url14 = "<iframe width=\"90%\"  scrolling=\"no\" frameborder=\"no\" allow=\"autoplay\" src=\"https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/1174020541&color=%23161820&auto_play=false&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true\"></iframe><div style=\"font-size: 10px; color: #cccccc;line-break: anywhere;word-break: normal;overflow: hidden;white-space: nowrap;text-overflow: ellipsis; font-family: Interstate,Lucida Grande,Lucida Sans Unicode,Lucida Sans,Garuda,Verdana,Tahoma,sans-serif;font-weight: 100;\"><a href=\"https://soundcloud.com/tthahzktqgfy\" title=\"박정민\" target=\"_blank\" style=\"color: #cccccc; text-decoration: none;\">박정민</a> · <a href=\"https://soundcloud.com/tthahzktqgfy/7h4pu7ealus7\" title=\"나 그댈위해 시 한편을 쓰겠어\" target=\"_blank\" style=\"color: #cccccc; text-decoration: none;\">나 그댈위해 시 한편을 쓰겠어</a></div>\n";
        String url15 = "<iframe width=\"90%\"  scrolling=\"no\" frameborder=\"no\" allow=\"autoplay\" src=\"https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/258463449&color=%23161820&auto_play=false&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true\"></iframe><div style=\"font-size: 10px; color: #cccccc;line-break: anywhere;word-break: normal;overflow: hidden;white-space: nowrap;text-overflow: ellipsis; font-family: Interstate,Lucida Grande,Lucida Sans Unicode,Lucida Sans,Garuda,Verdana,Tahoma,sans-serif;font-weight: 100;\"><a href=\"https://soundcloud.com/drykim\" title=\"김건우(Kim Gun Woo)\" target=\"_blank\" style=\"color: #cccccc; text-decoration: none;\">김건우(Kim Gun Woo)</a> · <a href=\"https://soundcloud.com/drykim/charlie-puth-one-call-away-cover-vocal-piano\" title=\"Charlie Puth(찰리 푸스) - One Call Away (Cover) Vocal &amp; Piano\" target=\"_blank\" style=\"color: #cccccc; text-decoration: none;\">Charlie Puth(찰리 푸스) - One Call Away (Cover) Vocal &amp; Piano</a></div>\n";
        String img_url11 = "https://i1.sndcdn.com/artworks-mFjlyv08ZkjwcB1g-nMLxHA-t500x500.jpg\n";
        String img_url12 = "https://i1.sndcdn.com/artworks-000510811914-t75zox-t500x500.jpg\n";
        String img_url13 = "https://i1.sndcdn.com/artworks-6iGGCMxRxruzENlC-f3HxfQ-t500x500.jpg\n";
        String img_url14 = "https://i1.sndcdn.com/artworks-qRdym3sroiQMbR1D-v0arZg-t500x500.jpg\n";
        String img_url15 = "https://i1.sndcdn.com/artworks-000157562935-r0lwxb-t500x500.jpg\n";

        Music music11 = Music.builder().id(100L).title("What Do I Call You").music_url(url11).img_url(img_url11).build();
        Music music12 = Music.builder().id(101L).title("잔납").music_url(url12).img_url(img_url12).build();
        Music music13 = Music.builder().id(102L).title("노을 - 너는 어땠을까").music_url(url13).img_url(img_url13).build();
        Music music14 = Music.builder().id(103L).title("나 그댈위해 시 한편을 쓰겠어").music_url(url14).img_url(img_url14).build();
        Music music15 = Music.builder().id(104L).title("Charlie Puth - One Call Away").music_url(url15).img_url(img_url15).build();

        musicRepository.save(music11);
        musicRepository.save(music12);
        musicRepository.save(music13);
        musicRepository.save(music14);
        musicRepository.save(music15);

        Like like1 = Like.builder().user(user).music(music11).build();
        Like like2 = Like.builder().user(user).music(music12).build();
        Like like3 = Like.builder().user(user).music(music13).build();
        Like like4 = Like.builder().user(user).music(music14).build();
        Like like5 = Like.builder().user(user).music(music15).build();

        likeRepository.save(like1);
        likeRepository.save(like2);
        likeRepository.save(like3);
        likeRepository.save(like4);
        likeRepository.save(like5);

    }

    @Test
    public void getMusicByUserLike() {

        List<Music> musicList = musicRepository.getMusicByUserLike("111112");

        for (Music music : musicList) {
            System.out.print(music.getTitle());
        }
    }
}