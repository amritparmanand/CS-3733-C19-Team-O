package Datatypes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class Agent extends Account {
    private int ttbID;
    private Set workingForms;

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

    @SuppressWarnings("Duplicates")
    public void register(Connection conn) {
        try {
            String createManufacturer = "INSERT INTO Agents (ttbid, username, password, fullname, email, phone) " +
                    "VALUES(?,?,?,?,?,?)";

            PreparedStatement prepStmt = conn.prepareStatement(createManufacturer);
            prepStmt.setInt(1, this.getTtbID());
            prepStmt.setString(2, this.getUsername());
            prepStmt.setString(3, this.getEncryptor().encode(this.getPassword()));
            prepStmt.setString(4, this.getFullName());
            prepStmt.setString(5, this.getEmail());
            prepStmt.setString(6, this.getPhone());

            prepStmt.executeUpdate();
            prepStmt.close();
        } catch (SQLException e) {
            if (!e.getSQLState().equals("X0Y32"))
                e.printStackTrace();
        }
    }

    public void assignNewForms(Connection conn) {
        if(this.workingForms.size() < 3) {
            try {
                String unassignedForms = "SELECT * FROM FORMS WHERE AssignedAgentrepID = null";
                PreparedStatement ps = conn.prepareStatement(unassignedForms);

                ResultSet rs = ps.executeQuery();
                ps.close();

            } catch (SQLException e) {
                if (!e.getSQLState().equals("X0Y32"))
                    e.printStackTrace();
            }
        }
    }
}
