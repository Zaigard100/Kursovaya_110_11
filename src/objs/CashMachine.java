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
     * Следующий банкомат в стеке
     */
    private CashMachine next;
    /**
     * Создает баманкомат с индетификационым номером.
     * @param id идентификационный номер банкомата
     */
    public CashMachine(long id) {
        this.id = id;
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
     * Функция для получения доступа к следующему банкомату в стеке
     * @return банкомат
     */
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

    public void dispose(){

    }
}
