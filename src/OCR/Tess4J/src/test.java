package OCR.Tess4J.src;

import java.io.File;
import net.sourceforge.tess4j.*;

public class test{

    public static void main(String[] args) {
        File imageFile = new File("src/UI/Images/surgeon.jpg");
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