package OCR.Tess4J.src;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.lept;
import org.bytedeco.javacpp.tesseract;
//import org.bytedeco.javacpp.tesseract.*;
//import org.bytedeco.javacpp.tesseract.TessBaseAPI;

import java.io.File;
import java.io.IOException;

import static junit.framework.TestCase.assertTrue;
import static org.bytedeco.javacpp.lept.pixDestroy;

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

//        ITesseract instance = new Tesseract();
//        instance.setDatapath("src\\OCR\\Tess4J\\tessdata");
//        instance.setLanguage("eng");
//
//        try {
//            String real_result = instance.doOCR(imageFile);
//            System.out.println(real_result);
//        } catch (TesseractException e) {
//            System.err.println(e.getMessage());
//        }


        BytePointer outText;

        tesseract.TessBaseAPI api = new tesseract.TessBaseAPI();

        // Open input image with leptonica library
        lept.PIX image = lept.pixRead("C:\\Users\\jiang\\Pictures\\Organic-Wine-Certification-Statement-TTB-USDA.jpg");
        api.SetImage(image);
        // Get OCR result
        outText = api.GetUTF8Text();
        String string = outText.getString();
        assertTrue(!string.isEmpty());
        System.out.println("OCR output:\n" + string);

        // Destroy used object and release memory
        api.End();
        outText.deallocate();
        pixDestroy(image);
    }
}