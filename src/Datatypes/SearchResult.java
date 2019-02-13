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
    private String phLevel;
    private String alcohol;
    private String year;
    private String beerWineSpirit;


    public SearchResult(String fancifulName, String companyName, String alcoholType, String phLevel,
                        String alcohol, String year, String beerWineSpirit)
    {
        this.fancifulName = fancifulName;
        this.companyName = companyName;
        this.alcoholType = alcoholType;
        this.phLevel = phLevel;
        this.alcohol = alcohol;
        this.year = year;
        this.beerWineSpirit = beerWineSpirit;

    }

    public SearchResult()
    {
        this.fancifulName = null;
        this.companyName = null;
        this.alcoholType = null;
        this.phLevel = null;
        this.alcohol = null;
        this.year = null;
        this.beerWineSpirit = null;

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
    public String getPhLevel() {
        return phLevel;
    }
    public void setPhLevel(String phLevel) {
        this.phLevel = phLevel;
    }
    public String getAlcohol() {
        return alcohol;
    }
    public void setAlcohol(String alcohol) {
        this.alcohol = alcohol;
    }
    public String getYear() {
        return year;
    }
    public void setYear(String year) {
        this.year = year;
    }
    public String getBeerWineSpirit() { return beerWineSpirit; }
    public void setBeerWineSpirit(String beerWineSpirit) { this.beerWineSpirit = beerWineSpirit; }
}
