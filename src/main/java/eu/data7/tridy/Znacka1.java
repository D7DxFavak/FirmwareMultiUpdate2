package eu.data7.tridy;

public class Znacka1
{
    private String kdoVlozil;
    private String kdoZmenil;
    private String kdyVlozeno;
    private String kdyZmeneno;
    
    public Znacka1(String kdoVlozil, String kdoZmenil, String kdyVlozeno, String kdyZmeneno)
    {
    this.kdoVlozil = kdoVlozil;
    this.kdoZmenil = kdoZmenil;
    this.kdyVlozeno = kdyVlozeno;
    this.kdyZmeneno = kdyZmeneno;
    }
    
    public String kdoVlozil()
    {
    return kdoVlozil;
    }
    public String kdoZmenil()
    {
    return kdoZmenil;
    }
    public String kdyVlozeno()
    {
    return kdyVlozeno;
    }
    public String kdyZmeneno()
    {
    return kdyZmeneno;
    }
    
} 