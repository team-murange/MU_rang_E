package com.example.murange.Domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "record")
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="record_id")
    private Long id;

    private String colorCode;

    private EmotionCategory mainEmotion;

    private EmotionCategory subEmotion;

    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Record(String colorCode, EmotionCategory mainEmotion, EmotionCategory subEmotion, LocalDate date, User user) {
        this.user = user;
        this.colorCode = colorCode;
        this.date = date;
        this.mainEmotion = mainEmotion;
        this.subEmotion = subEmotion;
    }
}
