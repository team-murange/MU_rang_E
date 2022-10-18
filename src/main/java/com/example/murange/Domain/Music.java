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
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="music_id")
    private Long id;

    private String title;

    @Column(length=5000)
    private String music_url;

    @Column(length=5000)
    private String img_url;

    private int streaming_cnt;

    @OneToOne
    @JoinColumn(name = "emotion_id", insertable = false, updatable = false)
    private Emotion emotion;

    @OneToMany(mappedBy = "music")
    private List<Like> like = new ArrayList<Like>();

    @Builder
    public Music(Long id, String title, String music_url, String img_url, int streaming_cnt, long emotion_id) {
        this.id = id;
        this.title = title;
        this.music_url = music_url;
        this.img_url = img_url;
        this.streaming_cnt = streaming_cnt;
    }
}
