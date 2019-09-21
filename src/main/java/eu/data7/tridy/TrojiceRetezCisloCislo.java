package eu.data7.tridy;

public class TrojiceRetezCisloCislo
{
    private String retez;
    private int c1;
    private int c2;
    
    public TrojiceRetezCisloCislo(String retez, int c1, int c2)
    {
    this.retez = retez;
    this.c1 = c1;
    this.c2 = c2;
    }

    public String toString() {
        return getRetez();
    }

    /**
     * @return the retez
     */
    public String getRetez() {
        return retez;
    }

    /**
     * @param retez the retez to set
     */
    public void setRetez(String retez) {
        this.retez = retez;
    }

    /**
     * @return the c1
     */
    public int getC1() {
        return c1;
    }

    /**
     * @param c1 the c1 to set
     */
    public void setC1(int c1) {
        this.c1 = c1;
    }

    /**
     * @return the c2
     */
    public int getC2() {
        return c2;
    }

    /**
     * @param c2 the c2 to set
     */
    public void setC2(int c2) {
        this.c2 = c2;
    }


} 