package Datatypes;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;

/**
 * @author Percy Jiang & Gabe Entov
 * @version It 1
 * Class for a manufacturer account
 */
public class Manufacturer extends Account {
    private int repID;
    private ArrayList<Form> workingForms = new ArrayList<>();
    private boolean hasFetchedForms = false;
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
    public boolean getHasFetchedForms() {
        return hasFetchedForms;
    }

    // Query the database to select forms where the TTB ID matches this agent's id
    // Call formFromResultSet into object and add it into the working Forms of this agent
    @SuppressWarnings("Duplicates")
    public void retrieveAssignedForms(Connection conn) {
        try {
            ArrayList<Form> jeans = new ArrayList<>();
            String assignedForms = "SELECT * FROM APPLICATIONS JOIN FORMS ON FORMS.FORMID = APPLICATIONS.FORMID WHERE APPLICATIONS.REPID = " + this.getRepID();
            PreparedStatement ps = conn.prepareStatement(assignedForms);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                jeans.add(formFromResultSet(rs));
            }
            setWorkingForms(jeans);
            ps.close();
        } catch (SQLException e) {
            if (!e.getSQLState().equals("X0Y32"))
                e.printStackTrace();
        }
    }

    public void setWorkingForms(ArrayList<Form> workingForms) {
        this.workingForms = workingForms;
    }

    public ArrayList<Form> getAssignedForms() {
        return workingForms;
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
    @SuppressWarnings("Duplicates")
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
        LabelImage formLabel = new LabelImage();
        Blob picture = rs.getBlob("labelImage");
        if (picture != null) {
            InputStream is = picture.getBinaryStream();
        }
//        f.getLabel().setLabelImage(img);
//        Image img = new Image();

        return f;
    }
}
