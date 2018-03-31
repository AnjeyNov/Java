import javax.swing.*;
import java.awt.event.ActionListener;

// Экипаж самолетв
public class StaffDiolog extends JDialog {

    staff captain = new Pilot("Капитан");               // Инициализация капитана
    staff second_pilot = new Pilot("Второй пилот");     // Инициализация второго пилота
    staff stw = new Stewardess();                         // Инициализация стюардессы

    // Конструктор экипажа
    StaffDiolog(){
        this.setTitle("Экипаж");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.add(captain.getPanel());
        this.add(second_pilot.getPanel());
        this.add(stw.getPanel());
        this.pack();
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        this.setVisible(true);
    }

    public void setStaffListener(ActionListener up, ActionListener down, ActionListener food){
        captain.setUp(up);
        captain.setDown(down);
        second_pilot.setUp(up);
        second_pilot.setDown(down);
        stw.setFood(food);
    }
}