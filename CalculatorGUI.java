import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class CalculatorGUI extends JFrame implements ActionListener {
    JButton[] operationButtons;
    JTextField res;
    Map<String, Double> variables;

    public CalculatorGUI() {
        super("Advanced Calculator");
        setLayout(new BorderLayout());

        // Panel for arithmetic operation buttons
        JPanel arithmeticPanel = new JPanel(new GridLayout(5, 1));
        String[] arithmeticLabels = {"Addition (+)", "Subtraction (-)",
                "Multiplication (*)", "Division (/)", "Trigonometric Functions", "Exponentiation (^)", "Square Root", "Logarithm", "Factorial"};
        operationButtons = new JButton[arithmeticLabels.length];
        for (int i = 0; i < arithmeticLabels.length; i++) {
            operationButtons[i] = new JButton(arithmeticLabels[i]);
            operationButtons[i].addActionListener(this);
            arithmeticPanel.add(operationButtons[i]);
        }

        // Panel for variable operations button
        JButton variableOperationButton = new JButton("Variable Operations");
        variableOperationButton.addActionListener(this);

        // Panel for result display
        res = new JTextField(10);
        res.setEditable(false);
        res.setFont(new Font("Arial", Font.BOLD, 20));
        res.setHorizontalAlignment(JTextField.RIGHT);

        add(arithmeticPanel, BorderLayout.CENTER);
        add(variableOperationButton, BorderLayout.WEST);
        add(res, BorderLayout.NORTH);

        variables = new HashMap<>();

        setSize(400, 500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        String operation = source.getText();

        switch (operation) {
            case "Addition (+)":
            case "Subtraction (-)":
            case "Multiplication (*)":
            case "Division (/)":
                performArithmeticOperation(operation);
                break;
            case "Trigonometric Functions":
                performTrigonometricOperation();
                break;
            case "Exponentiation (^)":
                performExponentiationOperation();
                break;
            case "Square Root":
                performSquareRootOperation();
                break;
            case "Logarithm":
                performLogarithmOperation();
                break;
            case "Factorial":
                performFactorialOperation();
                break;
            case "Variable Operations":
                performVariableOperations();
                break;
            default:
                break;
        }
    }

    public void performArithmeticOperation(String operation) {
        String variableName1 = JOptionPane.showInputDialog("Enter the name of variable 1:");
        Double value1 = variables.get(variableName1);
        if (value1 == null) {
            value1 = Double.parseDouble(JOptionPane.showInputDialog("Enter value for variable " + variableName1 + ":"));
            variables.put(variableName1, value1);
        }

        String variableName2 = JOptionPane.showInputDialog("Enter the name of variable 2:");
        Double value2 = variables.get(variableName2);
        if (value2 == null) {
            value2 = Double.parseDouble(JOptionPane.showInputDialog("Enter value for variable " + variableName2 + ":"));
            variables.put(variableName2, value2);
        }

        double result = 0;

        switch (operation) {
            case "Addition (+)":
                result = value1 + value2;
                break;
            case "Subtraction (-)":
                result = value1 - value2;
                break;
            case "Multiplication (*)":
                result = value1 * value2;
                break;
            case "Division (/)":
                if (value2 == 0) {
                    JOptionPane.showMessageDialog(null, "Error: Division by zero!");
                    return;
                }
                result = value1 / value2;
                break;
            default:
                break;
        }

        res.setText("Result: " + result);
    }

    public void performTrigonometricOperation() {
        String functionName = JOptionPane.showInputDialog("Enter the trigonometric function (sin, cos, tan):");
        String variableName = JOptionPane.showInputDialog("Enter the name of the variable:");
        Double angle = variables.get(variableName);
        if (angle == null) {
            angle = Double.parseDouble(JOptionPane.showInputDialog("Enter value for variable " + variableName + ":"));
            variables.put(variableName, angle);
        }

        double result = 0;

        switch (functionName.toLowerCase()) {
            case "sin":
                result = Math.sin(Math.toRadians(angle));
                break;
            case "cos":
                result = Math.cos(Math.toRadians(angle));
                break;
            case "tan":
                result = Math.tan(Math.toRadians(angle));
                break;
            default:
                JOptionPane.showMessageDialog(null, "Invalid trigonometric function!");
                return;
        }

        res.setText("Result: " + result);
    }

    public void performExponentiationOperation() {
        String baseVariableName = JOptionPane.showInputDialog("Enter the name of the base variable:");
        Double base = variables.get(baseVariableName);
        if (base == null) {
            base = Double.parseDouble(JOptionPane.showInputDialog("Enter value for variable " + baseVariableName + ":"));
            variables.put(baseVariableName, base);
        }

        String exponentVariableName = JOptionPane.showInputDialog("Enter the name of the exponent variable:");
        Double exponent = variables.get(exponentVariableName);
        if (exponent == null) {
            exponent = Double.parseDouble(JOptionPane.showInputDialog("Enter value for variable " + exponentVariableName + ":"));
            variables.put(exponentVariableName, exponent);
        }

        double result = Math.pow(base, exponent);

        res.setText("Result: " + result);
    }

    public void performSquareRootOperation() {
        String variableName = JOptionPane.showInputDialog("Enter the name of the variable:");
        Double number = variables.get(variableName);
        if (number == null) {
            number = Double.parseDouble(JOptionPane.showInputDialog("Enter value for variable " + variableName + ":"));
            variables.put(variableName, number);
        }

        if (number < 0) {
            JOptionPane.showMessageDialog(null, "Error: Square root of a negative number!");
            return;
        }

        double result = Math.sqrt(number);

        res.setText("Result: " + result);
    }

    public void performLogarithmOperation() {
        String variableName = JOptionPane.showInputDialog("Enter the name of the variable:");
        Double num = variables.get(variableName);
        if (num == null) {
            num = Double.parseDouble(JOptionPane.showInputDialog("Enter value for variable " + variableName + ":"));
            variables.put(variableName, num);
        }

        if (num <= 0) {
            JOptionPane.showMessageDialog(null, "Error: Logarithm of a non-positive number!");
            return;
        }

        double result = Math.log(num);

        res.setText("Result: " + result);
    }

    public void performFactorialOperation() {
        String variableName = JOptionPane.showInputDialog("Enter the name of the variable:");
        Double factorialNumber = variables.get(variableName);
        if (factorialNumber == null) {
            factorialNumber = Double.parseDouble(JOptionPane.showInputDialog("Enter value for variable " + variableName + ":"));
            variables.put(variableName, factorialNumber);
        }

        if (factorialNumber < 0 || factorialNumber != Math.floor(factorialNumber)) {
            JOptionPane.showMessageDialog(null, "Error: Factorial of a negative number or non-integer!");
            return;
        }

        int number = factorialNumber.intValue();
        int result = 1;
        for (int i = 2; i <= number; i++) {
            result *= i;
        }

        res.setText("Result: " + result);
    }

    public void performVariableOperations() {
        String[] variableOperations = {"Store Variables", "Retrieve Variable", "Delete Variable", "Display Total Number Of Variables"};
        String operation = (String) JOptionPane.showInputDialog(null, "Choose Variable Operation:",
                "Variable Operations", JOptionPane.QUESTION_MESSAGE, null, variableOperations, variableOperations[0]);

        switch (operation) {
            case "Store Variables":
                int numVariables = Integer.parseInt(JOptionPane.showInputDialog("How many variables do you want to store?"));
                for (int i = 0; i < numVariables; i++) {
                    String variableName = JOptionPane.showInputDialog("Enter variable name:");
                    double value = Double.parseDouble(JOptionPane.showInputDialog("Enter value for variable " + variableName + ":"));
                    variables.put(variableName, value);
                }
                break;

            case "Retrieve Variable":
                String retrieveVariableName = JOptionPane.showInputDialog("Enter variable name to retrieve:");
                Double retrievedValue = variables.get(retrieveVariableName);
                if (retrievedValue != null)
                    res.setText("Retrieved value of variable " + retrieveVariableName + ": " + retrievedValue);
                else
                    res.setText("Variable '" + retrieveVariableName + "' not found!");
                break;

            case "Delete Variable":
                String deleteVariableName = JOptionPane.showInputDialog("Enter variable name to delete:");
                if (variables.remove(deleteVariableName) != null)
                    res.setText("Variable '" + deleteVariableName + "' deleted successfully.");
                else
                    res.setText("Variable '" + deleteVariableName + "' not found!");
                break;

            case "Display Total Number Of Variables":
                int totalVariables = variables.size();
                StringBuilder variableList = new StringBuilder("Total number of variables: " + totalVariables + "\nVariables and their values:\n");
                for (Map.Entry<String, Double> entry : variables.entrySet()) {
                    variableList.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
                }
                res.setText(variableList.toString());
                break;

            default:
                JOptionPane.showMessageDialog(null, "Invalid choice!");
                break;
        }
    }

    public static void main(String[] args) {
        new CalculatorGUI();
    }
}

