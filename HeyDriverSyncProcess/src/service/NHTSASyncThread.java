/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import controller.cars_module.SyncNHTSADataCommand;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    static public boolean awake = false;
    static public int i = 0;
    @Override
    public void run() {
        while (execute_thread){
            try {
                Thread.sleep(30000); //CANTIDAD DE TIEMPO ENTRE CADA SINCRONIZACION CON EL SERVIDOR
                awake = true;
                DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

                LocalDateTime now = LocalDateTime.now();
                System.out.println("SINCRONIZACION INICIADA, POR FAVOR NO CERRAR EL PROCESO. FECHA Y HORA DE INICIO: " + dtf.format(now));
                SyncNHTSADataCommand sync = new SyncNHTSADataCommand();
                sync.execute();
                sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

                now = LocalDateTime.now();
                System.out.println("SINCRONIZACION COMPLETADA. FECHA Y HORA DE CULMINACION: " + dtf.format(now));
                awake = false;
            } catch (Exception ex) {

                DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

                LocalDateTime now = LocalDateTime.now();
                System.out.println(dtf.format(now));
                System.out.println("SINCRONIZACION FALLIDA. FECHA Y HORA DE CULMINACION: " + dtf.format(now));
                awake = false;
            }
        }
        try {
            this.finalize();
        } catch (Throwable ex) {
            Logger.getLogger(NHTSASyncThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
