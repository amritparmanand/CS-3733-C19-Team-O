package Datatypes;

import javafx.scene.image.Image;
import javafx.stage.*;

import java.io.File;

//by Rob and Clay

public class LabelImage {

    //Image selecting method
    public Image getImageFile(){
        //Create a file chooser
        FileChooser fileChooser = new FileChooser();
        //Select the extentions we allow for the files
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg"));
        //Actually get the label file
        File labelFile = fileChooser.showOpenDialog(null);
        //Make the file into an image
        Image labelImage = new Image(labelFile.toURI().toString());
        //WARNING: need to add to database! HEREREREEEEEEE
        return labelImage;

    }

}
