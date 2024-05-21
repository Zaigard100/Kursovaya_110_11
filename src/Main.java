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
        if(dispose()) {
            System.out.println("Введите название банка:");
            String bankName = Utilities.readLine();
            bank = new Bank(bankName);
        }
    }
    /**
     * Функция для вызова меню добавления нового филиала в банке
     */
    static void addBranch(){//TODO в идеале переписать
        long branchId,branchIdNew;
        String branchAddress;
        while (true) {// Ввод id
            System.out.println("Введите id нового филиала:");
            branchIdNew = Utilities.readULong();
            if(bank.findBranch(branchIdNew)==null) break;
            else System.out.println(
                    "Данный id занят другим филиалом!" +
                            "\nВведите id повторно:"
            );
        }
        Branch temp = bank.getBranch();
        if(temp == null) bank.setBranch(new Branch(branchIdNew));//проверка на заполненость заголовка
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
                    bank.addBranch(new Branch(branchIdNew));
                    break;
                case 2:
                    bank.setBranch(new Branch(branchIdNew));
                    bank.getBranch().setNext(temp);
                    break;
                case 3:
                    System.out.println("Введите id существующего филиала:");
                    branchId = Utilities.readULong();
                    find = bank.findBranch(branchId);
                    if(find!=null){
                        if(find==bank.getBranch()){
                            bank.setBranch(new Branch(branchIdNew));
                            bank.getBranch().setNext(temp);
                        }else bank.addBranch(bank.getPrevBranch(find),new Branch(branchIdNew));
                    }else System.out.println("Ненайден филиал с данным id!");
                    break;
                case 4:
                    System.out.println("Введите id существующего филиала:");
                    branchId = Utilities.readULong();
                    find = bank.findBranch(branchId);
                    if(find!=null){
                        bank.addBranch(find,new Branch(branchIdNew));
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
        if(bank==null) return null;
        Branch find;
        long branchId;
        System.out.println("Введите id филиала:");
        branchId = Utilities.readULong();
        find = bank.findBranch(branchId);
        if(find!=null) return find;
        else System.out.println("Не найден филиал с данным id!");
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
        String cashMachineAddress;
        while (true) {// Ввод id
            System.out.println("Введите id банкомата:");
            cashMachineId = Utilities.readULong();
            if(branch.findCashMachine(cashMachineId)==null) break;
            else System.out.println(
                    "Данный id занят другим банкаматом!" +
                            "\nВведите id повторно:"
            );
        }
        System.out.println("Введите адрес банкомата:");
        cashMachineAddress = Utilities.readLine();
        if(branch.getCashMachine()==null) branch.setCashMachine(new CashMachine(cashMachineId,cashMachineAddress));
        else branch.addCashMachine(new CashMachine(cashMachineId,cashMachineAddress));
    }
    static void deleteCashMachine(){
        if(bank==null) return;
        System.out.println("С каким филиалом работаем?");
        Branch branch = findBranch();
        if(branch == null) return;
        branch.delCashMachine();
    }
    /**
     * Удаление банка с предупреждением.
     */
    static boolean dispose(){
        if(bank!=null) {
            System.out.println(
                    "У вас есть не сохраненые данные." +
                    "\nХотите продолжить?(y/n):"
            );
            String enter = Utilities.readLine();
            Utilities.readLine();
            if(!Objects.equals(enter, "y")) return false;
            bank.dispose();
            bank = null;
            return true;
        }else{
            return true;
        }
    }

    public static void main(String[] args) {
        Utilities.init();
        while(true){
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

            int enter = Utilities.readUint();
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
                    else System.out.println("Филиал не найден");
                    break;
                case 7:
                    System.out.println("С каким филиалом работаем?(all-для просмотра всех филиалов)");
                    String str = Utilities.readLine();
                    if(Objects.equals(str, "all")){
                        System.out.println("Введите id банкомата:");
                        long cashMachineId = Utilities.readULong();
                        Branch temp = bank.getBranch();
                        while (temp != null){
                            CashMachine f = temp.findCashMachine(cashMachineId);
                            if(f!=null){
                                System.out.println("В филилале №"+temp.getId()+" найден банкомат c данным id по адресу "+ f.getAddress());
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
                    if(dispose()) bank = Utilities.readBank();
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
                        text.append('\n');
                        cashMachine = br.getCashMachine();
                        while (cashMachine!=null){
                            text.append('\t');text.append('\t');
                            text.append(cashMachine.getId());
                            text.append(" по адресу ");
                            text.append(cashMachine.getAddress());
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