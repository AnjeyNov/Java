import dialog.Pass_Dialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Airport extends JFrame {

    private Plane plane;                                   // Инициализация самолета
    private Pass_Dialog dio;                               // Инициализация диалоговое окно для регистрации пассажиров

    Airport() {
        initialization();
    }

    // инициализация оббекта класса Airport
    private void initialization(){
        plane = new Plane();
        this.setTitle("Самолет");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(plane.getMain_panel());
        this.setSize(1080, 720);
        this.setResizable(false);
        this.setVisible(true);
        dio = new Pass_Dialog();
        plane.setStaffListener(up, down, food);
        dio.setListener(in1_lst, in2_lst);
    }

    // Дествие при нажатии кнопки "Сесть в самолет"
    ActionListener in1_lst = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            check(true);
        }
    };

    // Дествие при нажатии кнопки "Сесть в самолет"
    ActionListener in2_lst = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            check(false);
        }
    };

    // Действие при нажатии кнопки набора высоты
    ActionListener up = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (plane.getHeight() == 0)
                dio.setUp();                // Установка недоступности регистрации новых пассажиров, если самолет был на земле
            plane.up(1);           // Набор высоты самолета
        }
    };

    // Действие при сбросе высоты
    ActionListener down = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            plane.down(1);         // Сброс высоты
            if (plane.getHeight() == 0)
                dio.setDown();              // Установка доступности регистрации новых пассажиров, если самолет на 0 высоте
        }
    };

    // Действие при нажатии кнопки разноса еды, которое выводит сообщение
    ActionListener food = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            plane.eat();
            JDialog food = new JDialog();
            JLabel ldl = new JLabel("Пассажиры накормлены");
            food.add(ldl);
            food.setVisible(true);
            food.pack();
        }
    };

    // сообщение об ошибке
    private void error(String message){
        JDialog error = new JDialog();
        JLabel ldl = new JLabel(message);
        error.add(ldl);
        error.setVisible(true);
        error.pack();
    }

    // проверка ввода
    private void check(boolean flag){
        //Проверка корректного ввода места
        if (dio.getPlace(flag) == 0 || dio.getPlace(flag)>80) {
            error("Введено некорректное место");
            return;
        }
        // Проверка ввода имени
        if (dio.getNewName(flag).length() == 0) {
            error("Введено некорректное имя");
            return;
        }
        // Проверка на добавление пассажира и вывод сообщения, если было запрошено занятое место
        if(flag == false) {
            if (plane.addSecondPassenger(dio.getNewName(flag), dio.getPlace(flag)))
                dio.OK(flag);
            else {
                error("Выбранное место занято, пожалуйста, выберите другое");
            }
        }
        else {
            if (plane.addFirstPassenger(dio.getNewName(flag), dio.getPlace(flag)))
                dio.OK(flag);
            else {
                error("Выбранное место занято, пожалуйста, выберите другое");
            }
        }
    }
}
