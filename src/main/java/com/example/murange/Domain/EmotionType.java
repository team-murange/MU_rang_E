package com.example.murange.Domain;

import java.util.Random;

public enum EmotionType {
    sad,happiness,angry,neutral,disgust,scared,surprised;

    private static final Random PRNG = new Random();

    public static String randomEmotionType()  {
        EmotionType[] emotionType = values();
        return emotionType[PRNG.nextInt(emotionType.length)].toString();
    }
}
