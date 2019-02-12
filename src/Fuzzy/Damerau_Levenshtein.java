package Fuzzy;

import Managers.DatabaseManager;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Damerau_Levenshtein implements IFuzzy {
    private DatabaseManager dbM;

    private int min5(int a,int b, int c, int d, int e){
        int x = Math.min(a, b);
        int y = Math.min(c, d);
        int z = Math.min(x,y);
        return Math.min(z,e);
    }

    public int Damerau_Levenshtein(String source, String target) {
        source = source.toLowerCase();
        target = target.toLowerCase();

        int[][] Table = new int[source.length()+1][target.length()+1];

        for(int row = 0; row <= source.length(); row++){
            for(int col = 0; col <= target.length(); col++){
                int v1 = 99;
                int v2 = 99;
                int v3 = 99;
                int v4 = 99;
                int v5 = 99;

                if(row==0 && col==0){
                    v1 = 0;
                }
                if(row > 0){
                    v2 = Table[row-1][col] + 1;
                }
                if(col > 0){
                    v3 = Table[row][col-1] +1;
                }
                if(row>0 && col>0){
                    if(source.charAt(row-1) != target.charAt(col-1)){
                        v4 = Table[row-1][col-1] + 1;
                    }
                    else{
                        v4 = Table[row-1][col-1];
                    }
                }
                if(row>1 && col>1 && source.charAt(row-1)==target.charAt(col-2) && source.charAt(row-2)==target.charAt(col-1)){
                    v5 = Table[row-2][col-2] + 1;
                }

                Table[row][col] = min5(v1,v2,v3,v4,v5);
            }
        }

        return Table[source.length()][target.length()];
    }


    @Override
    public String fuzzy(String input) {
        String best = "this is complete garbage";
        String iterator = "";
        int size = 0;

        try {
            String getSize = "select count(*) as size from FORMS";
            ResultSet r1 = dbM.getStmt().executeQuery(getSize);
            while(r1.next()){
                size = r1.getInt("size");
            }
        } catch (SQLException e) {
            if (!e.getSQLState().equals("X0Y32"))
                e.printStackTrace();
        }

        for(int i = 1; i <= size; i++){
            try {
                String q = "select BRANDNAME from FORMS where FORMID = " + i;
                ResultSet r2 = dbM.getStmt().executeQuery(q);
                while(r2.next()){
                    iterator = r2.getString("brandName");
                }
            } catch (SQLException e) {
                if (!e.getSQLState().equals("X0Y32"))
                    e.printStackTrace();
            }

            if(Damerau_Levenshtein(input,iterator) <= Damerau_Levenshtein(input,best)){
                best = iterator;
            }
        }

        return best;
    }
}
