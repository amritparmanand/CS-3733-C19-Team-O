package Datatypes;

/**
 * @author Robert Rinearson & Percy Jiang
 * @version It 1
 * Progress bar tracker for manufacturer application
 */

public class ProgressBar {

    private float alpha;
    private float hue;
    private float saturation;
    private float value;

    private int questions1;
    private int questions2;
    private int questions3;
    private int questiosn4;

    //rob
    //super lazy

    public float updateProgressBar1(int questionsAnswered1){
        float percentComplete = questionsAnswered1/questions1;
        return percentComplete;
    }

    public float updateProgressBar2(int questionsAnswered2){
        float percentComplete = questionsAnswered2/questions1;
        return percentComplete;
    }

    public float updateProgressBar3(int questionsAnswered3){
        float percentComplete = questionsAnswered3/questions1;
        return percentComplete;
    }

    public float updateProgressBar4(int questionsAnswered4){
        float percentComplete = questionsAnswered4/questions1;
        return percentComplete;
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
