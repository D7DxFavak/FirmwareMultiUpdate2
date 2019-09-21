/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.data7.tableTools;

import java.text.Format;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author 7Data Gotzy
 */
public class FormatRenderer extends DefaultTableCellRenderer {
    private Format formatter;

    public FormatRenderer(Format formatter) {
        if (formatter==null)
            throw new NullPointerException();
        this.formatter = formatter;
    }

    protected void setValue(Object obj) {
        setText(obj==null? "" : formatter.format(obj));
    }
}
