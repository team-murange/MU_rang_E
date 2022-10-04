package com.example.murange.Repository;

import com.example.murange.Domain.Music;
import com.example.murange.Domain.Record;
import com.example.murange.Domain.User;
import com.example.murange.Dto.RecordResponseDto;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@RunWith(SpringRunner.class)
@Commit // 확인용!, 없으면 자동 rollback 되어서 디비에서 확인 못함!
public class MusicRepositoryTest {
    @Autowired
    MusicRepository musicRepository;

    @Test
    public void getStub() {

        String url1 = "<iframe width=\"90%\"  scrolling=\"no\" frameborder=\"no\" allow=\"autoplay\" src=\"https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/1350581908&color=%23161820&auto_play=false&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true\"></iframe><div style=\"font-size: 10px; color: #cccccc;line-break: anywhere;word-break: normal;overflow: hidden;white-space: nowrap;text-overflow: ellipsis; font-family: Interstate,Lucida Grande,Lucida Sans Unicode,Lucida Sans,Garuda,Verdana,Tahoma,sans-serif;font-weight: 100;\"><a href=\"https://soundcloud.com/2u3ivt9iv6xt\" title=\"hello robot\" target=\"_blank\" style=\"color: #cccccc; text-decoration: none;\">hello robot</a> · <a href=\"https://soundcloud.com/2u3ivt9iv6xt/10cm-big-naughty-10cm\" title=\"10CM, BIG Naughty (서동현) - 딱 10CM만\" target=\"_blank\" style=\"color: #cccccc; text-decoration: none;\">10CM, BIG Naughty (서동현) - 딱 10CM만</a></div>\n";
        String url2 = "<iframe width=\"90%\"  scrolling=\"no\" frameborder=\"no\" allow=\"autoplay\" src=\"https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/833810287&color=%23161820&auto_play=false&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true\"></iframe><div style=\"font-size: 10px; color: #cccccc;line-break: anywhere;word-break: normal;overflow: hidden;white-space: nowrap;text-overflow: ellipsis; font-family: Interstate,Lucida Grande,Lucida Sans Unicode,Lucida Sans,Garuda,Verdana,Tahoma,sans-serif;font-weight: 100;\"><a href=\"https://soundcloud.com/bangtan\" title=\"BTS\" target=\"_blank\" style=\"color: #cccccc; text-decoration: none;\">BTS</a> · <a href=\"https://soundcloud.com/bangtan/thankyouarmy2020\" title=\"Still With You by JK of BTS\" target=\"_blank\" style=\"color: #cccccc; text-decoration: none;\">Still With You by JK of BTS</a></div>\n";
        String url3 = "<iframe width=\"90%\"  scrolling=\"no\" frameborder=\"no\" allow=\"autoplay\" src=\"https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/1326954859&color=%23161820&auto_play=false&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true\"></iframe><div style=\"font-size: 10px; color: #cccccc;line-break: anywhere;word-break: normal;overflow: hidden;white-space: nowrap;text-overflow: ellipsis; font-family: Interstate,Lucida Grande,Lucida Sans Unicode,Lucida Sans,Garuda,Verdana,Tahoma,sans-serif;font-weight: 100;\"><a href=\"https://soundcloud.com/blackpinkofficial\" title=\"BLACKPINK\" target=\"_blank\" style=\"color: #cccccc; text-decoration: none;\">BLACKPINK</a> · <a href=\"https://soundcloud.com/blackpinkofficial/pink-venom\" title=\"Pink Venom\" target=\"_blank\" style=\"color: #cccccc; text-decoration: none;\">Pink Venom</a></div>\n";
        String url4 = "<iframe width=\"90%\"  scrolling=\"no\" frameborder=\"no\" allow=\"autoplay\" src=\"https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/1347261091&color=%23161820&auto_play=false&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true\"></iframe><div style=\"font-size: 10px; color: #cccccc;line-break: anywhere;word-break: normal;overflow: hidden;white-space: nowrap;text-overflow: ellipsis; font-family: Interstate,Lucida Grande,Lucida Sans Unicode,Lucida Sans,Garuda,Verdana,Tahoma,sans-serif;font-weight: 100;\"><a href=\"https://soundcloud.com/gabriella-cl-95\" title=\"Gabriella CL 95\" target=\"_blank\" style=\"color: #cccccc; text-decoration: none;\">Gabriella CL 95</a> · <a href=\"https://soundcloud.com/gabriella-cl-95/lagom-hurt-cover-utk-sc\" title=\"New Jeans - Hurt Duet Cover\" target=\"_blank\" style=\"color: #cccccc; text-decoration: none;\">New Jeans - Hurt Duet Cover</a></div>\n";
        String url5 = "<iframe width=\"90%\"  scrolling=\"no\" frameborder=\"no\" allow=\"autoplay\" src=\"https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/732683581&color=%23161820&auto_play=false&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true\"></iframe><div style=\"font-size: 10px; color: #cccccc;line-break: anywhere;word-break: normal;overflow: hidden;white-space: nowrap;text-overflow: ellipsis; font-family: Interstate,Lucida Grande,Lucida Sans Unicode,Lucida Sans,Garuda,Verdana,Tahoma,sans-serif;font-weight: 100;\"><a href=\"https://soundcloud.com/l2share95\" title=\"L2Share♫95\" target=\"_blank\" style=\"color: #cccccc; text-decoration: none;\">L2Share♫95</a> · <a href=\"https://soundcloud.com/l2share95/red-velvet-psycho\" title=\"Red Velvet (레드벨벳) - Psycho\" target=\"_blank\" style=\"color: #cccccc; text-decoration: none;\">Red Velvet (레드벨벳) - Psycho</a></div>\n";
        String url6 = "<iframe width=\"90%\"  scrolling=\"no\" frameborder=\"no\" allow=\"autoplay\" src=\"https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/668417000&color=%23161820&auto_play=false&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true\"></iframe><div style=\"font-size: 10px; color: #cccccc;line-break: anywhere;word-break: normal;overflow: hidden;white-space: nowrap;text-overflow: ellipsis; font-family: Interstate,Lucida Grande,Lucida Sans Unicode,Lucida Sans,Garuda,Verdana,Tahoma,sans-serif;font-weight: 100;\"><a href=\"https://soundcloud.com/l2share42\" title=\"L2Share♫42\" target=\"_blank\" style=\"color: #cccccc; text-decoration: none;\">L2Share♫42</a> · <a href=\"https://soundcloud.com/l2share42/umpah-umpah\" title=\"Red Velvet - 음파음파 (Umpah Umpah)\" target=\"_blank\" style=\"color: #cccccc; text-decoration: none;\">Red Velvet - 음파음파 (Umpah Umpah)</a></div>\n";
        String url7 = "<iframe width=\"90%\"  scrolling=\"no\" frameborder=\"no\" allow=\"autoplay\" src=\"https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/813234718&color=%23161820&auto_play=false&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true\"></iframe><div style=\"font-size: 10px; color: #cccccc;line-break: anywhere;word-break: normal;overflow: hidden;white-space: nowrap;text-overflow: ellipsis; font-family: Interstate,Lucida Grande,Lucida Sans Unicode,Lucida Sans,Garuda,Verdana,Tahoma,sans-serif;font-weight: 100;\"><a href=\"https://soundcloud.com/l2share103\" title=\"L2Share♫103\" target=\"_blank\" style=\"color: #cccccc; text-decoration: none;\">L2Share♫103</a> · <a href=\"https://soundcloud.com/l2share103/taeyeon-happy-1\" title=\"태연 (TAEYEON) - Happy\" target=\"_blank\" style=\"color: #cccccc; text-decoration: none;\">태연 (TAEYEON) - Happy</a></div>\n";
        String url8 = "<iframe width=\"90%\"  scrolling=\"no\" frameborder=\"no\" allow=\"autoplay\" src=\"https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/1184208196&color=%23161820&auto_play=false&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true\"></iframe><div style=\"font-size: 10px; color: #cccccc;line-break: anywhere;word-break: normal;overflow: hidden;white-space: nowrap;text-overflow: ellipsis; font-family: Interstate,Lucida Grande,Lucida Sans Unicode,Lucida Sans,Garuda,Verdana,Tahoma,sans-serif;font-weight: 100;\"><a href=\"https://soundcloud.com/l2shareost27\" title=\"L2ShareOST27\" target=\"_blank\" style=\"color: #cccccc; text-decoration: none;\">L2ShareOST27</a> · <a href=\"https://soundcloud.com/l2shareost27/v-kim-taehyung-of-bts-christmas-tree-our-beloved-summer-ost-part-5\" title=\"V (Kim Taehyung) of BTS - Christmas Tree (Our Beloved Summer 그 해 우리는 OST Part 5)\" target=\"_blank\" style=\"color: #cccccc; text-decoration: none;\">V (Kim Taehyung) of BTS - Christmas Tree (Our Beloved Summer 그 해 우리는 OST Part 5)</a></div>\n";
        String url9 = "<iframe width=\"90%\"  scrolling=\"no\" frameborder=\"no\" allow=\"autoplay\" src=\"https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/706323277&color=%23161820&auto_play=false&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true\"></iframe><div style=\"font-size: 10px; color: #cccccc;line-break: anywhere;word-break: normal;overflow: hidden;white-space: nowrap;text-overflow: ellipsis; font-family: Interstate,Lucida Grande,Lucida Sans Unicode,Lucida Sans,Garuda,Verdana,Tahoma,sans-serif;font-weight: 100;\"><a href=\"https://soundcloud.com/whakfld0d0d098\" title=\"whakfl\" target=\"_blank\" style=\"color: #cccccc; text-decoration: none;\">whakfl</a> · <a href=\"https://soundcloud.com/whakfld0d0d098/iu-love-poem\" title=\"IU (아이유) - 러브 포엠 (Love Poem)\" target=\"_blank\" style=\"color: #cccccc; text-decoration: none;\">IU (아이유) - 러브 포엠 (Love Poem)</a></div>\n";
        String url10 = "<iframe width=\"90%\"  scrolling=\"no\" frameborder=\"no\" allow=\"autoplay\" src=\"https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/274995515&color=%23161820&auto_play=false&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true\"></iframe><div style=\"font-size: 10px; color: #cccccc;line-break: anywhere;word-break: normal;overflow: hidden;white-space: nowrap;text-overflow: ellipsis; font-family: Interstate,Lucida Grande,Lucida Sans Unicode,Lucida Sans,Garuda,Verdana,Tahoma,sans-serif;font-weight: 100;\"><a href=\"https://soundcloud.com/reakey\" title=\"rea\" target=\"_blank\" style=\"color: #cccccc; text-decoration: none;\">rea</a> · <a href=\"https://soundcloud.com/reakey/thought-of-you-john-park\" title=\"Thought Of You(네 생각) - John Park(존박)\" target=\"_blank\" style=\"color: #cccccc; text-decoration: none;\">Thought Of You(네 생각) - John Park(존박)</a></div>\n";
        String url11 = "<iframe width=\"90%\"  scrolling=\"no\" frameborder=\"no\" allow=\"autoplay\" src=\"https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/1027002883&color=%23161820&auto_play=false&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true\"></iframe><div style=\"font-size: 10px; color: #cccccc;line-break: anywhere;word-break: normal;overflow: hidden;white-space: nowrap;text-overflow: ellipsis; font-family: Interstate,Lucida Grande,Lucida Sans Unicode,Lucida Sans,Garuda,Verdana,Tahoma,sans-serif;font-weight: 100;\"><a href=\"https://soundcloud.com/nrcioesrqgcx\" title=\"dlrmdwl\" target=\"_blank\" style=\"color: #cccccc; text-decoration: none;\">dlrmdwl</a> · <a href=\"https://soundcloud.com/nrcioesrqgcx/what-do-i-call-you\" title=\"What Do I Call You\" target=\"_blank\" style=\"color: #cccccc; text-decoration: none;\">What Do I Call You</a></div>\n";
        String url12 = "<iframe width=\"90%\"  scrolling=\"no\" frameborder=\"no\" allow=\"autoplay\" src=\"https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/playlists/1217456059&color=%23161820&auto_play=false&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true\"></iframe><div style=\"font-size: 10px; color: #cccccc;line-break: anywhere;word-break: normal;overflow: hidden;white-space: nowrap;text-overflow: ellipsis; font-family: Interstate,Lucida Grande,Lucida Sans Unicode,Lucida Sans,Garuda,Verdana,Tahoma,sans-serif;font-weight: 100;\"><a href=\"https://soundcloud.com/real-mccoy-618816268\" title=\"actmanly\" target=\"_blank\" style=\"color: #cccccc; text-decoration: none;\">actmanly</a> · <a href=\"https://soundcloud.com/real-mccoy-618816268/sets/ndmmvte5xy7s\" title=\"잔납\" target=\"_blank\" style=\"color: #cccccc; text-decoration: none;\">잔납</a></div>\n";
        String url13 = "<iframe width=\"90%\"  scrolling=\"no\" frameborder=\"no\" allow=\"autoplay\" src=\"https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/1337238421&color=%23161820&auto_play=false&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true\"></iframe><div style=\"font-size: 10px; color: #cccccc;line-break: anywhere;word-break: normal;overflow: hidden;white-space: nowrap;text-overflow: ellipsis; font-family: Interstate,Lucida Grande,Lucida Sans Unicode,Lucida Sans,Garuda,Verdana,Tahoma,sans-serif;font-weight: 100;\"><a href=\"https://soundcloud.com/beerlove4\" title=\"맥주 사랑4\" target=\"_blank\" style=\"color: #cccccc; text-decoration: none;\">맥주 사랑4</a> · <a href=\"https://soundcloud.com/beerlove4/ngrlvpdttzha\" title=\"노을 - 너는 어땠을까\" target=\"_blank\" style=\"color: #cccccc; text-decoration: none;\">노을 - 너는 어땠을까</a></div>\n";
        String url14 = "<iframe width=\"90%\"  scrolling=\"no\" frameborder=\"no\" allow=\"autoplay\" src=\"https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/1174020541&color=%23161820&auto_play=false&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true\"></iframe><div style=\"font-size: 10px; color: #cccccc;line-break: anywhere;word-break: normal;overflow: hidden;white-space: nowrap;text-overflow: ellipsis; font-family: Interstate,Lucida Grande,Lucida Sans Unicode,Lucida Sans,Garuda,Verdana,Tahoma,sans-serif;font-weight: 100;\"><a href=\"https://soundcloud.com/tthahzktqgfy\" title=\"박정민\" target=\"_blank\" style=\"color: #cccccc; text-decoration: none;\">박정민</a> · <a href=\"https://soundcloud.com/tthahzktqgfy/7h4pu7ealus7\" title=\"나 그댈위해 시 한편을 쓰겠어\" target=\"_blank\" style=\"color: #cccccc; text-decoration: none;\">나 그댈위해 시 한편을 쓰겠어</a></div>\n";
        String url15 = "<iframe width=\"90%\"  scrolling=\"no\" frameborder=\"no\" allow=\"autoplay\" src=\"https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/258463449&color=%23161820&auto_play=false&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true\"></iframe><div style=\"font-size: 10px; color: #cccccc;line-break: anywhere;word-break: normal;overflow: hidden;white-space: nowrap;text-overflow: ellipsis; font-family: Interstate,Lucida Grande,Lucida Sans Unicode,Lucida Sans,Garuda,Verdana,Tahoma,sans-serif;font-weight: 100;\"><a href=\"https://soundcloud.com/drykim\" title=\"김건우(Kim Gun Woo)\" target=\"_blank\" style=\"color: #cccccc; text-decoration: none;\">김건우(Kim Gun Woo)</a> · <a href=\"https://soundcloud.com/drykim/charlie-puth-one-call-away-cover-vocal-piano\" title=\"Charlie Puth(찰리 푸스) - One Call Away (Cover) Vocal &amp; Piano\" target=\"_blank\" style=\"color: #cccccc; text-decoration: none;\">Charlie Puth(찰리 푸스) - One Call Away (Cover) Vocal &amp; Piano</a></div>\n";

        String img_url1 = "https://i1.sndcdn.com/artworks-LXIOzxnk35sHHw60-kk33gQ-t500x500.jpg\n";
        String img_url2 = "https://i1.sndcdn.com/artworks-7nmc0L048KDmtv7Q-aSOK7A-t500x500.jpg\n";
        String img_url3 = "https://i1.sndcdn.com/artworks-Ty4GRXSnxHw0-0-t500x500.jpg\n";
        String img_url4 = "https://i1.sndcdn.com/artworks-HBgxgsm1oKpTFMou-TvBXDQ-t500x500.jpg\n";
        String img_url5 = "https://i1.sndcdn.com/artworks-000657166189-dxjm1z-t500x500.jpg\n";
        String img_url6 = "https://i1.sndcdn.com/artworks-000585092600-nj2brs-t500x500.jpg\n";
        String img_url7 = "https://i1.sndcdn.com/artworks-5Sfnm5WTMyWAjb4d-TN8Jsg-t500x500.jpg\n";
        String img_url8 = "https://i1.sndcdn.com/artworks-lExRZJTjxYq0ddss-7gSmVQ-t500x500.jpg\n";
        String img_url9 = "https://i1.sndcdn.com/artworks-000627940567-yp7xud-t500x500.jpg\n";
        String img_url10 = "https://i1.sndcdn.com/artworks-000172933981-gtovq6-t500x500.jpg\n";
        String img_url11 = "https://i1.sndcdn.com/artworks-mFjlyv08ZkjwcB1g-nMLxHA-t500x500.jpg\n";
        String img_url12 = "https://i1.sndcdn.com/artworks-000510811914-t75zox-t500x500.jpg\n";
        String img_url13 = "https://i1.sndcdn.com/artworks-6iGGCMxRxruzENlC-f3HxfQ-t500x500.jpg\n";
        String img_url14 = "https://i1.sndcdn.com/artworks-qRdym3sroiQMbR1D-v0arZg-t500x500.jpg\n";
        String img_url15 = "https://i1.sndcdn.com/artworks-000157562935-r0lwxb-t500x500.jpg\n";

        Music music1 = Music.builder().id(1L).title("10CM, BIG Naughty (서동현) - 딱 10CM만").music_url(url1).img_url(img_url1).build();
        Music music2 = Music.builder().id(2L).title("Still With You by JK of BTS").music_url(url2).img_url(img_url2).build();
        Music music3 = Music.builder().id(3L).title("Pink Venom").music_url(url3).img_url(img_url3).build();
        Music music4 = Music.builder().id(4L).title("New Jeans - Hurt Duet Cover").music_url(url4).img_url(img_url4).build();
        Music music5 = Music.builder().id(5L).title("Red Velvet (레드벨벳) - Psycho").music_url(url5).img_url(img_url5).build();
        Music music6 = Music.builder().id(6L).title("Red Velvet - 음파음파 (Umpah Umpah)").music_url(url6).img_url(img_url6).build();
        Music music7 = Music.builder().id(7L).title("태연 (TAEYEON) - Happy").music_url(url7).img_url(img_url7).build();
        Music music8 = Music.builder().id(8L).title("V - Christmas Tree").music_url(url8).img_url(img_url8).build();
        Music music9 = Music.builder().id(9L).title("IU (아이유) - 러브 포엠 (Love Poem)").music_url(url9).img_url(img_url9).build();
        Music music10 = Music.builder().id(10L).title("Thought Of You(네 생각) - John Park(존박)").music_url(url10).img_url(img_url10).build();
        Music music11 = Music.builder().id(11L).title("What Do I Call You").music_url(url11).img_url(img_url11).build();
        Music music12 = Music.builder().id(12L).title("잔납").music_url(url12).img_url(img_url12).build();
        Music music13 = Music.builder().id(13L).title("노을 - 너는 어땠을까").music_url(url13).img_url(img_url13).build();
        Music music14 = Music.builder().id(14L).title("나 그댈위해 시 한편을 쓰겠어").music_url(url14).img_url(img_url14).build();
        Music music15 = Music.builder().id(15L).title("Charlie Puth - One Call Away").music_url(url15).img_url(img_url15).build();


        musicRepository.save(music1);
        musicRepository.save(music2);
        musicRepository.save(music3);
        musicRepository.save(music4);
        musicRepository.save(music5);
        musicRepository.save(music6);
        musicRepository.save(music7);
        musicRepository.save(music8);
        musicRepository.save(music9);
        musicRepository.save(music10);
        musicRepository.save(music11);
        musicRepository.save(music12);
        musicRepository.save(music13);
        musicRepository.save(music14);
        musicRepository.save(music15);

    }



}