package Fuzzy;

import java.sql.Connection;

public class FuzzyContext {

    private IFuzzy f = new hiddenScore();

    public FuzzyContext(){}

    public IFuzzy getF() {
        return f;
    }

    public void setF(IFuzzy f) {
        this.f = f;
    }

    public String fuzzy(String input, Connection conn){
        return f.fuzzy(input, conn);
    }
}
