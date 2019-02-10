package Datatypes;
/**
 * @author Robert Rinearson & Percy Jiang
 * @version It 1
 * Container for search results
 */
public class SearchResult {
    private String fancifulName;
    private String companyName;
    private String alcoholType;
    private boolean isBeer;
    private boolean isLiquor;
    private boolean isWine;
    private double phLevel;
    private double alcohol;
    private int year;


    public SearchResult(String fancifulName, String companyName, String alcoholType,
                        boolean isBeer, boolean isLiquor, boolean isWine, double phLevel,
                        double alcohol, int year)
    {
        this.fancifulName = fancifulName;
        this.companyName = companyName;
        this.alcoholType = alcoholType;
        this.isBeer = isBeer;
        this.isLiquor = isLiquor;
        this.isWine = isWine;
        this.phLevel = phLevel;
        this.alcohol = alcohol;
        this.year = year;

    }

    public SearchResult()
    {
        this.fancifulName = null;
        this.companyName = null;
        this.alcoholType = null;
        this.isBeer = false;
        this.isLiquor = false;
        this.isWine = false;
        this.phLevel = 0;
        this.alcohol = 0;
        this.year = 0;

    }

    public String getFancifulName() {
        return fancifulName;
    }
    public void setFancifulName(String fancifulName) {
        this.fancifulName = fancifulName;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public String getAlcoholType() {
        return alcoholType;
    }
    public void setAlcoholType(String alcoholType) {
        this.alcoholType = alcoholType;
    }
    public boolean isBeer() {
        return isBeer;
    }
    public void setBeer(boolean beer) {
        isBeer = beer;
    }
    public boolean isLiquor() {
        return isLiquor;
    }
    public void setLiquor(boolean liquor) {
        isLiquor = liquor;
    }
    public boolean isWine() {
        return isWine;
    }
    public void setWine(boolean wine) {
        isWine = wine;
    }
    public double getPhLevel() {
        return phLevel;
    }
    public void setPhLevel(double phLevel) {
        this.phLevel = phLevel;
    }
    public double getAlcohol() {
        return alcohol;
    }
    public void setAlcohol(double alcohol) {
        this.alcohol = alcohol;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }

}
