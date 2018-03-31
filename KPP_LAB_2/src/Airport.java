import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Airport extends JFrame {

    private Plane plane;                                   // Инициализация самолета
    private Pass_Dialog dio;                               // Инициализация диалоговое окно для регистрации пассажиров

    Airport(){
        plane = new Plane();
        this.setTitle("Самолет");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(plane.getMain_panel());
        this.setSize(1080,720);
        this.setResizable(false);
        this.setVisible(true);
        dio = new Pass_Dialog();
        plane.setStaffListener(up, down ,food);
        dio.setListener(in_lst);
    }

    // Дествие при нажатии кнопки "Сесть в самолет"
    ActionListener in_lst = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //Проверка корректного ввода места
            if(dio.getPlace()==0){
                JDialog error = new JDialog();
                JLabel ldl = new JLabel("Введено некорректное место");
                error.add(ldl);
                error.setVisible(true);
                error.pack();
                return;
            }
            // Проверка ввода имени
            if(dio.getNewName().length()==0){
                JDialog error = new JDialog();
                JLabel ldl = new JLabel("Введено некорректное имя");
                error.add(ldl);
                error.setVisible(true);
                error.pack();
                return;
            }
            // Проверка на добавление пассажира и вывод сообщения, если было запрошено занятое место
            if (plane.addPassanger(dio.getNewName(), dio.getPlace()))
                dio.OK();
            else {
                JDialog error = new JDialog();
                JLabel ldl = new JLabel("Выбранное место занято, пожалуйста, выберите другое");
                error.add(ldl);
                error.setVisible(true);
                error.pack();
            }
        }
    };


    // Действие при нажатии кнопки набора высоты
    ActionListener up = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(plane.getHeight()==0)
                dio.setUp();                // Установка недоступности регистрации новых пассажиров, если самолет был на земле
            plane.up(1);           // Набор высоты самолета
        }
    };

    // Действие при сбросе высоты
    ActionListener down = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            plane.down(1);         // Сброс высоты
            if(plane.getHeight()==0)
                dio.setDown();              // Установка доступности регистрации новых пассажиров, если самолет на 0 высоте
        }
    };


    // Действие при нажатии кнопки разноса еды, которое выводит сообщение
    ActionListener food = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JDialog food = new JDialog();
            JLabel ldl = new JLabel("Пассажиры накормлены");
            food.add(ldl);
            food.setVisible(true);
            food.pack();
        }
    };


}
