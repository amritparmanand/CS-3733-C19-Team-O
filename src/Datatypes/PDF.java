package Datatypes;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;

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



    public void open()throws IOException{
        formPDF.setAllSecurityToBeRemoved(true);
        pDAcroForm.setAppendOnly(false);
        pDAcroForm.flatten();
    }

    //Methods
    public void appendText(String text, float x, float y, float fontSize) throws IOException {
//        formPDF.setAllSecurityToBeRemoved(true);
//        pDAcroForm.setAppendOnly(false);
//        pDAcroForm.flatten();


        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA, fontSize);
        contentStream.newLineAtOffset(x,y);
        contentStream.showText(text);
        contentStream.endText();
//        contentStream.close();
//        formPDF.save(new File("./testfile.pdf"));
//        formPDF.close();
    }

    public void close() throws IOException {
        contentStream.close();
        formPDF.save(new File("./testfile.pdf"));
        formPDF.close();
    }



    public PDF() throws IOException {
    }
}
