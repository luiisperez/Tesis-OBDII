/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.users_module;

import common.entities.User;
import controller.Command;
import model.users_module.DAOUser;

/**
 *
 * @author LAPGrock
 */
public class LoginCommand extends Command{
    
    private User userToLogin;
    private User response;
    
    public LoginCommand(User _userToLogin){
        this.userToLogin = _userToLogin;
    }
    
    public User getResponse(){
        return this.response;
    }

    @Override
    public void execute() throws Exception{
        try{
            DAOUser dao = new DAOUser();
            response = dao.login(userToLogin);
        }catch (Exception ex){
            throw ex;
        }
        
    }
    
}

