/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Generator;

import java.util.Random;

/**
 *
 * @author Jasper
 */
public class RandomNumberGenerator {
    
    
    //JavaFX DataGenerator!
    private int minimum;
    private int maximum;
    private Random generator;

    public RandomNumberGenerator(int minimum, int maximum) {
        this.minimum = minimum;
        this.maximum = maximum;
        this.generator = new Random();
    }

    public double generate() {
        return generator.nextDouble() * (maximum - minimum + 1) + minimum;
    }

}
