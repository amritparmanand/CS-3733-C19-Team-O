package Datatypes;

/**
 * @author Percy
 * @version It3
 * the singleton class to hold application options
 * specifies how many label applications an agent reviews when a new batch is requested
 * specifies which fuzzy algorithm to use
 * specifies which format to download
 */
public class Setting {

    private int formLimit = 5; // Default is 5
    private String fuzzy = "hiddenScore";
    private char format = ',';

    private static Setting instance = new Setting();

    private Setting() {}

    public static Setting getInstance(){
        return instance;
    }


    // Getters and setters
    public int getFormLimit() {
        return formLimit;
    }
    public void setFormLimit(int formLimit) {
        this.formLimit = formLimit;
    }
    public String getFuzzy() {
        return fuzzy;
    }
    public void setFuzzy(String fuzzy) {
        this.fuzzy = fuzzy;
    }
    public char getFormat() {
        return format;
    }
    public void setFormat(char format) {
        this.format = format;
    }


}
