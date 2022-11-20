package ru.melonhell.mcdebugrender.core.utils;

public record MdrColor(
        int red,
        int green,
        int blue,
        int alpha
) {

    public static MdrColor WHITE = new MdrColor(255, 255, 255, 255);
    public static MdrColor fromRGB(int rgb) {
        int alpha = 0xFF;
        int red = 0xFF & rgb >> 16;
        int green = 0xFF & rgb >> 8;
        int blue = 0xFF & rgb;
        return new MdrColor(alpha, red, green, blue);
    }

    public static MdrColor fromARGB(int argb) {
        int alpha = 0xFF & argb >> 24;
        int red = 0xFF & argb >> 16;
        int green = 0xFF & argb >> 8;
        int blue = 0xFF & argb;
        return new MdrColor(alpha, red, green, blue);
    }

    public int asRGB() {
        return red << 16 | green << 8 | blue;
    }

    public int asARGB() {
        return alpha << 24 | red << 16 | green << 8 | blue;
    }
}
