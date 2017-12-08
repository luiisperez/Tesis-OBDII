package com.app.heydriver.heydriver.common.Entities;

/**
 * Created by Cristian on 5/12/2017.
 */

public class PredictionRead {
    private String _car;
    private String _model;
    private String _serial;
    private String _prediction;
    private String _desc;

    public PredictionRead() {
    }

    public String get_car() {
        return _car;
    }

    public void set_car(String _car) {
        this._car = _car;
    }

    public String get_model() {
        return _model;
    }

    public void set_model(String _model) {
        this._model = _model;
    }

    public String get_serial() {
        return _serial;
    }

    public void set_serial(String _serial) {
        this._serial = _serial;
    }

    public String get_prediction() {
        return _prediction;
    }

    public void set_prediction(String _prediction) {
        this._prediction = _prediction;
    }

    public String get_desc() {
        return _desc;
    }

    public void set_desc(String _desc) {
        this._desc = _desc;
    }
}
