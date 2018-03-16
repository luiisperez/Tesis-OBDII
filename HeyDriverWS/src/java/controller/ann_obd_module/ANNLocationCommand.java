/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.ann_obd_module;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderResult;
import com.google.code.geocoder.model.LatLng;
import common.entities.ObdData;
import java.util.ArrayList;
import model.ann_obd_module.OBDNeuralNetwork;
import controller.Command;
import java.util.List;
import model.obdData_module.DAOObdData;

/**
 *
 * @author crist
 */
public class ANNLocationCommand extends Command{
    private String serial;
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
    private ArrayList<String> locations = new ArrayList<String>();
    
        public ANNLocationCommand(String serial){
        this.serial = serial;
//        this.air_fuel_ratio = air_fuel_ratio;
//        this.timeadvance = timeadvance;
//        this.rpm = rpm;
//        this.stft2 = stft2;
//        this.stft1 = stft1;
//        this.ltft2 = ltft2;
//        this.ltft1 = ltft1;
//        this.maf = maf;
//        this.coolant = coolant;
//        this.motorcharge = motorcharge;
//        this.pressure_at = pressure_at;
//        this.admission_temp = admission_temp;
        
        //air_fuel_ratio/50, timeadvance/90, rpm/5500, stft2/25, stft1/25, ltft2/25, ltft1/25, maf/100, coolant/300, motorcharge/100, pressure_at/100, admission_temp/300
    }
        
        public ArrayList<String> getLocationsList(){
        return this.locations;
    }
        
    public boolean containsFaults(ArrayList<Integer> failures){
        for (Integer failure : failures) {
            if (failure == 1) {
                return true;
            }
        }
        return false;
    }
    
    public boolean containsAdress(ArrayList<String> adresses, String adress){
        for (String adresse : adresses) {
            if (adresse.equals(adress) == true) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void execute() throws Exception{
        try{
            Geocoder geocoder = new Geocoder();
            GeocoderRequest geocoderRequest;
            GeocodeResponse geocoderResponse;
            OBDNeuralNetwork ann = new OBDNeuralNetwork();
            ArrayList<ObdData> _histories;
            DAOObdData dao = new DAOObdData();                     
            _histories = dao.getHistoryBySerial(serial);
       
                for (ObdData _historie : _histories) {
                    ArrayList<Integer> failures = new ArrayList<>();
                    double[] firstANNResponse = ann.firstNeuralNetwork(_historie.getAirFuel_Ratio()/50, _historie.getSTFT2()/25, _historie.getSTFT1()/25, _historie.getLTFT2()/25, _historie.getLTFT1()/25, _historie.getMass_Air_Flow()/100, _historie.getIntake_Manifold_Pressure()/100);
                    double[] secondANNResponse = ann.secondNeuralNetwork(_historie.getAirFuel_Ratio()/50, _historie.getTiming_Advance()/90, _historie.getEngine_RPM()/5500, _historie.getSTFT2()/25, _historie.getSTFT1()/25, _historie.getLTFT2()/25, _historie.getLTFT1()/25, _historie.getMass_Air_Flow()/100, _historie.getEngine_Coolant_Temperature()/300, _historie.getEngine_Load()/100);
                    double[] thirdANNResponse = ann.thirdNeuralNetwork(_historie.getAirFuel_Ratio()/50, _historie.getTiming_Advance()/90, _historie.getEngine_RPM()/5500, _historie.getSTFT2()/25, _historie.getSTFT1()/25, _historie.getLTFT2()/25, _historie.getLTFT1()/25);
                    double[] forthANNResponse = ann.forthNeuralNetwork(_historie.getAir_Intake_Temperature()/100, _historie.getEngine_Coolant_Temperature()/300, _historie.getAirFuel_Ratio()/50, _historie.getSTFT2()/25, _historie.getSTFT1()/25, _historie.getLTFT2()/25, _historie.getLTFT1()/25, _historie.getTiming_Advance()/90, _historie.getEngine_RPM()/5500);
                    failures.add((int)Math.round(firstANNResponse[0]));
                    failures.add((int)Math.round(secondANNResponse[0]));
                    failures.add((int)Math.round(thirdANNResponse[0]));
                    failures.add((int)Math.round(forthANNResponse[0]));
                    if (containsFaults(failures) == true) {
                        //Thread.sleep(50);
                        geocoderRequest = new GeocoderRequestBuilder().setLocation(new LatLng(String.valueOf(_historie.getLat()), String.valueOf(_historie.getLon()))).setLanguage("es").getGeocoderRequest();
                        geocoderResponse = geocoder.geocode(geocoderRequest);
                        List<GeocoderResult> results = geocoder.geocode(geocoderRequest).getResults();
                        if(geocoderResponse!=null && results.isEmpty() == false)
                        {
                            if (containsAdress(locations,results.get(0).getFormattedAddress())== false)
                            {
                                locations.add(results.get(0).getFormattedAddress());
                            }
                        }
                    }
                    failures.removeAll(failures);
                }
            }catch (Exception ex){
            throw ex;
        }
        
    }
    
}
