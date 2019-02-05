package Datatypes;

import java.math.BigInteger;
import java.util.Date;

public class Form {

    private int brewerNumber;
    private int productSource;
    private int serialNumber;
    private int productType;
    private String brandName;
    private String fancifulName;
    private String applicantName;
    private String mailingAddress;
    private String formula;
    private String grapeVarietal;
    private String appellation;
    private String phoneNumber;
    private String emailAddress;
    private Date dateOfApplication;
    private String printName;
    private int beerWineSpirit;
    private double alcoholPercent;
    private int vintageYear;
    private double pHLevel;
    private int forms_pk;

    public Form() {
        this.brewerNumber = 0;
        this.productSource = 0;
        this.serialNumber = 0;
        this.productType = 0;
        this.brandName = null;
        this.fancifulName = null;
        this.applicantName = null;
        this.mailingAddress = null;
        this.formula = null;
        this.grapeVarietal = null;
        this.appellation = null;
        this.phoneNumber = null;
        this.emailAddress = null;
        this.dateOfApplication = null;
        this.printName = null;
        this.beerWineSpirit = 0;
        this.alcoholPercent = 0;
        this.vintageYear = 0;
        this.pHLevel = 0;
        this.forms_pk = 0;
    }

    public Form(int brewerNumber, int productSource, int serialNumber, int productType, String brandName, String fancifulName, String applicantName, String mailingAddress, String formula, String grapeVarietal, String appellation, String phoneNumber, String emailAddress, Date dateOfApplication, String printName, int beerWineSpirit, double alcoholPercent, int vintageYear, double pHLevel, int forms_pk) {
        this.brewerNumber = brewerNumber;
        this.productSource = productSource;
        this.serialNumber = serialNumber;
        this.productType = productType;
        this.brandName = brandName;
        this.fancifulName = fancifulName;
        this.applicantName = applicantName;
        this.mailingAddress = mailingAddress;
        this.formula = formula;
        this.grapeVarietal = grapeVarietal;
        this.appellation = appellation;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.dateOfApplication = dateOfApplication;
        this.printName = printName;
        this.beerWineSpirit = beerWineSpirit;
        this.alcoholPercent = alcoholPercent;
        this.vintageYear = vintageYear;
        this.pHLevel = pHLevel;
        this.forms_pk = forms_pk;
    }

    public int getBrewerNumber() {
        return brewerNumber;
    }

    public void setBrewerNumber(int brewerNumber) {
        this.brewerNumber = brewerNumber;
    }

    public int getProductSource() {
        return productSource;
    }

    public void setProductSource(int productSource) {
        this.productSource = productSource;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getProductType() {
        return productType;
    }

    public void setProductType(int productType) {
        this.productType = productType;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getFancifulName() {
        return fancifulName;
    }

    public void setFancifulName(String fancifulName) {
        this.fancifulName = fancifulName;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getGrapeVarietal() {
        return grapeVarietal;
    }

    public void setGrapeVarietal(String grapeVarietal) {
        this.grapeVarietal = grapeVarietal;
    }

    public String getAppellation() {
        return appellation;
    }

    public void setAppellation(String appelation) {
        this.appellation = appelation;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Date getDateOfApplication() {
        return dateOfApplication;
    }

    public void setDateOfApplication(Date dateOfApplication) {
        this.dateOfApplication = dateOfApplication;
    }

    public String getPrintName() {
        return printName;
    }

    public void setPrintName(String printName) {
        this.printName = printName;
    }

    public int getBeerWineSpirit() {
        return beerWineSpirit;
    }

    public void setBeerWineSpirit(int beerWineSpirit) {
        this.beerWineSpirit = beerWineSpirit;
    }

    public double getAlcoholPercent() {
        return alcoholPercent;
    }

    public void setAlcoholPercent(double alcoholPercent) {
        this.alcoholPercent = alcoholPercent;
    }

    public int getVintageYear() {
        return vintageYear;
    }

    public void setVintageYear(int vintageYear) {
        this.vintageYear = vintageYear;
    }

    public double getpHLevel() {
        return pHLevel;
    }

    public void setpHLevel(double pHLevel) {
        this.pHLevel = pHLevel;
    }

    public int getForms_pk() {
        return forms_pk;
    }

    public void setForms_pk(int forms_pk) {
        this.forms_pk = forms_pk;
    }
}
