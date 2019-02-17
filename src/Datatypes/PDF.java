package Datatypes;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @author Robert & Percy
 * @version It 3
 * PDF for Form
 */

public class PDF {

    File rawPDF = new File("C:\\Users\\jiang\\Documents\\CS3733\\CS-3733-C19-Team-O-Iteration-1\\src\\Resources\\ttbpdf.pdf");
    PDDocument formPDF = PDDocument.load(rawPDF);

    PDPage formPage1 = formPDF.getPage(0);
    PDPageContentStream contentStream = new PDPageContentStream(formPDF, formPage1, PDPageContentStream.AppendMode.APPEND, true, true);
    PDAcroForm pDAcroForm = formPDF.getDocumentCatalog().getAcroForm();

    public void fakeOpen()throws IOException{
        formPDF.setAllSecurityToBeRemoved(true);
        pDAcroForm.setAppendOnly(false);
        pDAcroForm.flatten();
    }

    public void fakeClose() throws IOException {
        contentStream.close();
        formPDF.save(new File("./TheFake.pdf"));
        formPDF.close();
    }

    // The new one
    File NrawPDF = new File("./TheFake.pdf");
    PDDocument NformPDF = PDDocument.load(NrawPDF);

    PDPage NformPage1 = NformPDF.getPage(0);
    PDPageContentStream NcontentStream = new PDPageContentStream(NformPDF, NformPage1, PDPageContentStream.AppendMode.APPEND, true, true);
    PDAcroForm NpDAcroForm = NformPDF.getDocumentCatalog().getAcroForm();

    public void NewOpen()throws IOException{
        NformPDF.setAllSecurityToBeRemoved(true);
        NpDAcroForm.setAppendOnly(false);
        NpDAcroForm.flatten();
    }

    public void appendText(String text, float x, float y, float fontSize) throws IOException {

        NcontentStream.beginText();
        NcontentStream.setFont(PDType1Font.HELVETICA, fontSize);
        NcontentStream.newLineAtOffset(x,y);
        NcontentStream.showText(text);
        NcontentStream.endText();
    }

    public void appendImage(String labelPath, float x, float y, float height, float width) throws IOException{
        PDImageXObject pdfLabel = PDImageXObject.createFromFile(labelPath, NformPDF );
        NcontentStream.drawImage(pdfLabel, x, y, height, width);
    }

    public void NewClose() throws IOException {
        NcontentStream.close();
        NformPDF.save(new File("./NewTest.pdf"));
        NformPDF.close();
    }


    public PDF() throws IOException {
    }
}
