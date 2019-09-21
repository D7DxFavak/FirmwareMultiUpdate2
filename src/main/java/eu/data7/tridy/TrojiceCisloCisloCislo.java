package eu.data7.tridy;

public class TrojiceCisloCisloCislo
{
    private int c1;
    private int c2;
    private int c3;
    
    public TrojiceCisloCisloCislo(int c1, int c2, int c3)
    {
    this.c1 = c1;
    this.c2 = c2;
    this.c3 = c3;
    }
    
    public String toString() {
        return "" + getC3() + "";
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

    /**
     * @return the c3
     */
    public int getC3() {
        return c3;
    }

    /**
     * @param c3 the c3 to set
     */
    public void setC3(int c3) {
        this.c3 = c3;
    }
    
} 