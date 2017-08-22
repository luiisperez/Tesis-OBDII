package com.app.heydriver.heydriver.common.Entities;


import com.google.gson.annotations.SerializedName;

public class Car {
    private String _serial;
    private String _brand;
    private String _model;
    private int _year;
    private int _error;

    /**
     * Constructor vacio
     */
    public Car(){ }

    public Car(String _serial, String _brand, String _model, int _year){
        this._serial = _serial;
        this._brand = _brand;
        this._model = _model;
        this._year = _year;
    }



    public String get_serial() {
        return _serial;
    }

    public void set_serial(String _serial) {
        this._serial = _serial;
    }

    public String get_brand() {
        return _brand;
    }

    public void set_brand(String _brand) {
        this._brand = _brand;
    }

    public String get_model() {
        return _model;
    }

    public void set_model(String _model) {
        this._model = _model;
    }

    public int get_year() {
        return _year;
    }

    public void set_year(int _year) {
        this._year = _year;
    }

    public int get_error() {
        return _error;
    }

    public void set_error(int _error) {
        this._error = _error;
    }
}
