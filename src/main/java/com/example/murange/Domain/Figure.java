package com.example.murange.Domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "figure")
public class Figure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="figure_id")
    private Long id;

    private float sad;

    private float happy;

    private float angry;

    private float neutral;

    private float disgusted;

    private float fearful;

    private float surprised;

    @OneToOne(mappedBy = "figure")
    private Music music;

    public void updateFigure(EmotionType emotionType) {
        switch (emotionType) {
            case sad:
                calcFigure(sad);
            case angry:
                calcFigure(angry);
            case happy:
                calcFigure(happy);
            case neutral:
                calcFigure(neutral);
            case disgust:
                calcFigure(disgusted);
            case surprised:
                calcFigure(surprised);
            case fearful:
                calcFigure(fearful);
        };
    }

    public float calcFigure(float emotionFigure) {
        float total = sad + happy + angry + neutral + disgusted + fearful + surprised;
        if (total >= 1) resetFigure();
        emotionFigure += 0.01;
        return emotionFigure;
    }

    public void resetFigure() {
        sad %= 10;
        happy %= 10;
        angry %= 10;
        neutral %= 10;
        disgusted %= 10;
        fearful %= 10;
        surprised %= 10;
    }

    @Builder
    public Figure(float sad, float happy, float angry, float neutral, float disgusted, float fearful, float surprised) {
        this.sad = sad;
        this.angry = angry;
        this.happy = happy;
        this.neutral = neutral;
        this.disgusted = disgusted;
        this.fearful = fearful;
        this.surprised = surprised;
    }
}
