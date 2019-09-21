/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.data7.firmwareautoupdate;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import eu.data7.dbfunkce.SQLFunkceObecne;
import eu.data7.dbfunkce.SQLFunkceObecne2;
import eu.data7.dbfunkce.TextFunkce1;
import eu.data7.dbtridy.TridaKlientskeZarizeni;
import eu.data7.tridy.DvojiceRetezRetez;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author Favak-ntb
 */
public class DeviceSniffer {

    private List<DvojiceRetezRetez> arAktivniSubsite;
    private List<DvojiceRetezRetez> arAktivniSubsite1;
    private List<DvojiceRetezRetez> arAktivniSubsite2;
    private List<DvojiceRetezRetez> arAktivniSubsite3;
    private List<TridaKlientskeZarizeni> arKlientZarizeni;
    private DvojiceRetezRetez drr;
    private String defaultLogin;
    private String defaultPass;
    private int[] defaultSSHPorts;
    private JSch jsch;
    private com.jcraft.jsch.Session session;
    private Properties config;

    public DeviceSniffer() {
        arAktivniSubsite = new ArrayList<>();
        arAktivniSubsite1 = new ArrayList<>();
        arAktivniSubsite2 = new ArrayList<>();
        arAktivniSubsite3 = new ArrayList<>();
        arKlientZarizeni = new ArrayList<>();
        naplnSeznamAktivniSite();        
        defaultLogin = "admin";
        defaultPass = "testicek";
        defaultSSHPorts = new int[]{22, 65022, 65222};

        Thread t = new Thread() {
            @Override
            public void run() {             
                najdiMikrotikZarizeni(arAktivniSubsite);
            }
        };
       
        t.start();      

        try {
            t.join();           
        } catch (Exception e) {
            e.printStackTrace();
        }      
    }

    private void naplnSeznamAktivniSite() {
        arAktivniSubsite.clear();
        boolean switching = false;
        int mod = 0;
        try {
            String dotaz
                    = "SELECT adresa_site, nazev_klienti "
                    + "FROM apinfo.subsit "
                    + "CROSS JOIN apinfo.klient "
                    + "CROSS JOIN apinfo.vazba "
                    + "WHERE klient.aktivni = true "
                    + "AND klient.id = vazba.id_klient "
                    + "AND vazba.id = subsit.id_vazba "
                    + "AND nactena_update = FALSE "
                    + "ORDER BY adresa_site ASC ";

            ResultSet q = PripojeniDB.dotazS(dotaz);
            while (q.next()) {
                drr = new DvojiceRetezRetez(SQLFunkceObecne.osetriCteniString(q.getString(1)), SQLFunkceObecne.osetriCteniString(q.getString(2)));              
                arAktivniSubsite.add(drr);
            }// konec while           
        } // konec try
        catch (Exception e) {
            e.printStackTrace();
            PripojeniDB.vyjimkaS(e);
        } // konec catch              
    }

    private void najdiMikrotikZarizeni(List<DvojiceRetezRetez> arZpracovatSubsite) {

        for (DvojiceRetezRetez drrTemp : arZpracovatSubsite) {
            boolean found = false;           
            if (drrTemp.getRetez1().contains("/")) {
                int subnet = Integer.valueOf(drrTemp.getRetez1().substring(drrTemp.getRetez1().indexOf("/") + 1));
                String networkIP = drrTemp.getRetez1().substring(0, drrTemp.getRetez1().indexOf("/"));
                int ipLastByte = Integer.valueOf(networkIP.substring(networkIP.lastIndexOf(".") + 1)) + 1;
                String accessPointIP = networkIP.substring(0, networkIP.lastIndexOf(".") + 1) + ipLastByte;
                int maxAddIp;
                switch (subnet) {
                    case (30): {
                        maxAddIp = 1;
                        break;
                    }
                    case (29): {
                        maxAddIp = 5;
                        break;
                    }
                    case (28): {
                        maxAddIp = 13;
                        break;
                    }
                    case (27): {
                        maxAddIp = 29;
                        break;
                    }
                    default: {
                        maxAddIp = 0;
                        break;
                    }
                }
                for (int i = 1; i < maxAddIp + 1; i++) {
                    ipLastByte++;
                    String currIp = networkIP.substring(0, networkIP.lastIndexOf(".") + 1) + ipLastByte;
                    for (int j = 0; j < defaultSSHPorts.length; j++) {
                        try {
                            jsch = new JSch();
                            session = jsch.getSession(defaultLogin, currIp, defaultSSHPorts[j]); //TODO dodelat ostatni                  
                            config = new Properties();
                            config.put("StrictHostKeyChecking", "no");
                            session.setConfig(config);
                            session.setPassword(defaultPass);
                            session.setTimeout(4000);
                            //System.out.println("Logging to : " + currIp + " port : " + defaultSSHPorts[j]);
                            session.connect();

                            ChannelExec channel = (ChannelExec) session.openChannel("exec");
                            BufferedReader in = new BufferedReader(new InputStreamReader(channel.getInputStream()));
                            channel.setCommand("system resource print");
                            channel.connect();
                            if (channel.isConnected()) {
                                TridaKlientskeZarizeni tkz = new TridaKlientskeZarizeni();
                                tkz.setAddress(currIp);
                                tkz.setNazev(drrTemp.getRetez2());
                                tkz.setLogin(defaultLogin);
                                tkz.setPassword(defaultPass);
                                tkz.setSubsit(drrTemp.getRetez1());
                                tkz.setIpAccessPoint(accessPointIP);
                                tkz.setSshPort(defaultSSHPorts[j]);
                                String msg = null;
                                while ((msg = in.readLine()) != null) {
                                    if (msg.contains("version:")) {
                                        if (msg.contains("(")) {
                                            msg = msg.substring(0, msg.indexOf("("));
                                        }
                                        tkz.setVerzeFwAktualni(msg.substring(msg.indexOf("version:") + 9));
                                    }
                                    if (msg.contains("architecture-name:")) {
                                        tkz.setNazevTypZarizeni(msg.substring(msg.indexOf("architecture-name:") + 19));
                                        tkz.setIdTypZarizeni(SQLFunkceObecne2.selectINTPole("SELECT typ_zarizeni_id FROM apinfo.typ_zarizeni WHERE typ_zarizeni_nazev = " + TextFunkce1.osetriZapisTextDB1(tkz.getNazevTypZarizeni())));
                                    }
                                }
                                i = maxAddIp;
                                j = defaultSSHPorts.length;                                
                                arKlientZarizeni.add(tkz);
                                zpracujMikrotikZarizeni(tkz);
                            }

                            channel.disconnect();
                            session.disconnect();

                        } catch (java.net.ConnectException e) {
                            System.out.println("Nelze se spojit " + currIp);
                            e.printStackTrace();
                            //TODO dodelat chybu
                        } catch (com.jcraft.jsch.JSchException e) {
                            System.out.println("Spojeni predcasne ukonceno " + currIp);
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    int idSubsit = SQLFunkceObecne2.selectINTPole("SELECT id FROM apinfo.subsit WHERE adresa_site = " + TextFunkce1.osetriZapisTextDB1(drrTemp.getRetez1()));
                    SQLFunkceObecne2.update("UPDATE apinfo.subsit SET nactena_update = TRUE WHERE id = " + idSubsit);

                }
            }
        }
    }

    private void zpracujMikrotikZarizeni() {
        for (TridaKlientskeZarizeni arKlientZarizeni1 : arKlientZarizeni) {
            int idSubsit = SQLFunkceObecne2.selectINTPole("SELECT id FROM apinfo.subsit WHERE adresa_site = " + TextFunkce1.osetriZapisTextDB1(arKlientZarizeni1.getSubsit()));
            arKlientZarizeni1.setIdSubsit(idSubsit);
            if (SQLFunkceObecne2.selectBooleanPole(
                    "SELECT EXISTS (SELECT klientska_zarizeni_id FROM apinfo.klientska_zarizeni WHERE klientska_zarizeni_subsit_id = " + idSubsit + ")") == true) {
                arKlientZarizeni1.setId(SQLFunkceObecne2.selectINTPole("SELECT klientska_zarizeni_id FROM apinfo.klientska_zarizeni WHERE klientska_zarizeni_subsit_id = " + idSubsit));
                arKlientZarizeni1.updateData();
            } else {
                arKlientZarizeni1.insertData();
            }
        }

    }

    private void zpracujMikrotikZarizeni(TridaKlientskeZarizeni arKlientZarizeni1) {
        int idSubsit = SQLFunkceObecne2.selectINTPole("SELECT id FROM apinfo.subsit WHERE adresa_site = " + TextFunkce1.osetriZapisTextDB1(arKlientZarizeni1.getSubsit()));
        arKlientZarizeni1.setIdSubsit(idSubsit);
        if (SQLFunkceObecne2.selectBooleanPole(
                "SELECT EXISTS (SELECT klientska_zarizeni_id FROM apinfo.klientska_zarizeni WHERE klientska_zarizeni_subsit_id = " + idSubsit + ")") == true) {
            arKlientZarizeni1.setId(SQLFunkceObecne2.selectINTPole("SELECT klientska_zarizeni_id FROM apinfo.klientska_zarizeni WHERE klientska_zarizeni_subsit_id = " + idSubsit));
            arKlientZarizeni1.updateData();
        } else {
            arKlientZarizeni1.insertData();
        }

    }

}
