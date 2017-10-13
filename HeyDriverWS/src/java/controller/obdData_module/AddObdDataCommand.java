package controller.obdData_module;

import common.entities.ObdData;
import controller.Command;
import model.obdData_module.DAOObdData;

/**
 *
 * @author cristian
 */
public class AddObdDataCommand extends Command{
        
    private ObdData obd_data_ToAdd;
    private int code;
    
    public AddObdDataCommand(ObdData obd_data_ToAdd){
        this.obd_data_ToAdd = obd_data_ToAdd;
    }
    
    public int getResponseCode(){
        return this.code;
    }

    @Override
    public void execute() throws Exception{
        try{
            DAOObdData dao = new DAOObdData();
            code = dao.create(obd_data_ToAdd);
        }catch (Exception ex){
            throw ex;
        }
        
    }
    
}
