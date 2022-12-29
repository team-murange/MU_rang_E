package com.example.murange.Domain;

import java.util.Random;

public enum EmotionCategory {

    happy(247,247, 13),
    neutral(239, 231, 218),
    surprised (245, 173, 43),
    sad(0, 176, 240),
    fearful(180, 96, 206),
    angry(227, 76, 29),
    disgusted(109, 218, 74),
    none(209, 212, 212);

    private int red;
    private int green;
    private int blue;

    EmotionCategory(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    private static final Random PRNG = new Random();

    public static EmotionCategory randomEmotionType()  {
        EmotionCategory[] emotionCategory = values();
        return emotionCategory[PRNG.nextInt(emotionCategory.length)];
    }

    public static int calcColorCode(int main, int sub)  {
        int result = (int) (main * 0.65F + sub * 0.35F);
        return Math.min(result, 255);
    }

    // 주감정, 부감정 입력받고 리턴
    public static String calcColorCodeByTwoEmotion(EmotionCategory mainEmotion, EmotionCategory subEmotion) {
        int calcRed = calcColorCode(mainEmotion.red, subEmotion.red);
        int calcGreen = calcColorCode(mainEmotion.green, subEmotion.green);
        int calcBlue = calcColorCode(mainEmotion.blue, subEmotion.blue);

        String redResult = String.format("%02x", calcRed);
        String greenResult = String.format("%02x", calcGreen);
        String blueResult = String.format("%02x", calcBlue);

        return redResult + greenResult + blueResult;
    }
}