package Datatypes;

import Managers.CacheManager;
import javafx.animation.*;
import javafx.beans.value.ObservableDoubleValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class Alcy {
    final static Image alcyHappy1 = new Image("UI/Icons/alcyImages/alcyhappy-01.png");
    final static Image alcyAngry1 = new Image("UI/Icons/alcyImages/alcyangry-01.png");
    final static Image alcyConfused1 = new Image("UI/Icons/alcyImages/alcyconfused-01.png");
    final static Image alcyDrunk1 = new Image("UI/Icons/alcyImages/alcydrunk-01.png");
    final static Image alcyPointing1 = new Image("UI/Icons/alcyImages/alcypointing-01.png");
    final static Image alcySad1 = new Image("UI/Icons/alcyImages/alcysad-01.png");
    final static Image alcySassy1 = new Image("UI/Icons/alcyImages/alcysassy-01.png");
    final static Image alcyStop1 = new Image("UI/Icons/alcyImages/alcystop-01.png");
    final static Image alcyTransformShort1 = new Image("UI/Icons/alcyImages/alcytransformshort-01.png");
    final static Image alcyTransformTall1 = new Image("UI/Icons/alcyImages/alcytransformtall-01.png");

    private ImageView imageView;

    private int imageIndex = 0;
    private int frameTime = 100;
    private ArrayList<Image> actionList = new ArrayList<>();
    private CacheManager cacheManager;

    public Alcy(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public ImageView getImageView() { return imageView; }
    public void setImageView(ImageView imageView) {
        this.imageView = imageView; }

    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(frameTime), (javafx.event.ActionEvent event) -> {
        imageView.setImage(actionList.get(imageIndex++));
    }));

    @FXML
    public void resetTimeline(){
        actionList.clear();
        imageIndex = 0;
        actionList.add(alcyTransformShort1);
        actionList.add(alcyTransformTall1);

    }

    @FXML
    public void playTimeline(){
        timeline.setCycleCount(actionList.size());
        timeline.play();
    }

    @FXML
    public void start(){
        resetTimeline();
        actionList.add(alcyHappy1);
        playTimeline();
    }

    @FXML public void happy(){
        resetTimeline();
        actionList.add(alcyHappy1);
        playTimeline();
    }

    @FXML public void angry(){
        resetTimeline();
        actionList.add(alcyAngry1);
        playTimeline();
    }

    @FXML public void confused(){
        resetTimeline();
        actionList.add(alcyConfused1);
        playTimeline();
    }

    @FXML public void drunk(){
        resetTimeline();
        actionList.add(alcyDrunk1);
        playTimeline();
    }

    @FXML public void pointing(){
        resetTimeline();
        actionList.add(alcyPointing1);
        playTimeline();
    }

    @FXML public void sad(){
        resetTimeline();
        actionList.add(alcySad1);
        playTimeline();
    }

    @FXML public void sassy(){
        resetTimeline();
        actionList.add(alcySassy1);
        playTimeline();
    }

    @FXML public void stop(){
        resetTimeline();
        actionList.add(alcyStop1);
        playTimeline();
    }

    public Alcy() {
    }

}
