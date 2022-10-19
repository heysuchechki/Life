package lida.kp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Box extends JPanel { //класс множества панелек

    Cell cell;

    public Box(int x, int y){ //конструктор
        super(); //вызов родительского конструктора
        cell = new Cell();
        setBounds(x * Config.SIZE, y * Config.SIZE, Config.SIZE, Config.SIZE);
        setBackground(Config.getColor(Condition.NONE));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                cell.turn(); //переворот ячеек
            }
        });
    }

    public void setColor() { //установка цвета
        setBackground(Config.getColor(cell.condition));
    }

    void step1 () {
        cell.step1(); //вызов у ячейки метода step1
        setColor();
    }

    void step2 () {
        cell.step2(); //вызов у ячейки метода step2
        setColor();
    }
}
