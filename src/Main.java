import java.util.Scanner;
import java.util.regex.MatchResult;

public class Main {
    public static void main(String[] args) throws ParamException {
        String[] st = new String[5];
        Scanner s = new Scanner(System.in);
        Main globalResult = new Main();
        int x = 0, y = 0, amount = 0;
        boolean numberFormat = false;//
        String[] rimNumber = new String[]{"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        if (s.findInLine("(.*)\\s*([+-/*])\\s*(.*).*") == null) {
            throw new ParamException("throws Exception //т.к. строка не является математическим неравентсвом");
        } else {
            MatchResult result = s.match();
            for (int i = 0; i <= result.groupCount(); i++) {
                if (i <= result.groupCount()) {
                    st[i] = result.group(i);
                }
            }
        }
        if (st[1].matches("\\d+") && ((st[3].matches("\\d+")))) {
            x = Integer.parseInt(st[1]);
            y = Integer.parseInt(st[3]);
        } else if (st[1].matches("[XIV]+") && (st[3].matches("[XIV]+"))) {
            numberFormat = true;
            for (int i = 0; i <= rimNumber.length; i++) {
                if (i != rimNumber.length) {
                    if (st[1].equals(rimNumber[i])) {
                        x = (i + 1);
                    }
                    if (st[3].equals(rimNumber[i])) {
                        y = (i + 1);
                    }
                }
            }
        } else if ((st[1].matches("[XIV]+") && (y != 1)) || (st[3].matches("[XIV]+") && (x != 1))) {
            throw new ParamException("throws Exception //т.к. используются одновременно разные системы счисления");
        } else {
            throw new ParamException("throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        amount = globalResult.resultPlus(x, y, st);
        if (numberFormat) {
            st[0] = "";
            if (amount <= 0) {
                throw new ParamException("throws Exception //т.к. в римской системе нет отрицательных чисел");
            }
            st[0] = (amount <= 10) ? rimNumber[amount - 1] : st[0];
            st[0] = (amount == 100) ? st[0] = "C" : st[0];
            st[0] = (amount == 90) ? st[0] = "XC" : st[0];
            if ((amount >= 40) && (amount < 50)) {
                st[0] += "XL";
                amount -= 40;
            }
            if ((amount >= 50 && amount <= 89)) {
                st[0] += "L";
                amount -= 50;
            }
            x = 0;
            if (amount < 40) {
                for (int i = 10; i <= amount; i += 10) {
                    if (i != amount) {
                        st[0] += "X";
                        x += 10;
                    }
                }
                if ((x / 10) != 0) {
                    st[0] += rimNumber[amount - (x + 1)];
                }
            }
        } else {
            st[0] = Integer.toString(amount);
        }
        System.out.println(st[0]);
        s.close();
    }

    public int resultPlus(int x, int y, String[] st) throws ParamException {
        switch (st[2]) {
            case "-" -> {
                return x - y;
            }
            case "+" -> {
                return x + y;
            }
            case "/" -> {
                return x / y;
            }
            case "*" -> {
                return x * y;
            }
            default -> {
                throw new ParamException("throws Exception //Иная ошибка (при компиляции результата)");
            }
        }
    }
}