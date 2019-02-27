package UI.Controllers;


import Datatypes.Agent;
import Datatypes.Alcy;
import Datatypes.Comments;
import Datatypes.Form;
import Managers.CacheManager;
import Managers.SceneManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
//import org.springframework.cglib.core.Local;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Clay Oshiro-Leavitt & Elizabeth Del Monaco & Percy
 * @version It 2
 * Controller for aApplicationFormPg4 of UI
 */
public class aApplicationFormPg4 {
    private SceneManager sceneM;
    private CacheManager cacheM;
    private Form form;
    private Comments comments;

    public aApplicationFormPg4(SceneManager sceneM, CacheManager cacheM, Form form, Comments comments) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
        this.form = form;
        this.comments = comments;
    }

    @FXML private JFXDatePicker dateOfApplication;
    @FXML private JFXTextField printName;
    @FXML private JFXTextArea Q16Comment;
    @FXML private JFXTextArea Q17Comment;
    @FXML private JFXTextArea Q18Comment;
    @FXML private JFXTextArea Q19Comment;
    @FXML private JFXTextField receiver;
    @FXML private JFXDatePicker dateIssued;
    @FXML private JFXTextField signature;
    @FXML private ImageView alcyView;
    @FXML private Text alcyLabel;

    @SuppressWarnings("Duplicates") @FXML public void initialize () {
        //load all the comments for this page
        Alcy alcy = cacheM.getAlcy();
        alcy.summonAlcy(alcyView, alcyLabel);
        alcy.sayAForm();
        Q16Comment.setText(comments.getComment16());
        Q17Comment.setText(comments.getComment17());
        Q18Comment.setText(comments.getComment18());
        Q19Comment.setText(comments.getComment19());

        Form form = this.form;

        printName.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (newPropertyValue)
                { cacheM.getAlcy().sayAHelpCompanyName();}
                else
                {
                    //saveDraft();
                    //cacheM.getAlcy().sayGreeting();
                }
            }
        });



        dateOfApplication.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (newPropertyValue)
                { cacheM.getAlcy().sayAHelpDate();}
                else
                {
                    //saveDraft();
                    //cacheM.getAlcy().sayGreeting();
                }
            }
        });
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        if(!form.getDateOfApplication().isEmpty()){
            dateOfApplication.setValue(LocalDate.parse(form.getDateOfApplication(), formatter));
        }
        dateOfApplication.setEditable(false);

        printName.setText(form.parseGarbage(form.getPrintName()));
        printName.setStyle(form.parseStyle(form.getPrintName()));
        printName.setEditable(false);
        System.out.println("it didn't get passed to agent");

        signature.setText(form.getSignature());
        if(!form.getDateIssued().isEmpty()){
            dateIssued.setValue(LocalDate.parse(form.getDateIssued(), formatter));
        }
    }

    @FXML
    public void search() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/SearchPage.fxml"));
        sceneM.changeScene(loader, new SearchPage(sceneM, cacheM));
    }

    @FXML
    public void back() throws IOException {
        comments.setComment16(Q16Comment.getText());
        comments.setComment17(Q17Comment.getText());
        comments.setComment18(Q18Comment.getText());
        comments.setComment19(Q19Comment.getText());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/aApplicationFormPg3.fxml"));
        sceneM.changeScene(loader, new aApplicationFormPg3(sceneM, cacheM, form,comments));
    }

    @FXML
    public void goToHomePage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/aHomepage.fxml"));
        sceneM.changeScene(loader, new aHomepage(sceneM, cacheM));
    }

    @FXML
    public void acceptForm() throws IOException {
        if (!signature.getText().isEmpty() && dateIssued.getValue().isAfter(LocalDate.now().minusYears(1000))) {
            form.setSignature(signature.getText());
            form.setDateIssued(dateIssued.getValue().toString());

            cacheM.approveForm(cacheM.getDbM().getConnection());
            Agent A = (Agent) cacheM.getAcct();
            A.approveOrDeny(form);
            goToHomePage();
        } else {
            System.out.println("invalid signature or date");
        }

        goToHomePage();
    }


    @SuppressWarnings("Duplicates") @FXML public void denyForm() throws Exception {
        comments.setComment16(Q16Comment.getText());
        comments.setComment17(Q17Comment.getText());
        comments.setComment18(Q18Comment.getText());
        comments.setComment19(Q19Comment.getText());
        cacheM.getForm().setComments(comments);

        if (!signature.getText().isEmpty() && dateIssued.getValue().isAfter(LocalDate.now().minusYears(1000))) {
            form.setSignature(signature.getText());
            form.setDateIssued(dateIssued.getValue().toString());

            cacheM.denyForm(cacheM.getDbM().getConnection());
            Agent A = (Agent) cacheM.getAcct();
            A.approveOrDeny(form);

            goToHomePage();
        } else {
            System.out.println("invalid signature or date");
        }
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
