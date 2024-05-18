package objs;

import java.util.Objects;

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
     * @param address адрес филиала.
     * @return Если находит филиал возвращает его же, иначе возвращает {@code null}.
     */
    public Branch findBranch(long id,String address){
        Branch temp = getBranch();
        while (temp.getNext() != null){
            if(temp.getId() == id && Objects.equals(temp.getAddress(), address)){
                return temp;
            }
            temp = temp.getNext();
        }
        return null;
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
     * Выполняет поиск филиала банка.
     * @param address адрес филиала.
     * @return Если находит филиал возвращает его же, иначе возвращает {@code null}.
     */
    public Branch findBranch(String address){
        Branch temp = getBranch();
        while (temp.getNext() != null){
            if(temp.getAddress() == address){
                return temp;
            }
            temp = temp.getNext();
        }
        return null;
    }
    /**
     * Выполняет поиск предыдущего филиала банка.
     * @param current филиал.
     * @return Если находит филиал возвращает его же, иначе возвращает {@code null}.
     */
    public Branch getPrevBranch(Branch current){
        if(current==branch) return null;
        Branch temp = branch;
        do{
            if(temp.getNext()==current){
                return temp;
            }
            temp = temp.getNext();
        }while (temp.getNext()!=null);
        return null;
    }
    /**
     * Добавляет филиал {@code add} после филиала {@code find}
     * @param find филиал после которого нужно добавить {@code add}
     * @param add добовляемы филиал
     */
    public void addBranch(Branch find,Branch add){
        Branch temp = find.getNext();
        add.setNext(temp);
        find.setNext(add);
    }
    /**
     * Добовляет филиалл {@code add} в конец списка
     * @param add добовляемы филиал
     */
    public void addBranch(Branch add){
        if(branch==null){
            branch = add;
            return;
        }
        Branch temp = branch;
        while (temp.getNext() != null){
            temp = temp.getNext();
        }
        addBranch(temp,add);
    }
    /**
     * Удаляет филиал {@code branch} из списка
     * @param branch удаляемы филиал
     * @return удаленны филиал
     */
    public Branch deleteBranch(Branch branch){
        Branch temp = this.branch;
        while (!temp.getNext().equals(branch)){
            temp = temp.getNext();
            if(temp == null){
                return null;
            }
        }
        temp.setNext(branch.getNext());
        branch.setNext(null);
        return branch;
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
        branch.dispose();
        branch = null;
    }

}
