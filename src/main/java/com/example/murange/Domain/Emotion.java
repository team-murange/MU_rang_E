package com.example.murange.Domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.lang.reflect.Field;

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

    private float happy;

    private float angry;

    private float neutral;

    private float disgusted;

    private float fearful;

    private float surprised;

    @OneToOne(mappedBy = "emotion")
    private Music music;

    public Field findField(String fieldStr) throws NoSuchFieldException {
        Class<?> clazz = Emotion.class;
        return clazz.getDeclaredField(fieldStr);
    }

    public void updateEmotionFigure(String fieldStr) throws NoSuchFieldException, IllegalAccessException {
        validateEmotionFigure();
        Field field = findField(fieldStr);
        Float value = (Float) field.get(this);
        field.set(this, value + 0.01F );
    }

    public void validateEmotionFigure() throws IllegalAccessException {
        float total = sad + happy + angry + neutral + disgusted + fearful + surprised;
        if (total >= 1) {
            resetFigure();
        }
    }

    public void resetFigure() throws IllegalAccessException {
        Field[] fields = Emotion.class.getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().equals("id") || field.getName().equals("music")) {
                continue;
            }
            Float newValue = (Float) field.get(this) / 10;
            field.set(this, newValue);
        }
    }

    @Builder
    public Emotion(float sad, float happy, float angry, float neutral, float disgusted, float fearful, float surprised) {
        this.sad = sad;
        this.angry = angry;
        this.happy = happy;
        this.neutral = neutral;
        this.disgusted = disgusted;
        this.fearful = fearful;
        this.surprised = surprised;
    }
}
