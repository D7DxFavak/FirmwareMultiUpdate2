package eu.data7.tridy;

public class DvojiceCisloBool implements NticeRetezBoolean {
    private int c;
    private boolean b;
    
    public DvojiceCisloBool(int c, boolean b) {
        this.c = c;
        this.b = b;
    }
    
    public String toString() {
        return "" + b + "";
    }
    
    public boolean getBoolean() {
        return b;
    }
    
} 