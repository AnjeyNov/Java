package dialog;
import client.FirstClass;
import client.SecondClass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// Диалоговое окно для регистрации пассажиров
public class Pass_Dialog extends JDialog {

    private FirstClass firstClass;
    private SecondClass secondClass;

    public Pass_Dialog() {
       this.initialization();
    }

    // flag = true - первый класс
    // flag = false - второй класс

    // успешная авторизация
    public void OK(boolean flag){
        if(flag)
            firstClass.OK();
        else
            secondClass.OK();
    }

    // получить введенное имя
    public String getNewName(boolean flag) {
        if(flag)
            return firstClass.getNewName();
        else
            return secondClass.getNewName();
    }

    // получить место
    public int getPlace(boolean flag){
        if(flag)
            return this.firstClass.getPlace();
        else
            return this.secondClass.getPlace();
    }

    // установить слушателей на кнопку сесть в самолет для первого и второго класса соответственно
    public void setListener(ActionListener listener1, ActionListener listener2){
        this.firstClass.setInListener(listener1);
        this.secondClass.setInListener(listener2);
    }

    // заблокировать регистрацию
    public void setUp(){
        this.setVisible(false);
    }

    // разблокировать регистрацию
    public void setDown(){
        this.setVisible(true);
    }

    // инициализация объекта класса
    private void initialization(){
        firstClass = new FirstClass();
        secondClass = new SecondClass();
        firstClass.delhunger();
        secondClass.delhunger();
        GridLayout lay = new GridLayout();
        lay.setColumns(1);
        lay.setRows(2);
        this.setTitle("Регистрация нового пассажира");
        this.setLayout(lay);
        this.add(firstClass.getPanel());
        firstClass.getPanel().setBorder(BorderFactory.createTitledBorder("Первый класс"));
        this.add(secondClass.getPanel());
        secondClass.getPanel().setBorder(BorderFactory.createTitledBorder("Второй класс"));
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        this.setVisible(true);
        this.pack();
    }
}
