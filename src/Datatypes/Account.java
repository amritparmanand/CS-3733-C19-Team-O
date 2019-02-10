package Datatypes;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Sam Silver
 * @version It 1
 * Abstract class for shared data from manufacturer and agent classes
 */
public abstract class Account implements IAccount{
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String phone;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Account(String username, String password, String fullName, String email, String phone) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public BCryptPasswordEncoder getEncryptor(){return passwordEncoder;}
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
