package kataTest2ndTry;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.Scanner;

public class lastTry {
    public enum Roman {
        I(1), II(2), III(3), IV(4), V(5), VI(6), VII(7), VIII(8), IX(9), X(10),
        XI(11), XII(12), XIII(13), XIV(14), XV(15), XVI(16), XVII(17), XVIII(18), XIX(19), XX(20),
        XXI(21), XXII(22), XXIII(23), XXIV(24), XXV(25), XXVI(26), XXVII(27), XXVIII(28), XXIX(29), XXX(30),
        XXXI(31), XXXII(32), XXXIII(33), XXXIV(34), XXXV(35), XXXVI(36), XXXVII(37), XXXVIII(38), XXXIX(39), XL(40),
        XLI(41), XLII(42), XLIII(43), XLIV(44), XLV(45), XLVI(46), XLVII(47), XLVIII(48), XLIX(49), L(50),
        LI(51), LII(52), LIII(53), LIV(54), LV(55), LVI(56), LVII(57), LVIII(58), LIX(59), LX(60),
        LXI(61), LXII(62), LXIII(63), LXIV(64), LXV(65), LXVI(66), LXVII(67), LXVIII(68), LXIX(69), LXX(70),
        LXXI(71), LXXII(72), LXXIII(73), LXXIV(74), LXXV(75), LXXVI(76), LXXVII(77), LXXVIII(78), LXXIX(79), LXXX(80),
        LXXXI(81), LXXXII(82), LXXXIII(83), LXXXIV(84), LXXXV(85), LXXXVI(86), LXXXVII(87), LXXXVIII(88), LXXXIX(89), XC(90),
        XCI(91), XCII(92), XCIII(93), XCIV(94), XCV(95), XCVI(96), XCVII(97), XCVIII(98), XCIX(99), C(100);

        private int arabic;

        Roman(int arabic){
            this.arabic = arabic;
        }
        public int getArabic(){
            return arabic;
        }
        public static Roman fromArabic (int arabic){
            for (Roman numeral : Roman.values()){
                if (numeral.getArabic() == arabic){
                    return numeral;
                }
            }
            throw new IllegalArgumentException("Недопустимое римское число: " + arabic);
        }
        public static int toArabic (String roman){
            return Roman.valueOf(roman).getArabic();
        }
        public static boolean isRoman (String input){
            try {
                Roman.valueOf(input);
                return true;
            } catch (IllegalArgumentException e){
                return false;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение:");
        String input = scanner.nextLine();

        int operatorIndex = -1;
        String operator = "";
        if (input.contains("+")) {
            operatorIndex = input.indexOf("+");
            operator = "+";
        } else if (input.contains("-")) {
         operatorIndex = input.indexOf("-");
         operator = "-";
        } else if (input.contains("*")) {
            operatorIndex = input.indexOf("*");
            operator = "*";
        } else if (input.contains("/")) {
            operatorIndex = input.indexOf("/");
            operator = "/";
        }

        String firstOperand;
        String secondOperand;
        String[] parts = input.split("\\"+operator);
        if (parts.length > 2){
            throw new IllegalArgumentException("Формат математической операции не удовлетворяет заданию - два операнда и один оператор");
        } else if (parts.length < 2) {
            throw new IllegalArgumentException("Cтрока не является математической операцией");
        }
        firstOperand = parts[0];
        secondOperand = parts[1];

        boolean isRoman = Roman.isRoman(firstOperand) && Roman.isRoman(secondOperand);
        if (!Roman.isRoman(firstOperand) && Roman.isRoman(secondOperand)) {
            throw new IllegalArgumentException("используются одновременно разные системы счисления");
        } else if (Roman.isRoman(firstOperand) && !Roman.isRoman(secondOperand)) {
            throw new IllegalArgumentException("используются одновременно разные системы счисления");
        }
        int num1 = isRoman ? Roman.toArabic(firstOperand) : Integer.parseInt(firstOperand);
        int num2 = isRoman ? Roman.toArabic(secondOperand) : Integer.parseInt(secondOperand);
        if (num1 > 10 || num1 < 1 || num2 > 10 || num2 < 1){
            throw new IllegalArgumentException("Допустимый диапазон чисел: от 1 до 10");
        }
        int result = 0;
        if (operator == "+"){
            result = num1+num2;
        } else if (operator == "-") {
            result = num1-num2;
        } else if (operator == "*") {
            result = num1*num2;
        } else if (operator == "/") {
            result = num1/num2;
        }
        if (isRoman){
            if (result < 0){
                System.out.println("В римской системе исчесления нет отрицательных чисел");
            } else if (result == 0) {
                throw new IllegalArgumentException("В системе римских цифр отсутствует ноль");
            }
            if (result > 0) {
                System.out.println("Ответ: " + Roman.fromArabic(result));
            }
        } else {
            System.out.println("Ответ: "+ result);
        }
    }

}
