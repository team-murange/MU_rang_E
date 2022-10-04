package com.example.murange.Domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Entity
@NoArgsConstructor
@Getter
@Table(name = "like")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="like_id")
    private Long id;


    @ManyToOne // (fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne // (fetch = FetchType.LAZY)
    @JoinColumn(name = "music_id")
    private Music music;

    @Builder
    public Like(User user, Music music) {
        this.user = user;
        this.music = music;
    }

}
