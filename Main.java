import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Calculator class
class Calculator {
    private Map<String, Double> variables; // Map to store variables
    private static final String FILE_NAME = "variables.txt"; // File to store variables

    public Calculator() {
        this.variables = new HashMap<>();
        loadVariablesFromFile();
    }

    // Addition method
    public double add(double num1, double num2) {
        return num1 + num2;
    }

    // Subtraction method
    public double subtract(double num1, double num2) {
        return num1 - num2;
    }

    // Multiplication method
    public double multiply(double num1, double num2) {
        return num1 * num2;
    }

    // Division method
    public double divide(double num1, double num2) {
        if (num2 == 0) {
            System.out.println("Error: Division by zero!");
            return Double.NaN; // Not a Number
        }
        return num1 / num2;
    }

    // Store variables method
    public void storeVariables(Map<String, Double> variablesToStore) {
        variables.putAll(variablesToStore);
        saveVariablesToFile();
        System.out.println("Variables stored successfully.");
    }

    // Retrieve variable method
    public double retrieveVariable(String variableName) {
        if (!variables.containsKey(variableName)) {
            System.out.println("Variable " + variableName + " not found!");
            return Double.NaN;
        }
        return variables.get(variableName);
    }

    // Load variables from file
    private void loadVariablesFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    String name = parts[0];
                    double value = Double.parseDouble(parts[1]);
                    variables.put(name, value);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading variables from file: " + e.getMessage());
        }
    }

    // Save variables to file
    private void saveVariablesToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Map.Entry<String, Double> entry : variables.entrySet()) {
                writer.write(entry.getKey() + "=" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving variables to file: " + e.getMessage());
        }
    }
    // Inside the Calculator class
    public void deleteVariable(String variableName) {
        if (variables.containsKey(variableName)) {
            variables.remove(variableName);
            saveVariablesToFile(); // Save updated variables to file
        } else {
            System.out.println("Variable '" + variableName + "' not found!");
        }
    }
    // Inside the Calculator class
    public int getTotalVariables() {
        return variables.size();
    }

    public Map<String, Double> getVariables() {
        return variables;
    }

}

// Main class
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();
        char continueOption;
        do {
            System.out.println("Welcome to the Advanced Calculator!");
            System.out.println("Enter your choice:");
            System.out.println("1.  Arithmetic Operations (+, -, *, /)");
            System.out.println("2. Variable Operation Block");
            System.out.println("3. Trigonometric Functions");
            System.out.println("4. Exponentiation (^)");
            System.out.println("5. Square Root");
            System.out.println("6. Logarithm");
            System.out.println("7. Factorial");

            int choice = scanner.nextInt();
            String variableName;

            switch (choice) {
                case 1:
                    System.out.println("Choose the operation:");
                    System.out.println("1. Addition (+)");
                    System.out.println("2. Subtraction (-)");
                    System.out.println("3. Multiplication (*)");
                    System.out.println("4. Division (/)");
                    int operationChoice = scanner.nextInt();

                    System.out.println("Enter the number of variables to operate on:");
                    int numVariablesToOperate = scanner.nextInt();
                    double result = 0;
                    for (int i = 0; i < numVariablesToOperate; i++) {
                        System.out.println("Enter variable name:");
                        variableName = scanner.next();
                        double value = calculator.retrieveVariable(variableName);
                        if (!Double.isNaN(value)) {
                            switch (operationChoice) {
                                case 1:
                                    result += value;
                                    break;
                                case 2:
                                    if (i == 0) {
                                        result = value; // Set initial value for subtraction
                                    } else {
                                        result -= value;
                                    }
                                    break;
                                case 3:
                                    if (i == 0) {
                                        result = 1; // Set initial value for multiplication
                                    }
                                    result *= value;
                                    break;
                                case 4:
                                    if (i == 0) {
                                        result = value; // Set initial value for division
                                    } else {
                                        if (value == 0) {
                                            System.out.println("Error: Division by zero!");
                                            return; // Exit from the case to avoid division by zero
                                        }
                                        result /= value;
                                    }
                                    break;
                                default:
                                    System.out.println("Invalid operation choice!");
                                    return; // Exit from the case if operation choice is invalid
                            }
                        }
                    }
                    System.out.println("Result of operation: " + result);
                    break;


                case 2:
                    System.out.println("Operations on Variables:");
                    System.out.println("1. Store Variables");
                    System.out.println("2. Retrieve Variable");
                    System.out.println("3. Delete Variable");
                    System.out.println("4. Display Total Number Of Variables");
                    int variableOperationChoice = scanner.nextInt();
                    switch (variableOperationChoice) {
                        case 1:
                            System.out.println("How many variables do you want to store?");
                            int numVariables = scanner.nextInt();
                            Map<String, Double> variablesToStore = new HashMap<>();
                            for (int i = 0; i < numVariables; i++) {
                                System.out.println("Enter variable name:");
                                variableName = scanner.next();
                                System.out.println("Enter value:");
                                double value = scanner.nextDouble();
                                variablesToStore.put(variableName, value);
                            }
                            calculator.storeVariables(variablesToStore);
                            break;

                        case 2:
                            System.out.println("Enter variable name:");
                            variableName = scanner.next();
                            double retrievedValue = calculator.retrieveVariable(variableName);
                            if (!Double.isNaN(retrievedValue))
                                System.out.println("Retrieved value of variable " + variableName + ": " + retrievedValue);
                            break;

                        case 3:
                            System.out.println("Enter the number of variables to delete:");
                            int numVariablesToDelete = scanner.nextInt();
                            for (int i = 0; i < numVariablesToDelete; i++) {
                                System.out.println("Enter variable name:");
                                String variableToDelete = scanner.next();
                                if (calculator.retrieveVariable(variableToDelete) != Double.NaN) {
                                    // Variable exists, delete it
                                    calculator.deleteVariable(variableToDelete);
                                    System.out.println("Variable '" + variableToDelete + "' deleted successfully.");
                                } else {
                                    System.out.println("Variable '" + variableToDelete + "' not found!");
                                }
                            }
                            break;
                        case 4 :
                            System.out.println("Total number of variables: " + calculator.getTotalVariables());
                            System.out.println("Variables and their values:");
                            for (Map.Entry<String, Double> entry : calculator.getVariables().entrySet()) {
                                System.out.println(entry.getKey() + ": " + entry.getValue());
                            }
                            break;

                        default:
                            System.out.println("Invalid choice!");
                    }
                    break;

                case 3:
                    System.out.println("Choose the trigonometric function:");
                    System.out.println("1. Sine (sin)");
                    System.out.println("2. Cosine (cos)");
                    System.out.println("3. Tangent (tan)");
                    int trigFunctionChoice = scanner.nextInt();

                    System.out.println("Enter the variable name:");
                    String variablesName = scanner.next();
                    double angleInDegrees = calculator.retrieveVariable(variablesName);
                    if (!Double.isNaN(angleInDegrees)) {
                        double ans = 0;
                        switch (trigFunctionChoice) {
                            case 1:
                                ans = Math.sin(Math.toRadians(angleInDegrees));
                                break;
                            case 2:
                                ans = Math.cos(Math.toRadians(angleInDegrees));
                                break;
                            case 3:
                                ans = Math.tan(Math.toRadians(angleInDegrees));
                                break;
                            default:
                                System.out.println("Invalid trigonometric function choice!");
                                return; // Exit from the case if function choice is invalid
                        }
                        System.out.println("Result of trigonometric function: " + ans);
                    } else {
                        System.out.println("Variable '" + variablesName + "' not found!");
                    }
                    break;
                case 4:
                    System.out.println("Enter the variable name for the base:");
                    String baseVariableName = scanner.next();
                    double base = calculator.retrieveVariable(baseVariableName);
                    if (!Double.isNaN(base)) {
                        System.out.println("Enter the variable name for the exponent:");
                        String exponentVariableName = scanner.next();
                        double exponent = calculator.retrieveVariable(exponentVariableName);
                        if (!Double.isNaN(exponent)) {
                            double exresult = Math.pow(base, exponent);
                            System.out.println("Result of exponentiation: " + exresult);
                        } else {
                            System.out.println("Variable '" + exponentVariableName + "' not found!");
                        }
                    } else {
                        System.out.println("Variable '" + baseVariableName + "' not found!");
                    }
                    break;

                case 5:
                    System.out.println("Enter the variable name for the number:");
                    String numberVariableName = scanner.next();
                    double number = calculator.retrieveVariable(numberVariableName);
                    if (!Double.isNaN(number) && number >= 0) {
                        double sqrtresult = Math.sqrt(number);
                        System.out.println("Square root of " + number + ": " + sqrtresult);
                    } else {
                        System.out.println("Invalid input or variable '" + numberVariableName + "' not found!");
                    }
                    break;

                case 6:
                    System.out.println("Enter the variable name for the number:");
                    String numVariableName = scanner.next();
                    double num = calculator.retrieveVariable(numVariableName);
                    if (!Double.isNaN(num) && num > 0) {
                        double logresult = Math.log(num);
                        System.out.println("Natural logarithm of " + num + ": " + logresult);
                    } else {
                        System.out.println("Invalid input or variable '" + numVariableName + "' not found!");
                    }
                    break;
                case 7:
                    System.out.println("Enter the variable name for the number:");
                    String factorialVariableName = scanner.next();
                    double factorialNumber = calculator.retrieveVariable(factorialVariableName);
                    if (!Double.isNaN(factorialNumber) && factorialNumber >= 0 && factorialNumber == (int)factorialNumber) {
                        int number1 = (int) factorialNumber;
                        int fresult = 1;
                        for (int i = 2; i <= number1; i++) {
                            fresult *= i;
                        }
                        System.out.println("Factorial of " + number1 + ": " + fresult);
                    } else {
                        System.out.println("Invalid input or variable '" + factorialVariableName + "' not found!");
                    }
                    break;



                default:
                    System.out.println("Invalid choice!");
            }
            System.out.println("Do you want to continue? (y/n)");
            continueOption = scanner.next().charAt(0);
            while (continueOption != 'y' && continueOption != 'n') {
                System.out.println("Please enter a valid option (y/n):");
                continueOption = scanner.next().charAt(0);
            }
        } while (continueOption == 'y');

        scanner.close();
    }
}


