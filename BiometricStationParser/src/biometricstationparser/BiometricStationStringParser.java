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
public class BiometricStationStringParser {

    public SensorData parse(String dataString) {
        
        if (!isValidString(dataString)) {
        return null;
    }
        double temperature = 0;
        int hearthbeat = 0;
        int coordinate_x = 0;
        int coordinate_y = 0;
        int coordinate_z = 0;
        
        //parsen
        
        
        
        return new SensorData(temperature, hearthbeat, coordinate_x, coordinate_y, coordinate_z);
    }

    private boolean isValidString(String dataString) {
//        if (dataString.indexOf("|") != -1
//                && dataString.indexOf("|") != -1
//                && dataString.indexOf("|") != -1) {
//            return true;
//        }   else {
//            return false;
//        }
//    }

        return (dataString.indexOf("|") != -1
                && dataString.indexOf("|") != -1
                && dataString.indexOf("|") != -1);
    }
}
