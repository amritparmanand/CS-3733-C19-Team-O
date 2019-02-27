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

    private boolean demonicSeance = false;

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
    final static Image alcyGun1 = new Image("UI/Icons/alcyImages/alcyglizzy-01.png");

    private ImageView imageView;
    private Text alcyLabel;

    private int imageIndex = 0;
    private int frameTime = 100;
    private ArrayList<Image> actionList = new ArrayList<>();

    public ImageView getImageView() { return imageView; }
    public void setImageView(ImageView imageView) { this.imageView = imageView; }
    public Text getAlcyLabel() { return alcyLabel; }
    public void setAlcyLabel(Text alcyLabel) { this.alcyLabel = alcyLabel; }
    public boolean isDemonicSeance() { return demonicSeance; }
    public void setDemonicSeance(boolean demonicSeance) { this.demonicSeance = demonicSeance; }

    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(frameTime), (javafx.event.ActionEvent event) -> {
        if(isDemonicSeance())
            imageView.setImage(actionList.get(imageIndex++));
    }));

    public Alcy(CacheManager cacheManager) {
        this.cacheM = cacheManager;
    }

    @FXML public void summonAlcy(ImageView alcyView, Text alcyLabel) {
        actionList.clear();
        this.setImageView(alcyView);
        this.setAlcyLabel(alcyLabel);
        System.out.println(this.isDemonicSeance());
        if (!this.isDemonicSeance()){
            alcyLabel.getParent().setVisible(false);
             alcyView.getParent().setVisible(false);
         }
        else {
            alcyLabel.getParent().setVisible(true);
            alcyView.getParent().setVisible(true);
        }

        resetTimeline();
        sayWelcome();

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
        actionList.add(alcyDrunk1);
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

    @FXML public void gun(){
        resetTimeline();
        actionList.add(alcyGun1);
        playTimeline();
    }

    /**ALCY PHRASES**/
    @FXML public void sayGreeting(){
        double random = Math.random();
        if(random>.5){
            alcyLabel.setText("Howdy, "+ cacheM.getAcct().getFullName()+"! I'm Alcy the Beer Can!");
            happy();
        }else{
            alcyLabel.setText("Hey, " + cacheM.getAcct().getFullName()+". I'm Alcy, AKA Schmizzed");
            drunk();
        }
    }

    @FXML public void sayWelcome(){
        double random = Math.random();
        if(random>.5){
            alcyLabel.setText("Howdy, I'm Alcy the Beer Can!");
            happy();
        }else{
            alcyLabel.setText("Hey, I'm Alcy AKA Schmizzed");
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
        if(Float.parseFloat(percentage)<100 && Float.parseFloat(percentage)>0){
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
            else{
                alcyLabel.setText("Looks good to me!");
                happy();
            }
        }else if(Float.parseFloat(percentage)>100 || Float.parseFloat(percentage)<0){
            alcyLabel.setText("Your alcohol content cannot defy the laws of physics!");
            sassy();
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
            alcyLabel.setText("What are you drinking over here, acid?");
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

    @FXML public void sayAboutUs(){
        double random = Math.random();
        if(random>.5){
            alcyLabel.setText("I've never seen a more good lookin' bunch o folks!");
            pointing();
        }else{
            alcyLabel.setText("Really? You'd trust these guys with your app?");
            confused();
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

    @FXML public void sayLogin(){
        double random = Math.random();
        if(random>.5){
            alcyLabel.setText("Hey dude, you wanna log in?");
            drunk();
        }else{
            alcyLabel.setText("Time to log in! Let's get to work!");
            happy();
        }
    }

    @FXML public void sayRegister(){
        double random = Math.random();
        if(random>.5){
            alcyLabel.setText("Oh, registering a new account I see... Nice choice!");
            happy();
        }else{
            alcyLabel.setText("Where better to sign up to than an alcohol database!");
            happy();
        }
    }

    @FXML public void sayMHomePage(){
        double random = Math.random();
        if(random>.5){
            alcyLabel.setText("Welcome, " + cacheM.getAcct().getFullName() +"! Here's your homepage! This is where you can fill out new forms and view your past ones!");
            drunk();
        }else{
            alcyLabel.setText("Ahhh... What a good looking homepage, don't ya think, "+ cacheM.getAcct().getFullName()+"?");
            drunk();
        }
    }

    @FXML public void sayAHomePage(){
        double random = Math.random();
        if(random>.5){
            alcyLabel.setText("Welcome, " + cacheM.getAcct().getFullName()+ ". Get back to work now!");
            happy();
        }else{
            alcyLabel.setText("Do you think the govermnent pays enough for you to sit around doing nothing, "+cacheM.getAcct().getFullName()+"?!");
            drunk();
        }
    }

    @FXML public void sayMForm(){
        double random = Math.random();
        if(random>.5){
            alcyLabel.setText("Oh nice! Another one for me to try!");
            happy();
        }else{
            alcyLabel.setText("I can't wait for the government to approve this in a couple months!");
            drunk();
        }
    }

    @FXML public void sayMViewForm(){
        double random = Math.random();
        if(random>.6){
            alcyLabel.setText("Jeez Louis, what'd you do wrong now!");
            confused();
        }if(random<.3){
            alcyLabel.setText("Ahh... "+cacheM.getForm().getFancifulName()+"... I watched you fill this out wrong... Oops...");
        }else{
            alcyLabel.setText("Can't even file paperwork correctly, huh?");
            sassy();
        }
    }

    @FXML public void sayAForm(){
        double random = Math.random();
        if(random>.5){
            alcyLabel.setText("Man, reading these things will kill ya faster than the stuff thats on em! "+ cacheM.getForm().getFancifulName()+" does sound pretty deadly, however...");
            sad();
        }else{
            alcyLabel.setText("Oh man! I can't wait for "+cacheM.getForm().getBrandName()+"'s newest hit!");
            happy();
        }
    }

    @FXML public void saySettings(){
        double random = Math.random();
        if(random>.5){
            alcyLabel.setText("This is where you can edit all your settings!");
            happy();
        }else{
            alcyLabel.setText("You've got so many options");
            drunk();
        }
    }

    @FXML public void saySettingsHover(){
        double random = Math.random();
        if(random>.5){
            alcyLabel.setText("DONT TOUCH THAT!");
            gun();
        }else{
            alcyLabel.setText("IF YOU TOUCH THAT I WILL HAUNT YOUR DREAMS FOR THE REST OF ETERNITY!");
            gun();
        }
    }

    @FXML public void saySettingsBye(){
        double random = Math.random();
        if(random>.5){
            alcyLabel.setText("Oh... Ok... well, bye, I guess...");
            sad();
        }else{
            alcyLabel.setText("I guess you dont need me anymore...");
            sad();
        }
    }

    @FXML public void saySettingsRelief(){
        double random = Math.random();
        if(random>.5){
            alcyLabel.setText("Woohoo! I'm not going away, baby!");
            happy();
        }else{
            alcyLabel.setText("I knew I was helpful!");
            happy();
        }
    }

    @FXML public void sayAScores(){
        double random = Math.random();
        if(random>.5){
            alcyLabel.setText("Look at all this," + cacheM.getAcct().getFullName() +"! Here's your homepage! Here's where you can view all your accolades!");
            happy();
        }else{
            alcyLabel.setText("Hmmmm... Looks like someone could be doin' a bit better, don'tcha think, "+ cacheM.getAcct().getFullName()+"?");
            confused();
        }
    }

    /**ALCY NON FORM HELP **/

    @FXML public void sayHelpMAccount(){
        double random = Math.random();
        if(random>.5){
            alcyLabel.setText("Oh! You're here to dive first into government bureaucracy, Mr. or Mrs. Manufacturer!");
            happy();
        }else{
            alcyLabel.setText("Wow! My friend the manufacturer, I hope the government isn't shut down so we can process your labels!");
            happy();
        }
    }

    @FXML public void sayHelpAAccount(){
        double random = Math.random();
        if(random>.5){
            alcyLabel.setText("Hello, agent! Let's start processing forms! Yay!");
            happy();
        }else{
            alcyLabel.setText("Welcome, agent! I'm ready to join you in some fun paperwork!");
            sassy();
        }
    }

    @FXML public void sayHelpUsername(){
        double random = Math.random();
        if(random>.5){
            alcyLabel.setText("Put in your username!");
            happy();
        }else{
            alcyLabel.setText("Enter your username now!");
            happy();
        }
    }

    @FXML public void sayHelpPassword(){
        double random = Math.random();
        if(random>.5){
            alcyLabel.setText("Pshh, I know your password, it isn't THAT secure");
            confused();
        }else{
            alcyLabel.setText("Wow, you couldn't think of anything better...");
            sassy();
        }
    }

    @FXML public void sayHelpLoginID(){
        double random = Math.random();
        if(random>.5){
            alcyLabel.setText("ID, sir.");
            confused();
        }else{
            alcyLabel.setText("License and registration, please.");
            sassy();
        }
    }

    @FXML public void sayHelpFullName(){
        double random = Math.random();
        if(random>.5){
            alcyLabel.setText("Your FULL LEGAL NAME. We are the government, so we know");
            confused();
        }else{
            alcyLabel.setText("First and Last name please!");
            happy();
        }
    }

    @FXML public void sayFullName(){
        double random = Math.random();
        if(random>.5){
            alcyLabel.setText("Wow thats a cool name, I guess...");
            sassy();
        }else{
            alcyLabel.setText("Have I heard of you before?");
            confused();
        }
    }

    @FXML public void sayHelpEmail(){
        double random = Math.random();
        if(random>.5){
            alcyLabel.setText("Put in your email where you want your password reset to be sent when you forget your password.");
            sassy();
        }else{
            alcyLabel.setText("You're gonna need this for your password reset.");
            sassy();
        }
    }

    /** ALCY HELPFUL TIPS **/

    //1
    @FXML public void sayMHelpRepID(){
        alcyLabel.setText("Include a third party representative ID Number if your application will be submitted by a third party representative, "+ cacheM.getAcct().getFullName()+".");
        happy();
    }

    @FXML public void sayMHelpBrewerNO(){
        alcyLabel.setText("This should look something like BW- or TPWBH- or DSP- number. Importers must enter the TTB basic permit number and brewers must enter the brewer’s notice number.");
        happy();
    }

    @FXML public void sayMHelpProductSource(){
        alcyLabel.setText("Tell me the source of the product by checking the appropriate box, ok?");
        happy();
    }

    @FXML public void sayMHelpSerialNumber(){
        alcyLabel.setText("You must assign a sequential serial number beginning with the last two digits of the current calendar year to each application and its duplicate, not to exceed 6\n" +
                "characters; e.g., 12-1, 12-2, etc.");
        happy();
    }

    @FXML public void sayMHelpBrandName(){
        alcyLabel.setText("Hey! Enter the name of the bottler, packer, or importer here, pal.");
        happy();
    }

    @FXML public void sayMHelpFancifulName(){
        alcyLabel.setText("Enter the name this drink is sold under!" );
        happy();
    }

    @FXML public void sayMHelpCompanyName(){
        alcyLabel.setText("Indicate your company name exactly as it appears on your plant registry, basic permit, or brewer’s notice.");
        happy();
    }

    @FXML public void sayMHelpMailingAddress(){
        alcyLabel.setText("Indicate your company address exactly as it appears on your plant registry, basic permit, or brewer’s notice.");
        happy();
    }

    @FXML public void sayMHelpFormula(){
        alcyLabel.setText("If you don't understand this one, look on our website guidelines! Heck if I know!");
        happy();
    }

    @FXML public void sayMHelpGrapeVariental(){
        alcyLabel.setText("You gotta list in this block each grape varietal that appears on wine labels.");
        happy();
    }

    @FXML public void sayMHelpWineAppellation(){
        alcyLabel.setText("Fill in only if a wine appellation of origin is stated on the label.");
        happy();
    }

    @FXML public void sayMHelpPhoneNumber(){
        alcyLabel.setText("Just type in your phone number here, I won't sell it...");
        drunk();
    }

    @FXML public void sayMHelpEmail(){
        alcyLabel.setText("Wherever you want alerts to be sent, put in THAT email address!");
        happy();
    }

    @FXML public void sayMHelpAlcoholPercentage(){
        alcyLabel.setText("So what is this thing closer to, water or jet fuel?");
        confused();
    }

    @FXML public void sayMHelppHLevel(){
        alcyLabel.setText("This will USUALLY be between 0 and 14, but with your special abilities...");
        happy();
    }
    @FXML public void sayMHelpVintageYear(){
        alcyLabel.setText("I hope this is older than me!");
        happy();
    }

    @FXML public void sayMHelpDate(){
        alcyLabel.setText("No, the date is not 'Today'...");
        sassy();
    }

    /**AGENT**/

    @FXML public void sayAHelpRepID(){
        alcyLabel.setText("Include a third party representative ID Number if your application will be submitted by a third party representative, "+ cacheM.getAcct().getFullName()+".");
        happy();
    }

    @FXML public void sayAHelpBrewerNO(){
        alcyLabel.setText("This should look something like BW- or TPWBH- or DSP- number. Importers must enter the TTB basic permit number and brewers must enter the brewer’s notice number.");
        happy();
    }

    @FXML public void sayAHelpProductSource(){
        alcyLabel.setText("Tell me the source of the product by checking the appropriate box, ok?");
        happy();
    }

    @FXML public void sayAHelpSerialNumber(){
        alcyLabel.setText("You must assign a sequential serial number beginning with the last two digits of the current calendar year to each application and its duplicate, not to exceed 6\n" +
                "characters; e.g., 12-1, 12-2, etc.");
        happy();
    }

    @FXML public void sayAHelpBrandName(){
        alcyLabel.setText("Hey! Enter the name of the bottler, packer, or importer here, pal.");
        happy();
    }

    @FXML public void sayAHelpFancifulName(){
        alcyLabel.setText("Enter the name this drink is sold under!" +
                "applicable.");
        happy();
    }

    @FXML public void sayAHelpCompanyName(){
        alcyLabel.setText("Indicate your company name exactly as it appears on your plant registry, basic permit, or brewer’s notice.");
        happy();
    }

    @FXML public void sayAHelpMailingAddress(){
        alcyLabel.setText("Indicate your company address exactly as it appears on your plant registry, basic permit, or brewer’s notice.");
        happy();
    }

    @FXML public void sayAHelpFormula(){
        alcyLabel.setText("If you don't understand this one, look on our website guidelines! Heck if I know!");
        happy();
    }

    @FXML public void sayAHelpGrapeVariental(){
        alcyLabel.setText("You gotta list in this block each grape varietal that appears on wine labels.");
        happy();
    }

    @FXML public void sayAHelpWineAppellation(){
        alcyLabel.setText("Fill in only if a wine appellation of origin is stated on the label.");
        happy();
    }

    @FXML public void sayAHelpPhoneNumber(){
        alcyLabel.setText("Just type in your phone number here, I won't sell it...");
        drunk();
    }

    @FXML public void sayAHelpEmail(){
        alcyLabel.setText("Wherever you want alerts to be sent, put in THAT email address!");
        happy();
    }

    @FXML public void sayAHelpAlcoholPercentage(){
        alcyLabel.setText("So what is this thing closer to water or jet fuel?");
        confused();
    }

    @FXML public void sayAHelppHLevel(){
        alcyLabel.setText("Just type in your phone number here, I won't sell it...");
        happy();
    }
    @FXML public void sayAHelpVintageYear(){
        alcyLabel.setText("I hope this is older than me!");
        happy();
    }

    @FXML public void sayAHelpDate(){
        alcyLabel.setText("No, the date is not 'Today'...");
        sassy();
    }


}

