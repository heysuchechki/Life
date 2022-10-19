package lida.kp;

public enum Condition { //перечисление состояний
    NONE,
    BORN,
    LIVE,
    DIED;

    public Condition step1 (int around){ 
        return switch (this) {
            case NONE -> (around == 3) ? BORN : NONE;
            case LIVE -> (around <= 1 || around >= 4) ? DIED : LIVE;
            default -> this;
        };
    }

    public Condition step2 (){
        return switch (this) {
            case BORN -> LIVE;
            case DIED -> NONE;
            default -> this;
        };
    }

    public boolean isAlive () { //метод проверки клетки на существование
        return this == LIVE || this == DIED;
    }
}
