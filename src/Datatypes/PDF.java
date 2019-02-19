package Datatypes;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.rendering.PDFRenderer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Robert & Percy
 * @version It 3
 * PDF for Form
 */

public class PDF {

    File NrawPDF = new File("/Users/robertrinearson/Documents/iteration2/src/Resources/ttbpdf.pdf");
    PDDocument NformPDF = PDDocument.load(NrawPDF);

    PDPage NformPage1 = NformPDF.getPage(0);
    PDPageContentStream NcontentStream = new PDPageContentStream(NformPDF, NformPage1, PDPageContentStream.AppendMode.APPEND, true, true);
    PDAcroForm NpDAcroForm = NformPDF.getDocumentCatalog().getAcroForm();

    public void fakeOpen()throws IOException{
        NformPDF.setAllSecurityToBeRemoved(true);
        NpDAcroForm.setAppendOnly(false);
        NpDAcroForm.flatten();
    }

    public void fakeClose() throws IOException {
        NcontentStream.close();
        NformPDF.save(new File("./TheFake.pdf"));
        NformPDF.close();
    }

    File rawPDF = new File("./TheFake.pdf");
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

    public void appendImage(String labelPath, float x, float y, float height, float width) throws IOException{
        PDImageXObject pdfLabel = PDImageXObject.createFromFile(labelPath, formPDF );
        contentStream.drawImage(pdfLabel, x, y, height, width);
    }

    public void closeStream() throws IOException {
        contentStream.close();
        formPDF.save(new File("./testfile.pdf"));
    }

    public void close() throws IOException {
        formPDF.close();
    }

    public Image renderPDF() throws IOException{
        PDFRenderer pdfRenderer = new PDFRenderer(PDDocument.load(new File("./testfile.pdf")));
        System.out.println("renderpdf");
        pdfRenderer.renderImage(0);
        Image image = SwingFXUtils.toFXImage(pdfRenderer.renderImage(0), null);
        return image;
    }

    public void savePDF(PDF pdf, Node node) throws IOException{

        //Create a file chooser
        DirectoryChooser directoryChooser = new DirectoryChooser();
        //Select the extentions we allow for the files
        //Actually get the folder
        directoryChooser.setInitialDirectory(null);

        Stage stage = (Stage) node.getScene().getWindow();

        File folder = directoryChooser.showDialog(null);

        if(folder!= null){
            System.out.println("saving!");

            formPDF.save(new File(folder +"/ttbformtest.pdf"));
        }



    }



    public PDF() throws IOException {
    }
}
