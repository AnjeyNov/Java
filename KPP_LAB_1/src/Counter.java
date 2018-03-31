import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.*;
import org.eclipse.swt.widgets.*;


public class Counter {
    public static void main(String[] args) {
        //Создаем объект Display для связи SWT
        //с дисплеем операционной системы
        Display display = new Display();

        //Создаем окно программы
        Shell shell = new Shell(display);
        GridLayout lay = new GridLayout();
        lay.numColumns = 2;
        shell.setLayout(lay);
        shell.setText("Counter");

        //Добавляем кнопочки и поля
        Label msg1 = new Label(shell, SWT.NONE);
        msg1.setText("Input word:");
        Text word = new Text(shell,SWT.RIGHT);
        word.setTextLimit(33);
        Label msg2 = new Label(shell, SWT.NONE);
        msg2.setText("Input symbol:");
        Text symbol = new Text(shell,SWT.LEFT);
        symbol.setTextLimit(1);
        Button button = new Button(shell, SWT.BUTTON2);
        button.setText("Count");
        Label msg3 = new Label(shell, SWT.NONE);
        msg3.setText("Just do it");
        Button LUCIK = new Button(shell, SWT.BUTTON2);
        LUCIK.setText("Луцик в сердце");

        //Делаем обработку событий
        SelectionListener listener1 = new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                msg3.setText("БГУИР - знания и стиль жизни (нет)");
                shell.pack();
            }

            @Override
            public void widgetDefaultSelected(SelectionEvent selectionEvent) {

            }
        };
        SelectionListener listener = new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                if(word.getText().length()==0 || symbol.getText().length()==0)
                    msg3.setText("Вы что-то забыли");
                else {
                    int count = 0;
                    for (int i = 0; i < word.getText().length(); i++)
                        if (symbol.getText().charAt(0) == word.getText().charAt(i))
                            count++;
                    msg3.setText("The result is " + count);
                }
                shell.pack();
            }

            @Override
            public void widgetDefaultSelected(SelectionEvent selectionEvent) {
                //Как-то такое себе
            }
        };
        LUCIK.addSelectionListener(listener1);
        button.addSelectionListener(listener);

        shell.pack();
        shell.open();

        //Обработка закрытия окна
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        //Ресурсы операционной системы
        //должны быть освобождены
        display.dispose();
    }
}
