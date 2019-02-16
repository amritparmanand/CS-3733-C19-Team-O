package edu.wpi.cs3733c19.teamO.Datatypes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * @author Percy Jiang & Gabe Entov
 * @version It 1
 * Class for a manufacturer account
 */
public class Manufacturer extends Account {
    private int repID;
    private String companyName;

    public Manufacturer(String username, String password, String fullName, String email, String phone, int repID, String companyName) {
        super(username, password, fullName, email, phone);
        this.repID = repID;
        this.companyName = companyName;
    }

    @SuppressWarnings("Duplicates")
    public Manufacturer(int id, Connection connection) {
        super("","","","","");

        try {
            String getData = "SELECT * FROM REPRESENTATIVES WHERE REPID = " + id;
            PreparedStatement stmt = connection.prepareStatement(getData);

            ResultSet result = stmt.executeQuery();

            result.next();
            super.setUsername(result.getString("username"));
            super.setPassword(result.getString("password"));
            super.setFullName(result.getString("fullName"));
            super.setEmail(result.getString("email"));
            super.setPhone(result.getString("phone"));
            this.companyName = result.getString("companyname");
            this.repID = id;

        } catch (SQLException e) {
            if (!e.getSQLState().equals("X0Y32"))
                e.printStackTrace();
        }
    }

    public int getRepID() {
        return repID;
    }
    public void setRepID(int repID) {
        this.repID = repID;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    // Parse a manufacturer object into database
    @SuppressWarnings("Duplicates")
    public void register(Connection conn) {
        try {
            String createManufacturer = "INSERT INTO Representatives(repid, username, password, fullname, companyname, email, phone) " +
                    "VALUES(?,?,?,?,?,?,?)";

            PreparedStatement prepStmt = conn.prepareStatement(createManufacturer);

            prepStmt.setInt(1, this.getRepID());
            prepStmt.setString(2, this.getUsername());
            prepStmt.setString(3, this.getEncryptor().encode(this.getPassword()));
            prepStmt.setString(4, this.getFullName());
            prepStmt.setString(5, this.getCompanyName());
            prepStmt.setString(6, this.getEmail());
            prepStmt.setString(7, this.getPhone());
            prepStmt.executeUpdate();
            prepStmt.close();

        } catch (SQLException e) {
            if (!e.getSQLState().equals("X0Y32"))
                e.printStackTrace();
        }
    }
}
