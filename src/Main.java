import objs.Bank;
import objs.Branch;
import objs.CashMachine;

import java.util.Objects;

public class Main {
    /**
     * Текущий банк с которым выполняется работа
     */
    static Bank bank;
    /**
     * Функция для создания нового банка. Если банк не пустой, то он зарание удаляется
     */
    static void createBank(){
        dispose();
        System.out.println("Введите название банка:");
        String bankName = Utilities.readLine();
        bank = new Bank(bankName);
    }
    /**
     * Функция для вызова меню добавления нового филиала в банке
     */
    static void addBranch(){//TODO в идеале переписать
        long branchId;
        String branchAddress;
        while (true) {// Ввод id
            System.out.println("Введите id филиала:");
            branchId = Utilities.readULong();
            if(bank.findBranch(branchId)==null) break;
            else System.out.println(
                    "Данный id занят другим филиалом!" +
                            "\nВведите id повторно:"
            );
        }
        System.out.println("Введите адрес филиала:");
        branchAddress = Utilities.readLine();
        Branch temp = bank.getBranch();
        if(temp == null) bank.setBranch(new Branch(branchId,branchAddress));//проверка на заполненость заголовка
        else {
            System.out.println(
                    "Куда добавить филиал" +
                    "\n1. В конец" +
                    "\n2. В начало" +
                    "\n3. Перед" +
                    "\n4. После"
            );
            int enter = Utilities.readInt();
            Branch find;
            switch (enter){
                case 1:
                    bank.addBranch(new Branch(branchId, branchAddress));
                    break;
                case 2:
                    bank.setBranch(new Branch(branchId,branchAddress));
                    bank.getBranch().setNext(temp);
                    break;
                case 3:
                    System.out.println("Введите id филиала:");
                    branchId = Utilities.readULong();
                    find = bank.findBranch(branchId);
                    if(find!=null){
                        bank.addBranch(find,new Branch(branchId, branchAddress));
                    }else System.out.println("Ненайден филиал с данным id!");

                case 4:
                    System.out.println("Введите id филиала:");
                    branchId = Utilities.readULong();
                    find = bank.findBranch(branchId);
                    if(find!=null){
                        if(find==bank.getBranch()){
                            bank.setBranch(new Branch(branchId,branchAddress));
                            bank.getBranch().setNext(temp);
                        }else bank.addBranch(find,bank.getPrevBranch(find));
                    }else System.out.println("Ненайден филиал с данным id!");
                    break;
            }
        }
    }
    /**
     * Функция для вызова меню поиска филиала
     * @return филиал
     */
    static Branch findBranch(){
        System.out.println(
                "Выберите по каким данным искать филилал" +
                        "\n1. По id" +
                        "\n2. По адрессу" +
                        "\n3. По id и адресу"
        );
        int enter = Utilities.readInt();
        Branch find;
        long branchId;
        String branchAddress;
        switch (enter){
            case 1:
                System.out.println("Введите id филиала:");
                branchId = Utilities.readULong();
                find = bank.findBranch(branchId);
                if(find!=null) return find;
                else System.out.println("Не найден филиал с данным id!");
                break;
            case 2:
                System.out.println("Введите адрес филиала:");
                branchAddress = Utilities.readLine();
                find = bank.findBranch(branchAddress);
                if(find!=null) return find;
                else System.out.println("Не найден филиал с данным адресом!");
                break;
            case 3:
                System.out.println("Введите id филиала:");
                branchId = Utilities.readULong();
                System.out.println("Введите адресс филиала:");
                branchAddress = Utilities.readLine();
                find = bank.findBranch(branchId,branchAddress);
                if(find!=null) return find;
                else System.out.println("Не найден филиал с данным и адресом!");
                break;
        }
        return null;
    }
    /**
     * Функция для удаления филиала банка с вызовом меню поиска филиала
     */
    static void deleteBranch(){
        Branch branch = findBranch();
        if(branch != null) bank.deleteBranch(branch);
    }

    /**
     * Функция для создания банкомата в филиале
     */
    static void addCashMachine(){
        System.out.println("С каким филиалом работаем?");
        Branch branch = findBranch();
        if(branch == null) return;
        long cashMachineId;
        while (true) {// Ввод id
            System.out.println("Введите id банкомата:");
            cashMachineId = Utilities.readULong();
            if(branch.findCashMachine(cashMachineId)==null) break;
            else System.out.println(
                    "Данный id занят другим банкаматом!" +
                            "\nВведите id повторно:"
            );
        }
        if(branch.getCashMachine()==null) branch.setCashMachine(new CashMachine(cashMachineId));
        else branch.addCashMachine(new CashMachine(cashMachineId));
    }
    static void deleteCashMachine(){
        System.out.println("С каким филиалом работаем?");
        Branch branch = findBranch();
        if(branch == null) return;
        branch.delCashMachine();
    }
    /**
     * Удаление банка с предупреждением.
     */
    static void dispose(){
        if(bank!=null) {
            System.out.println(
                    "У вас есть не сохраненые данные." +
                    "\nХотите продолжить?(y/n):"
            );
            String enter = Utilities.readLine();
            if(!Objects.equals(enter, "y")) return;
            bank.dispose();
            bank = null;
        }
    }

    public static void main(String[] args) {
        Utilities.init();
        boolean run = true;
        while(run){

            System.out.println(
                    "\n1.  Создать Банк" +
                    "\n2.  Добавить филиал" +
                    "\n3.  Удалить филиал" +
                    "\n4.  Добавить банкомат" +
                    "\n5.  Удалилить банкомат" +
                    "\n6.  Поиск филиала" +
                    "\n7.  Поиск банкомата" +
                    "\n8.  Удалить банк" +
                    "\n9.  Загрузить из файла" +
                    "\n10. Сохранить в файл" +
                    "\n11. Вывести всю структуру" +
                    "\n12. Завершить работу"
            );

            int enter = Utilities.readInt();

            switch (enter){

                case 1:
                    createBank();
                    break;
                case 2:
                    addBranch();
                    break;
                case 3:
                    deleteBranch();
                    break;
                case 4:
                    addCashMachine();
                    break;
                case 5:
                    deleteCashMachine();
                    break;
                case 6:
                    if(findBranch()!=null) System.out.println("Филиал найден");
                    break;
                case 7:
                    System.out.println("С каким филиалом работаем?(all-для просмотра всех филиалов)");
                    String str = Utilities.readLine();
                    if(Objects.equals(str, "all")){
                        System.out.println("Введите id банкомата:");
                        long cashMachineId = Utilities.readULong();
                        Branch temp = bank.getBranch();
                        while (temp != null){
                            if(temp.findCashMachine(cashMachineId)!=null){
                                System.out.println("В филилале №"+temp.getId()+" по адресу "+temp.getAddress()+ "найден банкомас c данным id");
                            }
                            temp = temp.getNext();
                        }
                        System.out.println("Конец поиска");
                        break;
                    }
                    Branch branch = findBranch();
                    if(branch == null) return;
                    System.out.println("Введите id банкомата:");
                    long cashMachineId = Utilities.readULong();
                    if(branch.findCashMachine(cashMachineId)!=null) System.out.println("Банкомат найден найден");
                    break;
                case 8:
                    dispose();
                    break;
                case 9:
                    dispose();
                    bank = Utilities.readBank();
                    break;
                case 10:
                    if(bank!=null) Utilities.saveBank(bank);
                    break;
                case 11:
                    StringBuilder text = new StringBuilder();
                    text.append("Банк ");
                    text.append(bank.getName());
                    text.append('\n');

                    Branch br = bank.getBranch();
                    CashMachine cashMachine;
                    while (br!=null){
                        text.append('\t');
                        text.append("Филиал №");
                        text.append(br.getId());
                        text.append(" по адресу ");
                        text.append(br.getAddress());
                        text.append('\n');
                        cashMachine = br.getCashMachine();
                        while (cashMachine!=null){
                            text.append('\t');text.append('\t');
                            text.append(cashMachine.getId());
                            cashMachine = cashMachine.getNext();
                            text.append('\n');
                        }
                        text.append("_______________________________");
                        text.append('\n');
                        br = br.getNext();
                    }
                    text.append('\n');
                    System.out.println(text);
                    break;
                case 12:
                    System.exit(0);
                    break;

            }

        }

    }
}