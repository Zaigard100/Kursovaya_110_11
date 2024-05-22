package objs;

/**
 * {@code CashMachine} класс храняций в себе основную информацию о банкомате.
 * Является элементом стека.
 */
public class CashMachine {
    /**
     * Идентификационный номер банкомата. Должен быть уникальным.
     */
    private long id;
    /**
     * Место нахождение/адресс банкомата
     */
    private String address;
    /**
     * Следующий банкомат в стеке
     */
    private CashMachine next;
    /**
     * Создает баманкомат с индетификационым номером и адресом.
     * @param id идентификационный номер банкомата
     * @param address адрес банкомата
     */
    public CashMachine(long id, String address) {
        this.id = id;
        this.address = address;
    }
    /**
     * Функция для получения идентификационного номера банкомата
     * @return индетификационый номер
     */
    public long getId() {
        return id;
    }
    /**
     * Функция для изменения иденификационного номера банкомата
     * @param id индетификационый номер
     */
    public void setId(long id) {
        this.id = id;
    }
    /**
     * Функция для получения адреса банкомата
     * @return  адреса банкомата
     */
    public String getAddress() {
        return address;
    }
    /**
     * Функция для изменения адреса банкомата
     * @param address  адреса банкомата
     */
    public void setAddress(String address) {
        this.address = address;
    }

    public CashMachine getNext() {
        return next;
    }
    /**
     * Функция для замены следующего элемента
     * @param next банкомат
     */
    public void setNext(CashMachine next) {
        this.next = next;
    }
    /**
     * Очистка структуры
     */
    public void dispose(){
        if(next==null) return;
        next.dispose();
        next = null;
    }
}
