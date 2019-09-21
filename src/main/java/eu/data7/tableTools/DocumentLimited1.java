package eu.data7.tableTools;

import java.awt.Toolkit;
import javax.swing.text.*;

//Document pro textove pole s max poctem znaku a bez tabulatoru
public class DocumentLimited1 extends PlainDocument
{

  protected int maxZnaku = 0;

  protected static Toolkit toolkit = Toolkit.getDefaultToolkit();

  public DocumentLimited1()
  {
  super();
  }

  public DocumentLimited1(int maxZn)
  {
  super();
  maxZnaku = maxZn;
  }

  public int getMaxZnaku()
  {
  return maxZnaku;
  }

  public void setMaxZnaku(int mz)
  {
  maxZnaku = mz;
  }

  public void insertString(int offset, String text, AttributeSet attributes) throws BadLocationException
  {
  //  System.out.println("delka je " + this.getLength() + ", delkatextu je " + text.length() + ", maxZnaku je " + maxZnaku);
    if(text != null)
    {
    int count = text.length();
    for (int i = 0; i < count; i++)
    {
      if ((Character.toString(text.charAt(i))).equals("\u0009"))
      {
        toolkit.beep();
        return;
      }
    }//konec for
    
    if (((this.getLength() + text.length()) > maxZnaku))
    {
      toolkit.beep();
      return;
    }
    super.insertString(offset, text, attributes);
    }//konec if text != null
  }

}//konec DocumentLimited1
