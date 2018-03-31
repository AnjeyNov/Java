import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Класс пилот
public class Pilot extends staff{
    private JButton UP;                                        // Кнопака набора высоты
    private JButton DOWN;                                      // Кнопка сброса высоты
    private JLabel pos;                                        // Текстовое поле с должностью

    // Конструктор принимающий в качестве параметра должность
    Pilot(String position){
        pos = new JLabel();
        this.position = position;
        pos.setText(position);
        panel = new JPanel();
        UP = new JButton();
        UP.setText("UP");
        DOWN = new JButton();
        DOWN.setText("DOWN");

        panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));
        panel.add(pos);
        panel.add(UP);
        panel.add(DOWN);
    }

    public void setUp(ActionListener listener){
        UP.addActionListener(listener);
    }

    public void setDown(ActionListener listener){
        DOWN.addActionListener(listener);
    }

    public void setFood(ActionListener listener){};
}
