package eu.data7.tridy;

public class DvojiceRetezBool implements NticeRetezBoolean {
    private String retez;
    private boolean b;
    //protected String retez;
    public DvojiceRetezBool(String retez, boolean bool) {
        this.retez = retez;
        this.b = bool;
    }
    
    public String toString() {
        return retez;
    }
    
    public boolean getBoolean() {
        return b;
    }
    
} 