package Datatypes;

import Managers.CacheManager;
import Managers.SceneManager;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;
import org.apache.commons.collections4.functors.FalsePredicate;

/**
 * @author Robert Rinearson & Percy Jiang
 * @version It 1
 * Progress bar tracker for manufacturer application
 */

public class ProgressBar {
    private SceneManager sceneM;
    private CacheManager cacheM;
    private Form form;

    private float alpha;
    private float hue;
    private float saturation;
    private float value;

    private int questions1;
    private int questions2;
    private int questions3;
    private int questiosn4;

    @FXML
    private JFXTextField repID;
    @FXML private JFXTextField brewerNO;
    @FXML private RadioButton domestic;
    @FXML private RadioButton imported;
    @FXML private JFXTextField serialNumber;
    @FXML private RadioButton wine;
    @FXML private RadioButton distilled;
    @FXML private RadioButton malt;
    @FXML private JFXTextField brandName;
    @FXML private JFXTextField fancifulName;
    @FXML private JFXTextField alcoholPercentage;
    @FXML private JFXTextField phLevel;
    @FXML private JFXTextField vintageYear;
    @FXML private RadioButton wine2;
    @FXML private RadioButton spirits2;
    @FXML private RadioButton beer2;
    @FXML private JFXTextField printName;
    @FXML private JFXTextField mailAddress;
    @FXML private JFXTextField formula;
    @FXML private JFXTextField grapes;
    @FXML private JFXTextField appellation;
    @FXML private JFXTextField phoneNumber;
    @FXML private JFXTextField email;
    @FXML private JFXTextField onlyState;
    @FXML private JFXTextField ttbID;
    @FXML private JFXTextField bottleCapacity;
    @FXML private JFXCheckBox certificateOfApproval;
    @FXML private JFXCheckBox certificateOfExemption;
    @FXML private JFXCheckBox DistinctiveLiquor;
    @FXML private JFXCheckBox resubmission;
    @FXML private ImageView imagePreview;
    @FXML private JFXDatePicker dateOfApplication;
    @FXML private JFXTextField applicantSig;
    @FXML private JFXTextField applicantNamePrint;
    @FXML private LabelImage image = new LabelImage();
    @FXML private Button button;
    @FXML private JFXToggleButton switchButton;




    //rob
    //super lazy


    public ProgressBar(SceneManager sceneM, CacheManager cacheM, Form form) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
        this.form = form;
    }

    public float updateProgressBar(){
        System.out.println("progressbar");

        float progressPercentage = 0;

        if(form.getBrewerNumber()!=""){
            progressPercentage+=1;
        }

        if(form.getProductSource()!=""){
            progressPercentage+=1;
        }

        if(form.getSerialNumber()!=""){
            progressPercentage+=1;
        }

        if(form.getProductType()!=""){
            progressPercentage+=1;
        }

        if(form.getBrandName()!=""){
            progressPercentage+=1;
        }

        if(form.getFancifulName()!=""){
            progressPercentage+=1;
        }

        if(form.getApplicantName()!=""){
            progressPercentage+=1;
        }

        if(form.getMailingAddress()!=""){
            progressPercentage+=1;
        }

        if(form.getFormula()!=""){
            progressPercentage+=1;
        }

        if(form.getGrapeVarietal()!=""){
            progressPercentage+=1;
        }

        if(form.getAppellation()!=""){
            progressPercentage+=1;
        }

        if(form.getPhoneNumber()!=""){
            progressPercentage+=1;
        }

        if(form.getEmailAddress()!=""){
            progressPercentage+=1;
        }

        if(form.getDateOfApplication()!=""){
            progressPercentage+=1;
        }

        if(form.getPrintName()!=""){
            progressPercentage+=1;
        }

        if(form.getBeerWineSpirit()!=""){
            progressPercentage+=1;
        }

        if(form.getAlcoholPercent()!=""){
            progressPercentage+=1;
        }

        if(form.getVintageYear()!=""){
            progressPercentage+=1;
        }

        if(form.getpHLevel()!=""){
            progressPercentage+=1;
        }

        if(form.getCertificateOfApproval() || form.getCertificateOfExemption()
        || form.getDistinctiveLiquor() || form.getResubmission() ){
            progressPercentage+=1;
        }

        if(form.getLabel()!=null){
            progressPercentage+=1;
        }

        progressPercentage= progressPercentage/19;

        return progressPercentage;

    }


    //we will make it change color later
//    void _onChanged(double value) {
//        setState(() {
//            _value = value;
//        });
//    }
//
//    HSVColor colorchange(double a double hf double sf double vf double hi double si double vi double value){
//
//        double dh = hf-hi;
//        double ds = sf-si;
//        double dv = vf-vi;
//        var colorFinal =  HSVColor.fromAHSV(1.0,hf,sf,vf);
//        var colorInitial = HSVColor.fromAHSV(1.0,hi,si,vi);
//        var colorChange = HSVColor.fromAHSV(a, hi +((value.round()/100)*dh), si + ((value.round()/100)*ds), vi + ((value.round()/100)*dv));
//        return colorChange;

    }
