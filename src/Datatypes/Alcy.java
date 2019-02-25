package Datatypes;

import Managers.CacheManager;
import Managers.SceneManager;
import javafx.animation.*;
import javafx.beans.value.ObservableDoubleValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class Alcy {

    //    private SceneManager sceneM;
    private CacheManager cacheM;
    private Form form;

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
    private Text alcyLabel;

    private int imageIndex = 0;
    private int frameTime = 100;
    private ArrayList<Image> actionList = new ArrayList<>();

    public ImageView getImageView() { return imageView; }
    public void setImageView(ImageView imageView) { this.imageView = imageView; }
    public Text getAlcyLabel() { return alcyLabel; }
    public void setAlcyLabel(Text alcyLabel) { this.alcyLabel = alcyLabel; }

    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(frameTime), (javafx.event.ActionEvent event) -> {
        imageView.setImage(actionList.get(imageIndex++));
    }));

    public Alcy(CacheManager cacheManager) {
        this.cacheM = cacheManager;
    }

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

    /**ALCY PHRASES**/
    @FXML public void sayGreeting(){
        double random = Math.random();
        if(random>.5){
            alcyLabel.setText("Howdy, "+ cacheM.getAcct().getFullName()+"! I'm Alcy the Beer Can!");
            happy();
        }else{
            alcyLabel.setText("Hey, " + cacheM.getAcct().getFullName()+". I'm Alcy AKA Schmizzed");
            drunk();
        }
    }

    @FXML public void sayWrongAnswer(String error){
        alcyLabel.setText("Hey, Punk! You've gotta put in a valid "+error+" before you can continue!");
        angry();
    }

    @FXML public void saySubmitFormError(String error){
        alcyLabel.setText("Watch it, pal! You cant submit a form like this!");
        stop();
    }

    @FXML public void sayWierdBeerPercentage(){
        String percentage = cacheM.getForm().getAlcoholPercent();
        String type = cacheM.getForm().getProductType();
        if(Integer.parseInt(percentage)>12.5 && type == "MALT"){
            alcyLabel.setText("Wow, thats a high percentage... Are you sure this is the correct value?");
            confused();
        }
        if(Integer.parseInt(percentage)<12.5 && type == "DISTILLED") {
            alcyLabel.setText("Wow, you're gonna have to drink a lot of this to get drunk! Are you sure this is the correct value?");
            sassy();
        }
        if(Integer.parseInt(percentage)>16 && type == "WINE"){
            alcyLabel.setText("Wow, thats a high percentage... Are you sure this is a wine?");
            confused();
        }
    }

    @FXML public void sayFancifulName(){
        double random = Math.random();
        if(random>.5){
            alcyLabel.setText("Mmm, I'd love to knock back a "+cacheM.getForm().getFancifulName()+" right about now!");
            drunk();
        }else{
            alcyLabel.setText(cacheM.getForm().getFancifulName()+" is a REAAAAAL doozy!");
            pointing();
        }
    }

    @FXML public void sayBrandName(){
        double random = Math.random();
        if(random>.5){
            alcyLabel.setText("I've heard "+cacheM.getForm().getBrandName()+" makes some good stuff!");
            happy();
        }else{
            alcyLabel.setText("I love "+cacheM.getForm().getBrandName()+"! They always get me wasted!");
            happy();
        }
    }

    @FXML public void sayMailingAddress(){
        double random = Math.random();
        if(random>.5){
            alcyLabel.setText("I've heard "+cacheM.getForm().getMailingAddress()+" is nice this time of year.");
            sassy();
        }else{
            alcyLabel.setText("Oh, I've been to a couple of wicked keggers in "+cacheM.getForm().getMailingAddress()+"!");
            drunk();
        }
    }

    @FXML public void saypHLevel(){

        if(Double.parseDouble(cacheM.getForm().getpHLevel())<3) {
            alcyLabel.setText("What are you drinking acid over here?");
            confused();
        }
        if(Double.parseDouble(cacheM.getForm().getpHLevel())>7) {
            alcyLabel.setText("Are you drinking dish soap?");
            confused();
        }
    }

    @FXML public void sayPhoneNumber(){
        double random = Math.random();
        String number = cacheM.getForm().getPhoneNumber();
        if(number.length()<10){
            alcyLabel.setText("What, is this the number you give to the creeps at the club?");
            confused();
        }
        else{
            if(random>.5){
                alcyLabel.setText("Got the digits, Score!");
                happy();
            }else{
                alcyLabel.setText("Wow, I was so close!");
                sassy();
            }
        }
    }

    @FXML public void sayWrongPassword(){
        double random = Math.random();
        if(random>.5){
            alcyLabel.setText("C'Mon! Even I remember it!");
            angry();
        }else{
            alcyLabel.setText("Nice try, moron!");
            angry();
        }
    }

    @FXML public void saySearchResult(){
        double random = Math.random();
        if(random>.5){
            alcyLabel.setText("If you want it, we got it!");
            happy();
        }else{
            alcyLabel.setText("That stuff hits like a brick!");
            drunk();
        }
    }

    @FXML public void sayNiceJob(){
        double random = Math.random();
        if(random>.5){
            alcyLabel.setText("Nice job!");
            happy();
        }else{
            alcyLabel.setText("Good work, Joe!");
            happy();
        }
    }









}
