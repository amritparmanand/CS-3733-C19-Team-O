package OCR.Tess4J.src;

import com.sun.jna.ptr.PointerByReference;
import net.sourceforge.lept4j.Leptonica;
import net.sourceforge.lept4j.Pix;
import net.sourceforge.lept4j.util.LeptUtils;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;



import static junit.framework.TestCase.assertEquals;

public class test{

    public static void main(String[] args) throws IOException {
        File imageFile = new File("C:\\Users\\jiang\\Pictures\\Organic-Wine-Certification-Statement-TTB-USDA.jpg");

//        Leptonica leptInstance = Leptonica.INSTANCE;
//        Pix pix = leptInstance.pixRead(imageFile.getPath());
//        BufferedImage result = LeptUtils.convertPixToImage(pix);
//        assertEquals(pix.w, result.getWidth());
//        assertEquals(pix.h, result.getHeight());
//        assertEquals(pix.d, result.getColorModel().getPixelSize());
//        System.out.println(String.format("Image properties: width=%d, height=%d, depth=%d", result.getWidth(), result.getHeight(), result.getColorModel().getPixelSize()));
//        PointerByReference pRef = new PointerByReference();
//        pRef.setValue(pix.getPointer());
//        leptInstance.pixDestroy(pRef);

        ITesseract instance = new Tesseract();
        instance.setDatapath("src\\OCR\\Tess4J\\tessdata");
        instance.setLanguage("eng");

        try {
            String real_result = instance.doOCR(imageFile);
            System.out.println(real_result);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
    }
}