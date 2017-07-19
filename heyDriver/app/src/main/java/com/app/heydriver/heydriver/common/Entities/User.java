package com.app.heydriver.heydriver.common.Entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by LuisAlejandro on 18-07-2017.
 */

public class User {
    @SerializedName("_id")
    private int _idUser;
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



    /**
     * Constructor vacio
     */
    public User(){ }

    public User(int idUser){ }

    public User(int idUser, String username, String password){
        _idUser=idUser;
        _username=username;
        _password=password;
    }

    public User(String username, String password){
        _username=username;
        _password=password;
    }

    public User(int idUser, String username, String password,String email){
        _idUser=idUser;
        _username=username;
        _password=password;
        _email=email;
    }

    public User(int idUser, String email){
        _idUser=idUser;
        _email=email;
    }

    public User(String email){
        _email=email;
    }

    public User(int idUser, String username, String password,String email, String phone,
                char sex, String birthdate){
        _idUser=idUser;
        _username=username;
        _password=password;
        _email=email;
        _phone=phone;
        _sex=sex;
        _birthdate=birthdate;
    }



    public User(int _idUser, String _username, String _email, String _phone, char _sex, String _birthdate) {
        this._idUser = _idUser;
        this._username = _username;
        this._email = _email;
        this._phone = _phone;
        this._sex = _sex;
        this._birthdate = _birthdate;
    }

    public int get_idUser() {
        return _idUser;
    }

    public void set_idUser(int _idUser) {
        this._idUser = _idUser;
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

}