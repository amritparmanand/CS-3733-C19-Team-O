package UI.Controllers;

import Managers.CacheManager;
import Managers.SceneManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import java.io.IOException;

/**
 * @author ???
 * @version It 1
 * Controller for mFormStorage of UI
 */
public class mFormStorage {

    private SceneManager sceneM;
    private CacheManager cacheM;

    @FXML private Button backToMHome;
    @FXML private Button search;
    @FXML private Button newForm;
    //@FXML private VBox getApp;

    public mFormStorage(SceneManager sceneM, CacheManager cacheM) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
    }

    @FXML
    public void newForm() throws IOException {
        //
        //
    }

    /*  @FXML
    public void loadForms(ActionEvent event) throws IOException {
        Pane formResult = null;
        try {

            formResult = FXMLLoader.load(getClass().getResource("/UI/Views/alcBox.fxml"));
            loadForms.getChildren().add(formResult);


            formResult.setId("Alcoholbox");
            formResult.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    try{
                        aApplicationFormPg1();

                    }
                    catch(IOException e){
                        e.printStackTrace();
                    }
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
*/

    @FXML
    public void searchPage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/SearchPage.fxml"));
        sceneM.changeScene(loader, new SearchPage(sceneM, cacheM));
    }

    @FXML
    public void goToHomePage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mHomepage.fxml"));
        sceneM.changeScene(loader, new mHomepage(sceneM, cacheM));
    }


}