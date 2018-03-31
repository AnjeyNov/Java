import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Диалоговое окно для регистрации пассажиров
public class Pass_Dialog extends JDialog {

    private Passanger new_pass;

    Pass_Dialog() {
        new_pass = new Passanger();
        this.setTitle("Регистрация нового пассажира");
        this.getContentPane().add(new_pass.getPanel());
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        this.setVisible(true);
        this.pack();
    }

    public void OK(){
        new_pass.OK();
    }

    public String getNewName() {
        return new_pass.getNewName();
    }

    public int getPlace(){
        return this.new_pass.getPlace();
    }

    public void setListener(ActionListener listener){
        this.new_pass.setInListener(listener);
    }

    public void setUp(){
        this.setVisible(false);
    }

    public void setDown(){
        this.setVisible(true);
    }
}
