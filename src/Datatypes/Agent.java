package Datatypes;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Set;

public class Agent extends Account {
    private int ttbID;
    private Set workingForms;
    private boolean hasFetchedForms = false;

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
        if(!hasFetchedForms) {
            getAssignedForms(conn);
        } else {
            if (this.workingForms.size() < 3) {
                try {
                    String unassignedForms = "SELECT * FROM FORMS WHERE AssignedAgentrepID = null";
                    PreparedStatement ps = conn.prepareStatement(unassignedForms);

                    ResultSet rs = ps.executeQuery();
                    ps.close();

                    while(rs.next() && this.workingForms.size() < 3) {
                        this.workingForms.add(formFromResultSet(rs));
                    }
                } catch (SQLException e) {
                    if (!e.getSQLState().equals("X0Y32"))
                        e.printStackTrace();
                }
            }
        }
    }

    private Form formFromResultSet(ResultSet rs) throws SQLException {
        return new Form(rs.getInt("brewerNumber"),
                        rs.getInt("productSource"),
                        rs.getInt("serialNumber"),
                        rs.getInt("productType"),
                        rs.getString("brandName"),
                        rs.getString("fancifulName"),
                        rs.getString("applicantName"),
                        rs.getString("mailingAddress"),
                        rs.getString("formula"),
                        rs.getString("grapeVarietal"),
                        rs.getString("appellation"),
                        rs.getString("phoneNumber"),
                        rs.getString("emailAddress"),
                        rs.getDate("dateOfApplication"),
                        rs.getString("printName"),
                        rs.getInt("beerWineSpirit"),
                        rs.getDouble("alcoholPercent"),
                        rs.getInt("vintageYear"),
                        rs.getDouble("pHLevel"),
                        rs.getInt("forms_pk"));
    }
}
