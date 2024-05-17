import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Utilities {


    private static Scanner console;

    /**
     * Инициализация класса {@code Utilities}.
     * <p>
     * Создает {@code Scanner} для работы с read функциями.
     */
    public static void init(){
        console = new Scanner(System.in);
    }

    /**
     * Функция для чтения с консоли данных типа {@code long}.
     * <p>
     * При вводе некорректных данных повторяет запросс ввода.
     * @return Возвращает число введенное в консоли
     */
    public static long readLong(){
        while (true) {
            try {
                long num = console.nextInt();
                return num;
            } catch (InputMismatchException e) {
                System.out.println("Введен неверный тип данных!" +
                        "\n Повторите ввод:");
            } catch (NoSuchElementException e){
                System.out.println("Нет данных для чтения!" +
                        "\n Повторите ввод:");
            }
        }
    }
    /**
     * Функция для чтения с консоли данных типа {@code long}.
     * <p>
     * При вводе отрицательного числа или некорректных данных повторяет запросс ввода
     * @return Возвращает неотрицательное число введенное в консоли.
     */
    public static long readULong(){
        while (true) {
            long num = readLong();
            if(num>=0) {
                return num;
            }else {
                System.out.println("Введено отрицательное число!" +
                        "\n Повторите ввод:");
            }
        }
    }
    /**
     * Функция для чтения с консоли данных типа {@code int}.
     * <p>
     * При вводе некорректных данных повторяет запросс ввода
     * @return Возвращает число введенное в консоли
     */
    public static int readInt(){
        while (true) {
            long num = readLong();
            if (num>=Integer.MIN_VALUE&&num<=Integer.MAX_VALUE) {
                return (int) num;
            }else{
                System.out.println("Введено слишком большое число!" +
                        "\n Повторите ввод:");
            }
        }
    }
    /**
     * Функция для чтения с консоли данных типа {@code int}.
     * <p>
     * При вводе отрицательного числа или некорректных данных повторяет запросс ввода
     * @return Возвращает неотрицательное число введенное в консоли
     */
    public static int readUint(){
        while (true) {
            int num = readInt();
            if (num >= 0) {
                return num;
            } else {
                System.out.println("Введено отрицательное число!" +
                        "\n Повторите ввод:");
            }
        }
    }
    /**
     * Функция для чтения с консоли строки.
     * <p>
     * При вводе некорректных данных повторяет запросс ввода
     * @return Возвращает строку введенную в консоль
     */
    public static String readLine(){
        while (true) {
            try {
                String str = console.nextLine();
                return str;
            } catch (InputMismatchException e) {
                System.out.println("Введен неверный тип данных!" +
                        "\n Повторите ввод:");
            } catch (NoSuchElementException e){
                System.out.println("Нет данных для чтения!" +
                        "\n Повторите ввод:");
            }
        }
    }
}
