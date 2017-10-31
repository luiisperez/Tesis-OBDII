package com.app.heydriver.heydriver.common.Entities;

/**
 * Created by crist on 31/10/2017.
 */

public class TroubleCode {
    private String _car;
    private String _serial;
    private String _dtc;
    private String _desc;

    public TroubleCode() {
    }

    public String get_car() {
        return _car;
    }

    public void set_car(String _car) {
        this._car = _car;
    }

    public String get_serial() {
        return _serial;
    }

    public void set_serial(String _serial) {
        this._serial = _serial;
    }

    public String get_dtc() {
        return _dtc;
    }

    public void set_dtc(String _dtc) {
        this._dtc = _dtc;
    }

    public String get_desc() {
        return _desc;
    }

    public void set_desc(String _desc) {
        this._desc = _desc;
    }
}
