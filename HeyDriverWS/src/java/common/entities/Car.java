package common.entities;

public class Car {
    private String _serial;
    private String _brand;
    private String _model;

    /**
     * Constructor vacio
     */
    public Car(){ }
    
    public Car(String _serial, String _brand, String _model){
        this._serial = _serial;
        this._brand = _brand;
        this._model = _model;
    }
}
