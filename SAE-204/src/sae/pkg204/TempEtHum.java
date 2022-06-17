/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sae.pkg204;

/**
 * cette classe permet de concerver les donnée de temperature et d'humidité.
 * @author Maxen
 */
public class TempEtHum {
    private double temperature;
    private double humidide;

    public TempEtHum(double temperature, double humidide) {
        this.temperature = temperature;
        this.humidide = humidide;
    }
    
    
    
    public double getTemperature() {
        return temperature;
    }

    public double getHumidide() {
        return humidide;
    }
}
