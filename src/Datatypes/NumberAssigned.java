package Datatypes;

/**
 * @author Percy
 * the singleton class to hold application options
 * specifies how many label applications an agent reviews when a new batch is requested
 */
public class NumberAssigned {

    // Default is 5
    private int num = 5;

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