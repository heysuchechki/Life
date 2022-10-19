package lida.kp;

import java.awt.*;

public class Config { //конфигурационный класс
    public static final int SIZE = 10; //размер одной ячейки
    public static final int WIDTH = 80; //ширина поля
    public static final int HEIGHT = 60; //высота поля
    public static final int SLEEPMS = 100; //таймер

    public static Color getColor (Condition condition) { //цвета ячеек
        switch (condition) {
            default -> {
            }
            case NONE -> {
                return Color.BLACK;
            }
            case BORN -> {
                return Color.GRAY;
            }
            case LIVE -> {
                return Color.WHITE;
            }
            case DIED -> {
                return Color.CYAN;
            }
        }
        return null;
    }
}
