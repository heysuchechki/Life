package lida.kp;

public class Main {

    public static void main(String[] args) {
	    Panel panel = new Panel(); //создание панели
        javax.swing.SwingUtilities.invokeLater(panel); //запуск этой панели как компонента Swing
        //запуск происходит в отдельном потоке
    }
}
