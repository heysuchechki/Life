package lida.kp;

import java.util.ArrayList;

public class Cell { //класс ячейки
    ArrayList<Cell> neighbor; //список соседей
    Condition condition; //состояние ячейки

    public Cell() { //конструктор
        condition = Condition.NONE;
        neighbor = new ArrayList<>();
    }

    void addNeighbor(Cell cell) { //добавление соседей
        neighbor.add(cell);
    }

    void step1() {
        int around = countNeighborCells();
        condition = condition.step1(around);
    }

    void step2() {
        condition = condition.step2();
    }

    int countNeighborCells() { //метод для вычисления кол-ва соседей
        int count = 0;
        for (Cell cell : neighbor)
            if (cell.condition.isAlive())
                count++;
        return count;
    }

    void turn() { //метод для переворота соседей
        for (Cell cell : neighbor)
            cell.condition = cell.condition.isAlive() ? Condition.NONE : Condition.LIVE;
    }
}
