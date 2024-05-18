import objs.Bank;
import objs.Branch;
import objs.CashMachine;

import java.io.*;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

/**
 * Класс для всяких инструментов по типу чтения с консоли и работе с файлами
 */
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
                return console.nextInt();
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
        if (console.hasNext()) console.nextLine();
        while (true) {
            try {
                return console.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Введен неверный тип данных!" +
                        "\n Повторите ввод:");
            } catch (NoSuchElementException e){
                System.out.println("Нет данных для чтения!" +
                        "\n Повторите ввод:");
            }
        }
    }

    public static void saveBank(Bank bank) {
        System.out.println("Введите название файла:");
        String fileName = readLine();
        StringBuilder text = new StringBuilder();
        File file = new File(fileName);
        if(file.exists()){
            file.delete();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        text.append(bank.getName());
        text.append('\n');
        Branch branch = bank.getBranch();
        CashMachine cashMachine;
        while (branch!=null){
            text.append(branch.getId());
            text.append('#');
            text.append(branch.getAddress());
            text.append(':');
            cashMachine = branch.getCashMachine();
            while (cashMachine!=null){
                text.append(cashMachine.getId());
                text.append('#');
                cashMachine = cashMachine.getNext();
            }
            text.append('!');
            text.append('\n');
            branch = branch.getNext();
        }
        text.append('\n');
        text.append('-');
        System.out.println(text);
        try(FileWriter writer = new FileWriter(fileName,false)){
            writer.write(text.toString());
        }catch (IOException ignored){

        }

    }
    public static Bank readBank() {
        System.out.println("Введите название файла:");
        String fileName = readLine();
        File file = new File(fileName);
        Bank bank = null;
        if (file.exists()) {
            String line;
            Branch branch;
            try {
                FileReader fr = new FileReader(fileName);
                BufferedReader reader = new BufferedReader(fr);
                line = reader.readLine();
                bank = new Bank(line);
                line = reader.readLine();
                String[] arr;

                while (!Objects.equals(line, "-")) {
                    arr = line.split(":")[0].split("#");
                    branch = new Branch(Long.parseLong(arr[0]), arr[1]);
                    arr = line.split(":")[1].split("#");
                    for (String el : arr) {
                        if (Objects.equals(el, "!")) break;
                        branch.addCashMachine(new CashMachine(Long.parseLong(el)));
                    }
                    bank.addBranch(branch);
                    line = reader.readLine();

                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        }
        return bank;
    }
}
