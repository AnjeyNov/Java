package crew;

import javax.swing.*;
import java.awt.event.ActionListener;

abstract public class Staff {
    protected JPanel panel;
    protected String position;                                   //Должность

    public JPanel getPanel() {
        return panel;
    }

    abstract public void setUp(ActionListener listener);
    abstract public void setDown(ActionListener listener);
    abstract public void setFood(ActionListener listener);
}