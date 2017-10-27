/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heydriversyncprocess;

import java.awt.event.KeyListener;
import java.util.Scanner;
import service.NHTSASyncThread;

/**
 *
 * @author LAPGrock
 */
public class HeyDriverSyncProcess {
    static private Thread threadSync = new Thread();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        boolean execute = true;
        Scanner keyboard = new Scanner(System.in);
        NHTSASyncThread sync = new NHTSASyncThread();
        threadSync = new Thread(sync);
        threadSync.start();
        while (execute){
            if (!NHTSASyncThread.awake){
                String input = keyboard.nextLine();
                if ("q".equals(input)) {   //ESCRIBIR Q PARA SALIR
                    threadSync.stop();
                    threadSync.destroy();
                    execute = false;
                }
            }
        }
        System.exit(0);
        
    }
    
}
