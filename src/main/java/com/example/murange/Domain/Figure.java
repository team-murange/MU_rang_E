package com.example.murange.Domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "emotion")
public class Figure {
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

    public void updateFigure(EmotionType emotionType) {
        switch (emotionType) {
            case sad:
                calcFigure(sad);
            case angry:
                calcFigure(angry);
            case happy:
                calcFigure(happiness);
            case neutral:
                calcFigure(neutral);
            case disgust:
                calcFigure(disgust);
            case surprised:
                calcFigure(surprised);
            case fearful:
                calcFigure(scared);
        };
    }

    public float calcFigure(float emotionFigure) {
        float total = sad + happiness + angry + neutral + disgust + scared + surprised;
        if (total >= 1) resetFigure();
        emotionFigure += 0.01;
        return emotionFigure;
    }

    public void resetFigure() {
        sad %= 10;
        happiness %= 10;
        angry %= 10;
        neutral %= 10;
        disgust %= 10;
        scared %= 10;
        surprised %= 10;
    }

    @Builder
    public Figure(float sad, float happiness, float angry, float neutral, float disgust, float scared, float surprised) {
        this.sad = sad;
        this.angry = angry;
        this.happiness = happiness;
        this.neutral = neutral;
        this.disgust = disgust;
        this.scared = scared;
        this.surprised = surprised;
    }
}
