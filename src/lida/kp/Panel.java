package lida.kp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Panel implements Runnable {

    JFrame frame; //создание поля игры
    Box[][] boxes;
    JFrame frame2; //создание поля счета

    @Override
    public void run() {
        initFrame();
        initBoxes();
        initTimer();
        initFrame2();
    }

    void initFrame2() { //метод инициализации поля счета
        frame2 = new JFrame();
        frame2.setLayout(new BorderLayout());
        frame2.setSize(Config.SIZE * 30,Config.SIZE * 10);
        frame2.setVisible(true);
        frame2.setTitle("Count");
    }

    void initFrame() { //метод инициализации поля игры
        frame = new JFrame();
        frame.getContentPane().setLayout(null);
        frame.setSize(Config.SIZE * Config.WIDTH, Config.SIZE * Config.HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setTitle("Life Game by Lidulya");
    }

    void initBoxes() { //метод инициализации боксов
        boxes = new Box[Config.WIDTH][Config.HEIGHT];
        for (int x = 0; x < Config.WIDTH; x++) //создание клеток
            for (int y = 0; y < Config.HEIGHT; y++) {
                boxes[x][y] = new Box(x, y);
                frame.add(boxes[x][y]); //добавление созданного бокса на поле
            }
        for (int x = 0; x < Config.WIDTH; x++) //выбираем конкретную клетку на экране
            for (int y = 0; y < Config.HEIGHT; y++)
                for (int sx = -1; sx <= +1; sx++) //проеверяем все соседние клетки
                    for (int sy = -1; sy <= +1; sy++)
                        if (!(sx == 0 && sy == 0)) //отсеиваем центральную клетку
                            boxes[x][y].cell.addNeighbor(boxes //каждую соседнюю клетку добавляем в cell в качестве соседа
                                    [(x + sx + Config.WIDTH) % Config.WIDTH]
                                    [(y + sy + Config.HEIGHT) % Config.HEIGHT].cell);

        /*for (int x = 10; x < 15; x++) {
            boxes[x][10].cell.condition = Condition.LIVE;
            boxes[x][10].setColor();
        }*/
    }

    private void initTimer() { //метод инициализации таймера
        TimerListener t1 = new TimerListener();
        Timer timer = new Timer(Config.SLEEPMS, t1);
        timer.start();
    }

    private class TimerListener implements ActionListener { //внутренний класс, имплиментирующий ActionListener
        boolean flop = false; //переключатель
        JLabel label = new JLabel();

        @Override
        public void actionPerformed(ActionEvent e) {
            flop = !flop;
            int col;
            for (int x = 0; x < Config.WIDTH; x++) //выбираем конкретную клетку на экране
                for (int y = 0; y < Config.HEIGHT; y++) {
                    if (flop) //условие переключателя
                        boxes[x][y].step1(); //вызываем step1
                    else {
                        boxes[x][y].step2(); //вызываем step2
                        //System.out.println(colAlive());

                        col = colAlive();
                        label.setText("Count of live: " + String.valueOf(col));
                        label.setVisible(true);
                        label.setFont(new Font("Serif", Font.PLAIN,26));
                        frame2.add(label, BorderLayout.CENTER);
                    }
                }
        }
    }

    int colAlive() { //подсчет кол-ва живых клеток
        int col = 0;
        for (int x = 0; x < Config.WIDTH; x++) //выбираем конкретную клетку на экране
            for (int y = 0; y < Config.HEIGHT; y++) {
                if (boxes[x][y].cell.condition.isAlive())
                   col++;
            }
        return col;
    }
}
