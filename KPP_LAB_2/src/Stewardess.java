import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Класс стюардесса
public class Stewardess extends staff{
    JLabel lbl;
    JButton FOOD;                                   // Кнопака разноса еды

    // Конструктор класса стюардесса
    Stewardess(){
        this.position = "Стюардесса";
        lbl = new JLabel(this.position);
        FOOD = new JButton();
        FOOD.setText("FOOD");
        panel = new JPanel();
        panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));
        panel.add(lbl);
        panel.add(FOOD);

    }

    public void setFood(ActionListener listener) {
        FOOD.addActionListener(listener);
    }

    public void setUp(ActionListener listener){};

    public void setDown(ActionListener listener){};
}
