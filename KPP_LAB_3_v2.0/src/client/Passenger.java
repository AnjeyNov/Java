package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Класс пассажир
public abstract class Passenger {
    String name = "No name";
    int place = 0;
    int hunger = 20;
    JPanel panel;
    JButton in = new JButton("Сесть в самолет");
    JButton out = new JButton("Выйти из самолета");
    JLabel name_ldl = new JLabel("ФИО");
    JTextField in_name = new JTextField();
    JLabel place_lbl = new JLabel("Место: ");
    JTextField in_place = new JTextField();
    JLabel hunger_lbl = new JLabel("Голод: ");
    JLabel hunger_lbl_2 = new JLabel();


    Passenger() {
        initialization();
    }

    ActionListener out_lst = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            name="Free";
            hunger = 20;
            name_ldl.setText(name);
            hunger_lbl.setText("Голод: " + Integer.toString(hunger));
            panel.setVisible(false);
        }
    };

    // Вернуть текущее имя
    public String getName() {
        return name;
    }

    // Вернтуь введенное имя
    public String getNewName(){
        return in_name.getText();
    }

    // Вернуть место
    public int getPlace() {
        int size=in_place.getText().length();
        for(int i = 0; i< size; i++)
            if(!('0' <= in_place.getText().charAt(i) && in_place.getText().charAt(i) <= '9'))
                return 0;
        if(!(1<=Integer.parseInt(in_place.getText()) && Integer.parseInt(in_place.getText()) <=524))
            return 0;
        return Integer.parseInt(in_place.getText());
    }

    // Установить имя (Зайти на самолет)
    public void setName(String name) {
        this.name = name;
        this.name_ldl.setText(name);
        this.out.setEnabled(true);
        this.panel.setVisible(true);
    }

    // Задать место
    public void setPlace(int place) {
        this.name = "Free";
        this.in_place.setVisible(false);
        this.in_name.setVisible(false);
        this.place = place;
        this.place_lbl.setText("Место: " + Integer.toString(this.place));
        this.in.setEnabled(false);
        this.out.setEnabled(false);
    }

    // Вернуть панель
    public JPanel getPanel() {
        return panel;
    }

    // Установить кслушателя на кнопку посадки
    public void setInListener(ActionListener lstr) {
        this.in.addActionListener(lstr);
    }

    // Установить слушателя на кнопку высадки
    public void setOutListener(ActionListener lstr) {
        this.out.addActionListener(lstr);
    }

    // Очистить форму ввода
    public void OK() {
        this.in_name.setText("");
        this.in_place.setText("");
    }

    // Заблокировать кнопку высодки при взлет
    public void setUp(){
        this.out.setEnabled(false);
    }

    // Разблокировать кнопку высадки при посадке
    public void setDown(){
        this.out.setEnabled(true);
    }

    public void upHunger(){
        if(hunger!=100 && name != "Free"){
            hunger++;
            hunger_lbl.setText("Голод: " + Integer.toString(hunger));
            return;
        }
        if(name == "Free") {
            hunger = 20;
            hunger_lbl.setText("Голод: 20");
        }

    }

    public void initialization(){
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEtchedBorder());
        GridLayout lay = new GridLayout();
        lay.setColumns(2);
        lay.setRows(4);
        panel.setLayout(lay);
        panel.add(name_ldl, lay);
        panel.add(in_name,lay);
        panel.add(place_lbl, lay);
        panel.add(in_place, lay);
        panel.add(hunger_lbl, lay);
        panel.add(hunger_lbl_2, lay);
        hunger_lbl_2.setEnabled(false);
        panel.add(in, lay);
        panel.add(out, lay);
        in.setEnabled(true);
        out.setEnabled(false);
        out.addActionListener(out_lst);
        this.panel.setSize(360,1080);

        in_name.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                place_lbl.transferFocus();
            }
        });

        in_place.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                in.doClick();
                name_ldl.transferFocus();
            }
        });
    }

    public void delhunger(){
        this.hunger_lbl.setVisible(false);
        this.hunger_lbl_2.setVisible(false);
    }
}
