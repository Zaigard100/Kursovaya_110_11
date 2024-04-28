package objs;

public class Bank {

    private String name;

    private Branch branch;

    public Bank(String name){
        this.name = name;
    }

    public Branch findBranch(long id,String address){
        Branch temp = getBranch();
        while (temp.getNext() != null){
            if(temp.getId() == id && temp.getAddress() == address){
                return temp;
            }
            temp = temp.getNext();
        }
        return null;
    }
    public Branch findBranch(long id){
        Branch temp = getBranch();
        while (temp.getNext() != null){
            if(temp.getId() == id){
                return temp;
            }
            temp = temp.getNext();
        }
        return null;
    }
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

    public void addBranch(Branch find,Branch add){
        Branch temp = find.getNext();
        add.setNext(temp);
        temp.setNext(add);
    }
    public void addBranch(Branch add){
        Branch temp = branch;
        while (temp.getNext() != null){
            temp = temp.getNext();
        }
        addBranch(temp,add);
    }
    public void addBranch(long id,String address,Branch add){
        addBranch(findBranch(id,address),add);
    }
    public void addBranch(long id,Branch add){
        addBranch(findBranch(id),add);
    }
    public void addBranch(String address,Branch add){
        addBranch(findBranch(address),add);
    }

    public Branch deleteBranch(Branch branch){
        Branch temp = this.branch;
        while (!temp.getNext().equals(branch)){
            temp = temp.getNext();
            if(temp == null){
                return null;
            }
        }
        temp.setNext(branch.getNext());
        return branch;
    }
    public Branch deleteBranch(long id,String address){
        return deleteBranch(findBranch(id,address));
    }
    public Branch deleteBranch(long id){
        return deleteBranch(findBranch(id));
    }
    public Branch deleteBranch(String address){
        return deleteBranch(findBranch(address));
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }
}
