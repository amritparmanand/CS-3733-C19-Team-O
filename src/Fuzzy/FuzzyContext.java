package Fuzzy;

public class FuzzyContext {

    private IFuzzy f;

    public FuzzyContext(){}

    public IFuzzy getF() {
        return f;
    }

    public void setF(IFuzzy f) {
        this.f = f;
    }

    public String fuzzy(String input){
        return f.fuzzy(input);
    }
}
