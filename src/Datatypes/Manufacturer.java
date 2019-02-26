package Datatypes;

//import io.undertow.websockets.core.BinaryOutputStream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import jdk.internal.util.xml.impl.Input;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
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

    // Query the database to select forms where the TTB ID matches this agent's id
    // Call formFromResultSet into object and add it into the working Forms of this agent
    @SuppressWarnings("Duplicates")
    public void setAssignedForms(Connection conn) {
        workingForms.clear();

        try {
            String assignedForms = "SELECT * FROM APPLICATIONS JOIN FORMS ON FORMS.FORMID = APPLICATIONS.FORMID" +
                    " WHERE APPLICATIONS.REPID ="+ this.repID;
            PreparedStatement ps = conn.prepareStatement(assignedForms);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                workingForms.add(formFromResultSet(rs));
            }
            ps.close();
        } catch (SQLException e) {
            if (!e.getSQLState().equals("X0Y32"))
                e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        byte[] picture = rs.getBytes("labelImage");
        FileOutputStream os = new FileOutputStream("temp.png");
        if (picture != null) {
            os.write(picture);
            os.close();
            File jimbus = new File("temp.png");
            f.getLabel().setLabelFile(jimbus);
            f.getLabel().setLabelImage(new Image(f.getLabel().getLabelFile().toURI().toString()));
            jimbus.delete();
        }

        return f;
    }

    @SuppressWarnings("Duplicates") public int countStatus(Connection connection, String status){
        int result = 0;

        String getNum = "SELECT count(APPID) as c FROM APPLICATIONS where STATUS = '" + status +"' and REPID = " + this.getRepID();
        try {
            ResultSet rset = connection.createStatement().executeQuery(getNum);
            while(rset.next())
                result = rset.getInt("c");
        } catch (SQLException e) {
            if (!e.getSQLState().equals("X0Y32"))
                e.printStackTrace();
        }

        return result;
    }
}