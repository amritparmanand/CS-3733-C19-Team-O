/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketserverfx;

import java.lang.invoke.MethodHandles;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import socketfx.Constants;
import socketfx.FxSocketServer;
import socketfx.SocketListener;


public class FXMLDocumentController implements Initializable {

    @FXML
    private Label rcvdUserLabel;
    @FXML
    private Label rcvdPasswordLabel;

    @FXML
    private Button sendButton;
    @FXML
    private TextField sendTextField;
    @FXML
    private Button connectButton;
    @FXML
    private Button disconnectButton;
    @FXML
    private TextField portTextField;
    @FXML
    private CheckBox autoConnectCheckBox;
    @FXML
    private Label connectedLabel;
    
    private final static Logger LOGGER =
            Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

    private ListView lastSelectedListView;

    private boolean isConnected;

    public enum ConnectionDisplayState {

        DISCONNECTED, WAITING, CONNECTED, AUTOCONNECTED, AUTOWAITING
    }

    private FxSocketServer socket;

    private void connect() {
        socket = new FxSocketServer(new FxSocketListener(),
                Integer.valueOf(portTextField.getText()),
                Constants.instance().DEBUG_NONE);
        socket.connect();
    }

    private void displayState(ConnectionDisplayState state) {
        switch (state) {
            case DISCONNECTED:
                connectButton.setDisable(false);
                disconnectButton.setDisable(true);
                sendButton.setDisable(true);
                sendTextField.setDisable(true);
                connectedLabel.setText("Not connected");
                break;
            case WAITING:
            case AUTOWAITING:
                connectButton.setDisable(true);
                disconnectButton.setDisable(true);
                sendButton.setDisable(true);
                sendTextField.setDisable(true);
                connectedLabel.setText("Waiting to connect");
                break;
            case CONNECTED:
                connectButton.setDisable(true);
                disconnectButton.setDisable(false);
                sendButton.setDisable(false);
                sendTextField.setDisable(false);

                connectedLabel.setText("Connected");
                break;
            case AUTOCONNECTED:
                connectButton.setDisable(true);
                disconnectButton.setDisable(true);
                sendButton.setDisable(false);
                sendTextField.setDisable(false);
                connectedLabel.setText("Connected");
                break;
        }
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        isConnected = false;
        displayState(ConnectionDisplayState.DISCONNECTED);






        Runtime.getRuntime().addShutdownHook(new ShutDownThread());

        /*
         * Uncomment to have autoConnect enabled at startup
         */
//        autoConnectCheckBox.setSelected(true);
//        displayState(ConnectionDisplayState.WAITING);
//        connect();
    }

    class ShutDownThread extends Thread {

        @Override
        public void run() {
            if (socket != null) {
                if (socket.debugFlagIsSet(Constants.instance().DEBUG_STATUS)) {
                    LOGGER.info("ShutdownHook: Shutting down Server Socket");    
                }
                socket.shutdown();
            }
        }
    }

    class FxSocketListener implements SocketListener {

        @Override
        public void onMessage(String line) {
            if (line != null && !line.equals("")) {
                int separationInd = line.indexOf(" ");
                String username = line.substring(0, separationInd);
                String password = line.substring((separationInd+1));
                rcvdUserLabel.setText(username);
                rcvdPasswordLabel.setText(password);
                if(username.equals("Admin") && password.equals("Password")) {
                    socket.sendMessage(username+ ": Logged in");
                }
                else
                {
                    socket.sendMessage("Invalid Username or Password");

                }
            }
        }

        @Override
        public void onClosedStatus(boolean isClosed) {
            if (isClosed) {
                isConnected = false;
                if (autoConnectCheckBox.isSelected()) {
                    displayState(ConnectionDisplayState.AUTOWAITING);
                    connect();
                } else {
                    displayState(ConnectionDisplayState.DISCONNECTED);
                }
            } else {
                isConnected = true;
                if (autoConnectCheckBox.isSelected()) {
                    displayState(ConnectionDisplayState.AUTOCONNECTED);
                } else {
                    displayState(ConnectionDisplayState.CONNECTED);
                }
            }
        }
    }




    @FXML
    private void handleSendMessageButton(ActionEvent event) {
        if (!sendTextField.getText().equals("")) {
            socket.sendMessage(sendTextField.getText());
        }
    }

    @FXML
    private void handleConnectButton(ActionEvent event) {
        displayState(ConnectionDisplayState.WAITING);
        connect();
    }

    @FXML
    private void handleDisconnectButton(ActionEvent event) {
        displayState(ConnectionDisplayState.DISCONNECTED);
        socket.shutdown();
    }

    @FXML
    private void handleAutoConnectCheckBox(ActionEvent event) {
        if (autoConnectCheckBox.isSelected()) {
            if (isConnected) {
                displayState(ConnectionDisplayState.AUTOCONNECTED);
            } else {
                displayState(ConnectionDisplayState.AUTOWAITING);
                connect();
            }
        } else {
            if (isConnected) {
                displayState(ConnectionDisplayState.CONNECTED);
            } else {
                displayState(ConnectionDisplayState.WAITING);
            }
        }
    }

}
