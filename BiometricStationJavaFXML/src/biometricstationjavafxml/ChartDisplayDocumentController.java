/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biometricstationjavafxml;

import Generator.RandomNumberGenerator;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

/**
 *
 * @author Jasper
 */
public class ChartDisplayDocumentController implements Initializable {
    
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
    private XYChart.Series accelerometerValues;

    @FXML
    private void generateRandomDataHandler(ActionEvent event) {

        System.out.println("You clicked me!");

        //Temperature
        temperatureValues.getData().add(new XYChart.Data(xValue, dataGenerator.generate()));

        //heartbeat
        heartbeatValues.getData().add(new XYChart.Data(xValue, dataGenerator.generate()));

        //accelerometer
        accelerometerValues.getData().add(new XYChart.Data(xValue, dataGenerator.generate()));
        xValue++;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //dataGenerator
        dataGenerator = new RandomNumberGenerator(MINIMUM_VALUE, MAXIMUM_VALUE);

        //Temperature
        temperatureValues = new XYChart.Series();

        // Add series to chart
        temperature.getData().add(temperatureValues);
        //Set Axis
        temperature.getYAxis().setLabel("Temperature [celcius]");
        temperature.getXAxis().setLabel("Measurement");

        //Heartbeat
        heartbeatValues = new XYChart.Series();

        // Add series to chart
        heartbeat.getData().add(heartbeatValues);
        //Set Axis
        heartbeat.getYAxis().setLabel("heartbeat [beats/second]");
        heartbeat.getXAxis().setLabel("Measurement");

        //Accelerometer
        accelerometerValues = new XYChart.Series();

        // add series to chart
        accelerometer.getData().add(accelerometerValues);
        //Set Axis
        accelerometer.getYAxis().setLabel("accelerometer [m/s2]");
        accelerometer.getXAxis().setLabel("Measurement");
    }  
    
}
