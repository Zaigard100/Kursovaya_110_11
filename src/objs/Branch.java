package objs;

public class Branch {

    private long id;
    private String address;
    private ATM atm;
    private Branch next;

    public Branch(long id, String adrees) {
        this.id = id;
        this.address = adrees;
    }

    public void addATM(ATM atm){
        atm.setNext(this.atm);
        this.atm = atm;
    }

    public ATM delATM(){
        ATM temp = atm;
        atm = atm.getNext();
        return temp;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ATM getAtm() {
        return atm;
    }

    public void setAtm(ATM atm) {
        this.atm = atm;
    }

    public Branch getNext() {
        return next;
    }

    public void setNext(Branch next) {
        this.next = next;
    }
}
