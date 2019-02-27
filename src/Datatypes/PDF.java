package Datatypes;

import com.jfoenix.controls.JFXButton;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
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

    File NrawPDF = new File("src/Resources/ttbpdf.pdf");
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

    public void savePDFtoDirectory(PDF pdf, Node node) throws IOException{

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

    //PDF BY ROB oops i mean
    /**@author Rob**/

    @FXML
    public void savePDF(Form form) throws IOException {

        System.out.println("saving pdf");
        PDF pdf = new PDF();

        pdf.open();

        pdf.appendText(Integer.toString(form.getRepID()), 24, 912, 10);
        pdf.appendText(form.getBrewerNumber(), 24, 865, 10);

        if(form.getProductSource() == "DOMESTIC")
            pdf.appendText("X", 142,871, 10);
        else if(form.getProductSource() == "IMPORTED")
            pdf.appendText("X", 201,871, 10);

        if(form.getSerialNumber() != "") {
            pdf.appendText(Character.toString(form.getSerialNumber().charAt(0)), 24, 811, 10);
            pdf.appendText(Character.toString(form.getSerialNumber().charAt(1)), 42, 811, 10);
            pdf.appendText(Character.toString(form.getSerialNumber().charAt(2)), 70, 811, 10);
            pdf.appendText(Character.toString(form.getSerialNumber().charAt(3)), 88, 811, 10);
            pdf.appendText(Character.toString(form.getSerialNumber().charAt(4)), 106, 811, 10);
            pdf.appendText(Character.toString(form.getSerialNumber().charAt(5)), 122, 811, 10);
        }

        //type of product
        if (form.getProductType() =="WINE")
            pdf.appendText("X", 152,828, 10);
        else if(form.getProductType()=="DISTILLED")
            pdf.appendText("X", 146,816, 10);
        else if(form.getProductType()== "MALT")
            pdf.appendText("X", 146, 804, 10);


        pdf.appendText(form.parseGarbage(form.getBrandName()), 24,780, 10);
        pdf.appendText(form.parseGarbage(form.getFancifulName()), 24,755, 10);
        pdf.appendText(form.parseGarbage(form.getApplicantName()), 268, 846, 10);
        pdf.appendText(form.parseGarbage(form.getMailingAddress()), 268,780, 10);
        pdf.appendText(form.parseGarbage(form.getFormula()), 24,722, 10);
        pdf.appendText(form.parseGarbage(form.getGrapeVarietal()), 153, 722, 10);
        pdf.appendText(form.parseGarbage(form.getAppellation()), 24,688, 10);
        pdf.appendText(form.parseGarbage(form.getEmailAddress()), 153, 656, 10);
        pdf.appendText(form.parseGarbage(form.getPhoneNumber()), 24, 656, 10);


        //type of application
        if(form.getCertificateOfApproval()){
            pdf.appendText("X", 398, 732, 10);
        }
        if(form.getCertificateOfExemption()){
            pdf.appendText("X", 398,720, 10);
            pdf.appendText(form.getOnlyState(), 451, 710, 10);
        }
        if(form.getDistinctiveLiquor()){
            pdf.appendText("X", 398, 700, 10);
            pdf.appendText(form.getBottleCapacity(), 541, 690, 10);
        }
        if(form.getResubmission()){
            pdf.appendText("X", 398,668, 10);
            pdf.appendText(Integer.toString(form.getTtbID()), 437, 658, 10);
        }
        try {
            if (form.getLabel().getLabelFile() != null)
                pdf.appendImage(form.getLabel().getLabelFile().getPath(), 200, 66, 200, 200);
        }catch(Exception e){
            System.out.println("Image file not found");
        }
        pdf.appendText(form.getDateOfApplication(), 24, 500, 10);
        //pdf.appendText(applicantSig.getText(), 138, 500, 10);
        pdf.appendText(form.getApplicantName(), 366, 500, 10);

        pdf.appendText("Additional Fields:", 24, 620, 10 );
        pdf.appendText("Alcohol Percentage: "+ form.getAlcoholPercent(), 24, 610, 10);
        pdf.appendText("pH Level: "+ form.getpHLevel(), 24, 600, 10);
        pdf.appendText("Vintage Year: "+ form.getVintageYear(), 24, 590, 10);

        pdf.closeStream();

        pdfPopupWindow(pdf,form);
        pdf.close();

        System.out.println("saved!");
    }


    //PoPup


    //POPUP WINDOW

    public void pdfPopupWindow(PDF pdf, Form form) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/UI/Views/PDFpopup.fxml"));

        //time for the
        Node vbox = root.getChildrenUnmodifiable().get(0);
        ImageView pdfImage = new ImageView();
        pdfImage.setImage(pdf.renderPDF());

        if (vbox instanceof VBox) {
            System.out.println("vboxinstance");
            Node navBox = ((VBox) vbox).getChildren().get(0);
            Node scrollPane = ((VBox) vbox).getChildren().get(1);

            if (navBox instanceof HBox){
                Node fancifulLabel = ((HBox) navBox).getChildren().get(0);
                Node saveButton = ((HBox) navBox).getChildren().get(1);

                ((Label) fancifulLabel).setText(form.parseGarbage(form.getFancifulName()));
                ((JFXButton) saveButton).setOnAction((event -> {
                    try {
                        pdf.savePDFtoDirectory(pdf, vbox);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }));
            }

            if (scrollPane instanceof ScrollPane){
                System.out.println("scrollpane instance");
                ((ScrollPane) scrollPane).setContent(pdfImage);
            }
        }
        popWindow(root);
    }

    @FXML public void popWindow(Parent root) throws IOException {
        Stage stage;
        stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("TTB PDF");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }



    public PDF() throws IOException {
    }
}
