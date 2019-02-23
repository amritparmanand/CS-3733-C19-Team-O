package OCR.Tess4J.src;

import java.io.File;
import net.sourceforge.tess4j.*;

public class test{

    public static void main(String[] args) {
        File imageFile = new File("C:\\Users\\Gabe's PC\\IdeaProjects\\CS-3733-C19-Team-O\\src\\OCR\\Tess4J\\images\\36ebe13f5edb8ead661f0d979f5e0bae.jpg");
        ITesseract instance = new Tesseract();  // JNA Interface Mapping
        instance.setDatapath("C:\\Users\\Gabe's PC\\IdeaProjects\\CS-3733-C19-Team-O\\src\\OCR\\Tess4J\\tessdata");
        instance.setLanguage("eng");
        // ITesseract instance = new Tesseract1(); // JNA Direct Mapping
        //instance.setDatapath("tessdata"); // path to tessdata directory

        try {
            String result = instance.doOCR(imageFile);
            System.out.println(result);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
    }
}