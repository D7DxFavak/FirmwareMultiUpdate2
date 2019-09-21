/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.data7.dbtridy;

import eu.data7.dbfunkce.SQLFunkceObecne;
import eu.data7.dbfunkce.SQLFunkceObecne2;
import eu.data7.dbfunkce.TextFunkce1;
import eu.data7.firmwareautoupdate.PripojeniDB;
import java.sql.ResultSet;
import java.util.Date;

/**
 *
 * @author Favak-ntb
 */
public class TridaKlientskeZarizeni {

    private long id;
    private String nazev;
    private String address;
    private int sshPort;
    private String subsit;
    private int idSubsit;
    private String ipAccessPoint;
    private int idTypZarizeni;
    private String nazevTypZarizeni;
    private String verzeFwAktualni;
    private Date datumFlashFW;
    private String login;
    private String password;
    private String popis;

    public boolean selectData(int id) {
        try {
            ResultSet q = PripojeniDB.dotazS("SELECT vykresy_id, vykresy_cislo, vykresy_revize, vykresy_poznamky, "
                    + "vykresy_nazev, vykresy_zakaznik_id, vykresy_je_realny "
                    + "FROM spolecne.vykresy "
                    + "WHERE vykresy_id = " + id);
            q.last();
            if (q.getRow() == 1) {
                q.first();

            }
            return true;
        } catch (Exception e) {
            PripojeniDB.vyjimkaS(e);
            return false;
        } finally {
            PripojeniDB.zavriPrikaz();

        }
    }

    public long insertData() {
        try {
            int rc = 0;
            rc = SQLFunkceObecne2.spustPrikaz("BEGIN");
            ResultSet id = PripojeniDB.dotazS("SELECT COALESCE(MAX(klientska_zarizeni_id),0) FROM apinfo.klientska_zarizeni");
            while (id.next()) {
                this.id = id.getInt(1) + 1;
            }
            if(this.nazev.isEmpty() || this.nazev == null) {
                this.nazev = "Chybejici jmeno";
            }
            //int pocet_mj = Integer.valueOf(PocetKusuTextField1.getText().trim()).intValue();
            int a = PripojeniDB.dotazIUD("INSERT INTO apinfo.klientska_zarizeni( "
                    + "klientska_zarizeni_id, klientska_zarizeni_nazev, klientska_zarizeni_ip_adresa,  "
                    + "klientska_zarizeni_subsit_id, klientska_zarizeni_servisni_ip,  "
                    + "klientska_zarizeni_typ_id, klientska_zarizeni_verze_fw, klientska_zarizeni_datum_flash,  "
                    + "klientska_zarizeni_login, klientska_zarizeni_password, klientska_zarizeni_popis,  "
                    + "klientska_zarizeni_poznamky, klientska_zarizeni_ssh_port) "
                    + "VALUES (" + this.id + "," + TextFunkce1.osetriZapisTextDB1(this.nazev) + ", " + TextFunkce1.osetriZapisTextDB1(this.address) + ", "
                    + (this.idSubsit) + ", null, "
                    + this.idTypZarizeni + ", " + TextFunkce1.osetriZapisTextDB1(this.verzeFwAktualni) + ", null, "
                    + TextFunkce1.osetriZapisTextDB1(this.login) + ", " + TextFunkce1.osetriZapisTextDB1(this.password) + ", null, null, " + this.sshPort + ")");
            //SQLFunkceObecne2.update("UPDATE apinfo.subsit SET nactena_update = TRUE WHERE id = " + this.idSubsit);
            rc = SQLFunkceObecne2.spustPrikaz("COMMIT");
        } catch (Exception e) {
            int rc = SQLFunkceObecne2.spustPrikaz("ROLLBACK");
            e.printStackTrace();
        }
        return this.id;
    }

    public int updateData() {
        int rc = 0;
        try {
            rc = SQLFunkceObecne2.spustPrikaz("BEGIN");
            String dotaz = "UPDATE apinfo.klientska_zarizeni "
                    + "SET klientska_zarizeni_nazev = " + TextFunkce1.osetriZapisTextDB1(this.nazev) + ", "
                    + "klientska_zarizeni_ip_adresa = " + TextFunkce1.osetriZapisTextDB1(this.address) + ", "
                    + "klientska_zarizeni_ssh_port = " + this.sshPort + ", "
                    + "klientska_zarizeni_subsit_id = " + this.idSubsit + ", "
                    + "klientska_zarizeni_typ_id = " + this.idTypZarizeni + ", "
                    + "klientska_zarizeni_verze_fw= " + TextFunkce1.osetriZapisTextDB1(this.verzeFwAktualni) + ", ";
            if (this.datumFlashFW != null) {
                dotaz += "klientska_zarizeni_datum_flash= " + TextFunkce1.osetriZapisDatumDB1(this.datumFlashFW) + ", ";
            }
            dotaz += "klientska_zarizeni_login = " + TextFunkce1.osetriZapisTextDB1(this.login) + ", "
                    + "klientska_zarizeni_password = " + TextFunkce1.osetriZapisTextDB1(this.password) + ", "
                    + "klientska_zarizeni_popis = " + TextFunkce1.osetriZapisTextDB1(this.popis) + " "
                    + "WHERE klientska_zarizeni_id = " + this.id;

            rc = PripojeniDB.dotazIUD(dotaz);
            
            rc = SQLFunkceObecne2.spustPrikaz("COMMIT");
            
        } catch (Exception e) {
            e.printStackTrace();
            rc = SQLFunkceObecne2.spustPrikaz("ROLLBACK");
        } finally {
            return rc;
        }
    }

    public void deleteData() {
        String dotaz = "DELETE FROM apinfo.klientska_zarizeni WHERE klientska_zarizeni_id = " + this.id;
        try {
            int a = PripojeniDB.dotazIUD(dotaz);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the nazev
     */
    public String getNazev() {
        return nazev;
    }

    /**
     * @param nazev the nazev to set
     */
    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the subsit
     */
    public String getSubsit() {
        return subsit;
    }

    /**
     * @param subsit the subsit to set
     */
    public void setSubsit(String subsit) {
        this.subsit = subsit;
    }

    /**
     * @return the ipAccessPoint
     */
    public String getIpAccessPoint() {
        return ipAccessPoint;
    }

    /**
     * @param ipAccessPoint the ipAccessPoint to set
     */
    public void setIpAccessPoint(String ipAccessPoint) {
        this.ipAccessPoint = ipAccessPoint;
    }

    /**
     * @return the idTypZarizeni
     */
    public int getIdTypZarizeni() {
        return idTypZarizeni;
    }

    /**
     * @param idTypZarizeni the idTypZarizeni to set
     */
    public void setIdTypZarizeni(int idTypZarizeni) {
        this.idTypZarizeni = idTypZarizeni;
    }

    /**
     * @return the nazevTypZarizeni
     */
    public String getNazevTypZarizeni() {
        return nazevTypZarizeni;
    }

    /**
     * @param nazevTypZarizeni the nazevTypZarizeni to set
     */
    public void setNazevTypZarizeni(String nazevTypZarizeni) {
        this.nazevTypZarizeni = nazevTypZarizeni;
    }

    /**
     * @return the verzeFwAktualni
     */
    public String getVerzeFwAktualni() {
        return verzeFwAktualni;
    }

    /**
     * @param verzeFwAktualni the verzeFwAktualni to set
     */
    public void setVerzeFwAktualni(String verzeFwAktualni) {
        this.verzeFwAktualni = verzeFwAktualni;
    }

    /**
     * @return the datumFlashFW
     */
    public Date getDatumFlashFW() {
        return datumFlashFW;
    }

    /**
     * @param datumFlashFW the datumFlashFW to set
     */
    public void setDatumFlashFW(Date datumFlashFW) {
        this.datumFlashFW = datumFlashFW;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the idSubsit
     */
    public int getIdSubsit() {
        return idSubsit;
    }

    /**
     * @param idSubsit the idSubsit to set
     */
    public void setIdSubsit(int idSubsit) {
        this.idSubsit = idSubsit;
    }

    /**
     * @return the port
     */
    public int getSshPort() {
        return sshPort;
    }

    /**
     * @param sshPort the port to set
     */
    public void setSshPort(int sshPort) {
        this.sshPort = sshPort;
    }

}
