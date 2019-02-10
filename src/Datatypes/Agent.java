package Datatypes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
/**
 * @author Percy Jiang & Gabe Entov
 * @version It 1
 * Class for an agent account
 */
public class Agent extends Account {
    private int ttbID;
    private ArrayList<Form> workingForms = new ArrayList<>();
    private boolean hasFetchedForms = false;

    // Why are there 2 constructors?
    public Agent(String username, String password, String fullName, String email, String phone, int ttbID) {
        super(username, password, fullName, email, phone);
        this.ttbID = ttbID;
    }

    public Agent(String username, String password, String fullName, String email, String phone, int ttbID, boolean hasFetchedForms) {
        super(username, password, fullName, email, phone);
        this.ttbID = ttbID;
        this.hasFetchedForms = hasFetchedForms;

        if(this.hasFetchedForms) {
            this.getWorkingForms();
        }
    }

    public int getTtbID() {
        return ttbID;
    }
    public void setTtbID(int ttbID) {
        this.ttbID = ttbID;
    }

    // Parse an agent object into database
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

    // As long as there are less than 3 forms in workingForms
    // Query the database to select forms where ttb ID is empty
    // Insert this agent's ID into the selected forms
    public void assignNewForms(Connection conn) {
        if (!hasFetchedForms)
            getAssignedForms(conn);

        if (this.workingForms.size() < 3) {
            try {
                String unassignedForms = "SELECT * FROM APPLICATIONS NATURAL RIGHT JOIN FORMS WHERE TTBID IS NULL";
                PreparedStatement ps = conn.prepareStatement(unassignedForms);

                ResultSet rs = ps.executeQuery();

                String insertingAgentID = "UPDATE APPLICATIONS SET TTBID = " + this.getTtbID() + " WHERE formID in (";
                while (rs.next() && this.workingForms.size() < 3) {
                    insertingAgentID = insertingAgentID.concat(rs.getInt("formID") + ", ");
                    this.workingForms.add(formFromResultSet(rs));
                }

                ps.close();

                insertingAgentID = insertingAgentID.substring(0, insertingAgentID.length() - 2).concat(")");

                ps = conn.prepareStatement(insertingAgentID);
                ps.executeUpdate();
                ps.close();
            } catch (SQLException e) {
                if (!e.getSQLState().equals("X0Y32"))
                    e.printStackTrace();
            }
        }
    }

    public ArrayList<Form> getWorkingForms() {
        return workingForms;
    }

    // Query the database to select forms where the TTB ID matches this agent's id
    // Call formFromResultSet into object and add it into the working Forms of this agent
    public void getAssignedForms(Connection conn) {
        try {
            String assignedForms = "SELECT * FROM APPLICATIONS NATURAL RIGHT JOIN FORMS WHERE TTBID = " + this.getTtbID();                ;
            PreparedStatement ps = conn.prepareStatement(assignedForms);

            ResultSet rs = ps.executeQuery();

            while (rs.next() && this.workingForms.size() < 3) { //extraneous < 3 because only three will ever be assigned
                workingForms.add(formFromResultSet(rs));
            }
            ps.close();
            this.hasFetchedForms = true;
        } catch (SQLException e) {
            if (!e.getSQLState().equals("X0Y32"))
                e.printStackTrace();
        }
    }

    // Parse a Form from database to object
    private Form formFromResultSet(ResultSet rs) throws SQLException {
        return new Form(rs.getInt("formID"),
                rs.getInt("repID"),
                rs.getInt("brewerNumber"),
                rs.getString("productSource"),
                rs.getInt("serialNumber"),
                rs.getString("productType"),
                rs.getString("brandName"),
                rs.getString("fancifulName"),
                rs.getString("applicantName"),
                rs.getString("mailingAddress"),
                rs.getString("formula"),
                rs.getString("grapeVarietal"),
                rs.getString("appellation"),
                rs.getString("phoneNumber"),
                rs.getString("emailAddress"),
                rs.getString("dateOfApplication"),
                rs.getString("printName"),
                rs.getInt("beerWineSpirit"),
                rs.getDouble("alcoholPercent"),
                rs.getInt("vintageYear"),
                rs.getDouble("pHLevel"));
    }
}
