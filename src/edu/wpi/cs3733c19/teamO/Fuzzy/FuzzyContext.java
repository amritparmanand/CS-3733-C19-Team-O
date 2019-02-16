package edu.wpi.cs3733c19.teamO.Fuzzy;

import java.sql.Connection;

public class FuzzyContext {

    private IFuzzy f;

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
