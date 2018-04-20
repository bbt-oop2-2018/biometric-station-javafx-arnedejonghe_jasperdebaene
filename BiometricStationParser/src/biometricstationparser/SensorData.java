/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biometricstationparser;

/**
 *
 * @author Jasper
 */
public class SensorData {

    private double temperature;
    private int hearthbeat;
    private int coordinate_x;
    private int coordinate_y;
    private int coordinate_z;

    public SensorData(double temperature, int hearthbeat, int coordinate_x, int coordinate_y, int coordinate_z) {
        this.temperature = temperature;
        this.hearthbeat = hearthbeat;
        this.coordinate_x = coordinate_x;
        this.coordinate_y = coordinate_y;
        this.coordinate_z = coordinate_z;

    }

    public double getTemperature() {
        return temperature;
    }

    public int getHearthbeat() {
        return hearthbeat;
    }

    public int getCoordinate_x() {
        return coordinate_x;
    }

    public int getCoordinate_y() {
        return coordinate_y;
    }

    public int getCoordinate_z() {
        return coordinate_z;
    }

    @Override
    public String toString() {
        return "temperature = " + temperature + "| hearthbeat = " + hearthbeat 
               + "coordinate_x = " + coordinate_x + "coordinate_y = " + coordinate_y
               + "coordinate_z = " + coordinate_z;
    }
}
