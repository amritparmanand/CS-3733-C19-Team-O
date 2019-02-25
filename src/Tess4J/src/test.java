package Tess4J.src;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

public class test{

    public static void main(String[] args) {
        File imageFile = new File("src/Tess4J/images/LabelHighRes_114.jpg");
        ITesseract instance = new Tesseract();
        instance.setDatapath("src/Tess4J.tessdata");
        instance.setLanguage("eng");

        try {
            String result = instance.doOCR(imageFile);
            System.out.println(result);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }

    }
}