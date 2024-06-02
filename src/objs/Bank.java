package objs;

/**
 * {@code Bank} класс хранящий в себе название банка {@code name} и заголовок списка филиалов этого банка {@code branch}
 * Является контейнером струтктуры ({@code Bank} - {@code Branch} - {@code ATM})
 * Содержит все основные функции для работы с филиалами банка
 */
public class Bank {
    /**
     * Название банка
     */
    private String name;
    /**
     * Заголовочный элемент списка филиалов
     */
    private Branch branch;
    /**
    * Конструктор для создания нового обьекта Bank
    *
    * @param name название банка
     */
    public Bank(String name){
        this.name = name;
    }
    /**
     * Выполняет поиск филиала банка.
     * @param id идентификационный номер филиала.
     * @return Если находит филиал возвращает его же, иначе возвращает {@code null}.
     */
    public Branch findBranch(long id){
        if(branch == null) return null;
        Branch temp = getBranch();
        while (temp != null){
            if(temp.getId() == id){
                return temp;
            }
            temp = temp.getNext();
        }
        return null;
    }

    /**
     * Добовляет филиалл {@code add} в список
     * @param add добовляемый филиал
     */
    public void addBranch(Branch add){
        if(branch==null){
            branch = add;
            return;
        }
        if(add.getId()<branch.getId()){
            add.setNext(branch);
            branch = add;
            return;
        }
        Branch temp = branch;

        while (temp.getNext() != null){
            if(add.getId()< temp.getNext().getId()){
                add.setNext(temp.getNext());
                temp.setNext(add);
                return;
            }
            temp = temp.getNext();
        }
        temp.setNext(add);
    }
    /**
     * Удаляет филиал {@code branch} из списка
     *
     * @param branch удаляемы филиал
     */
    public void deleteBranch(Branch branch){
        Branch temp = this.branch;
        while (!temp.getNext().equals(branch)){
            temp = temp.getNext();
            if(temp == null){
                return;
            }
        }
        temp.setNext(branch.getNext());
        branch.setNext(null);
    }
    /**
     * Функция возвращающая названеи банка
     * @return название банка
     */
    public String getName() {
        return name;
    }
    /**
     * Функция для изменения названия банка
     * @param name название банка
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Функция для получения доступа к заголовку списка филиалов
     * @return заголовок списка филилалов
     */
    public Branch getBranch() {
        return branch;
    }
    /**
     * Функция для изменения заголовки списка филиалов
     * @param branch заголовок списка филилалов
     */
    public void setBranch(Branch branch) {
        this.branch = branch;
    }
    /**
     * Очистка структуры
     */
    public void dispose(){
        if(branch==null) return;;
        branch.dispose();
        branch = null;
    }
}
