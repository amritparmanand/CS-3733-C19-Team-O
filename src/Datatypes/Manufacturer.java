package Datatypes;

public class Manufacturer extends Account {
    private int repID;
    private String companyName;

    public Manufacturer(String username, String password, String fullName, String email, String phone, int repID, String companyName) {
        super(username, password, fullName, email, phone);
        this.repID = repID;
        this.companyName = companyName;
    }
}
