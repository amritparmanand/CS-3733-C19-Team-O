package Fuzzy;

import Managers.DatabaseManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Levenshtein implements IFuzzy{

    private int min3(int a,int b, int c){
        int x = Math.min(a, b);
        return Math.min(x, c);
    }

    public int Levenshtein(String source, String target) {
        source = source.toLowerCase();
        target = target.toLowerCase();

        int[][] Table = new int[source.length()+1][target.length()+1];

        for(int i=0; i<source.length()+1; i++){
            Table[i][0] = i;
        }
        for(int i=0; i<target.length()+1; i++){
            Table[0][i] = i;
        }

        for(int row = 1; row <= source.length(); row++){
            for(int col = 1; col <= target.length(); col++){
                if(source.charAt(row-1) == target.charAt(col-1)){
                    Table[row][col] = Table[row-1][col-1];
                }
                else{
                    Table[row][col] = 1 + min3(Table[row][col-1], Table[row-1][col-1], Table[row-1][col]);
                }
            }
        }

        return Table[source.length()][target.length()];
    }

    @Override
    public String fuzzy(String input, Connection conn) {
        String best = "this is complete garbage";
        String iterator = "";
        int size = 0;

        try {
            String getEverything = "select * from FORMS";
            ResultSet r1 = conn.createStatement().executeQuery(getEverything);
            while(r1.next()){
                iterator = r1.getString("brandName");
                if(Levenshtein(input,iterator) <= Levenshtein(input,best)){
                    best = iterator;
                }
            }
        } catch (SQLException e) {
            if (!e.getSQLState().equals("X0Y32"))
                e.printStackTrace();
        }

        return best;
    }
}
