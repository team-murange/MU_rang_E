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

    private String music_url;

    private String img_url;

    private int cnt;

    @OneToOne
    @JoinColumn(name = "emotion_id", insertable = false, updatable = false)
    private Emotion emotion;

    @OneToMany(mappedBy = "music")
    private List<Like> like = new ArrayList<Like>();

    @Builder
    public Music(String title, String music_url, String img_url, int cnt, long emotion_id) {
        this.title = title;
        this.music_url = music_url;
        this.img_url = img_url;
        this.cnt = cnt;
    }
}
