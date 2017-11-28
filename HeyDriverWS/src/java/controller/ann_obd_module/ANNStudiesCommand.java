/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.ann_obd_module;

import common.entities.Car;
import controller.Command;
import java.util.ArrayList;
import model.ann_obd_module.OBDNeuralNetwork;
import model.cars_module.DAOCar;

/**
 *
 * @author LAPGrock
 */
public class ANNStudiesCommand extends Command{
    
    private double air_fuel_ratio;
    private double timeadvance;
    private double rpm;
    private double stft2;
    private double stft1;
    private double ltft2;
    private double ltft1;
    private double maf;
    private double coolant;
    private double motorcharge;
    private double pressure_at;
    private double admission_temp;
    private ArrayList<Integer> failures;
    
    public ANNStudiesCommand(double air_fuel_ratio, double timeadvance, double rpm, double stft2, 
                             double stft1, double ltft2, double ltft1, double maf, double coolant, 
                             double motorcharge, double pressure_at, double admission_temp){
    
        this.air_fuel_ratio = air_fuel_ratio;
        this.timeadvance = timeadvance;
        this.rpm = rpm;
        this.stft2 = stft2;
        this.stft1 = stft1;
        this.ltft2 = ltft2;
        this.ltft1 = ltft1;
        this.maf = maf;
        this.coolant = coolant;
        this.motorcharge = motorcharge;
        this.pressure_at = pressure_at;
        this.admission_temp = admission_temp;
    }
    
    public ArrayList<Integer> getFailuresList(){
        return this.failures;
    }

    @Override
    public void execute() throws Exception{
        try{
            OBDNeuralNetwork ann = new OBDNeuralNetwork();
            double[] firstANNResponse = ann.firstNeuralNetwork(air_fuel_ratio, stft2, stft1, ltft2, ltft1, maf, pressure_at);
            double[] secondANNResponse = ann.secondNeuralNetwork(air_fuel_ratio, timeadvance, rpm, stft2, stft1, ltft2, ltft1, maf, coolant, motorcharge);
            double[] thirdANNResponse = ann.thirdNeuralNetwork(air_fuel_ratio, timeadvance, rpm, stft2, stft1, ltft2, ltft1);
            double[] forthANNResponse = ann.forthNeuralNetwork(admission_temp, coolant, air_fuel_ratio, stft2, stft1, ltft2, ltft1, timeadvance, rpm);
        }catch (Exception ex){
            throw ex;
        }
        
    }
    
}
