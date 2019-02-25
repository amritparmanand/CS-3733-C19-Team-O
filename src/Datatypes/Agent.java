package Datatypes;

import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
/**
 * @author Percy Jiang & Gabe Entov
 * @version It 2
 * @since It 1
 * Class for an agent account
 */
public class Agent extends Account {
    private int ttbID;
    private ArrayList<Form> workingForms = new ArrayList<>();
    private ArrayList<Form> reviewedForms = new ArrayList<>();
    private ArrayList<Form> newForms = new ArrayList<>();
    private boolean hasFetchedForms = false;
    private boolean gotOldForms = false;
    private boolean gotCurrentForms = false;

    public Agent(String username, String password, String fullName, String email, String phone, int ttbID) {
        super(username, password, fullName, email, phone);
        this.ttbID = ttbID;
    }

    public Agent(String username, String password, String fullName, String email, String phone, int ttbID, boolean hasFetchedForms) {
        super(username, password, fullName, email, phone);
        this.ttbID = ttbID;
        this.hasFetchedForms = hasFetchedForms;

        if (this.hasFetchedForms) {
            this.getWorkingForms();
        }
    }

    public Agent(int id, Connection conn) {
        super("","","","","");

        try {
            String getData = "SELECT * FROM AGENTS WHERE TTBID = " + id;
            PreparedStatement stmt = conn.prepareStatement(getData);

            ResultSet result = stmt.executeQuery();

            result.next();
            super.setUsername(result.getString("username"));
            super.setPassword(result.getString("password"));
            super.setFullName(result.getString("fullName"));
            super.setEmail(result.getString("email"));
            super.setPhone(result.getString("phone"));

        } catch (SQLException e) {
            if (!e.getSQLState().equals("X0Y32"))
                e.printStackTrace();
        }

        this.ttbID = id;
    }

    public int getTtbID() {
        return ttbID;
    }
    public void setTtbID(int ttbID) {
        this.ttbID = ttbID;
    }
    public ArrayList<Form> getReviewedForms() {
        return reviewedForms;
    }
    public ArrayList<Form> getWorkingForms() {
        return workingForms;
    }
    public ArrayList<Form> getNewForms() {
        return newForms;
    }
    public boolean isHasFetchedForms() {
        return hasFetchedForms;
    }
    public void setNewForms(ArrayList<Form> newForms) {
        this.newForms = newForms;
    }
    public void setWorkingForms(ArrayList<Form> workingForms) {
        this.workingForms = workingForms;
    }
    public void setReviewedForms(ArrayList<Form> reviewedForms) {
        this.reviewedForms = reviewedForms;
    }
    public void setHasFetchedForms(boolean hasFetchedForms) {
        this.hasFetchedForms = hasFetchedForms;
    }
    public void setGotOldForms(boolean gotOldForms) {
        this.gotOldForms = gotOldForms;
    }
    public void setGotCurrentForms(boolean gotCurrentForms) {
        this.gotCurrentForms = gotCurrentForms;
    }
    public boolean isGotOldForms() {
        return gotOldForms;
    }
    public boolean isGotCurrentForms() {
        return gotCurrentForms;
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
    public void assignNewForms(Connection conn, int limit) throws IOException {

//        if (!hasFetchedForms)
//            getAssignedForms(conn);

        System.out.println("working forms size:"+workingForms.size());
        System.out.println("limit:"+limit);
        if (this.workingForms.size() < limit) {
            try {
                String unassignedForms = "SELECT * FROM APPLICATIONS JOIN FORMS ON FORMS.FORMID = APPLICATIONS.FORMID " +
                        "WHERE APPLICATIONS.TTBID IS NULL";
                Statement stmt = conn.createStatement();

                ResultSet rs = stmt.executeQuery(unassignedForms);


                while (rs.next() && this.workingForms.size() < limit) {
                    String insertingAgentID = "UPDATE APPLICATIONS SET TTBID = " + this.getTtbID() + " WHERE formID in (";
                    insertingAgentID = insertingAgentID.concat(rs.getLong("FORMID") + ")");
                    this.newForms.add(formFromResultSet(rs));
                    System.out.println(insertingAgentID);
                    stmt = conn.createStatement();
                    stmt.executeUpdate(insertingAgentID);
                }

                stmt.close();
                this.hasFetchedForms = true;
            } catch (SQLException e) {
                if (!e.getSQLState().equals("X0Y32"))
                    e.printStackTrace();
            }
        }
    }

    // Query the database to select forms where the TTB ID matches this agent's id
    // Call formFromResultSet into object and add it into the working Forms of this agent
    @SuppressWarnings("Duplicates")
    public void getAssignedForms(Connection conn) throws IOException {
        try {
            String assignedForms = "SELECT * FROM APPLICATIONS JOIN FORMS ON FORMS.FORMID = APPLICATIONS.FORMID WHERE" +
                    " APPLICATIONS.TTBID = " + this.getTtbID() + " and APPLICATIONS.STATUS = 'PENDING'";
            PreparedStatement ps = conn.prepareStatement(assignedForms);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                workingForms.add(formFromResultSet(rs));
            }
            ps.close();
            this.gotCurrentForms = true;
        } catch (SQLException e) {
            if (!e.getSQLState().equals("X0Y32"))
                e.printStackTrace();
        }
    }

    /**
     * @author Percy
     * @version It3
     * @param connection
     */
    @SuppressWarnings("Duplicates")
    public void getReviewedForms(Connection connection) throws IOException{
        try {
            String assignedForms = "SELECT * FROM APPLICATIONS JOIN FORMS ON FORMS.FORMID = APPLICATIONS.FORMID WHERE " +
                    "APPLICATIONS.TTBID = " + this.getTtbID()+ " and APPLICATIONS.STATUS = 'APPROVED' or APPLICATIONS.STATUS = 'DENIED'";
            PreparedStatement ps = connection.prepareStatement(assignedForms);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                reviewedForms.add(formFromResultSet(rs));
            }
            ps.close();
            this.gotOldForms = true;
        } catch (SQLException e) {
            if (!e.getSQLState().equals("X0Y32"))
                e.printStackTrace();
        }
    }

    //    // Parse a Form from database to object

    private Form formFromResultSet(ResultSet rs) throws SQLException, IOException {
        Form f = new Form();
        f.setFormID(rs.getLong("formID"));
        f.setRepID(rs.getInt("repID"));
        f.setBrewerNumber(rs.getString("brewerNumber"));
        f.setProductSource(rs.getString("productSource"));
        f.setSerialNumber(rs.getString("serialNumber"));
        f.setProductType(rs.getString("productType"));
        f.setBrandName(rs.getString("brandName"));
        f.setFancifulName(rs.getString("fancifulName"));
        f.setApplicantName(rs.getString("applicantName"));
        f.setMailingAddress(rs.getString("mailingAddress"));
        f.setFormula(rs.getString("formula"));
        f.setGrapeVarietal(rs.getString("grapeVarietal"));
        f.setAppellation(rs.getString("appellation"));
        f.setPhoneNumber(rs.getString("phoneNumber"));
        f.setEmailAddress(rs.getString("emailAddress"));
        f.setCertificateOfApproval(rs.getBoolean("certificateOfApproval"));
        f.setCertificateOfExemption(rs.getBoolean("certificateOfExemption"));
        f.setOnlyState(rs.getString("onlyState"));
        f.setDistinctiveLiquor(rs.getBoolean("distinctiveLiquor"));
        f.setBottleCapacity(rs.getString("bottleCapacity"));
        f.setTtbID(rs.getInt("TTBID"));
        f.setDateOfApplication(rs.getString("dateOfApplication"));
        f.setPrintName(rs.getString("printName"));
        f.setBeerWineSpirit(rs.getString("beerWineSpirit"));
        f.setAlcoholPercent(rs.getString("alcoholPercent"));
        f.setVintageYear(rs.getString("vintageYear"));
        f.setpHLevel(rs.getString("pHLevel"));
        f.setCommentString(rs.getString("comments"));

        // Deal with image
        Blob picture = rs.getBlob("labelImage");
        if (picture != null) {

            BufferedImage is = ImageIO.read(picture.getBinaryStream());
            File outputFile = new File("src/Tess4J/images/Storing.jpg");
            ImageIO.write(is, "JPG", outputFile);
            System.out.println("Storing called");

            LabelImage percy = new LabelImage();
            percy.setLabelImage(new Image(picture.getBinaryStream()));
            percy.setLabelFile(new File(outputFile.toString()));
            f.setLabel(percy);
        }

        return f;
    }

    public void approveOrDeny(Form form){
        this.reviewedForms.add(form);
        this.workingForms.remove(form);
    }

    public void pass(Form form){
        this.workingForms.remove(form);
    }
}
