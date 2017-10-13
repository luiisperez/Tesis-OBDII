package common.entities;

/**
 *
 * @author cristian
 */

public class ObdData {
    private float command_Equivalence_Ratio; // %
    private float ambient_Air_Temperature; // Volts -> V
    private float distance_traveled_with_MIL_on; // kilómetros -> km
    private float vehicle_Speed; //km/h
    private float STFT2; // %
    private float engine_oil_temperature; // ºC -> C
    private float engine_Load; // %
    private String vehicle_Identification_Number; // String Encriptado
    private String trouble_Codes;  // codigos de error
    private String engine_Runtime;
    private float timing_Advance; // %
    private float control_Module_Power_Supply ; // Volts -> V
    private float STFT1; // %
    private float mass_Air_Flow; // gramos por segundo g/s
    private float fuel_Pressure; // kilopascal -> kPa
    private float engine_RPM;  //RPM
    private float throttle_Position; // %
    private String fuel_type; // no siempre lo trae
    private float LTFT2; // %
    private float fuel_Consumption_Rate; // litros por hora -> L/h
    private float intake_Manifold_Pressure; // kilopascal -> kPa
    private String diagnostic_Trouble_Codes; // MIL is OFF0 codes
    private float wideband_AirFuel_Ratio; // Relación aire-combustible preciso -> float:int AFR
    private float engine_Coolant_Temperature; // ºC -> C
    private float airFuel_Ratio;  // Relación aire-combustible -> float:int AFR
    private float LTFT1; // %
    private float barometric_Pressure; // kilopascal -> kPa
    private float fuel_Level; // nivel de combustible -> %
    private float air_Intake_Temperature; // ºC -> C
    private float fuel_Rail_Pressure; // kilopascal -> kPa
    private String time_mark;
    private double lat = 0;
    private double lon = 0;
    private double alt = 0;

    public ObdData() {}

    public String getTime_mark() {
        return time_mark;
    }

    public void setTime_mark(String time_mark) {
        this.time_mark = time_mark;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getAlt() {
        return alt;
    }

    public void setAlt(double alt) {
        this.alt = alt;
    }

    public float getCommand_Equivalence_Ratio() {
        return command_Equivalence_Ratio;
    }

    public void setCommand_Equivalence_Ratio(float command_Equivalence_Ratio) {
        this.command_Equivalence_Ratio = command_Equivalence_Ratio;
    }

    public float getAmbient_Air_Temperature() {
        return ambient_Air_Temperature;
    }

    public void setAmbient_Air_Temperature(float ambient_Air_Temperature) {
        this.ambient_Air_Temperature = ambient_Air_Temperature;
    }

    public float getDistance_traveled_with_MIL_on() {
        return distance_traveled_with_MIL_on;
    }

    public void setDistance_traveled_with_MIL_on(float distance_traveled_with_MIL_on) {
        this.distance_traveled_with_MIL_on = distance_traveled_with_MIL_on;
    }

    public float getVehicle_Speed() {
        return vehicle_Speed;
    }

    public void setVehicle_Speed(float vehicle_Speed) {
        this.vehicle_Speed = vehicle_Speed;
    }

    public float getSTFT2() {
        return STFT2;
    }

    public void setSTFT2(float STFT2) {
        this.STFT2 = STFT2;
    }

    public float getEngine_oil_temperature() {
        return engine_oil_temperature;
    }

    public void setEngine_oil_temperature(float engine_oil_temperature) {
        this.engine_oil_temperature = engine_oil_temperature;
    }

    public float getEngine_Load() {
        return engine_Load;
    }

    public void setEngine_Load(float engine_Load) {
        this.engine_Load = engine_Load;
    }

    public String getVehicle_Identification_Number() {
        return vehicle_Identification_Number;
    }

    public void setVehicle_Identification_Number(String vehicle_Identification_Number) {
        this.vehicle_Identification_Number = vehicle_Identification_Number;
    }

    public String getTrouble_Codes() {
        return trouble_Codes;
    }

    public void setTrouble_Codes(String trouble_Codes) {
        this.trouble_Codes = trouble_Codes;
    }

    public String getEngine_Runtime() {
        return engine_Runtime;
    }

    public void setEngine_Runtime(String engine_Runtime) {
        this.engine_Runtime = engine_Runtime;
    }

    public float getTiming_Advance() {
        return timing_Advance;
    }

    public void setTiming_Advance(float timing_Advance) {
        this.timing_Advance = timing_Advance;
    }

    public float getControl_Module_Power_Supply() {
        return control_Module_Power_Supply;
    }

    public void setControl_Module_Power_Supply(float control_Module_Power_Supply) {
        this.control_Module_Power_Supply = control_Module_Power_Supply;
    }

    public float getSTFT1() {
        return STFT1;
    }

    public void setSTFT1(float STFT1) {
        this.STFT1 = STFT1;
    }

    public float getMass_Air_Flow() {
        return mass_Air_Flow;
    }

    public void setMass_Air_Flow(float mass_Air_Flow) {
        this.mass_Air_Flow = mass_Air_Flow;
    }

    public float getFuel_Pressure() {
        return fuel_Pressure;
    }

    public void setFuel_Pressure(float fuel_Pressure) {
        this.fuel_Pressure = fuel_Pressure;
    }

    public float getEngine_RPM() {
        return engine_RPM;
    }

    public void setEngine_RPM(float engine_RPM) {
        this.engine_RPM = engine_RPM;
    }

    public float getThrottle_Position() {
        return throttle_Position;
    }

    public void setThrottle_Position(float throttle_Position) {
        this.throttle_Position = throttle_Position;
    }

    public String getFuel_type() {
        return fuel_type;
    }

    public void setFuel_type(String fuel_type) {
        this.fuel_type = fuel_type;
    }

    public float getLTFT1() {
        return LTFT1;
    }

    public void setLTFT1(float LTFT1) {
        this.LTFT1 = LTFT1;
    }

    public float getFuel_Consumption_Rate() {
        return fuel_Consumption_Rate;
    }

    public void setFuel_Consumption_Rate(float fuel_Consumption_Rate) {
        this.fuel_Consumption_Rate = fuel_Consumption_Rate;
    }

    public float getIntake_Manifold_Pressure() {
        return intake_Manifold_Pressure;
    }

    public void setIntake_Manifold_Pressure(float intake_Manifold_Pressure) {
        this.intake_Manifold_Pressure = intake_Manifold_Pressure;
    }

    public String getDiagnostic_Trouble_Codes() {
        return diagnostic_Trouble_Codes;
    }

    public void setDiagnostic_Trouble_Codes(String diagnostic_Trouble_Codes) {
        this.diagnostic_Trouble_Codes = diagnostic_Trouble_Codes;
    }

    public float getWideband_AirFuel_Ratio() {
        return wideband_AirFuel_Ratio;
    }

    public void setWideband_AirFuel_Ratio(float wideband_AirFuel_Ratio) {
        this.wideband_AirFuel_Ratio = wideband_AirFuel_Ratio;
    }

    public float getEngine_Coolant_Temperature() {
        return engine_Coolant_Temperature;
    }

    public void setEngine_Coolant_Temperature(float engine_Coolant_Temperature) {
        this.engine_Coolant_Temperature = engine_Coolant_Temperature;
    }

    public float getAirFuel_Ratio() {
        return airFuel_Ratio;
    }

    public void setAirFuel_Ratio(float airFuel_Ratio) {
        this.airFuel_Ratio = airFuel_Ratio;
    }

    public float getBarometric_Pressure() {
        return barometric_Pressure;
    }

    public void setBarometric_Pressure(float barometric_Pressure) {
        this.barometric_Pressure = barometric_Pressure;
    }

    public float getFuel_Level() {
        return fuel_Level;
    }

    public void setFuel_Level(float fuel_Level) {
        this.fuel_Level = fuel_Level;
    }

    public float getAir_Intake_Temperature() {
        return air_Intake_Temperature;
    }

    public void setAir_Intake_Temperature(float air_Intake_Temperature) {
        this.air_Intake_Temperature = air_Intake_Temperature;
    }

    public float getFuel_Rail_Pressure() {
        return fuel_Rail_Pressure;
    }

    public void setFuel_Rail_Pressure(float fuel_Rail_Pressure) {
        this.fuel_Rail_Pressure = fuel_Rail_Pressure;
    }

    public float getLTFT2() {
        return LTFT2;
    }

    public void setLTFT2(float LTFT2) {
        this.LTFT2 = LTFT2;
    }

    @Override
    public String toString() {
        return "ObdData{" +
                "command_Equivalence_Ratio=" + command_Equivalence_Ratio +
                ", ambient_Air_Temperature=" + ambient_Air_Temperature +
                ", distance_traveled_with_MIL_on=" + distance_traveled_with_MIL_on +
                ", vehicle_Speed=" + vehicle_Speed +
                ", STFT2=" + STFT2 +
                ", engine_oil_temperature=" + engine_oil_temperature +
                ", engine_Load=" + engine_Load +
                ", vehicle_Identification_Number='" + vehicle_Identification_Number + '\'' +
                ", trouble_Codes='" + trouble_Codes + '\'' +
                ", engine_Runtime='" + engine_Runtime + '\'' +
                ", timing_Advance=" + timing_Advance +
                ", control_Module_Power_Supply=" + control_Module_Power_Supply +
                ", STFT1=" + STFT1 +
                ", mass_Air_Flow=" + mass_Air_Flow +
                ", fuel_Pressure=" + fuel_Pressure +
                ", engine_RPM=" + engine_RPM +
                ", throttle_Position=" + throttle_Position +
                ", fuel_type='" + fuel_type + '\'' +
                ", LTFT2=" + LTFT2 +
                ", fuel_Consumption_Rate=" + fuel_Consumption_Rate +
                ", intake_Manifold_Pressure=" + intake_Manifold_Pressure +
                ", diagnostic_Trouble_Codes='" + diagnostic_Trouble_Codes + '\'' +
                ", wideband_AirFuel_Ratio=" + wideband_AirFuel_Ratio +
                ", engine_Coolant_Temperature=" + engine_Coolant_Temperature +
                ", airFuel_Ratio=" + airFuel_Ratio +
                ", LTFT1=" + LTFT1 +
                ", barometric_Pressure=" + barometric_Pressure +
                ", fuel_Level=" + fuel_Level +
                ", air_Intake_Temperature=" + air_Intake_Temperature +
                ", fuel_Rail_Pressure=" + fuel_Rail_Pressure +
                ", lat=" + lat +
                ", lon=" + lon +
                ", alt=" + alt +
                '}';
    }
}

