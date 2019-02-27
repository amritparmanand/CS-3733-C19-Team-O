package Managers;

import Datatypes.SearchResult;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;

public class SearchManager {
    public boolean isActive;
    private LocalDate fromDate;
    private LocalDate toDate;

    private Integer brandFancyEither;

    private String originState;

    private boolean receivedCode0;
    private boolean receivedCode1;
    private boolean receivedCode2;
    private boolean receivedCode3;

    private String fromTTB;
    private String toTTB;

    private String fromSerial;
    private String toSerial;

    private String brewerNumber;

    public boolean exists(Object field) {
        return (field != null) ? true : false;
    }

    public SearchManager(LocalDate fromDate, LocalDate toDate, Integer brandFancyEither, String originState, String fromTTB, String toTTB, String fromSerial, String toSerial, String brewerNumber, boolean receivedCode0, boolean receivedCode1, boolean receivedCode2, boolean receivedCode3) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.brandFancyEither = brandFancyEither;
        this.originState = originState;
        this.receivedCode0 = receivedCode0;
        this.receivedCode1 = receivedCode1;
        this.receivedCode2 = receivedCode2;
        this.receivedCode3 = receivedCode3;
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

    public void setReceivedCode0(boolean receivedCode0) {
        this.receivedCode0 = receivedCode0;
    }

    public void setReceivedCode1(boolean receivedCode1) {
        this.receivedCode1 = receivedCode1;
    }

    public void setReceivedCode2(boolean receivedCode2) {
        this.receivedCode2 = receivedCode2;
    }

    public void setReceivedCode3(boolean receivedCode3) {
        this.receivedCode3 = receivedCode3;
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
        this.receivedCode0 = true;
        this.receivedCode1 = true;
        this.receivedCode2 = true;
        this.receivedCode3 = true;
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

    public boolean getReceivedCode0() {
        return receivedCode0;
    }

    public boolean getReceivedCode1() {
        return receivedCode1;
    }

    public boolean getReceivedCode2() {
        return receivedCode2;
    }

    public boolean getReceivedCode3() {
        return receivedCode3;
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


    public LinkedList<SearchResult> filter(LinkedList<SearchResult> approvedResults, String oldSearch) {
        LinkedList<SearchResult> filtered = new LinkedList<>();

        for (int i = 0; i < approvedResults.size(); i++) {
            SearchResult sr = approvedResults.get(i);
            boolean invalidateRecord = false;

            //brand fancy either Check
            if (exists(getBrandFancyEither())) {
                //System.out.println("NAME AND BRAND EXIST");
                switch (getBrandFancyEither()) {
                    case 3:
                        break;
                    case 2:
                        if (!sr.getFancifulName().toLowerCase().contains(oldSearch.toLowerCase()))
                            invalidateRecord = true;
                        break;
                    case 1:
                        if (!sr.getCompanyName().toLowerCase().contains(oldSearch.toLowerCase()))
                            invalidateRecord = true;
                        break;
                    default:
                        break;
                }
            }

            //date check
            LocalDate recordDate = null;
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/uuuu");
                recordDate = LocalDate.parse(sr.getApprovedDate(), formatter);
            } catch (DateTimeParseException e) {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
                    recordDate = LocalDate.parse(sr.getApprovedDate(), formatter);
                } catch (DateTimeParseException f) {
                    System.out.println("invalid date");
                }
            }
            if (exists(getFromDate())) {
                //System.out.println("FROM DATE EXISTS");
                if (recordDate.compareTo(getFromDate()) < 0)
                    invalidateRecord = true;
            }
            if (exists(getToDate())) {
                //System.out.println("TO DATE EXISTS");
                if (recordDate.compareTo(getToDate()) > 0)
                    invalidateRecord = true;
            }

            //origin check
            if (exists(getOriginState())) {
                if(!sr.getOrigin().equals(getOriginState()))
                    invalidateRecord = true;
            }

            //receivedCodeCheck
//            System.out.println(sr.getFormID());
//            System.out.println(sr.getFormID().substring(5, 8));
            try {
                switch (sr.getFormID().substring(5, 8)) {
                    case "000":
                        if (!receivedCode0)
                            invalidateRecord = true;
                        break;
                    case "001":
                        if (!receivedCode1)
                            invalidateRecord = true;
                        break;
                    case "002":
                        if (!receivedCode2)
                            invalidateRecord = true;
                        break;
                    case "003":
                        if (!receivedCode3)
                            invalidateRecord = true;
                        break;
                }
            }catch (Exception e){
                System.out.println("soupemn");
            }

            //ttbID range
            int recordTTBID = Integer.parseInt(sr.getTTBID());
            if (exists(getFromTTB())) {
                //System.out.println("FROM TTB EXISTS");
                if (recordTTBID < Integer.parseInt(getFromTTB()))
                    invalidateRecord = true;
            }
            if (exists(getToTTB())) {
                //System.out.println("TO TTB EXISTS");
                if (recordTTBID > Integer.parseInt(getToTTB()))
                    invalidateRecord = true;
            }

            //serialNumber range
            if (exists(getFromSerial())) {
                //System.out.println("FROM SERIAL EXISTS");
                if (sr.getSerialNum().compareTo(getFromSerial()) < 0)
                    invalidateRecord = true;
            }
            if (exists(getToSerial())) {
                //System.out.println("TO SERIAL EXISTS");
                if (sr.getSerialNum().compareTo(getToSerial()) > 0)
                    invalidateRecord = true;
            }

            //brewerNumber exact
            if (exists(getBrewerNumber())) {
                //System.out.println("BREWER NUMBER EXISTS");
                if (!sr.getBrewerNum().toLowerCase().equals(getBrewerNumber().toLowerCase()))
                    invalidateRecord = true;
            }

            if (!invalidateRecord)
                filtered.add(sr);
        }
        return filtered;
    }
}
