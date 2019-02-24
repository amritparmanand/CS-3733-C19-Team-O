package Datatypes;

import javafx.scene.image.Image;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
/**
 * @author Percy Jiang & Gabe Entov
 * @version It 4
 * @since It 1
 * Class for an agent account
 */
public class Agent extends Account {
    private int ttbID;
    private ArrayList<Form> workingForms = new ArrayList<>();
    private ArrayList<Form> reviewedForms = new ArrayList<>();
    private ArrayList<Form> approvedForms = new ArrayList<>();
    private ArrayList<Form> deniedForms = new ArrayList<>();
    private ArrayList<Form> passedForms = new ArrayList<>();
    private ArrayList<Form> newForms = new ArrayList<>();
    private boolean hasFetchedForms = false;
    private boolean gotOldForms = false;
    private boolean gotCurrentForms = false;
    private int score;
    private int numberPassed;
    private int numberApproved;
    private int numberDenied;
    private int[] achievments = new int[20];

    public Agent(String username, String password, String fullName, String email, String phone, int ttbID, int score, int numberApproved, int numberDenied, int numberPassed){
        super(username, password, fullName, email, phone);
        this.ttbID = ttbID;
        this.score = score;
        this.numberApproved = numberApproved;
        this.numberDenied = numberDenied;
        this.numberPassed = numberPassed;
   //     this.achievments = achievments;
    }

    public Agent(String username, String password, String fullName, String email, String phone, int ttbID, boolean hasFetchedForms, int score, int numberApproved, int numberDenied, int numberPassed) {
        super(username, password, fullName, email, phone);
        this.ttbID = ttbID;
        this.hasFetchedForms = hasFetchedForms;
        this.score = score;
        this.numberApproved = numberApproved;
        this.numberDenied = numberDenied;
        this.numberPassed = numberPassed;

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
            this.setScore(result.getInt("score"));

   //         this.setAchievments(result.getintArray("achievments"));

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
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public int[] getAchievments(){
        return achievments;
    }
    public void setAchievments(int[] achievments){
        this.achievments=achievments;
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

    public int getNumberPassed() {
        return numberPassed;
    }

    public void setNumberPassed(int numberPassed) {
        this.numberPassed = numberPassed;
    }

    public int getNumberApproved() {
        return numberApproved;
    }

    public void setNumberApproved(int numberApproved) {
        this.numberApproved = numberApproved;
    }

    public int getNumberDenied() {
        return numberDenied;
    }

    public void setNumberDenied(int numberDenied) {
        this.numberDenied = numberDenied;
    }

    // Parse an agent object into database
    @SuppressWarnings("Duplicates")
    public void register(Connection conn) {
        try {
            String createManufacturer = "INSERT INTO Agents (ttbid, username, password, fullname, email, phone, score, numberPassed, numberApproved, numberDenied, achievments) " +
                    "VALUES(?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement prepStmt = conn.prepareStatement(createManufacturer);
            prepStmt.setInt(1, this.getTtbID());
            prepStmt.setString(2, this.getUsername());
            prepStmt.setString(3, this.getEncryptor().encode(this.getPassword()));
            prepStmt.setString(4, this.getFullName());
            prepStmt.setString(5, this.getEmail());
            prepStmt.setString(6, this.getPhone());
            prepStmt.setInt(7, 0);
            prepStmt.setInt(8,0);
            prepStmt.setInt(9,0);
            prepStmt.setInt(10,0);
  //          prepStmt.setArray(8, [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]);

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
    public void assignNewForms(Connection conn, int limit) {

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
    public void getAssignedForms(Connection conn) {
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
    public void getReviewedForms(Connection connection){
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
        calculateScore(connection);
    }

    /**
     * @author Clay Oshiro-Leavitt
     * @version It 4
     * @param connection
     */
    public void calculateScore(Connection connection){
        score = reviewedForms.size()*5;
        try {
            String approvedFormSQL = "SELECT * FROM APPLICATIONS JOIN FORMS ON FORMS.FORMID = APPLICATIONS.FORMID WHERE " +
                    "APPLICATIONS.TTBID = " + this.getTtbID()+ " and APPLICATIONS.STATUS = 'APPROVED'";

            String deniedFormSQL = "SELECT * FROM APPLICATIONS JOIN FORMS ON FORMS.FORMID = APPLICATIONS.FORMID WHERE " +
                    "APPLICATIONS.TTBID = " + this.getTtbID()+ " and APPLICATIONS.STATUS = 'DENIED'";
            PreparedStatement ps = connection.prepareStatement(approvedFormSQL);
            PreparedStatement psDenied = connection.prepareStatement(deniedFormSQL);

            ResultSet rs = ps.executeQuery();
            ResultSet rsDenied = psDenied.executeQuery();

            while (rs.next()) {
                approvedForms.add(formFromResultSet(rs));
            }
            while (rsDenied.next()){
                deniedForms.add(formFromResultSet(rsDenied));
            }
            ps.close();
            psDenied.close();
            this.gotOldForms = true;
        } catch (SQLException e) {
            if (!e.getSQLState().equals("X0Y32"))
                e.printStackTrace();
        }
        numberApproved = approvedForms.size();
        numberDenied = deniedForms.size();
        try {
            String updateScore = "UPDATE AGENTS SET SCORE ="+ score + "WHERE TTBID =" + this.ttbID;
            String updateApproved = "UPDATE AGENTS SET NUMBERAPPROVED =" + numberApproved + "WHERE TTBID =" + this.ttbID;
            String updateDenied = "UPDATE AGENTS SET NUMBERDENIED =" + numberDenied + "WHERE TTBID =" + this.ttbID;

            PreparedStatement update = connection.prepareStatement(updateScore);
            PreparedStatement updateA = connection.prepareStatement(updateApproved);
            PreparedStatement updateD = connection.prepareStatement(updateDenied);
            update.executeUpdate();
            updateA.executeUpdate();
            updateD.executeUpdate();

            update.close();
            updateA.close();
            updateD.close();


        } catch (SQLException e) {
            if (!e.getSQLState().equals("X0Y32"))
                e.printStackTrace();
        }
    }

    //    // Parse a Form from database to object

    private Form formFromResultSet(ResultSet rs) throws SQLException {
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

        LabelImage formLabel = new LabelImage();
        Blob picture = rs.getBlob("labelImage");
        if (picture != null) {
            InputStream is = picture.getBinaryStream();
            f.getLabel().setLabelImage(new Image(is));
        }
//        f.getLabel().setLabelImage(img);
//        Image img = new Image();

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
