// SQL Funkce Obecne

package eu.data7.dbfunkce;

import eu.data7.tridy.TrojiceRetezCisloBool;
import eu.data7.tridy.CtvericeCisloCisloCisloCislo;
import eu.data7.tridy.Znacka1;
import eu.data7.tridy.TrojiceCisloCisloCislo;
import eu.data7.tridy.DvojiceRetezBool;
import eu.data7.tridy.DvojiceCisloRetez;
import eu.data7.tridy.DvojiceCisloCislo;
import eu.data7.tridy.DvojiceCisloBool;
import java.sql.*;
import java.util.*;
import eu.data7.firmwareautoupdate.PripojeniDB;
//import java.lang.*;

public class SQLFunkceObecne2 {
    
 private static Vector v;
 private static Integer i;
 private static DvojiceCisloRetez dvojiceCR;
 private static DvojiceCisloCislo dvojiceCC;
 private static TrojiceCisloCisloCislo trojiceCCC;
 private static CtvericeCisloCisloCisloCislo ctvericeCCCC;
 private static DvojiceCisloBool dvojiceCB;
 private static DvojiceRetezBool dvojiceRB;
 private static TrojiceRetezCisloBool trojiceRCB;
 private static Znacka1 zn;
 private static java.text.DateFormat df = java.text.DateFormat.getDateInstance();

 
//Metoda vracejici vektor DvojicCisloCislo ciselnych poli,
//pri chybe vraci vektor s jednim null objektem nasledovany Integer objektem s chybovym kodem
//Datove typy jednotlivych polozek musi odpovidat datovym typum poli tridy
//Dotaz musi byt typu SELECT
  public static synchronized Vector getVDvojicCisloCislo(String dotaz) {
    v = new Vector();
    try {
      ResultSet q = PripojeniDB.dotazS(dotaz);
      q.last();
      if(q.getRow() > 0) {
       q.beforeFirst();
       while (q.next()) {
        dvojiceCC = new DvojiceCisloCislo(q.getInt(1), q.getInt(2));
        v.addElement(dvojiceCC);
       }// konec while
      } // konec if
     } // konec try
    catch (Exception e) {
    int ch = PripojeniDB.vyjimkaS(e);
    if(ch == 0)
     ch = 10000;
    v.removeAllElements();
    v.add(null);
    v.add(new Integer(ch));
    } // konec catch

    return v;
  } //konec getVDvojicCisloCislo


//Metoda vracejici vektor DvojicCislaBoolean
//pri chybe vraci vektor s jednim null objektem nasledovany Integer objektem s chybovym kodem
//Datove typy jednotlivych polozek musi odpovidat datovym typum poli tridy
//Dotaz musi byt typu SELECT
  public static synchronized Vector getVDvojicCisloBoolean(String dotaz) {
    v = new Vector();
    try {
      ResultSet q = PripojeniDB.dotazS(dotaz);
      q.last();
      if(q.getRow() > 0) {
       q.beforeFirst();
       while (q.next()) {
        dvojiceCB = new DvojiceCisloBool(q.getInt(1), q.getBoolean(2));
        v.addElement(dvojiceCB);
       }// konec while
      } // konec if
     } // konec try
    catch (Exception e) {
    int ch = PripojeniDB.vyjimkaS(e);
    if(ch == 0)
     ch = 10000;
    v.removeAllElements();
    v.add(null);
    v.add(new Integer(ch));
    } // konec catch

    return v;
  } //konec getVDvojicCisloBoolean

  
  
//Metoda vracejici vektor DvojicRetezBoolean
//pri chybe vraci vektor s jednim null objektem nasledovany Integer objektem s chybovym kodem
//Datove typy jednotlivych polozek musi odpovidat datovym typum poli tridy
//Dotaz musi byt typu SELECT
  public static synchronized Vector getVDvojicRetezBoolean(String dotaz) {
    v = new Vector();
    try {
      ResultSet q = PripojeniDB.dotazS(dotaz);
      q.last();
      if(q.getRow() > 0) {
       q.beforeFirst();
       while (q.next()) {
        dvojiceRB = new DvojiceRetezBool(q.getString(1), q.getBoolean(2));
        v.addElement(dvojiceRB);
       }// konec while
      } // konec if
     } // konec try
    catch (Exception e) {
    int ch = PripojeniDB.vyjimkaS(e);
    if(ch == 0)
     ch = 10000;
    v.removeAllElements();
    v.add(null);
    v.add(new Integer(ch));
    } // konec catch

    return v;
  } //konec getVDvojicRetezBoolean
  

//Metoda vracejici vektor TrojicRetezCisloBoolean
//pri chybe vraci vektor s jednim null objektem nasledovany Integer objektem s chybovym kodem
//Datove typy jednotlivych polozek musi odpovidat datovym typum poli tridy
//Dotaz musi byt typu SELECT
  public static synchronized Vector getVTrojicRetezCisloBoolean(String dotaz) {
    v = new Vector();
    try {
      ResultSet q = PripojeniDB.dotazS(dotaz);
      q.last();
      if(q.getRow() > 0) {
       q.beforeFirst();
       while (q.next()) {
        trojiceRCB = new TrojiceRetezCisloBool(q.getString(1), q.getInt(2), q.getBoolean(3));
        v.addElement(trojiceRCB);
       }// konec while
      } // konec if
     } // konec try
    catch (Exception e) {
    int ch = PripojeniDB.vyjimkaS(e);
    if(ch == 0)
     ch = 10000;
    v.removeAllElements();
    v.add(null);
    v.add(new Integer(ch));
    } // konec catch

    return v;
  } //konec getVTrojicRetezCisloBoolean

  
//Metoda vracejici vektor DvojicCisloRetez ciselnych poli a textu 
//pri chybe vraci vektor s jednim null objektem nasledovany Integer objektem s chybovym kodem
//Datove typy jednotlivych polozek musi odpovidat datovym typum poli tridy
//Dotaz musi byt typu SELECT
  public static synchronized Vector getVDvojicCisloRetez(String dotaz) {
    v = new Vector();
    try {
      ResultSet q = PripojeniDB.dotazS(dotaz);
      q.last();
      if(q.getRow() > 0) {
       q.beforeFirst();
       while (q.next()) {
        dvojiceCR = new DvojiceCisloRetez(q.getInt(1), q.getString(2));
        v.addElement(dvojiceCR);
       }// konec while
      } // konec if
     } // konec try
    catch (Exception e) {
    int ch = PripojeniDB.vyjimkaS(e);
    if(ch == 0)
     ch = 10000;
    v.removeAllElements();
    v.add(null);
    v.add(new Integer(ch));
    } // konec catch
    
    return v;
  } //konec getVDvojicCisloRetez


  
 
//Obecny SELECT textove pole pomoci dotazu dotaz
 //prazdny retezec pri NULL nebo pri chybe
 //retezec pri uspechu (muze byt i prazdny, pokud je takto zadan)
 public static synchronized String selectStringPole(String dotaz) {
    String navrat = "";
    try {
      ResultSet q = PripojeniDB.dotazS(
      dotaz
      );
      q.last();
      if(q.getRow() == 1) {
      q.first();
      navrat = q.getString(1);
      if(q.wasNull()) {
      navrat = "";
      }
      } // konec if q.getRow() == 1
    } // konec try
    catch (Exception e) {
    PripojeniDB.vyjimkaS(e);
    } // konec catch

    return navrat;
  } //konec selectTXTPole
 
 
 
 //Obecny SELECT integer hodnoty z dotazu
 //navraci 0 pri NULL nebo pri chybe
 //cislo pri uspechu (muze byt i 0, pokud je takto zadano v tabulce)
 public static synchronized int selectINTPole(String dotaz) {
    int navrat = 0;
    try {
      ResultSet q = PripojeniDB.dotazS(
      dotaz
      );
      q.last();
      if(q.getRow() == 1) {
      q.first();
      navrat = q.getInt(1);
      if(q.wasNull()) {
      navrat = 0;
      }
      } // konec if q.getRow() == 1
    } // konec try
    catch (Exception ex) {
      PripojeniDB.vyjimkaS(ex);
    } // konec catch
    
    return navrat;
  } //konec selectINTPole

 
 
 //Obecny SELECT boolean hodnoty pomoci dotazu
 //navraci false pri NULL nebo pri chybe
 //hodnotu pri uspechu (muze byt i false, pokud je takto zadano v tabulce)
 public static synchronized boolean selectBooleanPole(String dotaz) {
    boolean navrat = false;
    try {
      ResultSet q = PripojeniDB.dotazS(
      dotaz 
      );
      q.last();
      if(q.getRow() == 1) {
      q.first();
      navrat = q.getBoolean(1);
      if(q.wasNull()) {
      navrat = false;
      }
      } // konec if q.getRow() == 1
    } // konec try
    catch (Exception ex) {
    PripojeniDB.vyjimkaS(ex);
    } // konec catch
    
    return navrat;
  } //konec selectBooleanPole


 
 
//Obecny SELECT pole datum z dotazu
 //prazdny retezec pri NULL nebo pri chybe
 //retezec pri uspechu (muze byt i prazdny, pokud je takto zadan)
 //datum bude zformatovano podle narodnich zvyklosti dane zeme
 public static synchronized String selectDatumPole(String dotaz) {
    //java.text.DateFormat df = java.text.DateFormat.getDateInstance();
    java.util.Date datum;
    String navrat = "";
    try {
      ResultSet q = PripojeniDB.dotazS(
      dotaz      
      );
      q.last();
      if(q.getRow() == 1) {
      q.first();
      datum = q.getDate(1);
      if(q.wasNull()) {
      navrat = "";
      }
      else {
      navrat = df.format(datum);
      }
      } // konec if q.getRow() == 1
    } // konec try
    catch (Exception e) {
    PripojeniDB.vyjimkaS(e);
    } // konec catch
    
    return navrat;
  } //konec selectDatumPole


 //Obecny SELECT textovych poli pole z dotazu
 //vektor retezcu pri uspechu (retezec ve vektoru muze byt i prazdny, pokud je takto zadan nebo je retezec null)
 public static synchronized Vector selectVStringPole(String dotaz) {
    v = new Vector();
    String s;
    int pocetSloupcu;
    try {
      ResultSet q = PripojeniDB.dotazS(
      dotaz
      );
      q.last();
      pocetSloupcu = q.getMetaData().getColumnCount();
      if(q.getRow() == 1) {
      q.first();
      for(int i = 1; i <= pocetSloupcu; i++) {
      s = q.getString(i);
      if(q.wasNull())
      s = "";
      v.add(s);
      }//konec for
      } // konec if q.getRow() == 1
    } // konec try
    catch (Exception e) {
    PripojeniDB.vyjimkaS(e);
    v.removeAllElements();
    } // konec catch
    
    return v;
  } //konec selectVTXTPole


 
 
 
 //Obecny SELECT vektoru objektu z dotazu
 //vektor objektu pri uspechu, prazdny vektor pri chybe
 public static synchronized Vector selectVektor(String dotaz) {
    v = new Vector();
    int pocetSloupcu;
    try {
      ResultSet q = PripojeniDB.dotazS(
      dotaz
      );
      q.last();
      pocetSloupcu = q.getMetaData().getColumnCount();
      if(q.getRow() == 1) {
      q.first();
      for(int i = 1; i <= pocetSloupcu; i++) {
      v.add(q.getObject(i));
      }//konec for
      } // konec if q.getRow() == 1
    } // konec try
    catch (Exception e) {   
    PripojeniDB.vyjimkaS(e);
    v.removeAllElements();
    } // konec catch
    
    return v;
  } //konec selectVPole


 //Obecny SELECT vektoru vektoru z dotazu
 //vektor vektoru objektu pri uspechu
 public static synchronized Vector selectVektorVektoru(String dotaz) {
    Vector navrat = new Vector();
    int pocetSloupcu;
    try {
      ResultSet q = PripojeniDB.dotazS(
      dotaz
      );
      pocetSloupcu = q.getMetaData().getColumnCount();
      q.last();
      if(q.getRow() > 0) {
      q.beforeFirst();
      while(q.next()) {
      v = new Vector();      
      for(int i = 1; i <= pocetSloupcu; i++)
       v.add(q.getObject(i));
      navrat.add(v);
      }//konec while
      } // konec if q.getRow() > 0
    } // konec try
    catch (Exception e) {   
    PripojeniDB.vyjimkaS(e);
    navrat.removeAllElements();
    } // konec catch
    
    return navrat;
  } //konec selectVektorVektoru
 
 
 
 
 //vrati timestamp ze serveru v java.sql.Timestamp, pokud chyba nebo NULL, tak navrat 0
 public static synchronized java.sql.Timestamp getTimestamp(String dotaz) {
    java.sql.Timestamp navrat = new java.sql.Timestamp(0);
    try {
      ResultSet q = PripojeniDB.dotazS(
      dotaz
      );
      q.last();
      if(q.getRow() == 1)
      {
      q.first();
      navrat = q.getTimestamp(1);
      if(q.wasNull())
      {
      navrat = new java.sql.Timestamp(0);
      }//konec if q.wasNull
      } // konec if q.getRow() == 1
    } // konec try
    catch (Exception e) {
    PripojeniDB.vyjimkaS(e);
    } // konec catch
    return navrat;
  } //konec getTimestamp
 
 
 
 
 //vrati soucasny timestamp transakce ze serveru v textovem retezci
 public static synchronized String getCurrentTimestamp() {
    String navrat = "";
    try {
      ResultSet q = PripojeniDB.dotazS(
      "SELECT to_char(current_timestamp,'DD.MM.YYYY HH24:MI:SS')"
      );
      q.last();
      if(q.getRow() == 1) {
      q.first();
      navrat = q.getString(1);
      if(q.wasNull()) {
      navrat = "";
      }//konec if q.wasNull
      } // konec if q.getRow() == 1
    } // konec try
    catch (Exception e) {
    PripojeniDB.vyjimkaS(e);
    } // konec catch
    
    return navrat;
  } //konec getCurrentTimestamp
 

 //vrati datum ze serveru v textovem formatu
 public static synchronized String getCurrentDate() {
    java.util.Date datum = null;
    String navrat = "";
    try {
      ResultSet q = PripojeniDB.dotazS(
      "SELECT current_date"
      );
      q.last();
      if(q.getRow() == 1)
      {
      q.first();
      datum = q.getDate(1);
      if(!q.wasNull()) {
      navrat = df.format(datum);
      }//konec if q.wasNull
      } // konec if q.getRow() == 1
    } // konec try
    catch (Exception e)  {
    PripojeniDB.vyjimkaS(e);
    } // konec catch
    return navrat;
  } //konec getCurrentDate


 //vrati jmeno aktualne pripojeneho uzivatele ze serveru
 public static synchronized String getCurrentUser() {
    String navrat = "";
    try {
      ResultSet q = PripojeniDB.dotazS(
      "SELECT session_user"
      );
      q.last();
      if(q.getRow() == 1) {
      q.first();
      navrat = q.getString(1);
      if(q.wasNull())
         navrat = "";
      } // konec if q.getRow() == 1
    } // konec try
    catch (Exception e) {
    PripojeniDB.vyjimkaS(e);
    } // konec catch
    return navrat;
  } //konec getCurrentUser

 
//vloz hodnoty z INSERT DOTAZU
 //0 pri uspechu
 //a jinak chybovy kod
 public static synchronized int insertI(String dotaz)
  {
    try {
      PripojeniDB.dotazIUD(
      dotaz
      );
    } // konec try
    catch (Exception e) {
     int vu = PripojeniDB.vyjimkaIUD(e);
     if (vu == 0)
     return 10000;
    else
     return vu;
    } // konec catch
    return 0;
  } //konec insertI

 
 //vloz hodnoty z dotazu dotaz
 //vraci dvojiciCisloCislo, kde v prvnim poli je hodnota RETURNING id a ve druhem chybovy kod
 //chybovy kod je 10000 pri fatalni chybe, 0 pri uspechu
 public static synchronized DvojiceCisloCislo insert2CC(String dotaz)
  {
    dvojiceCC = new DvojiceCisloCislo(0, 0);
    try {
      ResultSet q = PripojeniDB.dotazS(
      dotaz
      );
      q.next();
      dvojiceCC.setC1(q.getInt(1));
    } // konec try
    catch (Exception e) {
    int vs = PripojeniDB.vyjimkaS(e);
    if (vs == 0)
    dvojiceCC.setC2(10000);
    else
    dvojiceCC.setC2(vs);
    } // konec catch
    return dvojiceCC;
  } //konec insert2CC


 //vloz hodnoty z INSERT dotazu dotaz
 //vraci trojiciCisloCislo, kde v prvnich dvou polich je hodnota RETURNING id a ve tretim chybovy kod
 //chybovy kod je 10000 pri fatalni chybe, 0 pri uspechu
 public static synchronized TrojiceCisloCisloCislo insert3CCC(String dotaz) {
    trojiceCCC = new TrojiceCisloCisloCislo(0, 0, 0);
    try
    {
      ResultSet q = PripojeniDB.dotazS(
      dotaz
      );
      q.next();
      trojiceCCC.setC1(q.getInt(1));
      trojiceCCC.setC2(q.getInt(2));
    } // konec try
    catch (Exception e) {
    int vs = PripojeniDB.vyjimkaS(e);
    if (vs == 0)
    trojiceCCC.setC3(10000);
    else
    trojiceCCC.setC3(vs);
    } // konec catch
    return trojiceCCC;
  } //konec insert3CCC
 
 
 //vloz hodnoty z INSERT dotazu dotaz
 //vraci trojiciCisloCislo, kde v prvnich trech polich je hodnota RETURNING id a ve ctvrtem chybovy kod
 //chybovy kod je 10000 pri fatalni chybe, 0 pri uspechu
 public static synchronized CtvericeCisloCisloCisloCislo insert4CCCC(String dotaz) {
    ctvericeCCCC = new CtvericeCisloCisloCisloCislo(0, 0, 0, 0);
    try
    {
      ResultSet q = PripojeniDB.dotazS(
      dotaz
      );
      q.next();
      ctvericeCCCC.setC1(q.getInt(1));
      ctvericeCCCC.setC2(q.getInt(2));
      ctvericeCCCC.setC3(q.getInt(3));
    } // konec try
    catch (Exception e) {
    int vs = PripojeniDB.vyjimkaS(e);
    if (vs == 0)
     ctvericeCCCC.setC4(10000);
    else
     ctvericeCCCC.setC4(vs);
    } // konec catch
    return ctvericeCCCC;
  } //konec insert4CCCC
 
 
 
//update tabulku tabulka s vyrazem vyraz za podminky podminka
 //vraci 10000 pri fatalni chybe,
 //0 pri uspechu
 //a jinak chybovy kod
 public static synchronized int update(String dotaz) {
    try {
      PripojeniDB.dotazIUD (
      dotaz      
      );
    } // konec try
    catch (Exception e) {
    int vu = PripojeniDB.vyjimkaIUD(e);
    if (vu == 0)
    return 10000;
    else
    return vu;
    } // konec catch
    return 0;
  } //konec updateHodnoty

  
//obecny delete v tabulce
 //vraci 10000 pri fatalni chybe,
 //0 pri uspechu
 //a jinak chybovy kod
  public static synchronized int delete(String dotaz) {
    try {
      PripojeniDB.dotazIUD(
      dotaz
      );
    } // konec try
    catch (Exception e) {
    int vu = PripojeniDB.vyjimkaIUD(e);
    if (vu == 0)
    return 10000;
    else
    return vu;
    } // konec catch
    return 0;
  } //konec delete

 
//vraci pocet z tabulky tabulka
//s podminkou podminka
 public static synchronized int getPocet(String tabulka, String podminka) {
    int pocet = 0;
    try {
      ResultSet q = PripojeniDB.dotazS(
      "SELECT count(*) FROM " + tabulka +
      " " + podminka
      );
      q.first();
      pocet = q.getInt(1);
    } // konec try
    catch (Exception e) {
    PripojeniDB.vyjimkaS(e);
    } // konec catch
    return pocet;
  } //konec getPocet


 
//vraci pocet podle dotazu
 public static synchronized int getPocet(String dotaz) {
    int pocet = 0;
    try {
      ResultSet q = PripojeniDB.dotazS(
      dotaz
      );
      q.first();
      pocet = q.getInt(1);
    } // konec try
    catch (Exception e) {
    PripojeniDB.vyjimkaS(e);
    } // konec catch
    return pocet;
  } //konec getPocet

 
 
//vraci boolean hodnotu indikujici autorizaci pro typ nad danou tabulkou
 public static synchronized boolean overAutorizaci(String tabulka, String typ) {
    boolean navrat = false;
    try {
      ResultSet q = PripojeniDB.dotazS(
      "SELECT has_table_privilege ('" + tabulka + "', '" + typ + "')"
      );
      q.first();
      navrat = q.getBoolean(1);
    } // konec try
    catch (Exception e) {
    PripojeniDB.vyjimkaS(e);
    } // konec catch
    return navrat;
  } //konec overAutorizaci

 
 
 //Metoda vracejici chybovou hlasku
  public static synchronized String getCHybovouHlasku (int chybaID)
  {
    String vysledek = "Došlo k nespecifikované chybě při dotazu na databázový server!";
    try {
      ResultSet q = PripojeniDB.dotazS(
      "SELECT chybove_kody_nazev, chybove_kody_popis, chybove_kody_mozna_reseni" +
      " FROM chybove_kody" + 
      " WHERE chybove_kody_id = " + chybaID
      );
      q.last();
      if(q.getRow() == 1) {
       q.first();
        String nazev = q.getString(1);
        String popis = q.getString(2);
        String moznaReseni = q.getString(3);
        vysledek = nazev + "\n" + popis + "\n" + moznaReseni;
      } // konec if q.getRow() == 1
     } // konec try
    catch (Exception e) {
    PripojeniDB.vyjimkaS(e);
    } // konec catch
    return (vysledek);
  } //konec getChybovouHlasku




//vola ulozenou proceduru navracejici boolean
  public static synchronized boolean overPole(String funkce, String pole)
  {
    boolean proslo = false;
    try
    {
      ResultSet q = PripojeniDB.dotazS(
      "SELECT " + funkce + "('" + pole + "')"
      );
      q.first();
      proslo = q.getBoolean(1);
     } // konec try
    catch (Exception e)
    {
    PripojeniDB.vyjimkaS(e);
    } // konec catch
    finally
    {
    return proslo;
    } // konec finally
  } //konec overPole


  
//testuje, zda - li je mozne nastavit pole na urcitou hodnotu
//vraci true pri uspechu
//false pri chybe
  public static synchronized boolean testPole(String pole, String hodnota)
  {
  //System.out.println("dotaz je = " + "UPDATE testiky " +
   //   "SET " + pole.trim() + " = " + hodnota.trim() +
   //   " WHERE t_id = 1");
    boolean retc = false;
    int r = 0;
    try
    {
      r = PripojeniDB.dotazIUD(
      "UPDATE public.test_tabulka " +
      "SET " + pole.trim() + " = " + hodnota.trim() +
      " WHERE test_id = 1"
      );
    if (r == 1)
     retc = true;
    } // konec try
    catch (Exception ex) {
     //ex.printStackTrace();
     return retc;
    } // konec catch
     return retc;
  } //konec testPole

  

  
//vraci znacku pomoci funkce funkce a identifikatoru id
 public static synchronized Znacka1 getZnacku(String funkce, int id)
  {
    zn = new Znacka1("neznámý nebo prázdný", "neznámý nebo prázdný", "neznámý nebo prázdný", "neznámý nebo prázdný");
    try
    {
      ResultSet q = PripojeniDB.dotazS(
      "SELECT * FROM " + funkce + "(" + id + ")"
      );
      q.last();
      if(q.getRow() == 1)
      {
       q.first();
        String kv = q.getString("kv");
        String kz = q.getString("kz");
        String kdv = q.getString("kdv");
        String kdz = q.getString("kdz");
        zn = new Znacka1(kv, kz, kdv, kdz);
      } // konec if
     } // konec try
    catch (Exception e)
    {
    PripojeniDB.vyjimkaS(e);
    } // konec catch
    finally
    {
    return zn;
    } // konec finally
  } //konec getZnacku

 
 //Metoda pro aktualizaci hesla uzivatele uzivatel
 //vraci 0 pri uspechu, jinak chybovy kod
  public static synchronized int updateHesloUzivatele(String uzivatel, String heslo)
  {
    int navrat = 0;
    
    if(uzivatel.equalsIgnoreCase(""))
     return 10000;
    
    try
    {
      boolean b = PripojeniDB.dotazObecny(
      "ALTER ROLE " + uzivatel +
      " WITH ENCRYPTED PASSWORD " + TextFunkce1.osetriZapisTextDB1(heslo)
      );
    } // konec try
    catch (Exception e)
    {
    navrat = PripojeniDB.vyjimkaIUD(e);
    if (navrat == 0)
     navrat = 10000;
    } // konec catch
    finally
    {
    return navrat;
    } // konec finally
  } //konec updateHesloUzivatele

  
 //Metoda pro aktualizaci hesla aktualne prihlaseneho uzivatele
 //vraci 0 pri uspechu, jinak chybovy kod
  public static synchronized int updateHesloAktualnihoUzivatele(String heslo)
  {
    return updateHesloUzivatele(getCurrentUser(), heslo);
  } //konec updateHesloAktualnihoUzivatele

  

//Metoda spoustejici prikaz - vraci 0 pri uspechu, jinak chybovy kod
  public static synchronized int spustPrikaz(String prikaz)
  {
    int navrat = 10000;
    try
    {
      boolean b = PripojeniDB.dotazObecny(
      prikaz
      );
      navrat = 0;
    } // konec try
    catch (Exception e) {
    navrat = PripojeniDB.vyjimkaS(e);
    if(navrat == 0)
     navrat = 10000;
    } // konec catch
    return navrat;
  } //konec spustPrikaz

  
}