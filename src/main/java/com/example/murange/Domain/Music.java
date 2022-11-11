package com.example.murange.Domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "music")
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="music_id")
    private Long id;

    private String title;

    @Column(length=5000)
    private String music_url;       // 음악 태그 <iframe> url

    @Column(length=5000)
    private String img_url;         // 앨범 커버 이미지 url

    @Column(length=5000)
    private String soundcloud_url; // 실제 SoundCloud 링크

    @OneToOne
    @JoinColumn(name = "figure_id", insertable = false, updatable = false)
    private Figure figure;

    @OneToMany(mappedBy = "music")
    private List<Like> like = new ArrayList<Like>();

    @Builder
    public Music(Long id, String title, String music_url, String img_url, String soundcloud_url) {
        this.id = id;
        this.title = title;
        this.music_url = music_url;
        this.img_url = img_url;
        this.soundcloud_url = soundcloud_url;
    }
}
