import java.util.Scanner;
import java.util.regex.MatchResult;

/* Update
1. Добавлен .toUpperCase() к элементам массива. Можно писать маленькими буквами с помощью .toUpperCase() .
2. Добавлена проверка с ограничением значений из  примитивных типов данных x,y в диапозоне от [0 до 10] включительно .
3. Добавлена чистка от пробелов с помощью .trim .
4. Исправлен подсчет Римских чисел.
5. Добавлен новый метод RimNumber.
 */
public class Main {
    private static Main globalResult;

    public static void main(String[] args) throws ParamException {
        String[] st = new String[5];
        Scanner s = new Scanner(System.in);
        Main globalResult = new Main();
        int x = 0, y = 0, amount = 0;
        boolean numberFormat = false;
        String[] rimNumber = new String[]{"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};

        if (s.findInLine("(.*)\\s*([-+*/])\\s*(.*).*") == null) {
            throw new ParamException("throws Exception //т.к. строка не является математическим неравентсвом"); // Проверка на пустое значение
        } else {
            MatchResult result = s.match();
            for (int i = 0; i <= result.groupCount(); i++) { // Цикл добавления полученного в результате ответа в массив.
                if (i <= result.groupCount()) {
                    st[i] = result.group(i).trim().toUpperCase();
// В результате разбивает result на части.
                }
            }
        }
        if (st[1].matches("\\d+") && ((st[3].matches("\\d+")))) {  // Проверка 1 индека и 3 индекса на цифры.
            if ((Integer.parseInt(st[1]) >= 0) && (Integer.parseInt(st[1]) <= 10)) {// Проверка 1 значения
                x = Integer.parseInt(st[1]);
            } else {
                throw new ParamException("throws Exception //Калькулятор должен принимать на вход числа от 1 до 10 включительно, не более.");
            }
            if ((Integer.parseInt(st[3]) >= 0) && (Integer.parseInt(st[3]) <= 10)) { // Проверка 2 значения
                y = Integer.parseInt(st[3]);
            } else {
                throw new ParamException("throws Exception //Калькулятор должен принимать на вход числа от 1 до 10 включительно, не более.");
            }
            System.out.println(resultPlus(x, y, st));
        } else if (st[1].toUpperCase().matches("[XIV]+") && (st[3].toUpperCase().matches("[XIV]+"))) {
            RimNumber(st, x, y, rimNumber, amount);
            System.out.println(RimNumber(st, x, y, rimNumber, amount));
        } else if ((st[1].toUpperCase().matches("[XIV]+") && (st[3].matches("\\d+"))) ||
                (st[3].toUpperCase().matches("[XIV]+") && (st[1].matches("\\d+")))) { // проверка массива st с  индексом 1 на int значение и индекс 3 на String значение
            throw new ParamException("throws Exception //т.к. используются одновременно разные системы счисления");
        } else {
            throw new ParamException("throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        s.close();
    }

    public static String RimNumber(String[] st, int x, int y, String[] rimNumber, int amount) throws ParamException {
        String values = "";
        st[0] = "";
        for (int i = 0; i <= rimNumber.length; i++) { //  Цикл, в котором  выполняется проверка на равенство массива st с индексом 1,3 в массиве rimNumber.
            if (i != rimNumber.length) {
                if (rimNumber[i].equals(st[1])) {
                    x = (i + 1);
                }
                if (rimNumber[i].equals(st[3])) {
                    y = (i + 1);
                }

            }
        }

        amount = resultPlus(x, y, st);
        x = 0;
        y = (int) Math.ceil(amount % 10);
        if (amount <= 0) {
            throw new ParamException("throws Exception //т.к. в римской системе нет отрицательных чисел");
        }

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

        if (amount < 40) {
            for (int j = 10; j <= amount; j += 10) {
                if (j <= amount) {
                    st[0] += "X";
                    x += 10;
                }
            }
            while (y % 10 != 0) {
                y -= y;
                values += rimNumber[amount - (x + 1)];
            }
        }

        return st[0] + values;
    }

    public static int resultPlus(int x, int y, String[] st) throws ParamException {
        // метод логичесиких операций.
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