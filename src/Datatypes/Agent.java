package Datatypes;

public class Agent extends Account {
    private int ttbID;

    public Agent(String username, String password, String fullName, String email, String phone, int ttbID) {
        super(username, password, fullName, email, phone);
        this.ttbID = ttbID;
    }

    public int getTtbID() {
        return ttbID;
    }

    public void setTtbID(int ttbID) {
        this.ttbID = ttbID;
    }
}
