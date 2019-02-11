package Datatypes;

public class NumberAssigned {
    private int num=5;

    private static NumberAssigned instance = new NumberAssigned();

    private NumberAssigned() {}

    public static NumberAssigned getInstance(){
        return instance;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
