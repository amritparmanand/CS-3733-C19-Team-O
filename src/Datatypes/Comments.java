package Datatypes;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Comments {
    //comments 1 through 19
    String comment1 = "";
    String comment2 = "";
    String comment3 = "";
    String comment4 = "";
    String comment5 = "";
    String comment6 = "";
    String comment7 = "";
    String comment8 = "";
    String comment8a = "";
    String comment9 = "";
    String comment10 = "";
    String comment11 = "";
    String comment12 = "";
    String comment13 = "";
    String comment14 = "";
    String comment15 = "";
    String comment16 = "";
    String comment17 = "";
    String comment18 = "";
    String comment19 = "";

    Method[] methods = getClass().getMethods();

    public Comments() {
    }

    public String getComment1() {
        return comment1;
    }

    public void setComment1(String comment1) {
        this.comment1 = comment1;
    }

    public String getComment2() {
        return comment2;
    }

    public void setComment2(String comment2) {
        this.comment2 = comment2;
    }

    public String getComment3() {
        return comment3;
    }

    public void setComment3(String comment3) {
        this.comment3 = comment3;
    }

    public String getComment4() {
        return comment4;
    }

    public void setComment4(String comment4) {
        this.comment4 = comment4;
    }

    public String getComment5() {
        return comment5;
    }

    public void setComment5(String comment5) {
        this.comment5 = comment5;
    }

    public String getComment6() {
        return comment6;
    }

    public void setComment6(String comment6) {
        this.comment6 = comment6;
    }

    public String getComment7() {
        return comment7;
    }

    public void setComment7(String comment7) {
        this.comment7 = comment7;
    }

    public String getComment8() {
        return comment8;
    }

    public void setComment8(String comment8) {
        this.comment8 = comment8;
    }

    public String getComment8a() {
        return comment8a;
    }

    public void setComment8a(String comment8a) {
        this.comment8a = comment8a;
    }

    public String getComment9() {
        return comment9;
    }

    public void setComment9(String comment9) {
        this.comment9 = comment9;
    }

    public String getComment10() {
        return comment10;
    }

    public void setComment10(String comment10) {
        this.comment10 = comment10;
    }

    public String getComment11() {
        return comment11;
    }

    public void setComment11(String comment11) {
        this.comment11 = comment11;
    }

    public String getComment12() {
        return comment12;
    }

    public void setComment12(String comment12) {
        this.comment12 = comment12;
    }

    public String getComment13() {
        return comment13;
    }

    public void setComment13(String comment13) {
        this.comment13 = comment13;
    }

    public String getComment14() {
        return comment14;
    }

    public void setComment14(String comment14) {
        this.comment14 = comment14;
    }

    public String getComment15() {
        return comment15;
    }

    public void setComment15(String comment15) {
        this.comment15 = comment15;
    }

    public String getComment16() {
        return comment16;
    }

    public void setComment16(String comment16) {
        this.comment16 = comment16;
    }

    public String getComment17() {
        return comment17;
    }

    public void setComment17(String comment17) {
        this.comment17 = comment17;
    }

    public String getComment18() {
        return comment18;
    }

    public void setComment18(String comment18) {
        this.comment18 = comment18;
    }

    public String getComment19() {
        return comment19;
    }

    public void setComment19(String comment19) {
        this.comment19 = comment19;
    }

    public String generateComments(Object c) throws Exception
    {
        String comments = "";
        int getterNumber = 0;
        //iterate through all the comments
        List<Method> getters = new ArrayList<Method>();

        for (int i = 0; i < methods.length; i++) {
            if ((methods[i].getName().startsWith("get"))) {
                getters.add(methods[i]);
                System.out.println(getters.get(i));
                //System.out.println(methods[i].toString());
            }
        }

//        for(int i = 0; i < methods.length; i++)
//        {
//            System.out.println(methods[i]);
////            if(methods[i].getName().contains("get"))
////            {
////                //if it's a getter increment this
////                getterNumber ++;
////                System.out.println(getterNumber);
////                //if it is not ""
////                if(!methods[i].invoke(c).equals("\n")) {
////                    System.out.println(methods[i].invoke(c));
////                    comments += getterNumber + ". " + methods[i].invoke(c);
////                }
////            }
//        }

        //return the concatenation of all of them
        return comments;
    }
}
