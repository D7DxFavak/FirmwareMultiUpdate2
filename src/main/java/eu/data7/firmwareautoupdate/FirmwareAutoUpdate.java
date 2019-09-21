/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.data7.firmwareautoupdate;

import java.util.LinkedList;

/**
 *
 * @author Favak-ntb
 */
public class FirmwareAutoUpdate {
    
    public static PripojeniDB pripojeniDB;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        pripojeniDB = new PripojeniDB();
        
        int rca = pripojeniDB.navazSpojeniDB(args[1], args[3], args[5]);
        
        if(rca == 1) {
            //TODO send infomail
            System.out.println("selhalo pripojeni k DB");
        }
        
        if(args[7].equals("sniffer")) {
            DeviceSniffer ds = new DeviceSniffer();
        } else if(args[7].equals("updater")) {
            DeviceUpdater du = new DeviceUpdater();
        }
       
    }
    
    
    
}
