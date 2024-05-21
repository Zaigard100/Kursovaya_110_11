package objs;

import java.util.Objects;

/**
 * {@code Branch} класс хранящий основную информацию о филиале банка.
 * Является элементом структуры списка. Хранит в себе стек банкаматов.
 * Содержит все необходимые функции для работы со стеком банкоматов
 */
public class Branch {
    /**
     * Индетификационный номер филиала
     */
    private long id;
    /**
     * Заголовочный элемент стека банкоматов
     */
    private CashMachine cashMachine;
    /**
     * Следующий филиал в списке
     */
    private Branch next;
    /**
     * Создает обьект с инвормацией о филиале.
     * @param id идетификационный номер. Недолжен повторятся.
     * @param address адресс/местонахождения филиала
     */
    public Branch(long id) {
        this.id = id;
    }
    /**
     * Добавляет в стек банкомат
     * @param cashMachine банкомат
     */
    public void addCashMachine(CashMachine cashMachine){
        cashMachine.setNext(this.cashMachine);
        this.cashMachine = cashMachine;
    }
    /**
     * Удаляет из стека банкомат
     */
    public void delCashMachine(){
        CashMachine temp = cashMachine;
        cashMachine = cashMachine.getNext();
        temp.setNext(null);
    }
    /**
     * Поиск банкамата в стеке
     * @param id идентификационныйномер банкомата
     * @return Если находит банкомат возвращает его же, иначе возвращает {@code null}.
     */
    public CashMachine findCashMachine(long id){
        CashMachine temp = cashMachine;
        while (temp!=null){
            if(temp.getId()==id) return temp;
            temp = temp.getNext();
        }
        return null;
    }
    public CashMachine findCashMachine(String address){
        CashMachine temp = cashMachine;
        while (temp!=null){
            if(Objects.equals(temp.getAddress(), address)) return temp;
            temp = temp.getNext();
        }
        return null;
    }
    public CashMachine findCashMachine(long id,String address){
        CashMachine temp = cashMachine;
        while (temp!=null){
            if(temp.getId()==id&&Objects.equals(temp.getAddress(), address)) return temp;
            temp = temp.getNext();
        }
        return null;
    }
    /**
     * Функция для получения информации о идентификационном номере филиала
     * @return идентификационный номер филиала
     */
    public long getId() {
        return id;
    }
    /**
     * Функция для изменения идентификационного номера филиала
     * @param id идентификационный номер филиала
     */
    public void setId(long id) {
        this.id = id;
    }
    /**
     * Функция для доступа к заголовку стека банкоматов
     * @return заголовочный элемент стека банкаматов
     */
    public CashMachine getCashMachine() {
        return cashMachine;
    }
    /**
     * Функция для изменения заголовка стека банкаматов
     * @param cashMachine банкомат
     */
    public void setCashMachine(CashMachine cashMachine) {
        this.cashMachine = cashMachine;
    }
    /**
     * Функция для доступа к следующему по списку филиалу.
     * @return филиал
     */
    public Branch getNext() {
        return next;
    }
    /**
     * Функция для замены следующего филиала новым
     * @param next филиал
     */
    public void setNext(Branch next) {
        this.next = next;
    }
    /**
     * Очистка структуры
     */
    public void dispose(){
        if(next == null) return;
        next.dispose();
        next = null;
        if(cashMachine==null) return;
        cashMachine.dispose();
        cashMachine = null;
    }
}
