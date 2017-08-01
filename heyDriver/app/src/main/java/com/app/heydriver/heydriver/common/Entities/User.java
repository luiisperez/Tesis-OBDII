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
    @SerializedName("_firstname")
    private String _firstname;
    @SerializedName("_lastname")
    private String _lastname;
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

    public User(String username, String password,String email, String firstname, String lastname){
        _username = username;
        _password = password;
        _email = email;
        _firstname = firstname;
        _lastname = lastname;
    }



    public User(String _username, String _email, String _firstname, String _lastname, ArrayList<Car> _carsList) {
        this._username = _username;
        this._email = _email;
        this._firstname = _firstname;
        this._lastname = _lastname;
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

    public String get_firstname() {
        return _firstname;
    }

    public void set_firstname(String _firstname) {
        this._firstname = _firstname;
    }

    public String get_lastname() {
        return _lastname;
    }

    public void set_lastname(String _lastname) {
        this._lastname = _lastname;
    }

    public ArrayList<Car> get_carsList() {
        return _carsList;
    }

    public void set_carsList(ArrayList<Car> _carsList) {
        this._carsList = _carsList;
    }

}