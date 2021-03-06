/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.ann_obd_module;

import common.entities.Car;
import controller.Command;
import java.util.ArrayList;
import model.ann_obd_module.DAOFailure;
import model.ann_obd_module.OBDNeuralNetwork;
import model.cars_module.DAOCar;

/**
 *
 * @author LAPGrock
 */
public class ANNStudiesCommand extends Command{
    private String serial;
    private String brand;
    private String model;
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
    private ArrayList<Integer> failures = new ArrayList<Integer>();
    
    public ANNStudiesCommand(String serial, String brand, String model, double air_fuel_ratio, double timeadvance, double rpm, double stft2, 
                             double stft1, double ltft2, double ltft1, double maf, double coolant, 
                             double motorcharge, double pressure_at, double admission_temp){
        this.serial = serial;
        this.brand = brand;
        this.model = model;
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
            DAOFailure dao = new DAOFailure();
            double[] firstANNResponse = ann.firstNeuralNetwork(air_fuel_ratio, stft2, stft1, ltft2, ltft1, maf, pressure_at);
            double[] secondANNResponse = ann.secondNeuralNetwork(air_fuel_ratio, timeadvance, rpm, stft2, stft1, ltft2, ltft1, maf, coolant, motorcharge);
            double[] thirdANNResponse = ann.thirdNeuralNetwork(air_fuel_ratio, timeadvance, rpm, stft2, stft1, ltft2, ltft1);
            double[] forthANNResponse = ann.forthNeuralNetwork(admission_temp, coolant, air_fuel_ratio, stft2, stft1, ltft2, ltft1, timeadvance, rpm);
            double h = firstANNResponse[0];
            long j = Math.round(firstANNResponse[0]);
            failures.add((int)Math.round(firstANNResponse[0]));
            if (Math.round(firstANNResponse[0]) != 0){
                dao.create(serial, brand, model, "Consumo desproporcionado de combustible");
            }
            failures.add((int)Math.round(firstANNResponse[1]));
            if (Math.round(firstANNResponse[1]) != 0){
                dao.create(serial, brand, model, "Mezcla de Aire/Combustible muy pobre");
            }
            failures.add((int)Math.round(firstANNResponse[2]));
            if (Math.round(firstANNResponse[2]) != 0){
                dao.create(serial, brand, model, "Mezcla de Aire/Combustible muy rica");
            }
            failures.add((int)Math.round(firstANNResponse[3]));
            if (Math.round(firstANNResponse[3]) != 0){
                dao.create(serial, brand, model, "Sensor MAF sucio o averiado");
            }
            failures.add((int)Math.round(secondANNResponse[0]));
            if (Math.round(secondANNResponse[0]) != 0){
                dao.create(serial, brand, model, "Inyectores sucios o averiados");
            }
            failures.add((int)Math.round(secondANNResponse[1]));
            if (Math.round(secondANNResponse[1]) != 0){
                dao.create(serial, brand, model, "Bobina Averiada");
            }
            failures.add((int)Math.round(thirdANNResponse[0]));
            if (Math.round(thirdANNResponse[0]) != 0){
                dao.create(serial, brand, model, "Bujías propensas a daños");
            }
            failures.add((int)Math.round(forthANNResponse[0]));
            if (Math.round(forthANNResponse[0]) != 0){
                dao.create(serial, brand, model, "Vehículo propenso a recalentamiento");
            }
            failures.add((int)Math.round(forthANNResponse[1]));
            if (Math.round(forthANNResponse[1]) != 0){
                dao.create(serial, brand, model, "Radiador Averiado");
            }
        }catch (Exception ex){
            throw ex;
        }
        
    }
    
}