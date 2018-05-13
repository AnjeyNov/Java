package crew;

import javax.swing.*;
import java.awt.event.ActionListener;

// Класс стюардесса
public class Stewardess extends Staff {
    JLabel lbl;
    JButton FOOD;                                   // Кнопака разноса еды

    // Конструктор класса стюардесса
    public Stewardess() {
        initialization();
    }

    public void initialization() {
        this.position = "Стюардесса";
        lbl = new JLabel(this.position);
        FOOD = new JButton();
        FOOD.setText("FOOD");
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEtchedBorder());
        panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));
        panel.add(lbl);
        panel.add(FOOD);
    }

    public void setFood(ActionListener listener) {
        FOOD.addActionListener(listener);
    }

    public void setUp(ActionListener listener) {
    }

    public void setDown(ActionListener listener) {
    }

}
