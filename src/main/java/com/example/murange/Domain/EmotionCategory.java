package com.example.murange.Domain;

import java.util.Random;

public enum EmotionCategory {

    happy(229,229, 30),
    surprised(210, 173, 142),
    neutral(88, 140, 199),
    sad(132, 111, 191),
    fearful(132, 111, 191),
    angry(210, 41, 37),
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

    private static int calcColorCode(int main, int sub)  {
        int calc =  ((main + sub) / 2);
        return Math.min(calc, 255);
    }

    // 주감정, 부감정 입력받고 리턴
    public static String calcColorCodeByTwoEmotion(EmotionCategory mainEmotion, EmotionCategory subEmotion) {
        int calcRed = calcColorCode(mainEmotion.red, subEmotion.red);
        int calcGreen = calcColorCode(mainEmotion.green, subEmotion.green);
        int calcBlue = calcColorCode(mainEmotion.blue, subEmotion.blue);

        String redHex = Integer.toHexString(calcRed);
        String greenHex = Integer.toHexString(calcGreen);
        String blueHex = Integer.toHexString(calcBlue);

        return redHex + greenHex + blueHex;
    }
}