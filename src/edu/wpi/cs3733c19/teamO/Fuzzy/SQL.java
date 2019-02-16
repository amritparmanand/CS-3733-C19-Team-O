package edu.wpi.cs3733c19.teamO.Fuzzy;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQL implements IFuzzy {

    @Override
    public String fuzzy(String input, Connection conn) {
        String suggestion = "";
        try {
            String q = "select * from FORMS where BRANDNAME like '%" + input + "%'";
            ResultSet r = conn.createStatement().executeQuery(q);
            while(r.next()){
                suggestion = r.getString("brandName");
            }
        } catch (SQLException e) {
            if (!e.getSQLState().equals("X0Y32"))
                e.printStackTrace();
        }
        if(suggestion == ""){
            try {
                String q = "select * from FORMS where FANCIFULNAME like '%" + input + "%'";
                ResultSet r = conn.createStatement().executeQuery(q);
                while(r.next()){
                    suggestion = r.getString("fancifulName");
                }
            } catch (SQLException e) {
                if (!e.getSQLState().equals("X0Y32"))
                    e.printStackTrace();
            }
        }
        return suggestion;
    }
}
