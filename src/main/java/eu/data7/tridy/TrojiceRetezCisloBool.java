package eu.data7.tridy;

public class TrojiceRetezCisloBool implements NticeRetezBoolean {
    private String r;
    private int c;
    private boolean b;
    
    
    public TrojiceRetezCisloBool(String r, int c, boolean b) {
        this.r = r;
        this.c = c;
        this.b = b;
    }
    
    public String toString() {
        return getR();
    }
    
    public boolean getBoolean() {
        return isB();
    }

    /**
     * @return the r
     */
    public String getR() {
        return r;
    }

    /**
     * @param r the r to set
     */
    public void setR(String r) {
        this.r = r;
    }

    /**
     * @return the c
     */
    public int getC() {
        return c;
    }

    /**
     * @param c the c to set
     */
    public void setC(int c) {
        this.c = c;
    }

    /**
     * @return the b
     */
    public boolean isB() {
        return b;
    }

    /**
     * @param b the b to set
     */
    public void setB(boolean b) {
        this.b = b;
    }
    
} 