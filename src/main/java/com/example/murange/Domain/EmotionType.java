package com.example.murange.Domain;

import java.util.Random;

public enum EmotionType {
    sad,happy,angry,neutral,disgust,surprised,fearful;

    private static final Random PRNG = new Random();

    public static EmotionType randomEmotionType()  {
        EmotionType[] emotionType = values();
        return emotionType[PRNG.nextInt(emotionType.length)];
    }
}
