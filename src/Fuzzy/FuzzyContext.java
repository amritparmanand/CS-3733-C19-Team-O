package Fuzzy;

/**
 * @author Percy
 * @version It2
 */
public class FuzzyContext {

    private IFuzzy f;

    public FuzzyContext(){}

    public IFuzzy getF() {
        return f;
    }

    public void setF(IFuzzy f) {
        this.f = f;
    }

    public int fuzzy(String source, String target){
        return f.fuzzy(source,target);
    }
}
