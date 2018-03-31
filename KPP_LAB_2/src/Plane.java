import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// Класс самолет
public class Plane  {

    StaffDiolog staff;                                          // Инициализация экипажа

    private String name = "Airbus A380";                        // Название
    private String engines = "Турбовентиляторные двигатели";    // Тип двигателей
    private int caring = 524;                                   // Максимальная вместимость
    private int Current_quantity = 0;                           // Текущее количество пассажиров
    private int height = 0;                                     // Текушая высота
    private int max_height = 13115;                             // Максимальная высота

    private boolean flag = false;                               // True/False самолет в воздухе/на земле

    // Задали коллиество пассажиров чтобы экономить время и память при добавлении новых
    private List<Passanger> list_of_pass = new ArrayList<Passanger>(524);

    // Элементы интерфейса
    private JPanel main_panel;                                      // Основная панелб
    private JPanel information  = new JPanel();                     // Панель с информацией о самолете
    private JLabel engines_label = new JLabel();                    // Поля для вывода данных о самолете
    private JLabel engines_text = new JLabel();
    private JLabel caring_label = new JLabel();
    private JLabel caring_text = new JLabel();
    private JLabel nof_label = new JLabel();
    private JLabel nof_text = new JLabel();
    private JLabel max_height_label = new JLabel();
    private JLabel max_height_text = new JLabel();
    private JLabel height_label = new JLabel();
    private JLabel height_text = new JLabel();
    private JPanel pass_panel = new JPanel();                       // Панель с пассажирами
    private JScrollPane pass_pane = new JScrollPane(pass_panel);    // Скролл бар

    // Конструктор класса самолет
    Plane() {
        staff = new StaffDiolog();
        main_panel = new JPanel();
        this.main_panel.setBorder(BorderFactory.createTitledBorder(this.name));
        this.main_panel.setLayout(new BoxLayout(this.main_panel,BoxLayout.Y_AXIS));
        this.information.setLayout(new GridLayout(5,2));
        this.main_panel.add(information);

        // Информация о двигателях
        engines_label.setText("Тип двигателей: ");
        this.information.add(engines_label);
        engines_text.setText(engines);
        this.information.add(engines_text);

        // Информация от максимальной вместимости пассажиров
        caring_label.setText("Допустимое кол-во пассажиров: ");
        this.information.add(caring_label);
        caring_text.setText(Integer.toString(caring));
        this.information.add(caring_text);

        // Информация о текущем колличествое пассажиров
        nof_label.setText("Текущее кол-во пассажиров: ");
        this.information.add(nof_label);
        nof_text.setText(Integer.toString(0));
        this.information.add(nof_text);

        // Информация о максимальной высоте
        max_height_label.setText("Максимальная высота: ");
        this.information.add(max_height_label);
        max_height_text.setText(Integer.toString(max_height));
        this.information.add(max_height_text);

        //Информация о теущей высоте
        height_label.setText("Текущая высота: ");
        this.information.add(height_label);
        height_text.setText(Integer.toString(height));
        this.information.add(height_text);

        //Список пассажиров
        pass_pane.setBorder(BorderFactory.createTitledBorder("Список пассажиров"));
        pass_panel.setLayout(new BoxLayout(this.pass_panel, BoxLayout.Y_AXIS));
        for(int i=0; i<=524; i++)
        {
            Passanger pas = new Passanger();
            pas.setPlace(i);
            pas.setOutListener(out);
            list_of_pass.add(pas);
            pass_panel.add(pas.getPanel());
            pas.getPanel().setVisible(false);
        }
        pass_pane.revalidate();
        this.main_panel.add(pass_pane);
    }

    //Набор высоты
    public void up(int how_many){
        if(!flag) {
            for (int i = 0; i <= 524; i++)
                list_of_pass.get(i).setUp();
            flag=true;
        }
        if(this.height==this.max_height)
            return;
        this.height+= how_many;
        this.height_text.setText(Integer.toString(this.height));
    }

    // Сброс высоты
    public void down(int how_many){
        if(this.height==0)
            return;
        this.height-= how_many;
        this.height_text.setText(Integer.toString(this.height));
        if(this.height==0)
        {
            flag = false;
            for (int i = 0; i <= 524; i++)
                list_of_pass.get(i).setDown();
        }
    }

    public int getHeight() {
        return height;
    }

    public JPanel getMain_panel() {
        return main_panel;
    }

    public boolean addPassanger(String name, int place){
        if(list_of_pass.get(place).getName()=="Free"){
            list_of_pass.get(place).setName(name);
            list_of_pass.get(place).getPanel().setVisible(true);
            ++Current_quantity;
            nof_text.setText(Integer.toString(Current_quantity));
            return true;
        }
        else{
            return false;
        }
    }

    // Дествия при нащатии кнопки "Выйти из самолета"
    ActionListener out = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            --Current_quantity;                                     // Уменьшение текущего колличества пассажиров
            nof_text.setText(Integer.toString(Current_quantity));
        }
    };

    public void setStaffListener(ActionListener up, ActionListener down, ActionListener food){
        staff.setStaffListener(up, down, food);
    }
}
