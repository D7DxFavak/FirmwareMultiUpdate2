package eu.data7.tridy;

//Univerzalni roletka ze vstupniho vektoru dat
import java.util.Vector;
import javax.swing.*;

public class RoletkaUniverzalVector1 extends JComboBox {
//private Vector v;

    //konstruktor1
    public RoletkaUniverzalVector1(Vector v) {
        super();
        setData(v);
    }//konec konstruktoru1

    //konstruktor2 - sestroji prazdny JComboBox
    public RoletkaUniverzalVector1() {
        super();
    }//konec konstruktoru2

    //nastavit data do roletky normalne, bez vyberu polozky
    //Vektor musi obsahovat objekty typu DvojiceCisloRetez
    public boolean setData(Vector v) {
        if (v.isEmpty() == true) {
            return false;
        } else //else hlavni pro test isEmpty
        {
            for (int i = 0; i < v.size(); i++) {
                this.addItem(v.get(i));
            }// konec for
        }//konec else
        return true;
    }//konec setData

    //nastavit data a nastavit vyber na cislo id, pokud je id neexistuje, vynechej nastaveni vyberu
    public boolean setDataId(Vector v, int id) {
        if (v.isEmpty() == true) {
            return false;
        } else //else hlavni pro test isEmpty
        {
            int i = 0;
            for (i = 0; i < v.size(); i++) {
                this.addItem(v.get(i));
            }// konec for
            //vyber polozky
            for (i = 0; i < this.getItemCount(); i++) {
                if (((DvojiceCisloRetez) this.getItemAt(i)).cislo() == id) {
                    this.setSelectedIndex(i);
                    break;
                }//konec if
            }//konec for pro vyber polozky
        }//konec else
        return true;
    }//konec setDataId

    //nastavit data a nastavit vyber na index index, pokud je index mimo rozsah, ponechej vyber polozky
    public boolean setDataIndex(Vector v, int index) {
        if (v.isEmpty() == true) {
            return false;
        } else //else hlavni pro test isEmpty
        {
            int i = 0;
            for (i = 0; i < v.size(); i++) {
                this.addItem(v.get(i));
            }// konec for
            //vyber indexu
            if (this.getItemCount() > index & index >= 0) {
                this.setSelectedIndex(index);
            }
//       else
            //      this.setSelectedIndex(0);
        }//konec else
        return true;
    }//konec setDataIndex

    //nastavit vyber na cislo id bez nacteni dat, pokud je id neexistuje, vynechej nastaveni vyberu
    public void setDataOId(int id) {
        int i = 0;
        //vyber polozky
        for (i = 0; i < this.getItemCount(); i++) {
            if (((DvojiceCisloRetez) this.getItemAt(i)).cislo() == id) {
                this.setSelectedIndex(i);
                break;
            }//konec if
        }//konec for pro vyber polozky
    }//konec setDataOId

    //nastavit vyber na index index bez nacitani dat, pokud je index mimo rozsah, ponechej vyber polozky
    public void setDataOIndex(int index) {
        int i = 0;
        //vyber indexu
        if (this.getItemCount() > index & index >= 0) {
            this.setSelectedIndex(index);
        }
//       else
        //      this.setSelectedIndex(0);
    }//konec setDataOIndex
}