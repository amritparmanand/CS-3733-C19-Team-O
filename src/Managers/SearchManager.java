package Managers;

import java.time.LocalDate;

public class SearchManager {
    public boolean isActive;
    private LocalDate fromDate;
    private LocalDate toDate;

    private Integer brandFancyEither;

    private String originState;

    private Integer receivedCode;
    private String fromTTB;
    private String toTTB;

    private String fromSerial;
    private String toSerial;

    private String brewerNumber;

    public boolean exists(Object field) {
        return (field != null) ? true : false;
    }

    public SearchManager(LocalDate fromDate, LocalDate toDate, Integer brandFancyEither, String originState, Integer receivedCode, String fromTTB, String toTTB, String fromSerial, String toSerial, String brewerNumber) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.brandFancyEither = brandFancyEither;
        this.originState = originState;
        this.receivedCode = receivedCode;
        this.fromTTB = fromTTB;
        this.toTTB = toTTB;
        this.fromSerial = fromSerial;
        this.toSerial = toSerial;
        this.brewerNumber = brewerNumber;
        this.isActive = true;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public void setBrandFancyEither(Integer brandFancyEither) {
        this.brandFancyEither = brandFancyEither;
    }

    public void setOriginState(String originState) {
        this.originState = originState;
    }

    public void setReceivedCode(Integer receivedCode) {
        this.receivedCode = receivedCode;
    }

    public void setFromTTB(String fromTTB) {
        this.fromTTB = fromTTB;
    }

    public void setToTTB(String toTTB) {
        this.toTTB = toTTB;
    }

    public void setFromSerial(String fromSerial) {
        this.fromSerial = fromSerial;
    }

    public void setToSerial(String toSerial) {
        this.toSerial = toSerial;
    }

    public void setBrewerNumber(String brewerNumber) {
        this.brewerNumber = brewerNumber;
    }

    public SearchManager() {
        this.fromDate = null;
        this.toDate = null;
        this.brandFancyEither = null;
        this.originState = null;
        this.receivedCode = null;
        this.fromTTB = null;
        this.toTTB = null;
        this.fromSerial = null;
        this.toSerial = null;
        this.brewerNumber = null;
        this.isActive = false;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public Integer getBrandFancyEither() {
        return brandFancyEither;
    }

    public String getOriginState() {
        return originState;
    }

    public Integer getReceivedCode() {
        return receivedCode;
    }

    public String getFromTTB() {
        return fromTTB;
    }

    public String getToTTB() {
        return toTTB;
    }

    public String getFromSerial() {
        return fromSerial;
    }

    public String getToSerial() {
        return toSerial;
    }

    public String getBrewerNumber() {
        return brewerNumber;
    }
}
