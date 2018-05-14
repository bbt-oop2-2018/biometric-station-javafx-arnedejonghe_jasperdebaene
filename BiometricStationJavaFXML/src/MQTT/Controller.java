/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MQTT;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 *
 * @author Jasper
 */

package fxmlmqttchatclient;

import be.vives.oop.mqtt.chatservice.IMqttMessageHandler;
import be.vives.oop.mqtt.chatservice.MqttChatService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Controller implements Initializable, IMqttMessageHandler {

    @FXML
    private Button send;
    @FXML
    private TextArea message;
    @FXML
    private TextArea conversation;
    @FXML
    private TextField username;
    @FXML
    private TextField channel;

    // Allows us to use the wrapper for sending chat messages via MQTT
    private MqttChatService TemperatureService;
    private MqttChatService heartBeatService;
    private MqttChatService accelerometerService;
    
  
    public void channelChecker() {
        TemperatureService.switchChannel(channel.getText());
        heartBeatService.switchChannel(channel.getText());
        accelerometerService.switchChannel(channel.getText());
    }

    // This method is called if a chat message is received from mqtt
    @Override
    public void messageArrived(String channel, String message) {
        conversation.appendText(message + "\n");
        System.out.println("Received chat message (on channel = " + channel
                + "): " + message);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Create a chat service and allow this class to receive messages
        TemperatureService = new MqttChatService();
        TemperatureService.setMessageHandler(this);
        
        heartBeatService = new MqttChatService();
        heartBeatService.setMessageHandler(this);
        
        accelerometerService = new MqttChatService();
        accelerometerService.setMessageHandler(this);        
        // When the user closes the window we need to disconnect the client
        disconnectClientOnClose();
    }

    private void disconnectClientOnClose() {
        // Source: https://stackoverflow.com/a/30910015
        send.sceneProperty().addListener((observableScene, oldScene, newScene) -> {
            if (oldScene == null && newScene != null) {
                // scene is set for the first time. Now its the time to listen stage changes.
                newScene.windowProperty().addListener((observableWindow, oldWindow, newWindow) -> {
                    if (oldWindow == null && newWindow != null) {
                        // stage is set. now is the right time to do whatever we need to the stage in the controller.
                        ((Stage) newWindow).setOnCloseRequest((event) -> {
                            TemperatureService.disconnect();
                            heartBeatService.disconnect();
                            accelerometerService.disconnect();
                        });
                    }
                });
            }
        });
    }

}

