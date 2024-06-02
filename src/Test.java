import objs.Bank;
import objs.Branch;
import objs.CashMachine;

public class Test {
    static Bank bank;
    static Branch branch1,branch2,branch3,branch4,branch5;
    static CashMachine ca1,ca2,ca3,ca4,ca5,ca6,ca7,ca8,ca9,ca10,ca11,ca12,ca13,ca14,ca15,ca16,ca17,ca18,ca19,ca20;
    public static void main(String[] args) {

        Utilities.init();

        System.out.println("Создание банка");
        bank = new Bank("Test");
        showBank();

        System.out.println("Создание 5 филиалов");
        branch1 = new Branch(1);
        branch2 = new Branch(2);
        branch3 = new Branch(3);
        branch4 = new Branch(4);
        branch5 = new Branch(5);

        System.out.println("Добавление филиала branch1 в банкa " +
                "функцией addBranch(Branch) при bank.getBranch()==null");
        bank.addBranch(branch1);
        showBank();

        System.out.println("Обнуление branch в bank");
        bank.setBranch(null);

        System.out.println("Обнуление branch в bank");
        bank.setBranch(null);

        System.out.println("Добавление 3 филиалов в банк " +
                "функцией addBranch(Branch) ");
        bank.addBranch(branch1);
        bank.addBranch(branch5);
        bank.addBranch(branch4);
        showBank();

        System.out.println("Добавление 2 филиалов после branch1 и branch3 в банк " +
                "функцией addBranch(Branch,Branch) ");
        bank.addBranch(branch3);
        bank.addBranch(branch2);
        showBank();

        System.out.println("Создание 20 банкоматов");
        ca1 = new CashMachine(101,"A1");
        ca2 = new CashMachine(102,"A2");
        ca3 = new CashMachine(103,"A3");
        ca4 = new CashMachine(104,"A4");
        ca5 = new CashMachine(105,"B1");
        ca6 = new CashMachine(106,"B2");
        ca7 = new CashMachine(107,"B3");
        ca8 = new CashMachine(108,"B4");
        ca9 = new CashMachine(109,"C1");
        ca10 = new CashMachine(110,"C2");
        ca11 = new CashMachine(111,"C3");
        ca12 = new CashMachine(112,"C4");
        ca13 = new CashMachine(113,"D1");
        ca14 = new CashMachine(114,"D2");
        ca15 = new CashMachine(115,"D3");
        ca16 = new CashMachine(116,"D4");
        ca17 = new CashMachine(117,"E1");
        ca18 = new CashMachine(118,"E2");
        ca19 = new CashMachine(119,"E3");
        ca20 = new CashMachine(120,"E4");

        System.out.println("Добавление их в филиалы" +
                "функцией pushCashMachine(CashMachine) ");
        branch1.pushCashMachine(ca1);
        branch1.pushCashMachine(ca2);
        branch1.pushCashMachine(ca3);
        branch1.pushCashMachine(ca4);
        branch2.pushCashMachine(ca5);
        branch2.pushCashMachine(ca6);
        branch2.pushCashMachine(ca7);
        branch2.pushCashMachine(ca8);
        branch3.pushCashMachine(ca9);
        branch3.pushCashMachine(ca10);
        branch3.pushCashMachine(ca11);
        branch3.pushCashMachine(ca12);
        branch4.pushCashMachine(ca13);
        branch4.pushCashMachine(ca14);
        branch4.pushCashMachine(ca15);
        branch4.pushCashMachine(ca16);
        branch5.pushCashMachine(ca17);
        branch5.pushCashMachine(ca18);
        branch5.pushCashMachine(ca19);
        branch5.pushCashMachine(ca20);
        showBank();

        System.out.println("Поиск филиала branch3");
        if(branch3==bank.findBranch(3)) System.out.println("Совпадение");
        else System.out.println("Не совпадение Ошибка");

        System.out.println("Поиск банкомата по id(103)");
        if(ca3==branch1.findCashMachine(103)) System.out.println("Совпадение");
        else System.out.println("Не совпадение Ошибка");

        System.out.println("Поиск банкомата по адресу(B4)");
        if(ca8==branch2.findCashMachine("B4")) System.out.println("Совпадение");
        else System.out.println("Не совпадение Ошибка");

        System.out.println("Поиск банкомата по адресу(A1) и id(101)");
        if(ca1==branch1.findCashMachine(101,"A1")) System.out.println("Совпадение");
        else System.out.println("Не совпадение Ошибка");

        showBank();

        System.out.println("Удаление по одному банкомату из филиалов" +
                " функции popCashMachine()");
        branch1.popCashMachine();
        branch2.popCashMachine();
        branch3.popCashMachine();
        branch4.popCashMachine();
        branch5.popCashMachine();
        showBank();

        System.out.println("Уничтожение филиала dispose()");
        branch4.dispose();
        showBank();

        System.out.println("Уничтожение банка dispose()");
        bank.dispose();
        showBank();
    }

    static void showBank(){
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

        // Utilities.readLine();

    }

}
