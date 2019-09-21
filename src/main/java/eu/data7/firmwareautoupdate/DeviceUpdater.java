/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.data7.firmwareautoupdate;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import eu.data7.dbfunkce.SQLFunkceObecne;
import eu.data7.dbtridy.TridaKlientskeZarizeni;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.InetAddress;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import java.util.Properties;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

/**
 *
 * @author Favak-ntb
 */
public class DeviceUpdater {

    private List<TridaKlientskeZarizeni> arKlientZarizeni;
    private java.text.DateFormat df = java.text.DateFormat.getDateInstance();
    private JSch jsch;
    private com.jcraft.jsch.Session session;
    private List<String> commands;
    private Properties config;

    public DeviceUpdater() {
        arKlientZarizeni = new ArrayList<>();
        nacistZarizeni();
        updateZarizeni();
    }

    // Nacte seznam klientskych zarizeni na siti
    private void nacistZarizeni() {
        try {
            ResultSet q = PripojeniDB.dotazS("SELECT klientska_zarizeni_id, klientska_zarizeni_nazev, klientska_zarizeni_ip_adresa, "
                    + "klientska_zarizeni_subsit_id, klientska_zarizeni_servisni_ip, "
                    + "klientska_zarizeni_typ_id, klientska_zarizeni_verze_fw, klientska_zarizeni_datum_flash, "
                    + "klientska_zarizeni_login, klientska_zarizeni_password, klientska_zarizeni_popis, "
                    + "klientska_zarizeni_poznamky, klientska_zarizeni_ssh_port "
                    + "FROM apinfo.klientska_zarizeni "
                    + "WHERE klientska_zarizeni_id = 1");
            while (q.next()) {
                TridaKlientskeZarizeni tkz = new TridaKlientskeZarizeni();
                tkz.setId(SQLFunkceObecne.osetriCteniInt(q.getInt(1)));
                tkz.setNazev(SQLFunkceObecne.osetriCteniString(q.getString(2)));
                tkz.setAddress(SQLFunkceObecne.osetriCteniString(q.getString(3)));
                tkz.setSubsit(SQLFunkceObecne.osetriCteniString(q.getString(4)));
                //TODO servisni IP 5
                tkz.setIdTypZarizeni(SQLFunkceObecne.osetriCteniInt(q.getInt(6)));
                tkz.setVerzeFwAktualni(SQLFunkceObecne.osetriCteniString(q.getString(7)));
                if (!SQLFunkceObecne.osetriCteniString(q.getString(8)).isEmpty()) {
                    tkz.setDatumFlashFW(df.parse(SQLFunkceObecne.osetriCteniString(q.getString(8))));
                } else {
                    tkz.setDatumFlashFW(null);
                }
                tkz.setLogin(SQLFunkceObecne.osetriCteniString(q.getString(9)));
                tkz.setPassword(SQLFunkceObecne.osetriCteniString(q.getString(10)));
                //TODO popis 11
                //TODO poznamky 12
                tkz.setSshPort(SQLFunkceObecne.osetriCteniInt(q.getInt(13)));
                System.out.println("Pridano 1");
                arKlientZarizeni.add(tkz);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Nacte informace o aktualnich firmwarech z databaze
    private String nacistInfoAktualni(int idTypZarizeni) {
        //TODO dodelat nacitani z DB
        return "6.45.5";
    }

    //updatuje jednotliva zarizeni
    private void updateZarizeni() {
        for (TridaKlientskeZarizeni tkz : arKlientZarizeni) {
            if (!tkz.getVerzeFwAktualni().equals(nacistInfoAktualni(tkz.getIdTypZarizeni()))) {
                boolean settingInProgress = false;
                boolean reachable;
                if (ftpAktivace(tkz, true)) {
                    nahratFirmware(tkz);
                    try {
                        reachable = false;
                        Thread.sleep(2000);
                        while (!reachable) {
                            InetAddress address = InetAddress.getByName(tkz.getAddress());
                            reachable = address.isReachable(4);
                            Thread.sleep(500);
                        }
                       // System.out.println(tkz.getAddress() + " Reachable : " + reachable);
                        updateFirmware(tkz);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                try {
                    reachable = false;
                    Thread.sleep(2000);
                    while (!reachable) {
                        InetAddress address = InetAddress.getByName(tkz.getAddress());
                        reachable = address.isReachable(4);
                        Thread.sleep(500);
                    }
                    if (ftpAktivace(tkz, false)) {

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

    private boolean ftpAktivace(TridaKlientskeZarizeni tkz, boolean aktivovat) {
        ChannelExec channel = null;
        boolean vysledek = false;

        try {
            //settingInProgress = true;
            jsch = new JSch();
            session = jsch.getSession(tkz.getLogin(), tkz.getAddress(), tkz.getSshPort());
            config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.setPassword(tkz.getPassword());
            session.connect();
            if (aktivovat) {
                channel = (ChannelExec) session.openChannel("exec");
                channel.setCommand("ip service enable ftp");
                channel.connect();
                Thread.sleep(1000);
                channel.disconnect();
            } else {
                channel = (ChannelExec) session.openChannel("exec");
                channel.setCommand("ip service disable ftp");
                channel.connect();
                Thread.sleep(1000);
                channel.disconnect();
            }
            vysledek = true;
        } catch (Exception e) {
            vysledek = false;
            e.printStackTrace();
        } finally {
            channel.disconnect();
            session.disconnect();
            return vysledek;
            // settingInProgress = false;
        }
    }

    private void nahratFirmware(TridaKlientskeZarizeni tkz) {
        String fwSoubor = "";
        try {
            ResultSet q = PripojeniDB.dotazS("SELECT typ_zarizeni_soubor_fw FROM apinfo.typ_zarizeni WHERE typ_zarizeni_id = " + tkz.getIdTypZarizeni());
            while (q.next()) {
                fwSoubor = SQLFunkceObecne.osetriCteniString(q.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        File firmwareLocal = new File(fwSoubor);
        // File firmwareLocal = new File("routeros-mipsbe-6.45.5.npk");
        System.out.println("Soubor : " + firmwareLocal.getName());

        String server = tkz.getAddress();
        int port = tkz.getSshPort();
        String uzivatel = tkz.getLogin();
        String heslo = tkz.getPassword();
        ChannelExec channel = null;
        FTPClient mikrotikFTP = new FTPClient();
        try {
            // settingInProgress = true;
            mikrotikFTP.connect(server, 21);
            mikrotikFTP.login(uzivatel, heslo);
            mikrotikFTP.enterLocalActiveMode();

            mikrotikFTP.setFileType(FTP.BINARY_FILE_TYPE);

            String firmwareRemote = firmwareLocal.getName();
            InputStream inputStream = new FileInputStream(firmwareLocal);

            boolean done = mikrotikFTP.storeFile(firmwareRemote, inputStream);
            inputStream.close();
            Thread.sleep(1000);
            jsch = new JSch();
            session = jsch.getSession(uzivatel, server, port);
            //commands = nactiPrikazy();
            session.setPassword(heslo);
            config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();
            channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand("system reboot");
            channel.connect();
            Thread.sleep(1000);
            channel.disconnect();
            /* channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand("y");
            channel.connect();
            Thread.sleep(1000);*/

            //settingInProgress = false;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            channel.disconnect();
            session.disconnect();
        }
    }

    private void updateFirmware(TridaKlientskeZarizeni tkz) {
        ChannelExec channel = null;
        try {
            // settingInProgress = true;
            jsch = new JSch();
            session = jsch.getSession(tkz.getLogin(), tkz.getAddress(), tkz.getSshPort());
            // commands = nactiPrikazy();
            //session.setPassword(heslo);
            config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.setPassword(tkz.getPassword());
            session.connect();
            channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand("system routerboard upgrade");
            channel.connect();
            Thread.sleep(1000);
            channel.disconnect();
            channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand("system reboot");
            channel.connect();
            Thread.sleep(1000);
            /*channel.disconnect();
            schannel = (ChannelExec) session.openChannel("exec");
            channel.setCommand("y");
            channel.connect();
            Thread.sleep(1000);*/

            //settingInProgress = false;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            channel.disconnect();
            session.disconnect();
        }
    }
}
