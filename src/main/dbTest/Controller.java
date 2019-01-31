package dbTest;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.sql.*;
import java.util.Random;

public class Controller {
    @FXML
    private Button sendData;
    @FXML
    private Button getButton;
    @FXML
    private Label field1;

    public void sendRandomData(){
        Random r = new Random();
        Connection connection = null;
        Statement stmt = null;
        try {
            connection = DriverManager.getConnection("jdbc:derby:ttbDB;create=true");
            stmt = connection.createStatement();
        }
        catch(SQLException e){
            System.out.println("Connection Failed. Check stacktrace.");
            e.printStackTrace();
        }
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String generatedData = "INSERT INTO MANUFACTURERS VALUES("+ r.nextInt(50) + ", " +
                    " '" + alphabet.charAt(r.nextInt(alphabet.length())) +
                    alphabet.charAt(r.nextInt(alphabet.length())) +
                    alphabet.charAt(r.nextInt(alphabet.length()))+ "'," +
                    "'example@sample.com'," +
                    r.nextInt(9999999)+ ")";
        System.out.println(generatedData);
        try{
            stmt.execute(generatedData);
        }
        catch(SQLException e){
            if (!e.getSQLState().equals("23505"))
                e.printStackTrace();
            else
                System.out.println("Used a duplicate ID");
        }
    }
    public void clearData(){
        Connection connection = null;
        Statement stmt = null;
        try {
            connection = DriverManager.getConnection("jdbc:derby:ttbDB;create=true");
            stmt = connection.createStatement();
        }
        catch(SQLException e){
            System.out.println("Connection Failed. Check stacktrace.");
            e.printStackTrace();
        }
        String clear = "DELETE FROM MANUFACTURERS WHERE 1=1";
        try{
            stmt.execute(clear);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void buildData(){
        Connection connection = null;
        Statement stmt = null;
        try {
            connection = DriverManager.getConnection("jdbc:derby:ttbDB;create=true");
            stmt = connection.createStatement();
        }
        catch(SQLException e){
            System.out.println("Connection Failed. Check stacktrace.");
            e.printStackTrace();
        }
        Statement st = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd;
        int columnsNumber = 0;
        try {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT * FROM MANUFACTURERS");
            rsmd = rs.getMetaData();
            columnsNumber = rsmd.getColumnCount();
        } catch(SQLException e){
            e.printStackTrace();
        }
        String out = "";
        try {
            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {

                    out += (rs.getString(i) + " "); //Print one element of a row

                }

                out += "\n";//Move to the next line to print the next row.

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        field1.setText(out);
    }
}
