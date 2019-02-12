package Datatypes;

import javafx.scene.image.Image;
import javafx.stage.*;

import java.io.File;
/**
 * @author Robert Rinearson & Clay Oshiro-Leavitt
 * @version It 1
 * Image for label
 */


public class LabelImage {
    private File labelFile;
    private Image image;

    public LabelImage(){
        this.labelFile = null;
        this.image = null;
    }
//    Image selecting method
    public File getFile(){
        //Create a file chooser
        FileChooser fileChooser = new FileChooser();
        //Select the extentions we allow for the files
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg"));
        //Actually get the label file
        this.labelFile = fileChooser.showOpenDialog(null);
        //Make the file into an image
        this.image = new Image(labelFile.toURI().toString());
        //WARNING: need to add to database! HEREREREEEEEEE
        return labelFile;

    }

    public File getLabelFile() {
        return labelFile;
    }

    public void setLabelFile(File labelFile) {
        this.labelFile = labelFile;
    }

    public Image getLabelImage() {
        return image;
    }

    public void setLabelImage(Image labelImage) {
        this.image = labelImage;
    }



}
