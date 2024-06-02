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
        long branchIdNew;
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
            bank.addBranch(new Branch(branchIdNew));
        }
    }
    /**
     * Функция для вызова меню поиска филиала
     * @return если находит филиал возврацает его, иначе {@code null}
     */
    static Branch findBranch(){
        if(bank==null) return null;
        Branch find;
        long branchId;
        System.out.println("Введите id существующего филиала:");
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
        else branch.pushCashMachine(new CashMachine(cashMachineId,cashMachineAddress));
    }
    /**
     * Функция для вызова меню поиска банкомата в филиале;
     * @param br филиал в котором ищем банкомат
     * @return если находит банкомат возвращает его иначе {@code null}
     */
    static CashMachine findCashMachine(Branch br,int enter,long cashMachineId,String cashMachineAddress){
        if(bank==null) return null;
        if(br==null) return null;
        CashMachine find;
        switch (enter) {
            case 1:
                find = br.findCashMachine(cashMachineId);
                if (find != null) return find;
                return null;
            case 2:
                find = br.findCashMachine(cashMachineAddress);
                if (find != null) return find;
                return null;
            case 3:
                find = br.findCashMachine(cashMachineId,cashMachineAddress);
                if (find != null) return find;
                return null;
        }
        return null;
    }
    /**
     * Функция для вызова меню удаления банкомата
     */
    static void deleteCashMachine(){
        if(bank==null) return;
        System.out.println("С каким филиалом работаем?");
        Branch branch = findBranch();
        if(branch == null) return;
        branch.popCashMachine();
    }
    /**
     * Функция для вывода результата поиска филиала
     */
    static void findBranchInBank(){
        if(findBranch()!=null) System.out.println("Филиал найден");
        else System.out.println("Филиал не найден");
    }
    /**
     * Функция для вызова меню поиска банкомата
     */
    static void findCashMachineInBank(){
        long cashMachineId = 0;
        String cashMachineAddress = "";
        System.out.println("Поиск по всем филиалам?(y/n)");
        String str = Utilities.readLine();
        System.out.println("Во каким данным искать банкомат?" +
                "\n1. По id" +
                "\n2. По адресу" +
                "\n3. По адресу и id");
        int enter = Utilities.readUint();
        if(enter==1||enter==3){
            System.out.println("Введите id банкомата:");
            cashMachineId=Utilities.readULong();
        }
        if(enter==2||enter==3){
            System.out.println("Введите адрес банкомата:");
            cashMachineAddress=Utilities.readLine();
        }
        if(Objects.equals(str, "y")){
            Branch temp = bank.getBranch();
            while (temp != null){
                CashMachine f = findCashMachine(temp,enter,cashMachineId,cashMachineAddress);
                if(f!=null){
                    System.out.println("В филилале №"+temp.getId()+" найден банкомат c id "+f.getId()+" по адресу "+ f.getAddress());
                }
                temp = temp.getNext();
            }
            System.out.println("Конец поиска");
        }else {
            if (findCashMachine(findBranch(),enter,cashMachineId,cashMachineAddress) != null) System.out.println("Банкомат найден");
            else System.out.println("Банкомант не найден");
        }
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
    /**
     * Функция для вываода всей структуры банка
     */
    static void showBank(){
        if(bank==null){
            System.out.println("Пусто");
            return;
        }
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
                    findBranchInBank();
                    break;
                case 7:
                    findCashMachineInBank();
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
                    showBank();
                    break;
                case 12:
                    dispose();
                    System.exit(0);
                    break;
            }
        }
    }
}