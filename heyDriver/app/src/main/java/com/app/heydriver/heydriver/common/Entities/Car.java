package com.app.heydriver.heydriver.common.Entities;


public class Car {
    private String _licensePlate;
    private String _brand;
    private String _model;

    /**
     * Constructor vacio
     */
    public Car(){ }

    public Car(String _licensePlate, String _brand, String _model){
        this._licensePlate = _licensePlate;
        this._brand = _brand;
        this._model = _model;
    }
}
