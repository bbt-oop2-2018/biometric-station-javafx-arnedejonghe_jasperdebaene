/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biometricstationjavafxml;

import Generator.RandomNumberGenerator;
import MQTT.IMqttMessageHandler;
import MQTT.MqttBiometricStationService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

/**
 *
 * @author Jasper
 */
public class ChartDisplayDocumentController implements Initializable, IMqttMessageHandler {

    //datageneration
    private int xValue = 0;

    private final int MAXIMUM_VALUE = 100;
    private final int MINIMUM_VALUE = 0;
    private RandomNumberGenerator dataGenerator;

    @FXML
    private LineChart temperature;
    private XYChart.Series temperatureValues;

    @FXML
    private LineChart heartbeat;
    private XYChart.Series heartbeatValues;

    @FXML
    private LineChart accelerometer;
    private XYChart.Series accelerometerValueX;
    private XYChart.Series accelerometerValueY;
    private XYChart.Series accelerometerValueZ;

    @FXML
    private void generateRandomDataHandler(ActionEvent event) {

        //Temperature
        temperatureValues.getData().add(new XYChart.Data(xValue, dataGenerator.generate()));

        //heartbeat
        heartbeatValues.getData().add(new XYChart.Data(xValue, dataGenerator.generate()));

        //accelerometer
        accelerometerValueX.getData().add(new XYChart.Data(xValue, dataGenerator.generate()));
        accelerometerValueY.getData().add(new XYChart.Data(xValue, dataGenerator.generate()));
        accelerometerValueZ.getData().add(new XYChart.Data(xValue, dataGenerator.generate()));
        xValue++;
        System.out.println("You clicked me!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //dataGenerator
        dataGenerator = new RandomNumberGenerator(MINIMUM_VALUE, MAXIMUM_VALUE);

        //Temperature
        temperature.setLegendVisible(false);
        temperatureValues = new XYChart.Series();

        // Add series to chart
        temperature.getData().add(temperatureValues);
        //Set Axis
        temperature.getYAxis().setLabel("Temperature [celcius]");
        temperature.getXAxis().setLabel("Measurement");

        //Heartbeat
        heartbeat.setLegendVisible(false);
        heartbeatValues = new XYChart.Series();

        // Add series to chart
        heartbeat.getData().add(heartbeatValues);
        //Set Axis
        heartbeat.getYAxis().setLabel("heartbeat [beats/sec]");
        heartbeat.getXAxis().setLabel("Measurement");

        //Accelerometer
        accelerometer.setLegendVisible(false);
        accelerometerValueX = new XYChart.Series();
        accelerometerValueX.setName("X-Value");
        accelerometerValueY = new XYChart.Series();
        accelerometerValueY.setName("Y-Value");
        accelerometerValueZ = new XYChart.Series();
        accelerometerValueZ.setName("Z-Value");

        // add series to chart
        accelerometer.getData().add(accelerometerValueX);
        accelerometer.getData().add(accelerometerValueY);
        accelerometer.getData().add(accelerometerValueZ);
        //Set Axis
        accelerometer.getYAxis().setLabel("accelerometer [m/s2]");
        accelerometer.getXAxis().setLabel("Measurement");

        // Create a chat service and allow this class to receive messages
        temperatureService = new MqttBiometricStationService("Arne&Jasper", "temperature");
        temperatureService.setMessageHandler((IMqttMessageHandler) this);

        heartBeatService = new MqttBiometricStationService("Arne&Jasper", "heartbeat");
        heartBeatService.setMessageHandler((IMqttMessageHandler) this);

        accelerometerService = new MqttBiometricStationService("Arne&Jasper", "accelerometer");
        accelerometerService.setMessageHandler((IMqttMessageHandler) this);
        
        // When the user closes the window we need to disconnect the client
        disconnectClientOnClose();
    }

    // This method is called if a chat message is received from mqtt
    @Override
    public void messageArrived(String channel, String message) {
        //conversation.appendText(message + "\n");
        System.out.println("Received chat message (on channel = " + channel
                + "): " + message);
    }

    // Allows us to use the wrapper for sending chat messages via MQTT
    private MqttBiometricStationService temperatureService;
    private MqttBiometricStationService heartBeatService;
    private MqttBiometricStationService accelerometerService;


    private void disconnectClientOnClose() {
        // Source: https://stackoverflow.com/a/30910015
        temperature.sceneProperty().addListener((observableScene, oldScene, newScene) -> {
            if (oldScene == null && newScene != null) {
                // scene is set for the first time. Now its the time to listen stage changes.
                newScene.windowProperty().addListener((observableWindow, oldWindow, newWindow) -> {
                    if (oldWindow == null && newWindow != null) {
                        // stage is set. now is the right time to do whatever we need to the stage in the controller.
                        ((Stage) newWindow).setOnCloseRequest((event) -> {
                            temperatureService.disconnect();
                            heartBeatService.disconnect();
                            accelerometerService.disconnect();
                        });
                    }
                });
            }
        });
    }

}
