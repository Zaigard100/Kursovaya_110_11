package objs;

public class ATM {

    private long id;

    private ATM next;

    public ATM(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ATM getNext() {
        return next;
    }

    public void setNext(ATM next) {
        this.next = next;
    }
}
