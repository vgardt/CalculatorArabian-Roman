import java.util.Scanner;

public class Calculator {

    static Converter converter = new Converter();

    public static void main(String[] args) throws Exception {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите пример:");
            String expression = scanner.nextLine();
            System.out.println(parse(expression));
        }
    }

    public static String parse(String expression) throws Exception {
        String oper;
        int result;

        String[] operands = expression.split("[+-/*]");

        if (operands.length != 2) throw new Exception("Должно быть только два операнда");
        oper = selectOperation(expression);

        if (converter.isRoman(operands[0]) == converter.isRoman(operands[1])) {
            int num1, num2;
            boolean isRoman = converter.isRoman(operands[0]);
            if (isRoman) {
                num1 = converter.romanToInt(operands[0]);
                num2 = converter.romanToInt(operands[1]);

            } else {
                num1 = Integer.parseInt(operands[0]);
                num2 = Integer.parseInt(operands[1]);
            }
              if (!(num1>=1 && num1<=10)){
                throw new Exception("Первый операнд должен быть от 1 до 10 или от I до X включительно");
            }
            if (!(num2>=1 && num2<=10)){
                throw new Exception("Второй операнд должен быть от 1 до 10 или от I до X включительно");
            }
            assert oper != null;
            result = calc(num1, num2, oper);
            if (isRoman) {
                if (num1 < num2 && oper.equals("-")) {
                    throw new Exception("в римской системе нет отрицательных чисел");
                } else {
                    return converter.intToRoman(result);
                }
            }

        } else {
            throw new Exception("Используются одновременно разные системы счисления");
        }
        return String.valueOf(result);
    }

    static String selectOperation(String expression) {
        if (expression.contains("+")) return "+";
        else if (expression.contains("-")) return "-";
        else if (expression.contains("*")) return "*";
        else if (expression.contains("/")) return "/";
        else return null;
    }

    static int calc(int a, int b, String oper) {
        return switch (oper) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            default -> a / b;
        };
    }
}

