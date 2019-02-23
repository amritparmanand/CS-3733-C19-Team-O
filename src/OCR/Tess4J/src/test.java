package OCR.Tess4J.src;

import java.io.File;
import net.sourceforge.tess4j.*;

public class test{

    public static void main(String[] args) {
        File imageFile = new File("src/OCR/Tess4J/images/Question+Signs+appear+in+restaurants+and+other+locations+where+alcohol+is+stating.+Government+Warning_.tiff");
        ITesseract instance = new Tesseract();
        instance.setDatapath("src\\OCR\\Tess4J\\tessdata");
        instance.setLanguage("eng");

        try {
            String result = instance.doOCR(imageFile);
            System.out.println(result);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
    }
}