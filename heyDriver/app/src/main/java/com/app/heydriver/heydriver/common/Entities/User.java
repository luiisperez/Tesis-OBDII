package com.app.heydriver.heydriver.common.Entities;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by LuisAlejandro on 18-07-2017.
 */

public class User {
    @SerializedName("_username")
    private String _username;
    @SerializedName("_password")
    private String _password;
    @SerializedName("_email")
    private String _email;
    @SerializedName("_phone")
    private String _phone;
    @SerializedName("_sex")
    private char _sex;
    @SerializedName("_birthdate")
    private String _birthdate;
    @SerializedName("_carsList")
    private ArrayList<Car> _carsList;


    /**
     * Constructor vacio
     */
    public User(){ }

    public User(String email, String password){
        _email = email;
        _password = password;
    }

    public User(String username, String password, String email){
        _username = username;
        _password = password;
        _email = email;
    }

    public User(String username, String password,String email, String phone,
                char sex, String birthdate){
        _username = username;
        _password = password;
        _email = email;
        _phone = phone;
        _sex = sex;
        _birthdate = birthdate;
    }



    public User(String _username, String _email, String _phone, char _sex, String _birthdate, ArrayList<Car> _carsList) {
        this._username = _username;
        this._email = _email;
        this._phone = _phone;
        this._sex = _sex;
        this._birthdate = _birthdate;
        this._carsList = _carsList;
    }

    public String get_username() {
        return _username;
    }

    public void set_username(String _username) {
        this._username = _username;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public String get_phone() {
        return _phone;
    }

    public void set_phone(String _phone) {
        this._phone = _phone;
    }

    public char get_sex() {
        return _sex;
    }

    public void set_sex(char _sex) {
        this._sex = _sex;
    }

    public String get_birthdate() {
        return _birthdate;
    }

    public void set_birthdate(String _birthdate) {
        this._birthdate = _birthdate;
    }

    public ArrayList<Car> get_carsList() {
        return _carsList;
    }

    public void set_carsList(ArrayList<Car> _carsList) {
        this._carsList = _carsList;
    }



}