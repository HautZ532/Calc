import java.util.InputMismatchException;
import java.util.Scanner;


public class Calc {
    static Scanner scanner = new Scanner(System.in);
    static int num1;
    static int num2;
    static boolean h;
    static boolean j;
    static String operation;
    static int result;

    public static void main(String[] args) {
        System.out.println("Введите выражение 'a+b, a-b, a*b, a/b', где a и b целые арабские или римские числа от 0 до 10");
//      Считываем строку userInput которую ввёл пользователь
        String userInput = scanner.nextLine();
//      Создаём пустой символьный массив длиной 10 символов:  under_char
        char[] under_char = new char[10];
//      Заполняем символьный массив символами строки которую ввел пользователь и по ходу ловим знак операции
        for (int i = 0; i < userInput.length(); i++) {
            under_char[i] = userInput.charAt(i);
            if (under_char[i] == '+') {
                operation = "+";
            }
            if (under_char[i] == '-') {
                operation = "-";
            }
            if (under_char[i] == '*') {
                operation = "*";
            }
            if (under_char[i] == '/') {
                operation = "/";
            }
        }
        String under_charString = String.valueOf(under_char);
//      Разделяем строку
        String[] blacks = under_charString.split("[/*+-]");
        if (blacks.length <= 1){
            System.out.println("Cтрока не является математической операцией");
            System.exit(0);
        } else if (blacks.length > 2) {
            System.out.println("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            System.exit(0);
        }
//      Чистим от пробелов
        String stable00 = blacks[0].trim();
        String stable01 = blacks[1].trim();
//      Проверяем, арабские или римские символы содержат строки
        for(char c : stable00.toCharArray())
            h = Character.isDigit(c);
        for(char c : stable01.toCharArray())
            j = Character.isDigit(c);
//      Проверяем на принодлежность к арабской системе счисления
        if (h && j) {
            num1 = Integer.parseInt(stable00);
            num2 = Integer.parseInt(stable01);
//      Проверяем чтобы числа попадали в диапозон от 0-10
            if (num1 > 10 || num2 > 10) {
                System.out.println("Чиcла должны быть от 0 до 10");
            }
            else if (operation.equals("/") && num2 == 0) {
                System.out.println("Делить на ноль нельзя");
            }
            else {
                result = calculated(num1, num2, operation);
                System.out.println("Результат для арабских цифр");
                System.out.println(num1 + " " + operation + " " + num2 + " = " + result);
        }
//      Проверяем на принодлежность к римской системе счисления
        } else if (!h && !j){
            num1 = romanToNumber(stable00);
            num2 = romanToNumber(stable01);
            if (num1 < 0 || num2 <0) {
                System.out.println("Чиcла должны быть от 0 до 10");
            }
            else {
                result = calculated(num1, num2, operation);
                String resultRoman = convertNumToRoman(result);
                System.out.println(num1);
                System.out.println(num2);
                System.out.println("Результат для римских цефр");
                System.out.println(stable00 + " " + operation + " " + stable01 + " = " + resultRoman);
            }
            }
        else {
            System.out.println("Используются одновременно разные системы счисления");
        }
        }

    public static int calculated (int num1, int num2, String op) { //Выполнение математической
        int result = 0;                                            //операции
        switch (op) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                result = num1 / num2;
                break;
        }
        return result;
    }

    private static int romanToNumber (String roman) { //Конвертация римских цифр в арабские
        try {                                         //для подсчета
            switch (roman) {
                case "I":
                    return 1;
                case "II":
                    return 2;
                case "III":
                    return 3;
                case "IV":
                    return 4;
                case "V":
                    return 5;
                case "VI":
                    return 6;
                case "VII":
                    return 7;
                case "VIII":
                    return 8;
                case "IX":
                    return 9;
                case "X":
                    return 10;
            }
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Неверный формат данных");
        }
        return -1;
    }

    private static String convertNumToRoman (int numArabian) { //Конвертация результата к формату
                                                               //римской системе счисления
        String[] roman = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };
        return roman[numArabian];
    }

}