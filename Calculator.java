import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {

    JTextField textField;
    String operator;
    double num1, num2, result;

    Calculator() {
        setTitle("Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        // Display field
        textField = new JTextField();
        textField.setBounds(30, 40, 320, 40);
        textField.setEditable(false);
        add(textField);

        // Buttons
        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };

        int x = 30, y = 100;
        for (int i = 0; i < buttonLabels.length; i++) {
            JButton button = new JButton(buttonLabels[i]);
            button.setBounds(x, y, 70, 40);
            add(button);
            button.addActionListener(this);

            x += 80;
            if ((i + 1) % 4 == 0) {
                x = 30;
                y += 60;
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        String input = e.getActionCommand();

        if (input.matches("[0-9]")) {
            textField.setText(textField.getText() + input);
        } else if (input.equals("C")) {
            textField.setText("");
            operator = null;
            num1 = num2 = result = 0;
        } else if (input.equals("=")) {
            num2 = Double.parseDouble(textField.getText());
            switch (operator) {
                case "+": result = num1 + num2; break;
                case "-": result = num1 - num2; break;
                case "*": result = num1 * num2; break;
                case "/": result = (num2 != 0) ? num1 / num2 : 0; break;
            }
            textField.setText(String.valueOf(result));
        } else {  // Operator pressed
            num1 = Double.parseDouble(textField.getText());
            operator = input;
            textField.setText("");
        }
    }

    public static void main(String[] args) {
        Calculator f = new Calculator();
        f.setVisible(true);
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
