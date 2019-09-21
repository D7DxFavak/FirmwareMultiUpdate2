package eu.data7.tridy;

public class DvojiceCisloRetez {
    
     private String retez;
     private int cislo;
    
    public DvojiceCisloRetez() {
     this.cislo = 0;
     this.retez = "";
    }

    public DvojiceCisloRetez(int cislo, String retez) {
     this.cislo = cislo;
     this.retez = retez;
    }
    
    @Override
    public String toString() {
     return retez;
    }
    
    public int cislo() {
     return cislo;
    }

    /**
     * @param retez the retez to set
     */
    public void setRetez(String retez) {
        this.retez = retez;
    }

    /**
     * @param cislo the cislo to set
     */
    public void setCislo(int cislo) {
        this.cislo = cislo;
    }

} 