/**
 * @author Percy
 * @version It2
 */
public class Damerau_Levenshtein implements IFuzzy {

    private int min5(int a,int b, int c, int d, int e){
        int x = Math.min(a, b);
        int y = Math.min(c, d);
        int z = Math.min(x,y);
        return Math.min(z,e);
    }

    @Override
    public int fuzzy(String source, String target) {
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
}
