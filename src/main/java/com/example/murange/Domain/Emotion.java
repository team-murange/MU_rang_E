package com.example.murange.Domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "emotion")
public class Emotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="emotion_id")
    private Long id;

    private float sad;

    private float happiness;

    private float angry;

    private float neutral;

    private float disgust;

    private float scared;

    private float surprised;

    @OneToOne(mappedBy = "emotion")
    private Music music;

    @Builder
    public Emotion(float sad, float happiness, float angry, float neutral, float disgust, float scared, float surprised) {
        this.sad = sad;
        this.angry = angry;
        this.happiness = happiness;
        this.neutral = neutral;
        this.disgust = disgust;
        this.scared = scared;
        this.surprised = surprised;
    }
}
