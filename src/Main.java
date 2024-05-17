import objs.Bank;
import objs.Branch;

public class Main {
    /**
     * Текущий банк с которым выполняется работа
     */
    static Bank bank;

    /**
     * Функция для создания нового банка. Если банк не пустой, то он зарание удаляется
     */
    static void createBank(){
        if(bank!=null) bank = null;
        System.out.println("Введите название банка:");
        String bankName = Utilities.readLine();
        bank = new Bank(bankName);
    }

    public static void main(String[] args) {
        boolean run = true;
        while(run){

            System.out.println("\n1. Создать Банк" +
                    "\n2.  Добавить филиал" +
                    "\n3.  Удалить филиал" +
                    "\n4.  Добавить банкомат" +
                    "\n5.  Удалилить банкомат" +
                    "\n6.  Поиск филиала" +
                    "\n7.  Поиск банкомата" +
                    "\n8.  Удалить банк" +
                    "\n9.  Загрузить из файла" +
                    "\n10. Сохранить в файл" +
                    "\n11. Завершить работу"
            );

            int enter = Utilities.readInt();

            switch (enter){

                case 1:
                    createBank();
                    break;
                case 2:
                    long branchId;
                    String branchAddress;
                    while (true) {// ID
                        System.out.println("Введите название банка:");
                        branchId = Utilities.readULong();
                        if(bank.findBranch(branchId)==null) break;
                        else System.out.println(
                                "Данный id занят другим филиалом!" +
                                "\nВведите id повторно:"
                        );
                    }
                    System.out.println("Введите название банка:");
                    branchAddress = Utilities.readLine();
                    Branch br = bank.getBranch();
                    if(br == null) bank.setBranch(new Branch(branchId,branchAddress));
                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:

                    break;
                case 7:

                    break;
                case 8:

                    break;
                case 9:

                    break;
                case 10:

                    break;
                case 11:

                    break;

            }

        }

    }
}