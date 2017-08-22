package common.entities;

import java.util.ArrayList;

public class User {
    private String _username;
    private String _password;
    private String _email;
    private String _firstname;
    private String _lastname;
    private int _error;


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

    public int get_error() {
        return _error;
    }

    public void set_error(int _error) {
        this._error = _error;
    }

}