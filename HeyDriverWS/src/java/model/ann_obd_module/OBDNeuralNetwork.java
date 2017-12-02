/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ann_obd_module;

import org.neuroph.core.NeuralNetwork;

/**
 *
 * @author LAPGrock
 */
public class OBDNeuralNetwork {
    
    public double[] firstNeuralNetwork (double air_fuel_ratio, double stft2, double stft1, double ltft2, double ltft1, double maf, double pressure_at){
        NeuralNetwork neuralNetwork = NeuralNetwork.createFromFile("C:\\Users\\LAPGrock\\Documents\\GitHub\\Tesis-OBDII\\HeyDriverWS\\src\\java\\model\\ann_obd_module\\NeuralNetworksOBD\\FirstNeuralNetwork.nnet");
        // set network input 
        neuralNetwork.setInput(air_fuel_ratio, stft2, stft1, ltft2, ltft1, maf, pressure_at);
        neuralNetwork.calculate();
        double[] networkOutput = neuralNetwork.getOutput();
        return networkOutput;
    }
    
    public double[] secondNeuralNetwork (double air_fuel_ratio, double timeadvance, double rpm, double stft2, double stft1, double ltft2, double ltft1, double maf, double coolant, double motorcharge){
        NeuralNetwork neuralNetwork = NeuralNetwork.createFromFile("C:\\Users\\LAPGrock\\Documents\\GitHub\\Tesis-OBDII\\HeyDriverWS\\src\\java\\model\\model\\ann_obd_module\\NeuralNetworksOBD\\NeuralNetworksOBD\\SecondNeuralNetwork.nnet");
        // set network input 
        neuralNetwork.setInput(air_fuel_ratio, timeadvance, rpm, stft2, stft1, ltft2, ltft1, maf, coolant, motorcharge);
        neuralNetwork.calculate();
        double[] networkOutput = neuralNetwork.getOutput();
        return networkOutput;
    }
    
    public double[] thirdNeuralNetwork (double air_fuel_ratio, double timeadvance, double rpm, double stft2, double stft1, double ltft2, double ltft1){
        NeuralNetwork neuralNetwork = NeuralNetwork.createFromFile("C:\\Users\\LAPGrock\\Documents\\GitHub\\Tesis-OBDII\\HeyDriverWS\\src\\java\\model\\ann_obd_module\\NeuralNetworksOBD\\NeuralNetworksOBD\\ThirdNeuralNetwork.nnet");
        // set network input 
        neuralNetwork.setInput(air_fuel_ratio, timeadvance, rpm, stft2, stft1, ltft2, ltft1);
        neuralNetwork.calculate();
        double[] networkOutput = neuralNetwork.getOutput();
        return networkOutput;
    }
    
    public double[] forthNeuralNetwork (double admission_temp, double coolant, double air_fuel_ratio, double stft2, double stft1, double ltft2, double ltft1, double timeadvance, double rpm){
        NeuralNetwork neuralNetwork = NeuralNetwork.createFromFile("C:\\Users\\LAPGrock\\Documents\\GitHub\\Tesis-OBDII\\HeyDriverWS\\src\\java\\model\\ann_obd_module\\NeuralNetworksOBD\\NeuralNetworksOBD\\ForthNeuralNetwork.nnet");
        // set network input 
        neuralNetwork.setInput(admission_temp, coolant, air_fuel_ratio, stft2, stft1, ltft2, ltft1, timeadvance, rpm);
        neuralNetwork.calculate();
        double[] networkOutput = neuralNetwork.getOutput();
        return networkOutput;
    }
    
}
