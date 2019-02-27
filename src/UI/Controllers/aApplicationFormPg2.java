package UI.Controllers;


import Datatypes.*;
import Managers.CacheManager;
import Managers.SceneManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Clay Oshiro-Leavitt & Elizabeth Del Monaco
 * @version It 2
 * Controller for aApplicationFormPg2 of UI
 */
public class aApplicationFormPg2 extends Controller {
    private SceneManager sceneM;
    private CacheManager cacheM;
    private Form form;
    private Comments comments;

    public aApplicationFormPg2(SceneManager sceneM, CacheManager cacheM, Form form, Comments comments) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
        this.form = form;
        this.comments = comments;
    }

    @FXML private JFXTextField applicantName;
    @FXML private JFXTextField mailAddress;
    @FXML private JFXTextField formula;
    @FXML private JFXTextField grapes;
    @FXML private JFXTextField appellation;
    @FXML private JFXTextField phoneNumber;
    @FXML private JFXTextField email;
    @FXML private JFXTextArea Q8Comment;
    @FXML private JFXTextArea Q8aComment;
    @FXML private JFXTextArea Q9Comment;
    @FXML private JFXTextArea Q10Comment;
    @FXML private JFXTextArea Q11Comment;
    @FXML private JFXTextArea Q12Comment;
    @FXML private JFXTextArea Q13Comment;
    @FXML private JFXTextField receiver;
    @FXML private ImageView alcyView;
    @FXML private Text alcyLabel;




    @FXML public void initialize() {
        Alcy alcy = cacheM.getAlcy();
        alcy.summonAlcy(alcyView, alcyLabel);
        alcy.sayAForm();



        mailAddress.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (newPropertyValue)
                { cacheM.getAlcy().sayMHelpMailingAddress();}
                else
                {
                    //saveDraft();
                    cacheM.getAlcy().sayMailingAddress();
                }
            }
        });

        formula.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (newPropertyValue)
                { cacheM.getAlcy().sayMHelpFormula();}
                else
                {
                    //saveDraft();
                    //cacheM.getAlcy().sayGreeting();
                }
            }
        });

        grapes.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (newPropertyValue)
                { cacheM.getAlcy().sayAHelpGrapeVariental();}
                else
                {
                    //saveDraft();
                    //cacheM.getAlcy().sayGreeting();
                }
            }
        });

        appellation.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (newPropertyValue)
                { cacheM.getAlcy().sayAHelpWineAppellation();}
                else
                {
                    //saveDraft();
                    //cacheM.getAlcy().sayGreeting();
                }
            }
        });

        phoneNumber.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (newPropertyValue)
                {cacheM.getAlcy().sayAHelpPhoneNumber(); }
                else
                {
                    //saveDraft();
                    cacheM.getAlcy().sayPhoneNumber();
                }
            }
        });

        email.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (newPropertyValue)
                { cacheM.getAlcy().sayAHelpEmail();}
                else
                {
                    //saveDraft();
                    //cacheM.getAlcy().sayGreeting();
                }
            }
        });

        Q8Comment.setText(comments.getComment8());
        Q8aComment.setText(comments.getComment8a());
        Q9Comment.setText(comments.getComment9());
        Q10Comment.setText(comments.getComment10());
        Q11Comment.setText(comments.getComment11());
        Q12Comment.setText(comments.getComment12());
        Q13Comment.setText(comments.getComment13());

        applicantName.setText(form.parseGarbage(form.getApplicantName()));
        applicantName.setStyle(form.parseStyle(form.getApplicantName()));
        applicantName.setEditable(false);

        mailAddress.setText(form.parseGarbage(form.getMailingAddress()));
        mailAddress.setStyle(form.parseStyle(form.getMailingAddress()));
        mailAddress.setEditable(false);

        formula.setText(form.parseGarbage(form.getFormula()));
        formula.setStyle(form.parseStyle(form.getFormula()));
        formula.setEditable(false);

        grapes.setText(form.parseGarbage(form.getGrapeVarietal()));
        grapes.setStyle(form.parseStyle(form.getGrapeVarietal()));
        grapes.setEditable(false);

        appellation.setText(form.parseGarbage(form.getAppellation()));
        appellation.setStyle(form.parseStyle(form.getAppellation()));
        appellation.setEditable(false);

        phoneNumber.setText(form.parseGarbage(form.getPhoneNumber()));
        phoneNumber.setStyle(form.parseStyle(form.getPhoneNumber()));
        phoneNumber.setEditable(false);

        email.setText(form.parseGarbage(form.getEmailAddress()));
        email.setStyle(form.parseStyle(form.getEmailAddress()));
        email.setEditable(false);

        System.out.println("filled in info page 2");

    }

    @FXML
    public void search() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/SearchPage.fxml"));
        sceneM.changeScene(loader, new SearchPage(sceneM, cacheM));
    }

    @FXML
    public void back() throws IOException {
        comments.setComment8(Q8Comment.getText());
        comments.setComment8a(Q8aComment.getText());
        comments.setComment9(Q9Comment.getText());
        comments.setComment10(Q10Comment.getText());
        comments.setComment11(Q11Comment.getText());
        comments.setComment12(Q12Comment.getText());
        comments.setComment13(Q13Comment.getText());
        cacheM.getForm().setComments(comments);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/aApplicationFormPg1.fxml"));
        sceneM.changeScene(loader, new aApplicationFormPg1(sceneM, cacheM, form, comments));
    }

    @FXML
    public void nextPage() throws IOException {
        comments.setComment8(Q8Comment.getText());
        comments.setComment8a(Q8aComment.getText());
        comments.setComment9(Q9Comment.getText());
        comments.setComment10(Q10Comment.getText());
        comments.setComment11(Q11Comment.getText());
        comments.setComment12(Q12Comment.getText());
        comments.setComment13(Q13Comment.getText());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/aApplicationFormPg3.fxml"));
        sceneM.changeScene(loader, new aApplicationFormPg3(sceneM, cacheM, form,comments));
    }

    @FXML
    public void acceptForm() throws IOException {
        cacheM.approveForm(cacheM.getDbM().getConnection());
        Agent A = (Agent) cacheM.getAcct();
        A.approveOrDeny(form);
        goToHomePage();
    }

    @FXML
    public void goToHomePage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/aHomepage.fxml"));
        sceneM.changeScene(loader, new aHomepage(sceneM, cacheM));
    }

    @FXML
    public void denyForm() throws Exception {
        cacheM.denyForm(cacheM.getDbM().getConnection());
        comments.setComment8(Q8Comment.getText());
        comments.setComment8a(Q8aComment.getText());
        comments.setComment9(Q9Comment.getText());
        comments.setComment10(Q10Comment.getText());
        comments.setComment11(Q11Comment.getText());
        comments.setComment12(Q12Comment.getText());
        comments.setComment13(Q13Comment.getText());
        System.out.println(comments.generateComments());
        Agent A = (Agent) cacheM.getAcct();
        A.approveOrDeny(form);
        goToHomePage();
    }

    @FXML public void passForm() throws IOException{
        if (cacheM.passForm(cacheM.getDbM().getConnection(),cacheM.getForm().getFormID(), receiver.getText())) {
            Agent A = (Agent) cacheM.getAcct();
            A.pass(form);
            goToHomePage();
        }
    }

    @FXML
    public void logout() throws IOException {
        Agent A = (Agent) cacheM.getAcct();
        A.deleteLabels();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/LoginPage.fxml"));
        sceneM.changeScene(loader, new LoginPage(sceneM, new CacheManager(this.cacheM.getDbM())));

    }
    @FXML public void settings() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/settingPage.fxml"));
        sceneM.changeScene(loader, new settingPage(sceneM, cacheM));
    }
}
