package eu.data7.dbfunkce;

import java.util.Date;
import java.util.regex.*;
import javax.swing.JTextField;

public class TextFunkce1 { //Zacatek tridy

    private static String REGV;
    private static String VSTUP;
    private static Pattern vzor;
    private static Matcher srovnavac;
    private static java.text.DateFormat df = java.text.DateFormat.getDateInstance();

    public static java.sql.Date konverzeUtilDate2SQLDate(java.util.Date datum) {
        long cas = datum.getTime();
        return (new java.sql.Date(cas));
    }

    public static java.sql.Timestamp konverzeUtilDate2SQLTimestamp(java.util.Date datum) {
        long cas = datum.getTime();
        return (new java.sql.Timestamp(cas));
    }

    public static boolean testRegEx(String text, String textVzor) {
        REGV = textVzor;
        VSTUP = text;
        vzor = Pattern.compile(REGV);
        srovnavac = vzor.matcher(VSTUP);
        while (srovnavac.find()) {
            return true;
        }//konec while
        return false;
    }//konec testRegEx

    public static boolean osetriDatum(String datum) {
        //  System.out.println("datum " + datum+ " x " + datum.length());
        int index1 = 0, index2 = 0;
        datum = datum.trim().replaceAll(" ", "").replaceAll(",", ".").replaceAll("'", "").replaceAll("/", ".");

        /*index1 = datum.indexOf(".");
        index2 = datum.indexOf(".", index1 + 1);

        if (datum.lastIndexOf(".") != index2 || datum.substring(index2 + 1).length() != 4) {
            return false;
        }
        // System.out.println("index1 = " + index1 + ", index2 = " + index2 + ", lastIndexOf . = " + datum.lastIndexOf(".") + ", lastIndexOf , = " + datum.lastIndexOf(",") + ", substring = " + datum.substring(index2 + 1));
        if (index1 < 1 || index2 < 1) {
            return false;
        }
*/
        if (datum.length() > 7 && datum.length() < 11) {
            return true;
            //return SQLFunkceObecne2.testPole("test_datum1", osetriZapisTextDB1(datum));
        } else {
            return false;
        }
    }//konec osetriDatum

    public static boolean osetriDatumCas(String datumCas) {
        int index1 = 0, index2 = 0;
        datumCas = datumCas.trim().replaceAll(",", ".").replaceAll("'", "");
//    System.out.println("index1 = " + index1 + ", index2 = " + index2 + ", lastIndexOf . = " + datum.lastIndexOf(".") + ", lastIndexOf , = " + datum.lastIndexOf(",") + ", substring = " + datum.substring(index2 + 1));
        if (datumCas.length() > 7 && datumCas.length() < 25) {
            return SQLFunkceObecne2.testPole("test_datumcas1", osetriZapisTextDB1(datumCas));
        } else {
            return false;
        }
    }//konec osetriDatumCas

    //ziskej int hodnotu z textoveho pole JTextField
    //vraci 0 pri chybe nebo hodnote v poli = 0
    //jinak int hodnotu
    public static int getIntJTextField(JTextField pole) {
        int navrat = 0;
        try {
            navrat = Integer.valueOf(pole.getText().trim().replaceAll(" ", "")).intValue();
        } catch (Exception e) {
            navrat = 0;
        }
        return navrat;
    }//konec getIntJTextField

    //vraci null pro vstup INTEGER = "", jinak retezec s hodnotou
    public static synchronized String osetriZapisIntDB1(String vstup) {
        String vystup;
        if (vstup == "") {
            vystup = null;
            return vystup;
        }
        return (vstup.replaceAll(" ", ""));
    }//konec osetriZapisIntKeyDB1

    //vraci null pro vstup NUMERIC = "", jinak retezec s hodnotou
    public static synchronized String osetriZapisNumericDB1(String vstup) {
        String vystup;       
        if (vstup == "" || vstup.isEmpty()) {
            vystup = null;
           
            return vystup;
        }
        if (vstup == null) {
            vystup = null;
          
            return vystup;
        }       
        return (vstup.replaceAll(" ", "").replaceAll(",", "."));
    }//konec osetriZapisIntKeyDB1

    //vraci null pro vstup int = 0, jinak retezec s hodnotou
    public static synchronized String osetriZapisIntKeyDB1(int vstup) {
        String vystup;
        if (vstup == 0) {
            vystup = null;
            return vystup;
        }
        return (vstup + "");
    }//konec osetriZapisIntKeyDB1

    public static synchronized String osetriZapisLongKeyDB1(long vstup) {
        String vystup;
        if (vstup == 0) {
            vystup = null;
            return vystup;
        }
        return (vstup + "");
    }//konec osetriZapisIntKeyDB1

    //osetreni cteni cisla, pokud nacte null, tak vrati 0
    public static synchronized int osetriCteniIntKeyDB1(int nacteno) {
        Integer i = new Integer(nacteno);
        if (i == null) {
            return 0;
        } else {
            return nacteno;
        }
    }//konec osetriCteniIntKeyDB1

    //osetreni textu pro vstup do DB (escape sekvence, oriznuti bilych znaku apod)
    //pokud je prazdny, tak je navrat retezec null bez apostrofu,
    //jinak vraci retezec v apostrofech, pripadne s escape rozlisenim
    public static String osetriZapisTextDB1(String text) {
        if (text == null || text.equalsIgnoreCase("")) {
            return null;
        }
        if (text.indexOf("\\") > -1) {
            return "E'" + text.trim().replaceAll("\\\\", "\\\\\\\\").replaceAll("'", "''") + "'";
        }
        return "'" + text.trim().replaceAll("'", "''") + "'";
    }

    public static String osetriZapisDatumDB1(Date datum) {
        if (datum == null) {
            return null;
        } else {
            try {
                return "'" + df.format(datum).trim().replaceAll("'", "''") + "'";
            } catch (Exception e) {
                return null;
            }
        }
    }

    //osetreni textu pro vystup z DB (reverse escape sekvence, oriznuti bilych znaku apod)
    public static String osetriCteniTextDB1(String text) {
        if (text == null) {
            return "";
        }
        //System.out.println("text je: " + text);
        //return text.trim().replaceAll("''", "'").replaceAll("\\\\\\\\ ", "\\\\");
        return text.trim();
    }
}
