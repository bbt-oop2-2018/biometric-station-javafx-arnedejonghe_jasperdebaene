package MQTT;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nicod
 */
public interface IMqttMessageHandler {
    public void messageArrived(String channel, String message);
}
