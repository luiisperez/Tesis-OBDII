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
public class SignUpCommand extends Command{
    
    private User userToInsert;
    private User response;
    
    public SignUpCommand(User _userToInsert){
        this.userToInsert = _userToInsert;
    }
    
    public User getResponse(){
        return this.response;
    }

    @Override
    public void execute() throws Exception{
        try{
            DAOUser dao = new DAOUser();
            response = dao.create(userToInsert);
        }catch (Exception ex){
            throw ex;
        }
        
    }
    
}
