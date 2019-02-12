package Fuzzy;

import Managers.DatabaseManager;
import org.apache.derby.iapi.services.cache.CacheManager;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SQL implements IFuzzy {
    private DatabaseManager dbM;

    @Override
    public String fuzzy(String input) {
        String suggestion = "";
        try {
            String q = "select BRANDNAME from FORMS where BRANDNAME like '%" + input + "%'";
            ResultSet r = dbM.getStmt().executeQuery(q);
            while(r.next()){
                suggestion = r.getString("brandName");
                System.out.println(suggestion);
            }
        } catch (SQLException e) {
            if (!e.getSQLState().equals("X0Y32"))
                e.printStackTrace();
        }
        return suggestion;
    }
}
