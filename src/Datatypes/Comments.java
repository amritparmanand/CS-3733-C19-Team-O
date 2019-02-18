package Datatypes;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Comments {
    //comments 1 through 19
//    String comment1 = "";
//    String comment2 = "";
//    String comment3 = "";
//    String comment4 = "";
//    String comment5 = "";
//    String comment6 = "";
//    String comment7 = "";
//    String comment8 = "";
//    String comment8a = "";
//    String comment9 = "";
//    String comment10 = "";
//    String comment11 = "";
//    String comment12 = "";
//    String comment13 = "";
//    String comment14 = "";
//    String comment15 = "";
//    String comment16 = "";
//    String comment17 = "";
//    String comment18 = "";
//    String comment19 = "";

    //Method[] methods = getClass().getMethods();
    ArrayList<String> comments = new ArrayList<String> (20);

    public Comments() {
        for(int i = 0; i<20; i++) {
            comments.add("");
        }
    }

    public String getComment1() {
        return comments.get(0);
    }

    public void setComment1(String comment1) {
        comments.set(0, comment1);
    }

    public String getComment2() {
        return comments.get(1);
    }

    public void setComment2(String comment2) {
        comments.set(1, comment2);
    }

    public String getComment3() {
        return comments.get(2);
    }

    public void setComment3(String comment3) {
        comments.set(2, comment3);
    }

    public String getComment4() {
        return comments.get(3);
    }

    public void setComment4(String comment4) {
        comments.set(3, comment4);
    }

    public String getComment5() {
        return comments.get(4);
    }

    public void setComment5(String comment5) {
        comments.set(4, comment5);
    }

    public String getComment6() {
        return comments.get(5);
    }

    public void setComment6(String comment6) {
        comments.set(5, comment6);
    }

    public String getComment7() {
        return comments.get(6);
    }

    public void setComment7(String comment7) {
        comments.set(6, comment7);
    }

    public String getComment8() {
        return comments.get(7);
    }

    public void setComment8(String comment8) {
        comments.set(7, comment8);
    }

    public String getComment8a() {
        return comments.get(8);
    }

    public void setComment8a(String comment8a) {
        comments.set(8, comment8a);
    }

    public String getComment9() {
        return comments.get(9);
    }

    public void setComment9(String comment9) {
        comments.set(9, comment9);
    }

    public String getComment10() {
        return comments.get(10);
    }

    public void setComment10(String comment10) {
        comments.set(10, comment10);
    }

    public String getComment11() {
        return comments.get(11);
    }

    public void setComment11(String comment11) {
        comments.set(11, comment11);
    }

    public String getComment12() {
        return comments.get(12);
    }

    public void setComment12(String comment12) {
        comments.set(12, comment12);
    }

    public String getComment13() {
        return comments.get(13);
    }

    public void setComment13(String comment13) {
        comments.set(13, comment13);
    }

    public String getComment14() {
        return comments.get(14);
    }

    public void setComment14(String comment14) {
        comments.set(14, comment14);
    }

    public String getComment15() {
        return comments.get(15);
    }

    public void setComment15(String comment15) {
        comments.set(15, comment15);
    }

    public String getComment16() {
        return comments.get(16);
    }

    public void setComment16(String comment16) {
        comments.set(16, comment16);
    }

    public String getComment17() {
        return comments.get(17);
    }

    public void setComment17(String comment17) {
        comments.set(17, comment17);
    }

    public String getComment18() {
        return comments.get(18);
    }

    public void setComment18(String comment18) {
        comments.set(18, comment18);;
    }

    public String getComment19() {
        return comments.get(19);
    }

    public void setComment19(String comment19) {
        comments.set(19, comment19);
    }

    public String generateComments(Object c) throws Exception
    {
        String commentBlurb = "";
//        int getterNumber = 0;
//        //iterate through all the comments
//        List<Method> getters = new ArrayList<Method>();

//        for (int i = 0; i < methods.length; i++) {
//            if ((methods[i].getName().startsWith("get"))) {
//                getters.add(methods[i]);
//                //System.out.println(methods[i].toString());
//            }
//        }
//
//        System.out.println(getters.get(16));
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
        for(int i = 0; i < comments.size(); i++) {
            //if it is not ""
            if(!comments.get(i).equals(""))
            {
                if(i == 8)
                commentBlurb += "8a"  + ". " + comments.get(i) + "\n";

                if(i > 8)
                commentBlurb += i  + ". " + comments.get(i) + "\n";

                else
                commentBlurb += i+1  + ". " + comments.get(i) + "\n";


            }
            //return the concatenation of all of them
        }
        return commentBlurb;
    }
}
