import client.*;
import dialog.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Plane {

    StaffDialog staff;

    private String model;                   // название;
    private int max_passengers;             // вместимость;
    private int max_first_passengers;       // максимальное колличество паасажиров первого класса;
    private int current_first_passengers;   // текущее колличество пассажиров первого класса;
    private int max_second_passengers;      // максимальное колличество пассажиров второго класса;
    private int current_second_passengers;
    private int current_passengers;         // текущее количесвто пассажиров;
    private String engines;                 // двигатели;
    private int max_height;                 // максимальная высота;
    private int height;                     // высота;

    private boolean flag = false;           // True/False самолет в воздухе/на земле;

    private List<FirstClass> list_of_first;     // лист пассажиров первого класса;
    private List<SecondClass> list_of_second;   // лист пассажиров второго класса;

    // элементы интерфеса
    // Элементы интерфейса
    private JPanel main_panel;                                      // Основная панель;
    private JPanel information = new JPanel();                     // Панель с информацией о самолете;

    // Поля для вывода данных о самолете
    private JLabel engines_label = new JLabel();                    // двигатели;
    private JLabel engines_text = new JLabel();
    private JLabel mp_label = new JLabel();                         // максимальное колличество пассажиров;
    private JLabel mp_text = new JLabel();
    private JLabel cp_label = new JLabel();                         // текущее количество колличество пассажиров;
    private JLabel cp_text = new JLabel();
    private JLabel mfp_label = new JLabel();                        // максимальное колличество пассажиров
    private JLabel mfp_text = new JLabel();                         // первого класса;
    private JLabel cfp_label = new JLabel();                        // текущее колличество пассажиров
    private JLabel cfp_text = new JLabel();                         // певрого класса;
    private JLabel msp_label = new JLabel();                        // максимальное колличество пассажиров
    private JLabel msp_text = new JLabel();                         // второго класса;
    private JLabel csp_label = new JLabel();                        // текущее колличество пассажиров
    private JLabel csp_text = new JLabel();                         // второго класса;
    private JLabel max_height_label = new JLabel();                 // максимальная высота
    private JLabel max_height_text = new JLabel();
    private JLabel height_label = new JLabel();                     // текущая высота
    private JLabel height_text = new JLabel();

    private JPanel first_panel = new JPanel();                        // Панель с пассажирами
    private JScrollPane first_pane = new JScrollPane(first_panel);    // Скролл бар
    private JPanel second_panel = new JPanel();                       // Панель с пассажирами
    private JScrollPane second_pane = new JScrollPane(second_panel);  // Скролл бар

    Plane() {
        initialization();
    }

    // инициализация обьекта класса Plane
    private void initialization() {
        this.staff = new StaffDialog();

        this.model = "Zephyr One";
        this.engines = "Pulsar";
        this.max_passengers = 100;
        this.current_passengers = 0;
        this.max_first_passengers = 20;
        this.current_first_passengers = 0;
        this.max_second_passengers = 80;
        this.current_second_passengers = 0;
        this.max_height = 20000;
        this.height = 0;

        this.engines_label.setText("Двигатели: ");
        this.engines_text.setText(this.engines);
        this.mp_label.setText("Вместимость: ");
        this.mp_text.setText(Integer.toString(this.max_passengers));
        this.cp_label.setText("Текущее количесвто: ");
        this.cp_text.setText(Integer.toString(this.current_passengers));
        this.mfp_label.setText("Вместимость первого класса:");
        this.mfp_text.setText(Integer.toString(this.max_first_passengers));
        this.cfp_label.setText("Текущее количество в первом классе: ");
        this.cfp_text.setText(Integer.toString(this.current_first_passengers));
        this.msp_label.setText("Вместимость второго класса: ");
        this.msp_text.setText(Integer.toString(this.max_second_passengers));
        this.csp_label.setText("Текущее количество во втором классе: ");
        this.csp_text.setText(Integer.toString(this.current_second_passengers));
        this.max_height_label.setText("Максимальная высота: ");
        this.max_height_text.setText(Integer.toString(this.max_height));
        this.height_label.setText("Текущая высота: ");
        this.height_text.setText(Integer.toString(this.height));

        main_panel = new JPanel();
        this.main_panel.setBorder(BorderFactory.createTitledBorder(this.model));
        this.main_panel.setLayout(new BoxLayout(this.main_panel, BoxLayout.Y_AXIS));
        this.information.setLayout(new GridLayout(9, 2));
        this.main_panel.add(information);

        this.information.add(this.engines_label);
        this.information.add(this.engines_text);
        this.information.add(this.mp_label);
        this.information.add(this.mp_text);
        this.information.add(this.cp_label);
        this.information.add(this.cp_text);
        this.information.add(this.mfp_label);
        this.information.add(this.mfp_text);
        this.information.add(this.cfp_label);
        this.information.add(this.cfp_text);
        this.information.add(this.msp_label);
        this.information.add(this.msp_text);
        this.information.add(this.csp_label);
        this.information.add(this.csp_text);
        this.information.add(this.max_height_label);
        this.information.add(this.max_height_text);
        this.information.add(this.height_label);
        this.information.add(this.height_text);

        //Список пассажиров
        this.list_of_first = new ArrayList<FirstClass>(20);
        this.first_pane.setBorder(BorderFactory.createTitledBorder("Список пассажиров первого класса"));
        this.first_panel.setLayout(new BoxLayout(this.first_panel, BoxLayout.Y_AXIS));
        for (int i = 0; i <= 20; i++) {
            FirstClass pas = new FirstClass();
            pas.setPlace(i);
            pas.setOutListener(this.out_first);
            this.list_of_first.add(pas);
            this.first_panel.add(pas.getPanel());
            pas.getPanel().setVisible(false);
        }
        this.first_pane.revalidate();
        this.main_panel.add(first_pane);


        this.list_of_second = new ArrayList<SecondClass>(80);
        this.second_pane.setBorder(BorderFactory.createTitledBorder("Список пассажиров второго класса"));
        this.second_panel.setLayout(new BoxLayout(this.second_panel, BoxLayout.Y_AXIS));
        for (int i = 0; i <= 80; i++) {
            SecondClass pas = new SecondClass();
            pas.setPlace(i);
            pas.setOutListener(this.out_second);
            this.list_of_second.add(pas);
            this.second_panel.add(pas.getPanel());
            pas.getPanel().setVisible(false);
        }
        this.second_pane.revalidate();
        this.main_panel.add(second_pane);

    }

    // Дествия при нащатии кнопки "Выйти из самолета" в первом классе
    ActionListener out_first = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            --current_passengers;
            --current_first_passengers;
            cp_text.setText(Integer.toString(current_passengers));
            cfp_text.setText(Integer.toString(current_first_passengers));
        }
    };

    // Дествия при нащатии кнопки "Выйти из самолета" во втором классе
    ActionListener out_second = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            --current_passengers;
            --current_second_passengers;
            cp_text.setText(Integer.toString(current_passengers));
            csp_text.setText(Integer.toString(current_second_passengers));
        }
    };

    //Набор высоты
    public void up(int how_many){
        if(!flag) {
            for (int i = 0; i <= 20; i++)
                list_of_first.get(i).setUp();
            for (int i = 0; i <= 80; i++)
                list_of_second.get(i).setUp();
            flag=true;
        }
        if(this.height==this.max_height)
            return;
        this.height+= how_many;
        this.height_text.setText(Integer.toString(this.height));
        for (int i = 0; i <= 20; i++)
            list_of_first.get(i).upHunger();
        for (int i = 0; i <= 80; i++)
            list_of_second.get(i).upHunger();
    }

    // Сброс высоты
    public void down(int how_many){
        if(this.height==0)
            return;
        this.height-= how_many;
        this.height_text.setText(Integer.toString(this.height));
        for (int i = 0; i <= 20; i++)
            list_of_first.get(i).upHunger();
        for (int i = 0; i <= 80; i++)
            list_of_second.get(i).upHunger();
        if(this.height==0)
        {
            flag = false;
            for (int i = 0; i <= 20; i++)
                list_of_first.get(i).setDown();
            for (int i = 0; i <= 80; i++)
                list_of_second.get(i).setDown();
        }
    }

    // Вернуть текущую высоту
    public int getHeight() {
        return height;
    }

    // Вернуть главную панель
    public JPanel getMain_panel() {
        return main_panel;
    }

    // Добавить пассажира первого класса
    public boolean addFirstPassenger(String name, int place){
        if(list_of_first.get(place).getName()=="Free"){
            list_of_first.get(place).upHunger();
            list_of_first.get(place).setName(name);
            list_of_first.get(place).getPanel().setVisible(true);
            ++current_passengers;
            ++current_first_passengers;
            cp_text.setText(Integer.toString(current_passengers));
            cfp_text.setText(Integer.toString(current_first_passengers));
            return true;
        }
        else{
            return false;
        }
    }

    // Добавить пассажира второго класса
    public boolean addSecondPassenger(String name, int place){
        if(list_of_second.get(place).getName()=="Free"){
            list_of_second.get(place).upHunger();
            list_of_second.get(place).setName(name);
            list_of_second.get(place).getPanel().setVisible(true);
            ++current_passengers;
            ++current_second_passengers;
            cp_text.setText(Integer.toString(current_passengers));
            csp_text.setText(Integer.toString(current_second_passengers));
            return true;
        }
        else{
            return false;
        }
    }

    // Установить слушателей на персонал
    public void setStaffListener(ActionListener up, ActionListener down, ActionListener food){
        staff.setStaffListener(up, down, food);
    }

    // накормит пассажиров
    public void eat(){
        for (int i = 0; i <= 20; i++)
            list_of_first.get(i).eat(30);
    }

}
