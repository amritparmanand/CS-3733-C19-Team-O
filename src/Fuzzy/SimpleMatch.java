package Fuzzy;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SimpleMatch implements IFuzzy{
    @Override
    public int fuzzy(String source, String target) {
//        try {
//            String getData = "select * from AGENTS where TTBID = " + id;
//            ResultSet result = this.getStmt().executeQuery(getData);
//            while(result.next()){
//                hashedPassword = result.getString("password");
//            }
//        } catch (SQLException e) {
//            if (!e.getSQLState().equals("X0Y32"))
//                e.printStackTrace();
//        }

        return 0;
    }
}
