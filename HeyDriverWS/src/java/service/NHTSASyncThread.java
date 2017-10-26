/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LAPGrock
 */
public class NHTSASyncThread implements Runnable{
    
    static public boolean execute_thread = true;
    static public int i = 0;
    @Override
    public void run() {
        while (execute_thread){
            try {
                Thread.sleep(30000); //CANTIDAD DE TIEMPO ENTRE CADA SINCRONIZACION CON EL SERVIDOR
                System.out.println(i);
                i = i + 30;
            } catch (InterruptedException ex) {
                Logger.getLogger(NHTSASyncThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            this.finalize();
        } catch (Throwable ex) {
            Logger.getLogger(NHTSASyncThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
