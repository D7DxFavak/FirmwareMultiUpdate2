// SQL Funkce Obecne
package eu.data7.dbfunkce;

import eu.data7.tridy.TrojiceRetezCisloBool;
import eu.data7.tridy.DvojiceCisloRetez;
import eu.data7.tridy.DvojiceCisloBool;
import eu.data7.tridy.DvojiceCisloCislo;
import eu.data7.tridy.DvojiceRetezBool;
import eu.data7.tridy.Znacka1;
import java.sql.*;
import java.util.*;
import eu.data7.firmwareautoupdate.PripojeniDB;
//import java.lang.*;

public class SQLFunkceObecne {

    private static Vector v;
    private static Integer i;
    private static DvojiceCisloRetez dv;
    private static DvojiceCisloCislo dcc;
    private static DvojiceCisloBool dcb;
    private static DvojiceRetezBool drb;
    private static TrojiceRetezCisloBool trcb;
    private static Znacka1 zn;
    private static java.text.DateFormat df = java.text.DateFormat.getDateInstance();

//Metoda vracejici vektor DvojicCisloCislo ciselnych poli,
//pri chybe vraci vektor s jednim null objektem nasledovany Integer objektem s chybovym kodem
    public static synchronized Vector getVCislaCisla(String pole1, String pole2, String jmenoTabulky, String podminka) {
        v = new Vector();
        try {
            ResultSet q = PripojeniDB.prikaz.executeQuery(
                    "SELECT " + pole1 + ", " + pole2
                    + " FROM " + jmenoTabulky
                    + " " + podminka);
            q.last();
            if (q.getRow() > 0) {
                q.beforeFirst();
                while (q.next()) {
                    dcc = new DvojiceCisloCislo(q.getInt(1), q.getInt(2));
                    v.addElement(dcc);
                }// konec while
            } // konec if
        } // konec try
        catch (Exception e) {
            int ch = PripojeniDB.vyjimkaS(e);
            if (ch == 0) {
                ch = 10000;
            }
            v.removeAllElements();
            v.add(null);
            v.add(new Integer(ch));
        } // konec catch
/*    finally
        {
        PripojeniDB.zavriPrikaz();
        return v;
        } // konec finally
         */
        return v;
    } //konec getVCislaCisla

//Metoda vracejici vektor DvojicCislaBoolean
//pri chybe vraci vektor s jednim null objektem nasledovany Integer objektem s chybovym kodem
    public static synchronized Vector getVCislaBoolean(String poleCislo, String poleBoolean, String jmenoTabulky, String podminka) {
        v = new Vector();
        try {
            ResultSet q = PripojeniDB.prikaz.executeQuery(
                    "SELECT " + poleCislo + ", " + poleBoolean
                    + " FROM " + jmenoTabulky
                    + " " + podminka);
            q.last();
            if (q.getRow() > 0) {
                q.beforeFirst();
                while (q.next()) {
                    dcb = new DvojiceCisloBool(q.getInt(1), q.getBoolean(2));
                    v.addElement(dcb);
                }// konec while
            } // konec if
        } // konec try
        catch (Exception e) {
            int ch = PripojeniDB.vyjimkaS(e);
            if (ch == 0) {
                ch = 10000;
            }
            v.removeAllElements();
            v.add(null);
            v.add(new Integer(ch));
        } // konec catch
/*    finally
        {
        Pripojeni.zavriPrikaz();
        return v;
        } // konec finally
         */
        return v;
    } //konec getVCislaBoolean

//Metoda vracejici vektor DvojicRetezBoolean
//pri chybe vraci vektor s jednim null objektem nasledovany Integer objektem s chybovym kodem
    public static synchronized Vector getVTextBoolean(String poleText, String poleBoolean, String jmenoTabulky, String podminka) {
        v = new Vector();
        try {
            ResultSet q = PripojeniDB.prikaz.executeQuery(
                    "SELECT " + poleText + ", " + poleBoolean
                    + " FROM " + jmenoTabulky
                    + " " + podminka);
            q.last();
            if (q.getRow() > 0) {
                q.beforeFirst();
                while (q.next()) {
                    drb = new DvojiceRetezBool(q.getString(1), q.getBoolean(2));
                    v.addElement(drb);
                }// konec while
            } // konec if
        } // konec try
        catch (Exception e) {
            int ch = PripojeniDB.vyjimkaS(e);
            if (ch == 0) {
                ch = 10000;
            }
            v.removeAllElements();
            v.add(null);
            v.add(new Integer(ch));
        } // konec catch
/*    finally
        {
        Pripojeni.zavriPrikaz();
        return v;
        } // konec finally
         */
        return v;
    } //konec getVTextBoolean

//Metoda vracejici vektor TrojicRetezCisloBoolean
//pri chybe vraci vektor s jednim null objektem nasledovany Integer objektem s chybovym kodem
    public static synchronized Vector getVTextCisloBoolean(String poleText, String poleCislo, String poleBoolean, String jmenoTabulky, String podminka) {
        v = new Vector();
        try {
            ResultSet q = PripojeniDB.prikaz.executeQuery(
                    "SELECT " + poleText + ", " + poleCislo + ", " + poleBoolean
                    + " FROM " + jmenoTabulky
                    + " " + podminka);
            q.last();
            if (q.getRow() > 0) {
                q.beforeFirst();
                while (q.next()) {
                    trcb = new TrojiceRetezCisloBool(q.getString(1), q.getInt(2), q.getBoolean(3));
                    v.addElement(trcb);
                }// konec while
            } // konec if
        } // konec try
        catch (Exception e) {
            int ch = PripojeniDB.vyjimkaS(e);
            if (ch == 0) {
                ch = 10000;
            }
            v.removeAllElements();
            v.add(null);
            v.add(new Integer(ch));
        } // konec catch
/*    finally
        {
        PripojeniDB.zavriPrikaz();
        return v;
        } // konec finally
         */
        return v;
    } //konec getVTextCisloBoolean

    //Metoda vracejici vektor DvojicRetezCislo ciselnych poli a nazvu 
//pri chybe vraci vektor s jednim null objektem nasledovany Integer objektem s chybovym kodem
    public static Vector getVCislaNazvy(String poleID, String jmenoTabulky, String razeni) {
        return getVCislaText(poleID, "nazev", jmenoTabulky, "ORDER BY " + razeni);
    } //konec getVCislaNazvy

//Metoda vracejici vektor DvojicRetezCislo ciselnych poli a textu 
//pri chybe vraci vektor s jednim null objektem nasledovany Integer objektem s chybovym kodem
    public static synchronized Vector getVCislaText(String poleCislo, String poleText, String jmenoTabulky, String podminka) {
        v = new Vector();
        try {
            ResultSet q = PripojeniDB.prikaz.executeQuery(
                    "SELECT " + poleCislo + ", " + poleText
                    + " FROM " + jmenoTabulky
                    + " " + podminka);
            q.last();
            if (q.getRow() > 0) {
                q.beforeFirst();
                while (q.next()) {
                    dv = new DvojiceCisloRetez(q.getInt(1), q.getString(2));
                    v.addElement(dv);
                }// konec while
            } // konec if
        } // konec try
        catch (Exception e) {
            int ch = PripojeniDB.vyjimkaS(e);
            if (ch == 0) {
                ch = 10000;
            }
            v.removeAllElements();
            v.add(null);
            v.add(new Integer(ch));
        } // konec catch
/*    finally
        {
        PripojeniDB.zavriPrikaz();
        return v;
        } // konec finally
         */
        return v;
    } //konec getVCislaText

//Metoda vracejici vektor cisel z tabulky jmenoTabulky s podminkou podminka
//pri chybe vraci vektor s jednim null objektem nasledovany Integer objektem s chybovym kodem
    public static synchronized Vector getVCisla(String poleCislo, String jmenoTabulky, String podminka) {
        v = new Vector();
        try {
            ResultSet q = PripojeniDB.prikaz.executeQuery(
                    "SELECT " + poleCislo
                    + " FROM " + jmenoTabulky
                    + " " + podminka);
            q.last();
            if (q.getRow() > 0) {
                q.beforeFirst();
                while (q.next()) {
                    v.addElement(new Integer(q.getInt(1)));
                }// konec while
            } // konec if
        } // konec try
        catch (Exception e) {
            int ch = PripojeniDB.vyjimkaS(e);
            if (ch == 0) {
                ch = 10000;
            }
            v.removeAllElements();
            v.add(null);
            v.add(new Integer(ch));
        } // konec catch
/*    finally
        {
        PripojeniDB.zavriPrikaz();
        return v;
        } // konec finally
         */
        return v;
    } //konec getVCisla

//Metoda vracejici vektor textovych poli z tabulky jmenoTabulky s podminkou podminka
//pri chybe vraci vektor s jednim null objektem nasledovany Integer objektem s chybovym kodem
    public static synchronized Vector getVText(String poleText, String jmenoTabulky, String podminka) {
        v = new Vector();
        String s;
        try {
            ResultSet q = PripojeniDB.prikaz.executeQuery(
                    "SELECT " + poleText
                    + " FROM " + jmenoTabulky
                    + " " + podminka);
            q.last();
            if (q.getRow() > 0) {
                q.beforeFirst();
                while (q.next()) {
                    s = q.getString(1);
                    if (q.wasNull()) {
                        s = "";
                    }
                    v.addElement(s);
                }// konec while
            } // konec if
        } // konec try
        catch (Exception e) {
            int ch = PripojeniDB.vyjimkaS(e);
            if (ch == 0) {
                ch = 10000;
            }
            v.removeAllElements();
            v.add(null);
            v.add(new Integer(ch));
        } // konec catch
/*    finally
        {
        PripojeniDB.zavriPrikaz();
        return v;
        } // konec finally
         */
        return v;
    } //konec getVText

//Obecny SELECT textove pole z tabulky tabulka s podminkou podminka
    //prazdny retezec pri NULL nebo pri chybe
    //retezec pri uspechu (muze byt i prazdny, pokud je takto zadan)
    public static synchronized String selectTXTPole(String tabulka, String pole, String podminka) {
        String navrat = "";
        try {
            System.out.println("dotaz : " + "SELECT " + pole + " FROM " + tabulka
                    + " WHERE " + podminka);
            ResultSet q = PripojeniDB.prikaz.executeQuery(
                    "SELECT " + pole + " FROM " + tabulka
                    + " WHERE " + podminka);
            q.last();
            if (q.getRow() == 1) {
                q.first();
                navrat = q.getString(1);
                if (q.wasNull()) {
                    navrat = "";
                }
            } // konec if q.getRow() == 1
        } // konec try
        catch (Exception e) {
            PripojeniDB.vyjimkaS(e);
        } // konec catch
/*    finally
        {
        PripojeniDB.zavriPrikaz();
        return navrat;
        }//konec finally
         */
        return navrat;
    } //konec selectTXTPole

//Obecny SELECT textove pole pomoci dotazu dotaz
    //prazdny retezec pri NULL nebo pri chybe
    //retezec pri uspechu (muze byt i prazdny, pokud je takto zadan)
    public static synchronized String selectTXTPole(String dotaz) {
        System.out.println("selectTXTPole : SELECT " + dotaz);
        String navrat = "";
        try {
            ResultSet q = PripojeniDB.prikaz.executeQuery(
                    "SELECT " + dotaz);
            q.last();
            if (q.getRow() == 1) {
                q.first();
                navrat = q.getString(1);
                if (q.wasNull()) {
                    navrat = "";
                }
            } // konec if q.getRow() == 1
        } // konec try
        catch (Exception e) {
            e.printStackTrace();
            PripojeniDB.vyjimkaS(e);
        } // konec catch
/*    finally
        {
        PripojeniDB.zavriPrikaz();
        return navrat;
        }//konec finally
         */
        return navrat;
    } //konec selectTXTPole

//Obecny SELECT pole datum z tabulky tabulka s podminkou podminka
    //prazdny retezec pri NULL nebo pri chybe
    //retezec pri uspechu (muze byt i prazdny, pokud je takto zadan)
    //datum bude zformatovano podle narodnich zvyklosti dane zeme
    public static synchronized String selectDatumPole(String tabulka, String pole, String podminka) {
        java.text.DateFormat df = java.text.DateFormat.getDateInstance();
        java.util.Date datum;
        String navrat = "";
        try {
            ResultSet q = PripojeniDB.prikaz.executeQuery(
                    "SELECT " + pole + " FROM " + tabulka
                    + " WHERE " + podminka);
            q.last();
            if (q.getRow() == 1) {
                q.first();
                datum = q.getDate(1);
                if (q.wasNull()) {
                    navrat = "";
                } else {
                    navrat = df.format(datum);
                }
            } // konec if q.getRow() == 1
        } // konec try
        catch (Exception e) {
            PripojeniDB.vyjimkaS(e);
        } // konec catch
/*    finally
        {
        PripojeniDB.zavriPrikaz();
        return navrat;
        }//konec finally
         */
        return navrat;
    } //konec selectDatumPole

    //Obecny SELECT textovych poli pole z tabulky tabulka s podminkou podminka
    //vektor retezcu pri uspechu (retezec ve vektoru muze byt i prazdny, pokud je takto zadan nebo je retezec null)
    public static synchronized Vector selectVTXTPole(String tabulka, String pole, String podminka) {
        v = new Vector();
        String s;
        int pocetSloupcu;
        try {
            ResultSet q = PripojeniDB.prikaz.executeQuery(
                    "SELECT " + pole + " FROM " + tabulka
                    + " WHERE " + podminka);
            q.last();
            pocetSloupcu = q.getMetaData().getColumnCount();
            if (q.getRow() == 1) {
                q.first();
                for (int i = 1; i <= pocetSloupcu; i++) {
                    s = q.getString(i);
                    if (q.wasNull()) {
                        s = "";
                    }
                    v.add(s);
                }//konec for
            } // konec if q.getRow() == 1
        } // konec try
        catch (Exception e) {
            PripojeniDB.vyjimkaS(e);
            v.removeAllElements();
        } // konec catch
/*    finally
        {
        PripojeniDB.zavriPrikaz();
        return v;
        }//konec finally
         */
        return v;
    } //konec selectVTXTPole

    //Obecny SELECT objektu pole z tabulky tabulka s podminkou podminka
    //vektor objektu pri uspechu
    public static synchronized Vector selectVPole(String tabulka, String pole, String podminka) {
        v = new Vector();
        int pocetSloupcu;
        try {
            ResultSet q = PripojeniDB.prikaz.executeQuery(
                    "SELECT " + pole + " FROM " + tabulka
                    + " WHERE " + podminka);
            q.last();
            pocetSloupcu = q.getMetaData().getColumnCount();
            if (q.getRow() == 1) {
                q.first();
                for (int i = 1; i <= pocetSloupcu; i++) {
                    v.add(q.getObject(i));
                }//konec for
            } // konec if q.getRow() == 1
        } // konec try
        catch (Exception e) {
            PripojeniDB.vyjimkaS(e);
            v.removeAllElements();
        } // konec catch
/*    finally
        {
        PripojeniDB.zavriPrikaz();
        return v;
        }//konec finally
         */
        return v;
    } //konec selectVPole

    //Obecny SELECT vektoru vektoru z tabulky tabulka s podminkou podminka
    //vektor vektoru objektu pri uspechu
    public static synchronized Vector selectVVektoru(String tabulka, String pole, String podminka) {
        Vector navrat = new Vector();
        int pocetSloupcu;
        try {
            ResultSet q = PripojeniDB.prikaz.executeQuery(
                    "SELECT " + pole + " FROM " + tabulka
                    + " WHERE " + podminka);
            pocetSloupcu = q.getMetaData().getColumnCount();
            q.last();
            if (q.getRow() > 0) {
                q.beforeFirst();
                while (q.next()) {
                    v = new Vector();
                    for (int i = 1; i <= pocetSloupcu; i++) {
                        v.add(q.getObject(i));
                    }
                    navrat.add(v);
                }//konec while
            } // konec if q.getRow() > 0
        } // konec try
        catch (Exception e) {
            PripojeniDB.vyjimkaS(e);
            navrat.removeAllElements();
        } // konec catch
        finally {
            PripojeniDB.zavriPrikaz();
            return navrat;
        }//konec finally
    } //konec selectVVektoru

//Obecny SELECT integer hodnoty z tabulky tabulka s podminkou podminka
    //navraci 0 pri NULL nebo pri chybe
    //cislo pri uspechu (muze byt i 0, pokud je takto zadano v tabulce)
    public static synchronized int selectINTPole(String tabulka, String pole, String podminka) {
        int navrat = 0;
        try {
            ResultSet q = PripojeniDB.prikaz.executeQuery(
                    "SELECT " + pole + " FROM " + tabulka
                    + " WHERE " + podminka);
            q.last();
            if (q.getRow() == 1) {
                q.first();
                navrat = q.getInt(1);
                if (q.wasNull()) {
                    navrat = 0;
                }
            } // konec if q.getRow() == 1
        } // konec try
        catch (Exception e) {
            PripojeniDB.vyjimkaS(e);
        } // konec catch
        finally {
            PripojeniDB.zavriPrikaz();
            return navrat;
        }//konec finally
    } //konec selectINTPole

//Obecny SELECT boolean hodnoty z tabulky tabulka s podminkou podminka
    //navraci false pri NULL nebo pri chybe
    //hodnotu pri uspechu (muze byt i false, pokud je takto zadano v tabulce)
    public static synchronized boolean selectBooleanPole(String tabulka, String pole, String podminka) {
        boolean navrat = false;
        try {
            ResultSet q = PripojeniDB.prikaz.executeQuery(
                    "SELECT " + pole + " FROM " + tabulka
                    + " WHERE " + podminka);
            q.last();
            if (q.getRow() == 1) {
                q.first();
                navrat = q.getBoolean(1);
                if (q.wasNull()) {
                    navrat = false;
                }
            } // konec if q.getRow() == 1
        } // konec try
        catch (Exception e) {
            PripojeniDB.vyjimkaS(e);
        } // konec catch
/*    finally
        {
        PripojeniDB.zavriPrikaz();
        return navrat;
        }//konec finally
         */
        return navrat;
    } //konec selectBooleanPole

//Obecny SELECT boolean hodnoty pomoci dotazu dotaz
    //navraci false pri NULL nebo pri chybe
    //hodnotu pri uspechu (muze byt i false, pokud je takto zadano v tabulce)
    public static synchronized boolean selectBooleanPole(String dotaz) {
        boolean navrat = false;
        try {
            System.out.println("selectBooleanPole : " + "SELECT " + dotaz);
            ResultSet q = PripojeniDB.prikaz.executeQuery(
                    "SELECT " + dotaz);
            q.last();
            if (q.getRow() == 1) {
                q.first();
                navrat = q.getBoolean(1);
                if (q.wasNull()) {
                    navrat = false;
                }
            } // konec if q.getRow() == 1
        } // konec try
        catch (Exception e) {
            PripojeniDB.vyjimkaS(e);
        } // konec catch
/*    finally
        {
        PripojeniDB.zavriPrikaz();
        return navrat;
        }//konec finally
         */
        return navrat;
    } //konec selectBooleanPole

    //vrati timestamp ze serveru v java.sql.Timestamp, pokud chyba nebo NULL, tak navrat 0
    public static synchronized java.sql.Timestamp getTimestamp(String dotaz) {
        java.sql.Timestamp navrat = new java.sql.Timestamp(0);
        try {
            ResultSet q = PripojeniDB.prikaz.executeQuery(
                    "SELECT " + dotaz);
            q.last();
            if (q.getRow() == 1) {
                q.first();
                navrat = q.getTimestamp(1);
                if (q.wasNull()) {
                    navrat = new java.sql.Timestamp(0);
                }//konec if q.wasNull
            } // konec if q.getRow() == 1
        } // konec try
        catch (Exception e) {
            PripojeniDB.vyjimkaS(e);
        } // konec catch
/*    finally
        {
        PripojeniDB.zavriPrikaz();
        return navrat;
        }//konec finally
         */
        return navrat;
    } //konec getTimestamp

    //vrati soucasny timestamp ze serveru v textovem retezci
    public static synchronized String getCurrentTimestamp() {
        String navrat = "";
        try {
            ResultSet q = PripojeniDB.prikaz.executeQuery(
                    "SELECT to_char(current_timestamp,'DD.MM.YYYY HH24:MI:SS')");
            q.last();
            if (q.getRow() == 1) {
                q.first();
                navrat = q.getString(1);
                if (q.wasNull()) {
                    navrat = "";
                }//konec if q.wasNull
            } // konec if q.getRow() == 1
        } // konec try
        catch (Exception e) {
            PripojeniDB.vyjimkaS(e);
        } // konec catch
/*    finally
        {
        PripojeniDB.zavriPrikaz();
        return navrat;
        }//konec finally
         */
        return navrat;
    } //konec getCurrentTimestamp

    //vrati datum ze serveru v textovem formatu
    public static synchronized String getCurrentDate() {
        java.util.Date datum = null;
        String navrat = "";
        try {
            ResultSet q = PripojeniDB.prikaz.executeQuery(
                    "SELECT current_date");
            q.last();
            if (q.getRow() == 1) {
                q.first();
                datum = q.getDate(1);
                if (!q.wasNull()) {
                    navrat = df.format(datum);
                }//konec if q.wasNull
            } // konec if q.getRow() == 1
        } // konec try
        catch (Exception e) {
            PripojeniDB.vyjimkaS(e);
        } // konec catch
/*    finally
        {
        PripojeniDB.zavriPrikaz();
        return navrat;
        }//konec finally
         */
        return navrat;
    } //konec getCurrentDate

    //vrati jmeno aktualne pripojeneho uzivatele ze serveru
    public static synchronized String getCurrentUser() {
        String navrat = "";
        try {
            ResultSet q = PripojeniDB.prikaz.executeQuery(
                    "SELECT current_user");
            q.last();
            if (q.getRow() == 1) {
                q.first();
                navrat = q.getString(1);
                if (q.wasNull()) {
                    navrat = "";
                }
            } // konec if q.getRow() == 1
        } // konec try
        catch (Exception e) {
            PripojeniDB.vyjimkaS(e);
        } // konec catch
/*    finally
        {
        PripojeniDB.zavriPrikaz();
        return navrat;
        }//konec finally
         */
        return navrat;
    } //konec getCurrentUser

//vloz hodnoty do tabulky tabulka s poli pole
    //vraci 10000 pri fatalni chybe,
    //0 pri uspechu
    //a jinak chybovy kod
    public static synchronized int insertPoleHodnoty(String tabulka, String pole, String hodnoty) {
        System.out.println(
                "INSERT INTO " + tabulka + "(" + pole + ")"
                + " VALUES (" + hodnoty + ")");
        try {
            PripojeniDB.prikaz.executeUpdate(
                    "INSERT INTO " + tabulka + "(" + pole + ")"
                    + " VALUES (" + hodnoty + ")");
        } // konec try
        catch (Exception e) {
            int vu = PripojeniDB.vyjimkaIUD(e);
            PripojeniDB.zavriPrikaz();
            if (vu == 0) {
                return 10000;
            } else {
                return vu;
            }
        } // konec catch
//    PripojeniDB.zavriPrikaz();
        return 0;
    } //konec insertPoleHodnoty

//update tabulku tabulka s vyrazem vyraz za podminky podminka
    //vraci 10000 pri fatalni chybe,
    //0 pri uspechu
    //a jinak chybovy kod
    public static synchronized int updateHodnoty(String tabulka, String vyraz, String podminka) {
        try {
            System.out.println("UPDATE " + tabulka + " SET " + vyraz
                    + " WHERE " + podminka);
            PripojeniDB.prikaz.executeUpdate(
                    "UPDATE " + tabulka + " SET " + vyraz
                    + " WHERE " + podminka);
        } // konec try
        catch (Exception e) {
            int vu = PripojeniDB.vyjimkaIUD(e);
//    PripojeniDB.zavriPrikaz();
            if (vu == 0) {
                return 10000;
            } else {
                return vu;
            }
        } // konec catch
//    PripojeniDB.zavriPrikaz();
        return 0;
    } //konec updateHodnoty

//vloz dve integer hodnoty id a hodnota do tabulky tabulka
    //vraci 10000 pri fatalni chybe,
    //0 pri uspechu
    //a jinak chybovy kod
    public static synchronized int insertDInt(String tabulka, int id, int hodnota) {
        try {
            PripojeniDB.prikaz.executeUpdate(
                    "INSERT INTO " + tabulka
                    + " VALUES (" + id + ", " + hodnota + ")");
        } // konec try
        catch (Exception e) {
            int vu = PripojeniDB.vyjimkaIUD(e);
//    PripojeniDB.zavriPrikaz();
            if (vu == 0) {
                return 10000;
            } else {
                return vu;
            }
        } // konec catch
//    PripojeniDB.zavriPrikaz();
        return 0;
    } //konec insertDInt

//obecny delete v tabulce
    //vraci 10000 pri fatalni chybe,
    //0 pri uspechu
    //a jinak chybovy kod
    public static synchronized int delete(String tabulka, String podminka) {
        try {
            PripojeniDB.prikaz.executeUpdate(
                    "DELETE FROM " + tabulka
                    + " WHERE " + podminka);
        } // konec try
        catch (Exception e) {
            int vu = PripojeniDB.vyjimkaIUD(e);
//    PripojeniDB.zavriPrikaz();
            if (vu == 0) {
                return 10000;
            } else {
                return vu;
            }
        } // konec catch
//    PripojeniDB.zavriPrikaz();
        return 0;
    } //konec delete

//vymaz dve pojmenovane integer hodnoty id a hodnota z tabulky tabulka
    //vraci 10000 pri fatalni chybe,
    //0 pri uspechu
    //a jinak chybovy kod
    public static synchronized int deleteDInt(String tabulka, String sID, int id, String sHodnota, int hodnota) {
        try {
            PripojeniDB.prikaz.executeUpdate(
                    "DELETE FROM " + tabulka
                    + " WHERE ((" + sID + " = " + id + ") AND (" + sHodnota + " = " + hodnota + "))");
        } // konec try
        catch (Exception e) {
            int vu = PripojeniDB.vyjimkaIUD(e);
//    PripojeniDB.zavriPrikaz();
            if (vu == 0) {
                return 10000;
            } else {
                return vu;
            }
        } // konec catch
//    PripojeniDB.zavriPrikaz();
        return 0;
    } //konec deleteDInt

//vymaz radek s identifikatorem identifikator a  id = id z tabulky tabulka
    //vraci 10000 pri fatalni chybe,
    //0 pri uspechu
    //a jinak chybovy kod
    public static synchronized int deleteRadekID(String tabulka, String identifikator, int id) {
        try {
            PripojeniDB.prikaz.executeUpdate(
                    "DELETE FROM " + tabulka
                    + " WHERE (" + identifikator + " = " + id + ")");
        } // konec try
        catch (Exception e) {
            int vu = PripojeniDB.vyjimkaIUD(e);
//    PripojeniDB.zavriPrikaz();
            if (vu == 0) {
                return 10000;
            } else {
                return vu;
            }
        } // konec catch
//    PripojeniDB.zavriPrikaz();
        return 0;
    } //konec deleteRadekID

//vraci pocet z tabulky tabulka
//s podminkou podminka
    public static synchronized int getPocet(String tabulka, String podminka) {
        int pocet = 0;
        try {
            ResultSet q = PripojeniDB.prikaz.executeQuery(
                    "SELECT count(*) FROM " + tabulka
                    + " " + podminka);
            q.first();
            pocet = q.getInt(1);
        } // konec try
        catch (Exception e) {
            PripojeniDB.vyjimkaS(e);
        } // konec catch
        finally {
//    PripojeniDB.zavriPrikaz();
            return pocet;
        } // konec finally
    } //konec getPocet

//vraci boolean hodnotu indikujici autorizaci pro typ nad danou tabulkou
    public static synchronized boolean overAutorizaci(String tabulka, String typ) {
        boolean navrat = false;
        try {
            ResultSet q = PripojeniDB.prikaz.executeQuery(
                    "SELECT has_table_privilege ('" + tabulka + "', '" + typ + "')");
            q.first();
            navrat = q.getBoolean(1);
        } // konec try
        catch (Exception e) {
            PripojeniDB.vyjimkaS(e);
        } // konec catch
        finally {
//    PripojeniDB.zavriPrikaz();
            return navrat;
        } // konec finally
    } //konec overAutorizaci

//Metoda vracejici chybovou hlasku
    public static synchronized String getCHybovouHlasku(int chybaID) {
        String vysledek = "Došlo k nespecifikované chybě při dotazu na databázový server!";
        System.out.println("Chyba id " + chybaID);
        try {
            ResultSet q = PripojeniDB.prikaz.executeQuery(
                    "SELECT nazev, popis, mozna_reseni"
                    + " FROM chybove_kody"
                    + " WHERE chyba_id = " + chybaID);
            q.last();
            if (q.getRow() == 1) {
                q.first();
                String nazev = q.getString(1);
                String popis = q.getString(2);
                String moznaReseni = q.getString(3);
                vysledek = nazev + "\n" + popis + "\n" + moznaReseni;
            } // konec if q.getRow() == 1
        } // konec try
        catch (Exception e) {
            PripojeniDB.vyjimkaS(e);
//    PripojeniDB.zavriPrikaz();
        } // konec catch
        finally {
//    PripojeniDB.zavriPrikaz();
            return (vysledek);
        } // konec finally
    } //konec getChybovouHlasku

//vola ulozenou proceduru navracejici boolean
    public static synchronized boolean overPole(String funkce, String pole) {
        //System.out.println("SQLFunkceObecne : " +  "SELECT " + funkce + "('" + pole + "')");
        boolean proslo = false;
        try {
            //  System.out.println("overpole : "+ "SELECT " + funkce + "('" + pole + "')");
            ResultSet q = PripojeniDB.prikaz.executeQuery(
                    "SELECT " + funkce + "('" + pole + "')");
            q.first();
            proslo = q.getBoolean(1);
            // System.out.println("proslo : " + proslo);
        } // konec try
        catch (Exception e) {
            PripojeniDB.vyjimkaS(e);
        } // konec catch
        finally {
//    PripojeniDB.zavriPrikaz();
            return proslo;
        } // konec finally
    } //konec overPole

//testuje, zda - li je mozne nastavit pole na urcitou hodnotu
//vraci true pri uspechu
//false pri chybe
    public static synchronized boolean testPole(String pole, String hodnota) {
//    System.out.println("dotaz je = " + "UPDATE testiky " +
//      "SET " + pole.trim() + " = " + hodnota.trim() +
//      " WHERE t_id = 1");
        boolean retc = false;
        int r = 0;
        try {
            r = PripojeniDB.prikaz.executeUpdate(
                    "UPDATE testiky "
                    + "SET " + pole.trim() + " = " + hodnota.trim()
                    + " WHERE t_id = 1");
            if (r == 1) {
                retc = true;
            }
        } // konec try
        catch (Exception e) {
//    e.printStackTrace();
//    PripojeniDB.zavriPrikaz();
            return retc;
        } // konec catch
//    PripojeniDB.zavriPrikaz();
        return retc;
    } //konec testPole

//osetri cteni retezce, pokud je nacten null, tak vrati prazdny retezec
    public static synchronized String osetriCteniString(String nacteno) {
        if (nacteno == null) {
            return "";
        } else {
            return nacteno.trim();
        }
    }//konec osetriCteniString  

    //osetreni cteni cisla, pokud nacte null, tak vrati 0
    public static synchronized int osetriCteniInt(int nacteno) {
        Integer i = new Integer(nacteno);
        if (i == null) {
            return 0;
        } else {
            return nacteno;
        }
    }//konec osetriCteniInt

    //osetri vstup retezcu, pokud je prazdny, tak je navrat retezec null bez apostrofu,
    //jinak vraci retezec v apostrofech
    public static synchronized String osetriVstupString(String vstup) {
        if (vstup == null || vstup.equalsIgnoreCase("")) {
            vstup = null;
            return vstup;
        }
        return ("'" + vstup.trim() + "'");
    }//konec osetriVstupString

    //vraci null pro vstup int = 0, jinak retezec s hodnotou
    public static synchronized String osetriVstupInt(int vstup) {
        String vystup;
        if (vstup == 0) {
            vystup = null;
            return vystup;
        }
        return ("" + vstup + "");
    }//konec osetriVstupInt

//vraci znacku pomoci funkce funkce a identifikatoru id
    public static synchronized Znacka1 getZnacku(String funkce, int id) {
        zn = new Znacka1("neznámý nebo prázdný", "neznámý nebo prázdný", "neznámý nebo prázdný", "neznámý nebo prázdný");
        try {
            ResultSet q = PripojeniDB.prikaz.executeQuery(
                    "SELECT * FROM " + funkce + "(" + id + ")");
            q.last();
            if (q.getRow() == 1) {
                q.first();
                String kv = q.getString("kv");
                String kz = q.getString("kz");
                String kdv = q.getString("kdv");
                String kdz = q.getString("kdz");
                zn = new Znacka1(kv, kz, kdv, kdz);
            } // konec if
        } // konec try
        catch (Exception e) {
            PripojeniDB.vyjimkaS(e);
        } // konec catch
        finally {
//    PripojeniDB.zavriPrikaz();
            return zn;
        } // konec finally
    } //konec getZnacku

    //Metoda pro aktualizaci hesla uzivatele uzivatel
    //vraci 0 pri uspechu, jinak chybovy kod
    public static synchronized int updateHesloUzivatele(String uzivatel, String heslo) {
        int navrat = 0;

        if (uzivatel.equalsIgnoreCase("")) {
            return 10000;
        }

        try {
            boolean b = PripojeniDB.prikaz.execute(
                    "ALTER USER " + uzivatel
                    + " WITH ENCRYPTED PASSWORD " + osetriVstupString(heslo));
        } // konec try
        catch (Exception e) {
            navrat = PripojeniDB.vyjimkaIUD(e);
            if (navrat == 0) {
                navrat = 10000;
            }
        } // konec catch
/*    finally
        {
        PripojeniDB.zavriPrikaz();
        return navrat;
        }//konec finally
         */
        return navrat;
    } //konec updateHesloUzivatele

    //Metoda pro aktualizaci hesla aktualne prihlaseneho uzivatele
    //vraci 0 pri uspechu, jinak chybovy kod
    public static synchronized int updateHesloAktualnihoUzivatele(String heslo) {
        return updateHesloUzivatele(getCurrentUser(), heslo);
    } //konec updateHesloAktualnihoUzivatele

    //vrati seznam uzivatelu ve skupine skupina
    //retezec cisel(UID uzivatelu) oddeleny carkami
    public static synchronized String getSeznamUzivateluSkupiny(String skupina) {
        String navrat = "";
        try {
            ResultSet q = PripojeniDB.prikaz.executeQuery(
                    "SELECT grolist FROM pg_group WHERE groname = " + osetriVstupString(skupina));
            q.last();
            if (q.getRow() == 1) {
                q.first();
                navrat = q.getString(1);
                if (q.wasNull()) {
                    navrat = "";
                }
            } // konec if q.getRow() == 1
        } // konec try
        catch (Exception e) {
            PripojeniDB.vyjimkaS(e);
        } // konec catch
        finally {
//    PripojeniDB.zavriPrikaz();
            return navrat.replaceAll("\\{", "").replaceAll("\\}", "");
        }//konec finally
    } //konec getSeznamUzivateluSkupiny

//Metoda spoustejici prikaz - vraci 0 pri uspechu, jinak chybovy kod
    public static synchronized int spustPrikaz(String prikaz) {
        int navrat = 10000;
        try {
            boolean b = PripojeniDB.prikaz.execute(
                    prikaz);
            navrat = 0;
        } // konec try
        catch (Exception e) {
            navrat = PripojeniDB.vyjimkaS(e);
            if (navrat == 0) {
                navrat = 10000;
            }
        } // konec catch
/*    finally
        {
        PripojeniDB.zavriPrikaz();
        return navrat;
        }//konec finally
         */
        return navrat;
    } //konec spustPrikaz
}