package Datatypes;

public abstract class Account {
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String phone;

    public Account(String username, String password, String fullName, String email, String phone) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
    }
}
